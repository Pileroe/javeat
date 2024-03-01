package com.generation.javeat.model.dto.delivery;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeliveryInstRqstDto 
{
    private Integer idRestaurant;
    private Integer idUser;
    private String expected_arrival;
    private String paymentMethod;
    private String notes;
    private Map<Integer, Integer> dishes;
}
