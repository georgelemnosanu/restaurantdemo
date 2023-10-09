package com.restaurant.restaurantdemo.service;


import com.restaurant.restaurantdemo.model.Command;
import com.restaurant.restaurantdemo.model.CommandMenuItem;
import com.restaurant.restaurantdemo.model.MenuItem;
import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.repository.CommandRepository;
import com.restaurant.restaurantdemo.repository.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandService {

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private TableServiceImpl tableService;

    @Autowired
    private MenuItemServiceImpl menuItemService;



    public List<Command> commandList(){
        return commandRepository.findAll();
    }

    public Command findById(Integer commandId){
        return commandRepository.findById(commandId)
                .orElseThrow(()-> new EntityNotFoundException("Command Not Found!"));
    }


//    @Transactional
//    public Command addMenuItemsWithQuantities(Command command, Map<MenuItem, Integer> menuItemsWithQuantities) {
//        if (command.getId() == null) {
//            command = commandRepository.save(command);
//        }
//        Command finalCommand = command;
//        menuItemsWithQuantities.forEach((menuItem, quantity) -> {
//            CommandMenuItem commandMenuItem = new CommandMenuItem(finalCommand, menuItem, quantity);
//            finalCommand.getMenuItemsWithQuantities().add(commandMenuItem);
//        });
//
//        return commandRepository.save(command);
//    }


    @Transactional
    public Command createCommandWithMenuItems(Integer tableId, Map<Integer, Integer> menuItemsWithQuantities) {
        // Fetch the table by its ID
        Table table = tableService.findbyId(tableId);

        if (table == null) {
            throw new RuntimeException("Table not found with ID: " + tableId);
        }

        // Create a new Command
        Command command = new Command();
        command.setTable(table);

        // Create CommandMenuItem entities for menu items with quantities
        for (Map.Entry<Integer, Integer> entry : menuItemsWithQuantities.entrySet()) {
            Integer menuItemId = entry.getKey();
            Integer quantity = entry.getValue();
            MenuItem menuItem = menuItemService.findbyId(menuItemId);

            if (menuItem != null) {
                CommandMenuItem commandMenuItem = new CommandMenuItem(command, menuItem, quantity);
                command.getMenuItemsWithQuantities().add(commandMenuItem);
            }
        }

        return commandRepository.save(command);
    }

    private Command getCommand(Integer commandId) {
       Command command;
        Optional<Command> commandFound = commandRepository.findById(commandId);
        if (!commandFound.isPresent()) {
            command = new Command();
            command.setId(commandId);
            command = commandRepository.save(command);
        } else {
           command = commandFound.get();
        }
        return command;
    }


    public Command createComand(Command command){
        return commandRepository.save(command);
    }
}
