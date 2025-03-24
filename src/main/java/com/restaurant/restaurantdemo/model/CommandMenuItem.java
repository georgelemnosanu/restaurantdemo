package com.restaurant.restaurantdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restaurant.restaurantdemo.model.Command;
import com.restaurant.restaurantdemo.model.MenuItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"command"})
public class CommandMenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "command_id")
    private Command command;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    @JsonIgnoreProperties({"image", "imageData"})
    private MenuItem menuItem;

    private Integer quantity;

    private String additionalNotes;

    public CommandMenuItem() {}

    public CommandMenuItem(Command command, MenuItem menuItem, Integer quantity) {
        this.command = command;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }
}
