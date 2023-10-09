package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality,Integer> {
}
