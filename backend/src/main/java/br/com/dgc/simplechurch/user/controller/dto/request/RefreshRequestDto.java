package br.com.dgc.simplechurch.user.controller.dto.request;

public class RefreshRequestDto {
    private String accessToken;
    private String refreshToken;

    public RefreshRequestDto() {
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
