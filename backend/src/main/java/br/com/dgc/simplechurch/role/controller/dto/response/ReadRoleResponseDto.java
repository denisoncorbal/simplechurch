package br.com.dgc.simplechurch.role.controller.dto.response;

import java.util.UUID;

public class ReadRoleResponseDto {
    private UUID id;
    private String name;

    public ReadRoleResponseDto() {
    }

    public ReadRoleResponseDto(UUID id, String name) {
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
