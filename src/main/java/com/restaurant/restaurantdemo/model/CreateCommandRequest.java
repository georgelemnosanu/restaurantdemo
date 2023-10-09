package com.restaurant.restaurantdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class CreateCommandRequest {
    private Integer tableId;
    private Map<Integer, Integer> menuItemsWithQuantities;

    // Getters and setters
}