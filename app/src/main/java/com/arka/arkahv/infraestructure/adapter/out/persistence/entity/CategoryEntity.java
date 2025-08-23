package com.arka.arkahv.infraestructure.adapter.out.persistence.entity;

import com.arka.arkahv.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int id;


    private String description;

    @OneToMany(mappedBy= "category")
    @JsonIgnore
    private List<ProductEntity> products;
}
