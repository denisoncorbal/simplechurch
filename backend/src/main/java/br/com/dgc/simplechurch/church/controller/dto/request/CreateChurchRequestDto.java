package br.com.dgc.simplechurch.church.controller.dto.request;

public class CreateChurchRequestDto {
    private String name;
    private String cnpj;

    public CreateChurchRequestDto() {
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
