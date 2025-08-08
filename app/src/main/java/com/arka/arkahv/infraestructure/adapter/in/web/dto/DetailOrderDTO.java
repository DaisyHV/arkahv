package com.arka.arkahv.infraestructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrderDTO {
    private int id;
    private int  product_id;
    private int order_id;
    private int numberProducts;
}
