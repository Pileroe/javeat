package com.generation.javeat.model.dto.dish;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DishDtoW extends DishDtoBase
{
    private Integer id; 
    private List<String> ingredients;


}
