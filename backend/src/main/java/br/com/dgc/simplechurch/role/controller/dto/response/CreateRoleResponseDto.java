package br.com.dgc.simplechurch.role.controller.dto.response;

import java.util.UUID;

public class CreateRoleResponseDto {
    private UUID id;
    private String name;

    public CreateRoleResponseDto() {
    }

    public CreateRoleResponseDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
