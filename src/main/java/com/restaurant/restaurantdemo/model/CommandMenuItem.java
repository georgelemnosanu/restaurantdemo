package com.restaurant.restaurantdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommandMenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "command_id")
    private Command command;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    private Integer quantity;

    public CommandMenuItem(Command command, MenuItem menuItem, Integer quantity) {
        this.command=command;
        this.menuItem=menuItem;
        this.quantity=quantity;
    }

    public CommandMenuItem() {

    }

    // Constructors, getters, and setters
}