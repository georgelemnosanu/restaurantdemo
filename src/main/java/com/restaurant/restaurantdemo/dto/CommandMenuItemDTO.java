package com.restaurant.restaurantdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CommandMenuItemDTO {
    private Integer menuItemId;
    private Integer quantity;
    private String additionalNotes;

    @JsonProperty("menuItem")
    public void unpackMenuItem(Map<String, Object> menuItem) {
        if (menuItem != null && menuItem.get("id") != null) {
            // Poți adăuga și conversie sigură, de exemplu:
            this.menuItemId = Integer.valueOf(menuItem.get("id").toString());
        }

    }
}
