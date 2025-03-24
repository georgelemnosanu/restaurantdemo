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
    private String image;
    private String category;
    private List<String> ingredients;

    public MenuItemDTO() {

    }
}
