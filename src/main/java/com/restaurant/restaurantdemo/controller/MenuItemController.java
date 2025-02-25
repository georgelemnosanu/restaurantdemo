package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.dto.MenuItemDTO;
import com.restaurant.restaurantdemo.model.MenuItem;
import com.restaurant.restaurantdemo.model.Speciality;
import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.repository.MenuItemRepository;
import com.restaurant.restaurantdemo.repository.SpecialityRepository;
import com.restaurant.restaurantdemo.service.MenuItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menuItem")
@CrossOrigin(origins = "https://lmncheap.store")
public class MenuItemController {
    @Autowired
    private MenuItemServiceImpl menuItemService;
    @Autowired
    private MenuItemRepository menuItemRepository;


    @PostMapping(value = "/submitCreateMenuItem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createMenuItem(
            @RequestPart("image") MultipartFile imageFile,
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("price") String priceStr,
            @RequestPart("ingredients") String ingredientsStr,
            @RequestPart("specialityId") String specialityIdStr
    ) {
        try {
            // APELĂM direct SERVICE-ul, pasăm datele
            menuItemService.createMenuItem(
                    imageFile,
                    name,
                    description,
                    priceStr,
                    ingredientsStr,
                    specialityIdStr
            );
            return ResponseEntity.ok("MenuItem created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating MenuItem: " + e.getMessage());
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
    public List<MenuItemDTO> getAllMenuItems() {
        List<MenuItem> items = menuItemRepository.findAll();
        return items.stream().map(item -> {
            MenuItemDTO dto = new MenuItemDTO();
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setDescription(item.getDescription());
            dto.setPrice(item.getPrice());
            dto.setIngredients(item.getIngredients());
            // Pentru categoria, presupunem că vrei numele specialității:
            dto.setCategory(item.getSpeciality() != null ? item.getSpeciality().getName() : null);

            // Pentru imagine: dacă imageData nu e null, adaugă prefixul
            if (item.getImageData() != null) {
                String base64Image = Base64.getEncoder().encodeToString(item.getImageData());
                dto.setImage("data:image/jpeg;base64," + base64Image);
            } else {
                dto.setImage(null); // sau poți seta un URL de placeholder dacă dorești
            }

            return dto;
        }).collect(Collectors.toList());
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
