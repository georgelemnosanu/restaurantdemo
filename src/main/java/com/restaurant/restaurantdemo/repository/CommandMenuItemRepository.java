package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.CommandMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandMenuItemRepository extends JpaRepository<CommandMenuItem,Integer> {
}
