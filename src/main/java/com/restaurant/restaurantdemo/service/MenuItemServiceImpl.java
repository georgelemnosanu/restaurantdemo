package com.restaurant.restaurantdemo.service;

import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.model.MenuItem;
import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.repository.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl {

    private final MenuItemRepository menuItemRepository;

    public MenuItem createMenuItem(MenuItem menuItem){
        return menuItemRepository.save(menuItem);
    }

    public List<MenuItem> menuItemList(){
        return menuItemRepository.findAll();
    }

    public MenuItem updateMenuItem(Integer menuId, String newName,String newDescription,double newPrice){
        MenuItem existingMenuItem = menuItemRepository.findById(menuId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"MenuItem Not Found"));
        existingMenuItem.setName(newName);
        existingMenuItem.setDescription(newDescription);
        existingMenuItem.setPrice(newPrice);
        return menuItemRepository.save(existingMenuItem);
    }

    public void deleteMenuItem(Integer menuItemId){
        menuItemRepository.deleteById(menuItemId);
    }
    public MenuItem findbyId(Integer menuItemId){
        return menuItemRepository.findById(menuItemId)
                .orElseThrow(()-> new EntityNotFoundException("MenuItem Not Found!"));
    }
}
