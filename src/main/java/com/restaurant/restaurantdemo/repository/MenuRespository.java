package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRespository extends JpaRepository<Menu,Integer> {

}
