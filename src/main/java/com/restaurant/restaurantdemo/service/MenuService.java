package com.restaurant.restaurantdemo.service;

import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.repository.MenuRespository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRespository menuRespository;


    public List<Menu> menuList(){
        return  menuRespository.findAll();
    }

    public Menu findById(Integer id){
        return menuRespository.findById(id).orElseThrow(()-> new ObjectNotFoundException(HttpStatus.NOT_FOUND,"Menu not Found"));
    }

    public Menu createMenu(Menu menu){
        return menuRespository.save(menu);
    }

    public void deleteMenu(Integer menuId){
        menuRespository.deleteById(menuId);
    }

}
