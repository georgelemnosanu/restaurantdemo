package com.restaurant.restaurantdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@jakarta.persistence.Table(name = "tables")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tableName;

    @Column(name = "bill_requested", nullable = false, columnDefinition = "boolean default false")
    private boolean billRequested = false;
}
