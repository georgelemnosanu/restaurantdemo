package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality,Integer> {
    List<Speciality> findBySpecialityClass_Id(Integer classId);

}
