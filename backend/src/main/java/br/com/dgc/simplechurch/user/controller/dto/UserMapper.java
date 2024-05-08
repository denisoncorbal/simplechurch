package br.com.dgc.simplechurch.user.controller.dto;

import java.util.stream.Collectors;

import br.com.dgc.simplechurch.user.controller.dto.request.RefreshRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.request.SignInRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.response.LoginResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.RefreshResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.SignInResponseDto;
import br.com.dgc.simplechurch.user.model.Role;
import br.com.dgc.simplechurch.user.model.User;

public class UserMapper {
    public User signInRequestDtoToUser(SignInRequestDto signInRequestDto) {
        return new User(signInRequestDto.getFirstName(), signInRequestDto.getLastName(), signInRequestDto.getEmail(),
                signInRequestDto.getPassword());
    }

    public SignInResponseDto userToSignInResponseDto(User user) {
        return new SignInResponseDto(user.getEmail());
    }

    public LoginResponseDto userAndTokensToLoginResponseDto(User user, String accessToken, String refreshToken) {
        return new LoginResponseDto(user.getFirstName(), user.getLastName(), accessToken, refreshToken,
                user.getRoles().stream().map((Role::getName)).collect(Collectors.toSet()));
    }

    public RefreshResponseDto tokenAndRefreshRequestDtoToRefreshResponseDto(String token,
            RefreshRequestDto refreshRequestDto) {
        return new RefreshResponseDto(token, refreshRequestDto.getRefreshToken());
    }
}
