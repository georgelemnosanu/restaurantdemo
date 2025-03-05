package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {
    List<MenuItem> findBySpeciality_SpecialityClass_Id(Integer specialityClassId);


}
