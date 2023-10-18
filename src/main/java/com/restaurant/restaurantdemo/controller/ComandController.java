package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.model.Command;
import com.restaurant.restaurantdemo.model.CreateCommandRequest;
import com.restaurant.restaurantdemo.model.MenuItem;
import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.service.CommandService;
import com.restaurant.restaurantdemo.service.MenuItemServiceImpl;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/command")
@CrossOrigin
public class ComandController {

    @Autowired
    private CommandService commandService;


    @Autowired
    MenuItemServiceImpl menuItemService;

     @GetMapping("/viewAllCommand")
     public List<Command> commandList(){
         return commandService.commandList();
    }



    @PostMapping("/create")
    public ResponseEntity<Command> createCommandWithMenuItems(
            @RequestBody CreateCommandRequest request) {
        Integer tableId = request.getTableId();
        Map<Integer, Integer> menuItemsWithQuantities = request.getMenuItemsWithQuantities();
        String barAdditionalInformation = request.getBarNotes();
        String kitchenAdditionalInformation = request.getKitchenNotes();

        Command createdCommand = commandService.createCommandWithMenuItems(
                tableId,
                menuItemsWithQuantities,
                barAdditionalInformation,
                kitchenAdditionalInformation
        );

        return new ResponseEntity<>(createdCommand, HttpStatus.CREATED);
    }

    @PutMapping("/editCommand/{commandId}")
    public ResponseEntity<Command> editCommandWithMenuItems(
            @PathVariable Integer commandId,
            @RequestBody CreateCommandRequest request) {
        Map<Integer, Integer> menuItemsWithQuantities = request.getMenuItemsWithQuantities();
        String barAdditionalInformation = request.getBarNotes();
        String kitchenAdditionalInformation = request.getKitchenNotes();

        Command updatedCommand = commandService.editCommandWithMenuItems(
                commandId,
                menuItemsWithQuantities,
                barAdditionalInformation,
                kitchenAdditionalInformation
        );

        return new ResponseEntity<>(updatedCommand, HttpStatus.OK);
    }


    @GetMapping("/{commandId}")
    public ResponseEntity<Command> getCommandById(@PathVariable Integer commandId) {
        Command command = commandService.findById(commandId);

        if (command == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(command, HttpStatus.OK);
    }


}






