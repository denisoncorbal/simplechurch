package br.com.dgc.simplechurch.church.controller.dto.response;

import java.util.UUID;

public class ReadChurchResponseDto {
    private UUID id;
    private String name;
    private String cnpj;

    public ReadChurchResponseDto() {
    }

    public ReadChurchResponseDto(UUID id, String name, String cnpj) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
