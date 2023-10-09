package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.model.Command;
import com.restaurant.restaurantdemo.model.MenuItem;
import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.repository.MenuItemRepository;
import com.restaurant.restaurantdemo.repository.TableRepository;
import com.restaurant.restaurantdemo.service.CommandService;
import com.restaurant.restaurantdemo.service.MenuItemServiceImpl;
import com.restaurant.restaurantdemo.service.TableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableServiceImpl tableService;

    @Autowired
    private CommandService commandService;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private MenuItemServiceImpl menuItemService;

    @GetMapping("/{tableId}")
    public ResponseEntity<Table> getTableById(@PathVariable Integer tableId) {
        Table table = tableService.findbyId(tableId);

        if (table == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(table, HttpStatus.OK);
    }

//    @PostMapping("/{tableId}/create-command")
//    public ResponseEntity<Command> createCommand(
//            @PathVariable Integer tableId,
//            @RequestBody Command command
//    ) {
//        Table table = tableService.findbyId(tableId);
//
//        if (table == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        command.setTable(table);
//        Command createdCommand = commandService.createComand(command);
//        return new ResponseEntity<>(createdCommand, HttpStatus.CREATED);
//    }


//    @PostMapping("/{tableId}/add-menu-items")
//    public ResponseEntity<Command> addMenuItemsWithQuantities(
//            @PathVariable Integer tableId,
//            @RequestBody Map<Integer, Integer> menuItemsWithQuantities) {
//        // Retrieve the table by ID
//        Table table = tableService.findbyId(tableId);
//
//        if (table == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        // Create a new Command
//        Command command = new Command();
//        command.setTable(table);
//
//        Map<MenuItem, Integer> menuItemMap = new HashMap<>();
//        for (Map.Entry<Integer, Integer> entry : menuItemsWithQuantities.entrySet()) {
//            Integer menuItemId = entry.getKey();
//            Integer quantity = entry.getValue();
//            MenuItem menuItem = menuItemService.findbyId(menuItemId);
//
//            if (menuItem != null) {
//                menuItemMap.put(menuItem, quantity);
//            }
//        }
//
//        if (!menuItemMap.isEmpty()) {
//            // Add the menu items with quantities to the new command
//            command = commandService.addMenuItemsWithQuantities(command, menuItemMap);
//            return ResponseEntity.ok(command);
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }
    @GetMapping("/allTables")
    public List<Table>getAllTable(){
        return tableService.findAll();
    }


}
