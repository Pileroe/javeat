package com.generation.javeat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javeat.model.dto.login.LoginRequest;
import com.generation.javeat.model.dto.restaurant.FilteredRestaurantRqst;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoWNoDellivery;
import com.generation.javeat.model.dto.user.UserDtoWWithID;
import com.generation.javeat.model.dtoservices.RestaurantConverter;
import com.generation.javeat.model.dtoservices.UserConverter;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.entities.User;
import com.generation.javeat.model.repositories.RestaurantRepository;
import com.generation.javeat.model.repositories.UserRepository;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    RestaurantConverter RConv;

    @PostMapping("/allrestaurants")
    public List<RestaurantDtoWNoDellivery> allrestaurants() {

        return rRepo.findAll().stream().map(e -> RConv.restaurantDtoWNoDellivery(e)).toList();
    }

    @PostMapping("/estaurants")
    public List<RestaurantDtoWNoDellivery> restaurant(@RequestBody FilteredRestaurantRqst dto) {

        return rRepo.findAll().stream().filter((f)->f.ggetFoodTypes()).map(e -> RConv.restaurantDtoWNoDellivery(e)).toList();


    }


}
