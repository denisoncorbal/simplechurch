package br.com.dgc.simplechurch.user.controller.dto.response;

public class SignInResponseDto {
    String email;

    public SignInResponseDto() {
    }

    public SignInResponseDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
