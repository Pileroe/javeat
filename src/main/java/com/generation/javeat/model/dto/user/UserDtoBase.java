package com.generation.javeat.model.dto.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDtoBase 
{

    private String mail;
    private String password;
    private String phone;
    private Integer positionY;
    private Integer positionX;

}
