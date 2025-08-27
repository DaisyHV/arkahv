package com.arka.arkahv.infraestructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int id;
    private String description;
    private double price;
    private int stock;
    private int categoriaId;


}
