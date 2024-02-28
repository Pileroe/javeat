package com.generation.javeat.model.dto.delivery;

import java.time.LocalDateTime;
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
public class DeliveryDtoBase 
{
    private LocalDateTime expected_arrival;
    private int distance;
    private String paymentMethod;
    private String notes;

}
