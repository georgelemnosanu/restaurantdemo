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
public class SpecialityDTO {
    private Integer id;
    private String name;

    public SpecialityDTO() {

    }
}
