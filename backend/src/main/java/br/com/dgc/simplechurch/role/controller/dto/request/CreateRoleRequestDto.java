package br.com.dgc.simplechurch.role.controller.dto.request;

public class CreateRoleRequestDto {
    private String name;

    public CreateRoleRequestDto() {
    }

    public CreateRoleRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
