package com.generation.javeat.model.dto.restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RestaurantDtoR extends RestaurantDtoBase
{
    private Integer id;
    private String phone;
    private Integer openingHour, closingHour, maxDeliveryDistance;
    private double deliveryPricePerUnit;
}
