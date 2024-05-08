package br.com.dgc.simplechurch.church.controller.dto.response;

import java.util.UUID;

public class CreateChurchResponseDto {
    private UUID id;
    private String name;

    public CreateChurchResponseDto() {
    }

    public CreateChurchResponseDto(UUID id, String name) {
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
