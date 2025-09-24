package com.arka.arkahv.infraestructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity(name = "refreshtoken")
@Data
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UserEntity usuario;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    //Constructores, Getters y Setters
}
