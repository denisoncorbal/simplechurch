package br.com.dgc.simplechurch.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dgc.simplechurch.security.jwt.JwtService;
import br.com.dgc.simplechurch.user.controller.dto.UserMapper;
import br.com.dgc.simplechurch.user.controller.dto.request.LoginRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.request.RefreshRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.request.SignInRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.response.LoginResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.RefreshResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.SignInResponseDto;
import br.com.dgc.simplechurch.user.exception.PasswordInvalidException;
import br.com.dgc.simplechurch.user.model.User;
import br.com.dgc.simplechurch.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;
    private UserDetailsService userDetailsService;
    private JwtService jwtService;
    private final UserMapper userMapper = new UserMapper();

    public UserController(UserService userService, UserDetailsService userDetailsService, JwtService jwtService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> attemptToLogin(@RequestBody LoginRequestDto loginRequestDto)
            throws PasswordInvalidException {
        User user = (User) this.userDetailsService.loadUserByUsername(loginRequestDto.getEmail());
        if (!this.userService.checkPassword(user, loginRequestDto.getPassword()))
            throw new PasswordInvalidException("Senha inválida");
        String accessToken = this.jwtService.generateToken(user, jwtService.ACCESS_TOKEN);
        String refreshToken = this.jwtService.generateToken(user, jwtService.REFRESH_TOKEN);
        return ResponseEntity.ok(this.userMapper.userAndTokensToLoginResponseDto(user, accessToken, refreshToken));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        User user = this.userService.createUser(this.userMapper.signInRequestDtoToUser(signInRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userMapper.userToSignInResponseDto(user));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponseDto> signIn(@RequestBody RefreshRequestDto refreshRequestDto) {
        final String userEmail = jwtService.extractUsername(refreshRequestDto.getRefreshToken(),
                jwtService.REFRESH_TOKEN);
        User user = (User) this.userDetailsService.loadUserByUsername(userEmail);
        String accessToken = this.jwtService.generateToken(user, jwtService.ACCESS_TOKEN);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userMapper.tokenAndRefreshRequestDtoToRefreshResponseDto(accessToken, refreshRequestDto));
    }
}
