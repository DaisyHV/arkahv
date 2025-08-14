package com.arka.arkahv.infraestructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private int id;
    private Date date_order;
    private double total;
    //private int user;

    private List<DetailOrderDTO> details;


}
