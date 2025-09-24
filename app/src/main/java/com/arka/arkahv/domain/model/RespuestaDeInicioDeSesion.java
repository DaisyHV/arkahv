package com.arka.arkahv.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

@NoArgsConstructor
public class RespuestaDeInicioDeSesion {
    private String token;
    private String refreshToken;
    private String email;

    public RespuestaDeInicioDeSesion(String token, String refreshToken, String email) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.email = email;
    }


}
