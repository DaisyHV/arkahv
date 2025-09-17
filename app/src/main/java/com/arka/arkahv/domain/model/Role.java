package com.arka.arkahv.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {


    private int  id_role;
    private String description;
    private List<Customer> customers;
}
