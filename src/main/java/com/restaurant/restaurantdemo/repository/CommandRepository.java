package com.restaurant.restaurantdemo.repository;

import com.restaurant.restaurantdemo.model.Command;
import com.restaurant.restaurantdemo.model.CommandStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command,Integer> {
    List<Command> findByTableId(Integer tableId);
    List<Command> findByTableIdAndStatus(Integer tableId, CommandStatus status);


}
