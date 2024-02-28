package com.generation.javeat.model.dto.restaurant;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RestaurantDtoBase 
{
    private String name; 
    private List<String> foodTypes;
    private int positionX; 
    private int positionY; 
    private String imgUrl;
   
}
