package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.model.*;
import com.restaurant.restaurantdemo.repository.CommandRepository;
import com.restaurant.restaurantdemo.repository.SpecialityClassRepository;
import com.restaurant.restaurantdemo.repository.TableRepository;
import com.restaurant.restaurantdemo.service.CommandService;
import com.restaurant.restaurantdemo.service.MenuItemServiceImpl;
import com.restaurant.restaurantdemo.service.TableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/command")
@CrossOrigin
public class ComandController {

    @Autowired
    private CommandService commandService;
    @Autowired
    private TableServiceImpl tableService;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CommandRepository commandRepository;

    @GetMapping("/allSpecialityClass")
    public List<SpecialityClass> specialityClassList(){
        return commandService.specialityClassList();
    }

     @GetMapping("/viewAllCommand")
     public List<Command> commandList(){
         return commandService.commandList();
    }

    @GetMapping("/bySpeciality")
    public ResponseEntity<Map<String, List<Command>>> getCommandsGroupedBySpeciality() {
        List<Command> commands = commandService.commandList();
        Map<String, List<Command>> groupedCommands = new HashMap<>();

        for (Command command : commands) {

            Set<String> specialityNames = new HashSet<>();


            for (CommandMenuItem item : command.getMenuItemsWithQuantities()) {
                if (item.getMenuItem() != null &&
                        item.getMenuItem().getSpeciality() != null &&
                        item.getMenuItem().getSpeciality().getSpecialityClass() != null) {

                    String specialityName = item.getMenuItem().getSpeciality().getSpecialityClass().getName();
                    specialityNames.add(specialityName);
                }
            }

            for (String specialityName : specialityNames) {
                groupedCommands.computeIfAbsent(specialityName, k -> new ArrayList<>()).add(command);
            }
        }

        return ResponseEntity.ok(groupedCommands);
    }

    @PutMapping("/requestBill/{tableId}")
    public ResponseEntity<?> requestBill(@PathVariable Integer tableId) {
        Table table = tableService.findbyId(tableId);
        if (table == null) {
            return ResponseEntity.notFound().build();
        }
        table.setBillRequested(true);
        tableRepository.save(table);

        List<Command> commands = commandService.findActiveCommandsByTableId(tableId);

        return ResponseEntity.ok(Map.of(
                "orders", commands,
                "billRequested", true
        ));
    }


    @GetMapping("/table/{tableId}")
    public ResponseEntity<?> getCommandsByTableId(@PathVariable Integer tableId) {
        List<Command> commands = commandService.findActiveCommandsByTableId(tableId);

        Table table = tableService.findbyId(tableId);
        boolean billRequested = (table != null) && table.isBillRequested();

        return ResponseEntity.ok(Map.of(
                "orders", commands,
                "billRequested", billRequested
        ));
    }

    @PutMapping("/close/{tableId}")
    public ResponseEntity<?> closeCommand(@PathVariable Integer tableId) {
        List<Command> commands = commandService.findCommandsByTableId(tableId);
        if (commands.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        for (Command command : commands) {
            command.setStatus(CommandStatus.CLOSED);
            commandRepository.save(command);
        }

        Table table = tableService.findbyId(tableId);
        if (table != null) {
            table.setBillRequested(false);
            tableRepository.save(table);
        }

        return ResponseEntity.ok(Map.of(
                "orders", commands,
                "billRequested", false
        ));
    }

    @PostMapping("/create")
    public ResponseEntity<Command> createCommand(@RequestBody CreateCommandRequest requestDto) {
        Command command = commandService.createCommand(requestDto);
        return ResponseEntity.ok(command);
    }

//    @PutMapping("/editCommand")
//    public ResponseEntity<Command> editCommandWithMenuItems(
//            @RequestBody CreateCommandRequest request) {
//
//        Map<Integer, Integer> menuItemsWithQuantities = request.getMenuItemsWithQuantities();
//        String barAdditionalInformation = request.getBarNotes();
//        String kitchenAdditionalInformation = request.getKitchenNotes();
//        Integer commandId = request.getCommandId();
//
//        Command updatedCommand = commandService.editCommandWithMenuItems(
//                commandId,
//                menuItemsWithQuantities,
//                barAdditionalInformation,
//                kitchenAdditionalInformation
//        );
//
//        return new ResponseEntity<>(updatedCommand, HttpStatus.OK);
//    }


    @GetMapping("/{commandId}")
    public ResponseEntity<Command> getCommandById(@PathVariable Integer commandId) {
        Command command = commandService.findById(commandId);

        if (command == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(command, HttpStatus.OK);
    }


}






