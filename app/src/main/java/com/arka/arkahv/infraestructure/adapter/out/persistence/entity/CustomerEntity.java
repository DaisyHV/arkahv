package com.arka.arkahv.infraestructure.adapter.out.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private int id;

    @Column(columnDefinition="TEXT") //name, length
    private String firstname;
    @Column(columnDefinition="TEXT") //name, length
    private String lastname;

    private String numberid;

    private String email;

    @OneToMany(mappedBy= "customer")
    @JsonIgnore
    private List<OrderEntity> orders;

    //created_at datetime
    //updated_at datetime







}
