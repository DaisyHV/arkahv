package com.arka.arkahv.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adress {


    private int id;
    private String street;
    private String number;
    private String complement;
    private String city;
    private Customer customer;
}
