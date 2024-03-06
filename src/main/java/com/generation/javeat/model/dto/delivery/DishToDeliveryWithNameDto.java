package com.generation.javeat.model.dto.delivery;

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
public class DishToDeliveryWithNameDto {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
}
