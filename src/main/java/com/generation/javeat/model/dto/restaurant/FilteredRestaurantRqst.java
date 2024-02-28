package com.generation.javeat.model.dto.restaurant;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FilteredRestaurantRqst {
    private List<String> foodTypes;
    private int positionX; 
    private int positionY; 
}