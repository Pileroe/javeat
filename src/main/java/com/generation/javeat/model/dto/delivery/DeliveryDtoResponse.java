package com.generation.javeat.model.dto.delivery;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeliveryDtoResponse 
{
    private Integer orderId;
    private String restaurantName;
    private String paymentMethod;
    private String notes;
    private LocalDateTime expectedArrival; 
    private double riderRevenue;
    private double dishesPrice;
    private double totalPrice; 
    private List<DishToDeliveryWithNameDto> dishes;
}
