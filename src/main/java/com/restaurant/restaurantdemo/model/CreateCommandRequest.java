package com.restaurant.restaurantdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class CreateCommandRequest {
    private Integer tableId;
    private Map<Integer, Integer> menuItemsWithQuantities;

    @JsonProperty("kitchenNotes")
    private String kitchenNotes;

    @JsonProperty("barNotes")
    private String barNotes;


}