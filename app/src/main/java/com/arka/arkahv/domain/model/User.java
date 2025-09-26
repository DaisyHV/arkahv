package com.arka.arkahv.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    private String userName;


    private String emailAddress;


    private String password;

    // adicional seguridad rol
    private String role;

    //Constructores, Getters y Setters
}
