package com.restaurant.restaurantdemo.service;


import com.restaurant.restaurantdemo.model.*;
import com.restaurant.restaurantdemo.repository.CommandRepository;
import com.restaurant.restaurantdemo.repository.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Command> commandListKitchen() {
        List<Command> kitchenCommands = new ArrayList<>();

        return kitchenCommands;
    }
    public List<Command> commandListBar(){
        List<Command> barCommandList = new ArrayList<>();

        for(Command command : commandRepository.findAll()){
            for(CommandMenuItem commandMenuItem : command.getMenuItemsWithQuantities()){
                MenuItem menuItem = commandMenuItem.getMenuItem();
                Speciality speciality = menuItem.getSpeciality();
                if(speciality.getSpecialityClass().getId() == 1){
                    barCommandList.add(command);
                    break;
                }
            }
        }
        return barCommandList;
    }


    @Transactional
    public Command createCommandWithMenuItems(Integer tableId, Map<Integer, Integer> menuItemsWithQuantities,String barAdditionalInformation, String kitchenAdditionalInformation) {
        Table table = tableService.findbyId(tableId);

        if (table == null) {
            throw new RuntimeException("Table not found with ID: " + tableId);
        }

        Command command = new Command();
        command.setTable(table);
        command.setBarAdditionalInformation(barAdditionalInformation);
        command.setKitchenAdditionalInformation(kitchenAdditionalInformation);

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

    public Command editCommandWithMenuItems(Integer commandid, Map<Integer, Integer> menuItemsWithQuantities,String barAdditionalInformation, String kitchenAdditionalInformation) {
        Command existingCommand = commandRepository.findById(commandid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Command not found"));
        existingCommand.setBarAdditionalInformation(barAdditionalInformation);
        existingCommand.setKitchenAdditionalInformation(kitchenAdditionalInformation);

        for (Map.Entry<Integer, Integer> entry : menuItemsWithQuantities.entrySet()) {
            Integer menuItemId = entry.getKey();
            Integer quantity = entry.getValue();
            MenuItem menuItem = menuItemService.findbyId(menuItemId);

            if (menuItem != null) {
                CommandMenuItem commandMenuItem = new CommandMenuItem(existingCommand, menuItem, quantity);
                existingCommand.getMenuItemsWithQuantities().add(commandMenuItem);
            }
        }

        return commandRepository.save(existingCommand);
    }


    }

