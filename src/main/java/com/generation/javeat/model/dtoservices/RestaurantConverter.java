package com.generation.javeat.model.dtoservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.javeat.model.dto.restaurant.RestaurantDtoBase;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoWNoDellivery;
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
                .deliveryPricePerUnit(r.getDeliveryPricePerUnit())
                .imgUrl(r.getImgUrl())
                .build(); 
    }


    public RestaurantDtoWNoDellivery restaurantDtoWNoDellivery(Restaurant r)
    {
        return RestaurantDtoWNoDellivery
                .builder()
                .name(r.getName())
                .foodTypes(r.getFoodTypes())
                .positionX(r.getPositionX())
                .positionY(r.getPositionY())
                .deliveryPricePerUnit(r.getDeliveryPricePerUnit())
                .imgUrl(r.getImgUrl())
                .id(r.getId())
                .build();
    }

}
