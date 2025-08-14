package com.arka.arkahv.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DetailOrder {

    private int id;
    private int idProduct;
    private int idOrder;
    private int number;
   // no se si son necesarias private LocalDateTime created_at;
    //private LocalDateTime updated_at;


}
