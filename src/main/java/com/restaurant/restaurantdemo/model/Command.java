package com.restaurant.restaurantdemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String barAdditionalInformation;
    private String kitchenAdditionalInformation;

    @ManyToOne
    @JoinColumn(name = "table_id")
    @JsonIgnore
    private Table table;

    @OneToMany(mappedBy = "command", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("command")
    private Set<CommandMenuItem> menuItemsWithQuantities = new HashSet<>();




     public Command(){
     }



}