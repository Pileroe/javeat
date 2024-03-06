package com.generation.javeat.model.dtoservices;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoBase;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoR;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoWMenu;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoWNoDelivery;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.repositories.MenuRepository;
import com.generation.javeat.model.repositories.OwnerRepository;
import com.generation.javeat.model.repositories.RestaurantRepository;

@Service
public class RestaurantConverter {
    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    DishConverter dConv;

    @Autowired
    OwnerRepository oRepo;

    @Autowired
    MenuRepository mRepo;

    public RestaurantDtoBase restaurantDtoBase(Restaurant r) {
        return RestaurantDtoBase
                .builder()
                .name(r.getName())
                .foodTypes(r.getFoodTypes())
                .positionX(r.getPositionX())
                .positionY(r.getPositionY())
                .imgUrl(r.getImgUrl())
                .build();
    }

    public Restaurant putRestaurantFromDto(RestaurantDtoR dto) {
        return Restaurant
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .foodTypes(dto.getFoodTypes())
                .positionX(dto.getPositionX())
                .positionY(dto.getPositionY())
                .imgUrl(dto.getImgUrl())
                .phone(dto.getPhone())
                .owner(rRepo.findById(dto.getId()).get().getOwner())
                .menu(rRepo.findById(dto.getId()).get().getMenu())
                .openingHour(dto.getOpeningHour())
                .closingHour(dto.getClosingHour())
                .maxDeliveryDistance(dto.getMaxDeliveryDistance())
                .deliveryPricePerUnit(dto.getDeliveryPricePerUnit())
                .build();
    }

    public RestaurantDtoWNoDelivery restaurantDtoWNoDelivery(Restaurant r) {
        if (r == null) {
            throw new IllegalArgumentException("Il parametro 'r' non può essere null");
        }

        boolean isOpen = isOpen(r);
        String name = r.getName() != null ? r.getName() : "Nome non disponibile";
        List<String> foodTypes = r.getFoodTypes() != null ? r.getFoodTypes() : Collections.emptyList();
        String imgUrl = r.getImgUrl() != null ? r.getImgUrl() : "URL immagine non disponibile";

        // Gestisci i casi in cui positionX e positionY possano essere null.
        // Ad esempio, puoi usare 0 come valore di default oppure un valore specifico
        // che indichi "non disponibile".
        // Integer positionX = r.getPositionX() != null ? r.getPositionX() : -1; // -1 o un altro valore a tua scelta
        // Integer positionY = r.getPositionY() != null ? r.getPositionY() : -1; // -1 o un altro valore a tua scelta

        return RestaurantDtoWNoDelivery.builder()
                .name(name)
                .foodTypes(foodTypes)
                .positionX(r.getPositionX())
                .positionY(r.getPositionY())
                .imgUrl(imgUrl)
                .isOpen(isOpen)
                .id(r.getId()) // Gestisci anche qui il caso null se necessario
                .build();
    }

    public RestaurantDtoWMenu restaurantDtoWMenu(Restaurant r) {
        return RestaurantDtoWMenu
                .builder()
                .name(r.getName())
                .foodTypes(r.getFoodTypes())
                .positionX(r.getPositionX())
                .positionY(r.getPositionY())
                .imgUrl(r.getImgUrl())
                .id(r.getId())
                .phone(r.getPhone())
                .openingHour(r.getOpeningHour())
                .closingHour(r.getClosingHour())
                .maxDeliveryDistance(r.getMaxDeliveryDistance())
                .menu(rRepo.findById(r.getId()).get().getMenu().getDishes().stream().map(d -> dConv.dishDtoW(d))
                        .toList())
                .build();
    }

    public boolean isOpen(Restaurant r) {
        LocalTime currentTime = LocalTime.now();

        if (r.getOpeningHour() == 0 || r.getClosingHour() == 0)
            return false;

        if (r.getOpeningHour() >= r.getClosingHour())
            return currentTime.isAfter(LocalTime.of(r.getOpeningHour(), 0))
                    || currentTime.isBefore(LocalTime.of(r.getClosingHour(), 0));
        else
            return currentTime.isAfter(LocalTime.of(r.getOpeningHour(), 0))
                    && currentTime.isBefore(LocalTime.of(r.getClosingHour(), 0));

    }

}
