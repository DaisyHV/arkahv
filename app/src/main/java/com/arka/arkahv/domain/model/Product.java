package com.arka.arkahv.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    //tambien pueden ir metodos de negocio
    private int id;
    private String description;
    private int stock;
    private double price;
    private Category category;
    //--private List<DetailOrder> details_product;
}
