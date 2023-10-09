package com.restaurant.restaurantdemo.service;

import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.model.Speciality;
import com.restaurant.restaurantdemo.repository.MenuRespository;
import com.restaurant.restaurantdemo.repository.SpecialityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl {

private final MenuRespository menuRespository;
private final SpecialityRepository specialityRepository;

  public void createMenu(String name){
      Menu menu = new Menu();
      menu.setName(name);
      menuRespository.save(menu);
  }

    public List<Menu> findAllMenu() {
        return menuRespository.findAll();
    }

 public Menu findById(Integer menuId) {
     return menuRespository.findById(menuId).orElseThrow(() -> new EntityNotFoundException("Menu not found"));
 }





 }


