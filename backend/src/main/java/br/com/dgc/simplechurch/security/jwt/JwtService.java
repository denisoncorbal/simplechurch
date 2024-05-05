package br.com.dgc.simplechurch.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final String ISSUER = "SimpleChurchBackend";
    private final String AUDIENCE = "SimpleChurchFrontend";
    private final int ACCESS_EXPIRATION_TIME_IN_MINUTES = 10;
    private final int ACCESS_NOT_BEFORE_MINUTES = 2;
    private final int ACCESS_SECONDS_ALLOWED_VARIATION = 30;
    private final int REFRESH_EXPIRATION_TIME_IN_MINUTES = 10;
    private final int REFRESH_NOT_BEFORE_MINUTES = 2;
    private final int REFRESH_SECONDS_ALLOWED_VARIATION = 30;

    private record JwtType(
            int expirationTimeInMinutes,
            int notBeforeInMinutes,
            int secondsAllowedVariation) {
    }

    public final JwtType ACCESS_TOKEN = new JwtType(ACCESS_EXPIRATION_TIME_IN_MINUTES, ACCESS_NOT_BEFORE_MINUTES,
            ACCESS_SECONDS_ALLOWED_VARIATION);
    public final JwtType REFRESH_TOKEN = new JwtType(REFRESH_EXPIRATION_TIME_IN_MINUTES, REFRESH_NOT_BEFORE_MINUTES,
            REFRESH_SECONDS_ALLOWED_VARIATION);

    private final RsaJsonWebKey RSA_JSON_WEB_KEY = getSignInKey();
    private final String ALGORITHM = AlgorithmIdentifiers.RSA_USING_SHA256;

    private RsaJsonWebKey getSignInKey() {
        try {
            return RsaJwkGenerator.generateJwk(2048);
        } catch (JoseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String extractUsername(String token, JwtType type) {
        return extractClaim(token, func -> {
            try {
                return func.getSubject();
            } catch (MalformedClaimException e) {
                e.printStackTrace();
                return null;
            }
        }, type);
    }

    public <T> T extractClaim(String token, Function<JwtClaims, T> claimsResolver, JwtType type) {
        final JwtClaims claims = extractAllClaims(token, type);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, JwtType type) {
        return generateToken(new HashMap<>(), userDetails, type);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, JwtType type) {
        return buildToken(extraClaims, userDetails, type);
    }

    /*
     * public long getExpirationTime() {
     * return EXPIRATION_TIME_IN_MINUTES;
     * }
     */

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            JwtType type) {
        // Generate an RSA key pair, which will be used for signing and verification of
        // the JWT, wrapped in a JWK

        // Give the JWK a Key ID (kid), which is just the polite thing to do
        RSA_JSON_WEB_KEY.setKeyId("k1");

        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(ISSUER); // who creates the token and signs it
        claims.setAudience(AUDIENCE); // to whom the token is intended to be sent
        claims.setExpirationTimeMinutesInTheFuture(type.expirationTimeInMinutes); // time when the token will expire (10
        // minutes from now)
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow(); // when the token was issued/created (now)
        claims.setNotBeforeMinutesInThePast(type.notBeforeInMinutes); // time before which the token is not yet valid (2
        // minutes ago)
        claims.setSubject(userDetails.getUsername()); // the subject/principal is whom the token is about
        claims.setStringListClaim("roles",
                userDetails.getAuthorities().stream().map((authority) -> authority.getAuthority()).toList()); // multi-valued

        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(claims.toJson());

        // The JWT is signed using the private key
        jws.setKey(RSA_JSON_WEB_KEY.getPrivateKey());

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process
        jws.setKeyIdHeaderValue(RSA_JSON_WEB_KEY.getKeyId());

        // Set the signature algorithm on the JWT/JWS that will integrity protect the
        // claims
        jws.setAlgorithmHeaderValue(ALGORITHM);

        // Sign the JWS and produce the compact serialization or the complete JWT/JWS
        // representation, which is a string consisting of three dot ('.') separated
        // base64url-encoded parts in the form Header.Payload.Signature
        // If you wanted to encrypt it, you can simply set this jwt as the payload
        // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
        String jwt;
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return jwt;
    }

    public boolean isTokenValid(String token, UserDetails userDetails, JwtType type) {
        final String username = extractUsername(token, type);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token, type);
    }

    private boolean isTokenExpired(String token, JwtType type) {
        return extractExpiration(token, type).before(new Date());
    }

    private Date extractExpiration(String token, JwtType type) {
        return new Date(extractClaim(token, func -> {
            try {
                return func.getExpirationTime();
            } catch (MalformedClaimException e) {
                e.printStackTrace();
                return null;
            }
        }, type).getValueInMillis());
    }

    private JwtClaims extractAllClaims(String token, JwtType type) {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setAllowedClockSkewInSeconds(type.secondsAllowedVariation) // allow some leeway in validating time
                                                                            // based
                                                                            // claims to account for
                // clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer(ISSUER) // whom the JWT needs to have been issued by
                .setExpectedAudience(AUDIENCE) // to whom the JWT is intended for
                .setVerificationKey(RSA_JSON_WEB_KEY.getKey()) // verify the signature with the public key
                .setJwsAlgorithmConstraints( // only allow the expected signature algorithm(s) in the given context
                        ConstraintType.PERMIT, ALGORITHM) // which is only RS256 here
                .build(); // create the JwtConsumer instance

        try {
            // Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
            return jwtClaims;
        } catch (InvalidJwtException e) {
            e.printStackTrace();
            return null;
        }
    }
}
