package com.arka.arkahv.infraestructure.adapter.out.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonBackReference
    private ProductEntity product;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonBackReference
    private OrderEntity order;



    private int number;




    // Variables y métodos para created_at y updated_at

    @JsonIgnore
    //@Column(columnDefinition = "DATETIME")
    private LocalDateTime created_at;

    @JsonIgnore
    //@Column(columnDefinition = "DATETIME")
    private LocalDateTime updated_at;

    @PrePersist
    public void devolveraFechaActualInsertar(){
        this.created_at =now();


    }

    @PreUpdate
    public void devolveraFechaActualActualizar(){

        this.updated_at =now();

    }

}
