package com.arka.arkahv.domain.model;

public class SolicitudRefreshToken {
    private String refreshToken;

    public SolicitudRefreshToken() {
    }

    public SolicitudRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
