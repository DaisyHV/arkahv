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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    @Column(columnDefinition="TEXT") //name, length
    private String firstname;
    @Column(columnDefinition="TEXT") //name, length
    private String lastname;

    private int age;
    private String numberid;
    private Adress adress;
    private Role role;
    private List<Order> orders;
}
