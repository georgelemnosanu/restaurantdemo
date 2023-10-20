package com.restaurant.restaurantdemo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@jakarta.persistence.Table(name = "speciality")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "specialityClass_id")
    private SpecialityClass specialityClass;


    private String name;

    @OneToMany(mappedBy = "speciality")
    @JsonIgnoreProperties("speciality")
    private List<MenuItem> items;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonIgnoreProperties("specialities")
    private Menu menu;

}
