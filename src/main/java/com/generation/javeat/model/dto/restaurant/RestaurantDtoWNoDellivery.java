package com.generation.javeat.model.dto.restaurant;
import com.generation.javeat.model.entities.Menu;
import com.generation.javeat.model.entities.Restaurant;

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
public class RestaurantDtoWNoDellivery extends RestaurantDtoBase
{
   private Integer id; 
   private boolean isOpen; 
   
   
   
   
  

    

}
