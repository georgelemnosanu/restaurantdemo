package com.restaurant.restaurantdemo.service;

import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.model.MenuItem;
import com.restaurant.restaurantdemo.model.Speciality;
import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.repository.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl {

    private final MenuItemRepository menuItemRepository;

    private final SpecialityServiceImpl specialityService;

    public void createMenuItem(
            MultipartFile imageFile,
            String name,
            String description,
            String priceStr,
            String ingredientsStr,
            String specialityIdStr
    ) throws IOException {
        double price = Double.parseDouble(priceStr);

        byte[] imageData = imageFile.getBytes();

        List<String> ingredients = Arrays.asList(ingredientsStr.split(","));

        int specialityId = Integer.parseInt(specialityIdStr);
        Speciality speciality = specialityService.findById(specialityId).orElse(null);
        if (speciality == null) {
            throw new RuntimeException("Speciality with ID " + specialityId + " not found");
        }

        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setPrice(price);
        menuItem.setIngredients(ingredients);
        menuItem.setImageData(imageData); // BLOB
        menuItem.setSpeciality(speciality);

        menuItemRepository.save(menuItem);
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
