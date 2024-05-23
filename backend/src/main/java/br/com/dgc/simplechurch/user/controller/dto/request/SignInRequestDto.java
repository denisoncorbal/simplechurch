package br.com.dgc.simplechurch.user.controller.dto.request;

import java.util.Optional;
import java.util.UUID;

public class SignInRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UUID roleId;
    private Optional<UUID> churchId;

    public SignInRequestDto() {
    }

    

    public SignInRequestDto(String firstName, String lastName, String email, String password, UUID roleId,
            Optional<UUID> churchId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.churchId = churchId;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public Optional<UUID> getChurchId() {
        return churchId;
    }

    public void setChurchId(Optional<UUID> churchId) {
        this.churchId = churchId;
    }

}
