package com.restaurant.restaurantdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class MenuItemDTO {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private String image; // Imaginea va fi în format base64, cu prefixul corespunzător
    private String category; // Numele categoriei
    private List<String> ingredients;

    public MenuItemDTO() {

    }
}
