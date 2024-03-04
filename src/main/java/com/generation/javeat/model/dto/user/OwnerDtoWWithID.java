package com.generation.javeat.model.dto.user;

import com.generation.javeat.model.dto.user.UserDtoBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OwnerDtoWWithID extends UserDtoBase {
    private Integer id;
}