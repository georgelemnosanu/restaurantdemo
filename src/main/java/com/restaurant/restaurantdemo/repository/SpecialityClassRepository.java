package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.SpecialityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityClassRepository extends JpaRepository<SpecialityClass,Integer> {
}
