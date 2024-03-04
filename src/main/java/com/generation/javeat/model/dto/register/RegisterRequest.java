package com.generation.javeat.model.dto.register;
import com.generation.javeat.model.entities.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RegisterRequest 
{
    private String mail,password,phone;
    private Integer  positionX, positionY;
    private boolean owner;
    
}
