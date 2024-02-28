package com.generation.javeat.model.dto.restaurant;
import java.util.List;

import com.generation.javeat.model.dto.dish.DishDtoW;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RestaurantDtoWMenu extends RestaurantDtoWNoDelivery
{
    private String phone; 
    private int openingHour, closingHour, maxDeliveryDistance;
    private List<DishDtoW> menu; 
}
