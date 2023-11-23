package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.model.MenuItem;
import com.restaurant.restaurantdemo.model.Speciality;
import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.repository.MenuRespository;
import com.restaurant.restaurantdemo.repository.SpecialityRepository;
import com.restaurant.restaurantdemo.repository.TableRepository;
import com.restaurant.restaurantdemo.service.MenuItemServiceImpl;
import com.restaurant.restaurantdemo.service.MenuServiceImpl;
import com.restaurant.restaurantdemo.service.SpecialityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    @Autowired
    private final MenuServiceImpl menuService;

    @Autowired
    private final SpecialityServiceImpl specialityService;

    @Autowired
    private final MenuItemServiceImpl menuItemService;

    @Autowired
    private MenuRespository menuRespository;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    public ApiController(
            MenuServiceImpl menuService,
            SpecialityServiceImpl specialityService,
            MenuItemServiceImpl menuItemService
    ) {
        this.menuService = menuService;
        this.specialityService = specialityService;
        this.menuItemService = menuItemService;
    }


    @GetMapping("/specialities/{specialityId}/menuitems")
    public ResponseEntity<List<MenuItem>> getMenuItemsBySpeciality(@PathVariable Integer specialityId) {
        Optional<Speciality> speciality = specialityRepository.findById(specialityId);

        if (speciality == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<MenuItem> menuItems = speciality.get().getItems();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/table/{tableId}/menus/{menuId}/specialities")
    public ResponseEntity<List<Speciality>> getSpecialitiesInMenu(
            @PathVariable Integer tableId,
            @PathVariable Integer menuId
    ) {
        Optional<Table> table = tableRepository.findById(tableId);
        Optional<Menu> menu = menuRespository.findById(menuId);

        if (!table.isPresent() || !menu.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Speciality> specialities = specialityService.specialityList();
        return new ResponseEntity<>(specialities, HttpStatus.OK);
    }

}
