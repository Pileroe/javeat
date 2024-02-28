package com.generation.javeat.model.dtoservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.javeat.model.dto.delivery.DeliveryInstRqstDto;
import com.generation.javeat.model.entities.Delivery;
import com.generation.javeat.model.entities.Dish;
import com.generation.javeat.model.entities.DishToDelivery;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.entities.User;
import com.generation.javeat.model.repositories.DeliveryRepository;
import com.generation.javeat.model.repositories.DishRepository;
import com.generation.javeat.model.repositories.RestaurantRepository;
import com.generation.javeat.model.repositories.UserRepository;

import static  com.generation.javeat.utils.Utils.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Service
public class DeliveryConverter 
{
    @Autowired
    DeliveryRepository dRepo;

    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    DishRepository repoD;

    @Autowired
    UserRepository uRepo;

    // private Integer idRestaurant;
    // private Integer idUser;
    // private LocalDateTime expected_arrival;
    // private String paymentMethod;
    // private String notes;
    // private List<Integer> dishesId;

    public Delivery deliveryDtoRqstDelivery(DeliveryInstRqstDto del)
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
                .dishesDeliveries(null)
                .build();

    }

    public Set<DishToDelivery> dishesByIds(List<Integer> num)
    {
        Set<DishToDelivery> newSet = new HashSet<DishToDelivery>();

        for(Integer n : num)
        {
            // newSet.add(repoD.findById(n).get());
        }

        return newSet;
    }

   
}
