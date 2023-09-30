package com.alkan.monobackend.entities;

import jakarta.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean isDelivered = false;
    @OneToOne(cascade = CascadeType.ALL)
    private Basket basket;

}
