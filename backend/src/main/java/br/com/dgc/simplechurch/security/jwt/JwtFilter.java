package br.com.dgc.simplechurch.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    JwtService jwtService;
    UserDetailsService userDetailsService;
    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        logger.info("Checking presence of Authorization Header");
        String authorization = request.getHeader("Authorization");
        if (authorization != null && !authorization.isBlank() && authorization.startsWith("Bearer")) {
            logger.info("Extracting username from token");
            String token = authorization.substring(7);
            final String userEmail = jwtService.extractUsername(token, jwtService.ACCESS_TOKEN);
            logger.info("Username: " + userEmail);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                logger.info("Getting UserDetails from username");
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                logger.info("Authorities: " + userDetails.getAuthorities().toString());
                if (jwtService.isTokenValid(token, userDetails, jwtService.ACCESS_TOKEN)) {
                    logger.info("Setting credentials");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
