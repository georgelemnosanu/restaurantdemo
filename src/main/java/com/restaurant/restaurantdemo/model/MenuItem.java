package com.restaurant.restaurantdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.*;

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

    @ElementCollection
    @CollectionTable(
            name = "menu_item_ingredients",
            joinColumns = @JoinColumn(name = "menu_item_id")
    )
    private List<String> ingredients = new ArrayList<>();

    private double price;

    @Lob
    @Column(name = "image_data")
    @JsonIgnore
    private byte[] imageData;

    @JsonProperty("imageData")
    public String getImageDataBase64() {
        return imageData != null ? Base64.getEncoder().encodeToString(imageData) : null;
    }

    @ManyToOne
    @JoinColumn(name="speciality_id")
    private Speciality speciality;

    @JsonProperty("category")
    public String getCategoryName() {
        return speciality != null ? speciality.getName() : null;
    }

    public MenuItem(Integer id) {
        this.id = id;
    }

    public MenuItem() {

    }
}
