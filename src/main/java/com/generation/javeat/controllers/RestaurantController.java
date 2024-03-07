package com.generation.javeat.controllers;

import static com.generation.javeat.utils.Utils.calculateDistanceToRestaurant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    RestaurantConverter RConv;

    @Autowired
    OwnerRepository oRepo;

    @Operation(description = "Leggo tutti i Ristoranti nel DB")
    @GetMapping("/allrestaurants")
    public List<RestaurantDtoWNoDelivery> allrestaurants() {

        return rRepo.findAll().stream().map(e -> (RestaurantDtoWNoDelivery) RConv.restaurantDtoWNoDelivery(e))
                .collect(Collectors.toList());

    }

    @Operation(description = "Leggo un Ristorante nel DB tramite ID dell' Owner")
    @GetMapping("/restaurantowner/{id}")
    public RestaurantDtoWMenu detailrestaurantowner(@PathVariable Integer id) {
        // return RConv.restaurantDtoWMenu(rRepo.findById(id).get());
        return RConv.restaurantDtoWMenu(oRepo.findById(id).get().getRestaurant());
    }

    @Operation(description = "Leggo tutti i FoodTypes dei Ristoranti nel DB")
    @GetMapping("/foodtypes")
    public List<String> foodtypes() {
        return rRepo.findAll().stream().flatMap(restaurant -> restaurant.getFoodTypes().stream()).distinct()
                .collect(Collectors.toList());
    }

    @Operation(description = "Leggo un Ristorante nel DB tramite ID")
    @GetMapping("/restaurants/{id}")
    public RestaurantDtoWMenu detailrestaurants(@PathVariable Integer id) {
        // return RConv.restaurantDtoWMenu(rRepo.findById(id).get());
        return RConv.restaurantDtoWMenu(rRepo.findById(id).get());
    }

    @Operation(description = "Inserisco un Ristorante nel DB")
    @PostMapping("/restaurants")
    public List<RestaurantDtoWNoDelivery> restaurant(@RequestBody FilteredRestaurantRqst dto) {
        Stream<Restaurant> restaurantStream = rRepo.findAll().stream();

        // Filtra per distanza solo se le posizioni sono definite
        if (dto.getPositionX() != null && dto.getPositionY() != null) {
            restaurantStream = restaurantStream.filter(f -> calculateDistanceToRestaurant(f, dto.getPositionX(), dto.getPositionY()) <= dto.getDistance());
        }

        return restaurantStream
                .filter(f -> dto.getFoodTypes().isEmpty()
                        || !Collections.disjoint(f.getFoodTypes(), dto.getFoodTypes()))
                .map(e -> (RestaurantDtoWNoDelivery) RConv.restaurantDtoWNoDelivery(e)) // Cast esplicito, se
                                                                                        // necessario
                .collect(Collectors.toList()); // Usa questo se `toList()` non è disponibile
    }

    @Operation(description = "Aggiorno un Ristorante nel DB")
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
