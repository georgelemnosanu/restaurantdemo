package com.restaurant.restaurantdemo.service;

import com.restaurant.restaurantdemo.model.Table;
import com.restaurant.restaurantdemo.repository.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableServiceImpl {

    @Autowired
    private final TableRepository tableRepository;



  public List<Table> findAll(){
        return tableRepository.findAll();
  }

  public Table findbyId(Integer tableId){
      return tableRepository.findById(tableId)
              .orElseThrow(()-> new EntityNotFoundException("Table Not Found!"));
  }
  }


