package br.com.dgc.simplechurch.user.controller.dto;

import java.util.stream.Collectors;

import br.com.dgc.simplechurch.role.model.Role;
import br.com.dgc.simplechurch.user.controller.dto.request.RefreshRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.request.SignInRequestDto;
import br.com.dgc.simplechurch.user.controller.dto.response.AssociateUserAndChurchResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.AssociateUserAndRoleResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.LoginResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.RefreshResponseDto;
import br.com.dgc.simplechurch.user.controller.dto.response.SignInResponseDto;
import br.com.dgc.simplechurch.user.model.User;

public class UserMapper {
    public static User signInRequestDtoToUser(SignInRequestDto signInRequestDto) {
        return new User(signInRequestDto.getFirstName(), signInRequestDto.getLastName(), signInRequestDto.getEmail(),
                signInRequestDto.getPassword());
    }

    public static SignInResponseDto userToSignInResponseDto(User user) {
        return new SignInResponseDto(user.getId(), user.getEmail());
    }

    public static LoginResponseDto userAndTokensToLoginResponseDto(User user, String accessToken, String refreshToken) {
        return new LoginResponseDto(user.getFirstName(), user.getLastName(), accessToken, refreshToken,
                user.getRoles().stream().map((Role::getName)).collect(Collectors.toSet()));
    }

    public static RefreshResponseDto tokenAndRefreshRequestDtoToRefreshResponseDto(String token,
            RefreshRequestDto refreshRequestDto) {
        return new RefreshResponseDto(token, refreshRequestDto.getRefreshToken());
    }

    public static AssociateUserAndRoleResponseDto userToAssociateUserAndRoleResponseDto(User user) {
        return new AssociateUserAndRoleResponseDto(user.getId(), user.getEmail(),
                user.getRoles().stream().map(Role::getName).toList());
    }

    public static AssociateUserAndChurchResponseDto userToAssociateUserAndChurchResponseDto(User user) {
        return new AssociateUserAndChurchResponseDto(user.getId(), user.getEmail(), user.getChurch().getId());
    }
}
