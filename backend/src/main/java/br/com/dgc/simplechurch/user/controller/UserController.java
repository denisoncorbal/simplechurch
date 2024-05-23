package br.com.dgc.simplechurch.user.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dgc.simplechurch.role.service.RoleService;
import br.com.dgc.simplechurch.security.jwt.JwtService;
import br.com.dgc.simplechurch.user.controller.dto.UserMapper;
import br.com.dgc.simplechurch.user.controller.dto.request.LoginRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.request.RefreshRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.request.SignInRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.response.AssociateUserAndChurchResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.AssociateUserAndRoleResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.LoginResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.RefreshResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.SignInResponseDto;
import br.com.dgc.simplechurch.user.exception.InvalidRoleException;
import br.com.dgc.simplechurch.user.exception.PasswordInvalidException;
import br.com.dgc.simplechurch.user.model.User;
import br.com.dgc.simplechurch.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;
    private UserDetailsService userDetailsService;
    private RoleService roleService;
    private JwtService jwtService;

    private final String ADMIN_ROLE_NAME = "Admin";

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserDetailsService userDetailsService, JwtService jwtService,
            RoleService roleService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.roleService = roleService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> attemptToLogin(@RequestBody LoginRequestDto loginRequestDto)
            throws PasswordInvalidException {
        User user = (User) this.userDetailsService.loadUserByUsername(loginRequestDto.getEmail());
        if (!this.userService.checkPassword(user, loginRequestDto.getPassword()))
            throw new PasswordInvalidException("Senha inválida");
        String accessToken = this.jwtService.generateToken(user, jwtService.ACCESS_TOKEN);
        String refreshToken = this.jwtService.generateToken(user, jwtService.REFRESH_TOKEN);
        return ResponseEntity.ok(UserMapper.userAndTokensToLoginResponseDto(user, accessToken, refreshToken));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto)
            throws InvalidRoleException {
        logger.info("Attempt to signin");
        if (signInRequestDto.getRoleId() == null
                && SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ADMIN_ROLE_NAME))) {
            logger.info("User requesting is admin and is trying to create a admin user");
            signInRequestDto = new SignInRequestDto(signInRequestDto.getFirstName(), signInRequestDto.getLastName(),
                    signInRequestDto.getEmail(), signInRequestDto.getPassword(),
                    this.roleService.readRoleByName(ADMIN_ROLE_NAME).get().getId(),
                    signInRequestDto.getChurchId());
        }

        if (signInRequestDto.getRoleId() == null) {
            logger.info("Attempt to create admin user failed");
            throw new InvalidRoleException("Invalid role id");
        }

        logger.info("Creating user");
        User user = this.userService.createUser(UserMapper.signInRequestDtoToUser(signInRequestDto));
        logger.info("Adding role to user");
        user = this.userService.addRoleToUser(user.getId(), signInRequestDto.getRoleId());
        if (signInRequestDto.getChurchId() != null && signInRequestDto.getChurchId().isPresent()) {
            logger.info("Church provided, adding church");
            user = this.userService.addChurchToUser(user.getId(), signInRequestDto.getChurchId().get());
        }

        logger.info("User created with success");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.userToSignInResponseDto(user));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponseDto> signIn(@RequestBody RefreshRequestDto refreshRequestDto) {
        final String userEmail = jwtService.extractUsername(refreshRequestDto.getRefreshToken(),
                jwtService.REFRESH_TOKEN);
        User user = (User) this.userDetailsService.loadUserByUsername(userEmail);
        String accessToken = this.jwtService.generateToken(user, jwtService.ACCESS_TOKEN);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.tokenAndRefreshRequestDtoToRefreshResponseDto(accessToken, refreshRequestDto));
    }

    @PostMapping("/{userId}/role/{roleId}")
    public ResponseEntity<AssociateUserAndRoleResponseDto> associateUserAndRole(@PathVariable UUID userId,
            @PathVariable UUID roleId) {
        User user = this.userService.addRoleToUser(userId, roleId);
        return ResponseEntity.ok(UserMapper.userToAssociateUserAndRoleResponseDto(user));
    }

    @PostMapping("/{userId}/church/{churchId}")
    public ResponseEntity<AssociateUserAndChurchResponseDto> associateUserAndChurch(@PathVariable UUID userId,
            @PathVariable UUID churchId) {
        User user = this.userService.addChurchToUser(userId, churchId);
        return ResponseEntity.ok(UserMapper.userToAssociateUserAndChurchResponseDto(user));
    }
}
