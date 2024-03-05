package com.generation.javeat.controllers;

import static com.generation.javeat.utils.Utils.calculateDistanceToRestaurant;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.generation.javeat.model.dto.restaurant.FilteredRestaurantRqst;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoR;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoWMenu;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoWNoDelivery;
import com.generation.javeat.model.dtoservices.RestaurantConverter;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.repositories.OwnerRepository;
import com.generation.javeat.model.repositories.RestaurantRepository;
import com.generation.javeat.model.repositories.UserRepository;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    RestaurantConverter RConv;

    @Autowired
    OwnerRepository oRepo;

    @GetMapping("/allrestaurants")
    public List<RestaurantDtoWNoDelivery> allrestaurants() {

        return rRepo.findAll().stream().map(e -> RConv.restaurantDtoWNoDellivery(e)).toList();
    }

    @GetMapping("/restaurants/{id}")
    public RestaurantDtoWMenu detailrestaurants(@PathVariable Integer id) {
        // return RConv.restaurantDtoWMenu(rRepo.findById(id).get());
        return RConv.restaurantDtoWMenu(oRepo.findById(id).get().getRestaurant());
    }

    @PostMapping("/restaurants")
    public List<RestaurantDtoWNoDelivery> restaurant(@RequestBody FilteredRestaurantRqst dto) {
        List<Restaurant> filtratiDistanza = rRepo.findAll().stream()
                .filter(f -> calculateDistanceToRestaurant(f, dto.getPositionX(), dto.getPositionY()) <= dto
                        .getDistance())
                .toList();

        return filtratiDistanza.stream()
                .filter(f -> dto.getFoodTypes().isEmpty()
                        || !Collections.disjoint(f.getFoodTypes(), dto.getFoodTypes()))
                .map(e -> RConv.restaurantDtoWNoDellivery(e))
                .toList();
    }

    @RestController
    public class RistoranteController {

        @PutMapping("/ristoranti")
        public ResponseEntity<?> aggiornaRistorante(@RequestBody RestaurantDtoR entita) {
            try {
                Restaurant r = RConv.putRestaurantFromDto(entita);
                return new ResponseEntity<Restaurant>(rRepo.save(r), HttpStatus.OK);
            } catch (Exception e) {
                // Gestione dell'errore
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
