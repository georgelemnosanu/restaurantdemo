package com.restaurant.restaurantdemo.service;

import com.restaurant.restaurantdemo.model.Speciality;
import com.restaurant.restaurantdemo.model.SpecialityClass;
import com.restaurant.restaurantdemo.repository.SpecialityClassRepository;
import com.restaurant.restaurantdemo.repository.SpecialityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl {
    private final SpecialityRepository specialityRepository;
    private final SpecialityClassRepository specialityClassRepository;

    public Speciality createSpeciality(Speciality speciality){
        return specialityRepository.save(speciality);
    }

    public void deleteSpeciality(Integer specialityId) {
        specialityRepository.deleteById(specialityId);
    }

    public List<Speciality> specialityList(){
      return  specialityRepository.findAll();
    }

    public List<SpecialityClass> specialityClassList(){
        return specialityClassRepository.findAll();
    }

    public Speciality updateSpeciality(Integer specialityId, String newName) {
        Speciality existingSpeciality = specialityRepository.findById(specialityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Speciality not found"));
        existingSpeciality.setName(newName);
        return specialityRepository.save(existingSpeciality);
    }

    public Optional<Speciality> findById(Integer specialityId) {
        return specialityRepository.findById(specialityId);
    }
}
