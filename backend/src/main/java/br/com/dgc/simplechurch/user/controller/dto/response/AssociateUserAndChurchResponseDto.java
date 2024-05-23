package br.com.dgc.simplechurch.user.controller.dto.response;

import java.util.UUID;

public class AssociateUserAndChurchResponseDto {
    private UUID id;
    private String email;
    private UUID churchId;

    public AssociateUserAndChurchResponseDto() {
    }

    public AssociateUserAndChurchResponseDto(UUID id, String email, UUID churchId) {
        this.id = id;
        this.email = email;
        this.churchId = churchId;
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

    public UUID getChurchId() {
        return churchId;
    }

    public void setChurchId(UUID churchId) {
        this.churchId = churchId;
    }

}
