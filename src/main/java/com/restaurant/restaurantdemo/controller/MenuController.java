package com.restaurant.restaurantdemo.controller;


import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.repository.MenuRespository;
import com.restaurant.restaurantdemo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRespository menuRespository;


     @GetMapping("/allMenu")
      List<Menu> menuList(){
    return menuService.menuList();
}

   @PostMapping("/submitCreateMenu")
    public ResponseEntity<String> createMenu(@RequestBody Menu menu) {
       try {
           menuService.createMenu(menu);
           return ResponseEntity.ok("Menu created successfully");
       } catch (ResponseStatusException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating Menu");
       }
   }

       @PutMapping("/editMenu/{menuId}")
               public ResponseEntity<String> updateMenu(@PathVariable Integer menuId, @RequestParam String newName){
              Menu menu = menuService.findById(menuId);
              menu.setName(newName);
              menuRespository.save(menu);
              return ResponseEntity.status(HttpStatus.ACCEPTED).body("The menu was edited");
       }

    @DeleteMapping("/deleteMenu/{menuId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Integer menuId){
        try{
            menuService.deleteMenu(menuId);
            return ResponseEntity.ok().build();
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


   }






