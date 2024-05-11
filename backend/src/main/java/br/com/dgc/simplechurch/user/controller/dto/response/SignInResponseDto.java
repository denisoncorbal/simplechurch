package br.com.dgc.simplechurch.user.controller.dto.response;

import java.util.UUID;

public class SignInResponseDto {
    private UUID id;
    private String email;

    public SignInResponseDto() {
    }

    public SignInResponseDto(UUID id, String email) {
        this.id = id;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
