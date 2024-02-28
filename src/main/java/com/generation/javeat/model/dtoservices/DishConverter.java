package com.generation.javeat.model.dtoservices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.javeat.model.dto.dish.DishDtoW;
import com.generation.javeat.model.entities.Dish;
import com.generation.javeat.model.entities.Menu;
import com.generation.javeat.model.repositories.DishRepository;

@Service
public class DishConverter 
{
    @Autowired
    DishRepository dRepo; 

    public DishDtoW dishDtoW (Dish d)
    {
        return DishDtoW
                .builder()
                .name(d.getName())
                .category(d.getCategory())
                .price(d.getPrice())
                .id(d.getId())
                .ingredients(d.getIngredients())
                .build(); 

    }

    public List<DishDtoW> menuToDishDtoList(Menu menu) 
    {
        return menu.getDishes()
                .stream()
                .map(d-> dishDtoW(d))
                .collect(Collectors.toList());

    }

    // public DishToDelivery disheToDto(Dish menu) 
    // {
    //     return menu.getDishes()
    //             .stream()
    //             .map(d-> dishDtoW(d))
    //             .collect(Collectors.toList());

    // }

}
