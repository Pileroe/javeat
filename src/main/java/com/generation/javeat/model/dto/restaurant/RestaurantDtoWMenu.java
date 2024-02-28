package com.generation.javeat.model.dto.restaurant;

import com.generation.javeat.model.entities.Menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RestaurantDtoWMenu extends RestaurantDtoWNoDellivery
{
    private String phone; 
    private int openingHour, closingHour, maxDeliveryDistance;
    private Menu menuWithDish;
}
