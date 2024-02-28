package com.generation.javeat.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/allrestaurants")
    public List<RestaurantDtoWNoDellivery> allrestaurants() {

        return rRepo.findAll().stream().map(e -> RConv.restaurantDtoWNoDellivery(e)).toList();
    }

    @PostMapping("/restaurants")
    public List<RestaurantDtoWNoDellivery> restaurant(@RequestBody FilteredRestaurantRqst dto) {
        List<Restaurant> filtratiDistanza = rRepo.findAll().stream()
                .filter(f -> calculateDistanceToRestaurant(f,dto.getPositionX(),dto.getPositionY())<dto.getDistance()).toList();

        return filtratiDistanza.stream().filter(f -> !Collections.disjoint(f.getFoodTypes(), dto.getFoodTypes()))
                .map(e -> RConv.restaurantDtoWNoDellivery(e)).toList();
    }

    public double calculateDistanceToRestaurant(Restaurant restaurant, Integer userX, Integer userY) {            
         // Coordinate del ristorante         
         int restaurantX = restaurant.getPositionX();         
         int restaurantY = restaurant.getPositionY();          
         // Calcola la differenza tra le coordinate x e y         
         int diffX = restaurantX - userX;         
         int diffY = restaurantY - userY;          
         // Calcola la somma dei quadrati delle differenze         
         int squaredDiffX = diffX * diffX;         
         int squaredDiffY = diffY * diffY;          
         // Calcola la radice quadrata della somma dei quadrati delle differenze         
         double distance = Math.sqrt(squaredDiffX + squaredDiffY);          
         return distance;     
    }
}
