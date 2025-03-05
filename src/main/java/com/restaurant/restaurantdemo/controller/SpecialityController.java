package com.restaurant.restaurantdemo.controller;

import com.restaurant.restaurantdemo.dto.MenuItemDTO;
import com.restaurant.restaurantdemo.dto.SpecialityDTO;
import com.restaurant.restaurantdemo.model.Speciality;
import com.restaurant.restaurantdemo.model.SpecialityClass;
import com.restaurant.restaurantdemo.repository.SpecialityRepository;
import com.restaurant.restaurantdemo.service.SpecialityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/speciality")
public class SpecialityController {

    @Autowired
    private SpecialityServiceImpl specialityServiceImpl;
    @Autowired
    private SpecialityRepository specialityRepository;

    @GetMapping("/allSpeciality")
    public List<SpecialityDTO> specialityList(){
        List<Speciality> specialities = specialityRepository.findAll();
        return specialities.stream().map(s -> {
            SpecialityDTO dto = new SpecialityDTO();
            dto.setId(s.getId());
            dto.setName(s.getName());
            return dto;
        }).collect(Collectors.toList());
    }

//    @GetMapping("/allSpecialityWithMenuItems")
//    public List<SpecialityDTO> specialityDTOS(){
//        List<Speciality> specialities =specialityRepository.findAll().stream().toList();
//    }

    @GetMapping ("/allSpecialityClass")
    public List<SpecialityClass> specialityClassList(){
        return specialityServiceImpl.specialityClassList();
    }

    @GetMapping("/allSpecialityByClassId")
    public List<Speciality> specialityByClassId(Integer classId){
        return specialityServiceImpl.listSpecialityByClassId(classId);
    }



    @PostMapping("/submitCreateSpeciality")
    public ResponseEntity<String> createSpeciality(@RequestBody Speciality speciality) {
        try {
            specialityServiceImpl.createSpeciality(speciality);
            return ResponseEntity.ok("Speciality created successfully");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating speciality");
        }
    }

    @PutMapping("/updateSpeciality/{specialityId}")
    public ResponseEntity<String> updateSpeciality(
            @PathVariable Integer specialityId,
            @RequestParam String newName
    ) {
        try {
            Optional<Speciality> optionalSpeciality = specialityServiceImpl.findById(specialityId);

            if (optionalSpeciality.isPresent()) {
                specialityServiceImpl.updateSpeciality(specialityId,newName);
                return ResponseEntity.ok("Speciality updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating speciality");
        }
    }
    @DeleteMapping("/deleteSpeciality/{specialityId}")
    public ResponseEntity<Void> deleteSpeciality(@PathVariable Integer specialityId) {
        try {
            specialityServiceImpl.deleteSpeciality(specialityId);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}


