package com.generation.javeat.model.dto.delivery;
import java.util.List;

import com.generation.javeat.model.entities.DishToDelivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeliveryDtoWithDelivery 
{
    private Integer idRestaurant;
    private Integer idUser;
    private List<DishToDelivery> dishToDelivery;

}
