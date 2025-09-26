package com.arka.arkahv.infraestructure.adapter.out.persistence.entity;

import com.arka.arkahv.domain.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_factura")
        private int id;

        private double total;

        @Column(columnDefinition = "DATETIME")
        private Date date_order;

        //--@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        //--@ManyToOne
        //--@JoinColumn(name = "id_user")
        //--private User user;

        @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        //@JsonManagedReference

        private List<DetailOrderEntity> details_order;

        @ManyToOne
        @JoinColumn(name = "id_customer")
        //@JsonIgnore
        private CustomerEntity customer;


    }

