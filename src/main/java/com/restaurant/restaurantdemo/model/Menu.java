package com.restaurant.restaurantdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@jakarta.persistence.Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "menu")
    @JsonIgnoreProperties("menu") // Ignore the 'menu' property in Speciality
    private List<Speciality> specialities;
}
