package com.generation.javeat.model.dtoservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javeat.model.dto.delivery.DelveryInstRqstDto;
import com.generation.javeat.model.entities.Delivery;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.entities.User;
import com.generation.javeat.model.repositories.DeliveryRepository;
import com.generation.javeat.model.repositories.RestaurantRepository;
import com.generation.javeat.model.repositories.UserRepository;

import static  com.generation.javeat.utils.Utils.*;

@Service
public class DeliveryConverte 
{
    @Autowired
    DeliveryRepository dRepo;

    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    UserRepository uRepo;

    public Delivery deliveryDtoRqstDelivery(DelveryInstRqstDto del)
    {
        User user = uRepo.findById(del.getIdUser()).get();
        Restaurant restaurant = rRepo.findById(del.getIdRestaurant()).get();

        return Delivery
                .builder()
                .expected_arrival(del.getExpected_arrival())//!!!!!!!!!!!!!!!!!!!!!!
                .distance(calculateDistanceToRestaurant(restaurant,user.getPositionX(),user.getPositionY()))
                .paymentMethod(del.getPaymentMethod())
                .notes(del.getNotes())
                .user(user)
                .restaurant(restaurant)
                .build();

    }
}
