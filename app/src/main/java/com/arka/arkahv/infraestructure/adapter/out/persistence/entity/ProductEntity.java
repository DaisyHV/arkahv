package com.arka.arkahv.infraestructure.adapter.out.persistence.entity;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.domain.model.DetailOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id_product")
    private int id;

    @Column(columnDefinition="TEXT")
    private String description;


    private int stock;


    private double price;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    //@JsonIgnore
    private CategoryEntity category;

   @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonManagedReference
   @JsonIgnore
    private List<DetailOrderEntity> details_product;

}
