package com.ing.test.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private Float price;

    @Column
    private Long quantity;
}
