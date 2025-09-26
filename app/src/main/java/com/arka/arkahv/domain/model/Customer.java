package com.arka.arkahv.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    private int id;


    private String firstname;

    private String lastname;


    private String numberid;
    //private Adress adress;
    //private Role role;
    private String email;
}
