package br.com.dgc.simplechurch.user.controller.dto.response;

public class LoginResponseDto {
    private String firstName;
    private String lastName;
    private String accessToken;
    private String refreshToken;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String firstName, String lastName, String accessToken, String refreshToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
