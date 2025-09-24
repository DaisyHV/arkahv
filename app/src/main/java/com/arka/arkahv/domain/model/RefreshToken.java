package com.arka.arkahv.domain.model;

import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    private Long id;

    private User usuario;


    private String token;


    private Instant expiryDate;

    //Constructores, Getters y Setters
}

