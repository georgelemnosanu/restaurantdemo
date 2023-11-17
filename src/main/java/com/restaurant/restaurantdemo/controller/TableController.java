package com.restaurant.restaurantdemo.controller;


import com.restaurant.restaurantdemo.model.Menu;
import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.repository.TableRepository;
import com.restaurant.restaurantdemo.service.TableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableServiceImpl tableService;

    @Autowired
    private TableRepository tableRepository;

    @GetMapping("/{tableId}")
    public ResponseEntity<Table> getTableById(@PathVariable Integer tableId) {
        Table table = tableService.findbyId(tableId);

        if (table == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @GetMapping("/allTables")
    public List<Table>getAllTable(){
        return tableService.findAll();
    }


    @PostMapping("/createTable")
    public ResponseEntity<String> createTable(@RequestBody Table table){
        try {
            tableService.createTable(table);
            return ResponseEntity.ok("Table created successfully");
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Creating Table");
        }
    }

    @PutMapping("/editTable/{tableId}")
    public ResponseEntity<String> updateMenu(@PathVariable Integer tableId, @RequestParam String newName){
       Table table = tableService.findbyId(tableId);
        table.setTableName(newName);
        tableRepository.save(table);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The table was edited");
    }

}
