package com.generation.javeat.model.dtoservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.javeat.model.dto.delivery.DelveryInstRqstDto;
import com.generation.javeat.model.dto.restaurant.RestaurantDtoWNoDellivery;
import com.generation.javeat.model.dto.user.UserDtoR;
import com.generation.javeat.model.entities.Delivery;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.entities.User;
import com.generation.javeat.model.repositories.DeliveryRepository;
import com.generation.javeat.model.repositories.RestaurantRepository;
import com.generation.javeat.model.repositories.UserRepository;

import static  com.generation.javeat.utils.Utils.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryConverte 
{
    @Autowired
    DeliveryRepository dRepo;

    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    UserRepository uRepo;

    // private Integer idRestaurant;
    // private Integer idUser;
    // private LocalDateTime expected_arrival;
    // private String paymentMethod;
    // private String notes;
    // private List<Integer> dishesId;

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

    public User dtoRToUser(UserDtoR dto) {

        return User
                .builder()
                .mail(dto.getMail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .positionX(dto.getPositionX())
                .positionY(dto.getPositionY())
                .build();
    }

}
