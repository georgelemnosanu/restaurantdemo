package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
}
