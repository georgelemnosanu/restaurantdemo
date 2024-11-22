package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.model.MenuItem;
import com.restaurant.restaurantdemo.model.Speciality;
import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.repository.MenuItemRepository;
import com.restaurant.restaurantdemo.repository.SpecialityRepository;
import com.restaurant.restaurantdemo.service.MenuItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/menuItem")
@CrossOrigin(origins = "https://lmncheap.store")
public class MenuItemController {
    @Autowired
    private MenuItemServiceImpl menuItemService;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @PostMapping("/submitCreateMenuItem")
    public ResponseEntity<String> createMenuItem(@RequestBody MenuItem menuItem) {
        try {
            menuItemService.createMenuItem(menuItem);
            return ResponseEntity.ok("MenuItem created successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating MenuItem");
        }
    }

    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> getTableById(@PathVariable Integer menuItemId) {
        MenuItem menuItem = menuItemService.findbyId(menuItemId);

        if (menuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @GetMapping("/viewAllMenuItems")
    public List<MenuItem> allMenuItems() {
        return menuItemRepository.findAll();
    }

    @PutMapping("/editMenuItem/{menuItemId}")
    public ResponseEntity<String> updateMenuItem(
            @PathVariable Integer menuItemId,
            @RequestParam String newName,
            @RequestParam String newDescription,
            @RequestParam double newPrice) {
        try {
            Optional<MenuItem> existingMenuItem = menuItemRepository.findById(menuItemId);
            if (existingMenuItem.isPresent()) {
                menuItemService.updateMenuItem(menuItemId, newName, newDescription, newPrice);
                return ResponseEntity.ok("MenuItem updated Successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating MenuItem");
        }
    }
    @DeleteMapping("/deleteMenuItem/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Integer menuItemId){
        try{
            menuItemService.deleteMenuItem(menuItemId);
            return ResponseEntity.ok().build();
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
