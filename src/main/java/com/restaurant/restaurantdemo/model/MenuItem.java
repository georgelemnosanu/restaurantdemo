package com.restaurant.restaurantdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@jakarta.persistence.Table(name = "menuItem")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private double price;


    @ManyToOne
    @JoinColumn(name="speciality_id")
    private Speciality speciality;


    public MenuItem(Integer id) {
        this.id = id;
    }

    public MenuItem() {

    }
}
