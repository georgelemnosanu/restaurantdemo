package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command,Integer> {
}
