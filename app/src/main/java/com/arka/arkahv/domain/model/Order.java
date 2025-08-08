package com.arka.arkahv.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private int id;
    private double total;
    private Date date_order;
    private User user;
    private List<DetailOrder> details_order;
}
