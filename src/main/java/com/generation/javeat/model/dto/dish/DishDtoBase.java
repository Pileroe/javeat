package com.generation.javeat.model.dto.dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DishDtoBase 
{

    private String name, category; 
    private double price; 
}
