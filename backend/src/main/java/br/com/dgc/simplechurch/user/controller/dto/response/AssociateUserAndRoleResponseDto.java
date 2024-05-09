package br.com.dgc.simplechurch.user.controller.dto.response;

import java.util.List;
import java.util.UUID;

public class AssociateUserAndRoleResponseDto {
    private UUID id;
    private String email;
    private List<String> roles;

    public AssociateUserAndRoleResponseDto() {
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public AssociateUserAndRoleResponseDto(UUID id, String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

}
