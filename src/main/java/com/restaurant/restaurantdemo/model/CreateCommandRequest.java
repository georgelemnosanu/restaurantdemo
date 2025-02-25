package com.restaurant.restaurantdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restaurant.restaurantdemo.dto.CommandMenuItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class CreateCommandRequest {
    private Integer tableId;
    private List<CommandMenuItemDTO> items;
}