package com.generation.javeat.model.dtoservices;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.javeat.model.dto.restaurant.RestaurantDtoBase;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoWMenu;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoWNoDelivery;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.repositories.RestaurantRepository;

@Service
public class RestaurantConverter 
{
    @Autowired
    RestaurantRepository rRepo; 


    public RestaurantDtoBase restaurantDtoBase(Restaurant r)
    {
        return RestaurantDtoBase
                .builder()
                .name(r.getName())
                .foodTypes(r.getFoodTypes())
                .positionX(r.getPositionX())
                .positionY(r.getPositionY())
                .imgUrl(r.getImgUrl())
                .build(); 
    }


    public RestaurantDtoWNoDelivery restaurantDtoWNoDellivery(Restaurant r)
    {
        boolean isOpen =isOpen(r);
        return RestaurantDtoWNoDelivery
                .builder()
                .name(r.getName())
                .foodTypes(r.getFoodTypes())
                .positionX(r.getPositionX())
                .positionY(r.getPositionY())
                .imgUrl(r.getImgUrl())
                .isOpen(isOpen)
                .id(r.getId())
                
                .build();
    }

    public RestaurantDtoWMenu restaurantDtoWMenu(Restaurant r)
    {
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
                .menuWithDish(r.getMenu())
                .build();
    }

    public boolean isOpen(Restaurant r) 
        {
       
            LocalTime currentTime = LocalTime.now();
                    
            if (r.getOpeningHour()==null|| r.getClosingHour() == null) 
                return false; 
        
            if (r.getOpeningHour() >= r.getClosingHour()) 
                return currentTime.isAfter(LocalTime.of(r.getOpeningHour(), 0)) || currentTime.isBefore(LocalTime.of(r.getClosingHour(), 0));
           else 
                return currentTime.isAfter(LocalTime.of(r.getOpeningHour(), 0)) && currentTime.isBefore(LocalTime.of(r.getClosingHour(), 0));
            
        }
}

      
