package com.arka.arkahv.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SolicitudDeInicioDeSesion {
    private String email;
    private String password;


}