package com.generation.javeat.model.dtoservices;

import com.generation.javeat.model.dto.register.RegisterRequest;
import com.generation.javeat.model.entities.User;

public class UserConverter {

    public User RegisterToUser(RegisterRequest dto) {

        return User
                .builder()
                .mail(dto.getMail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .positionX(dto.getPositionX())
                .positionY(dto.getPositionY())
                .build();
    }
}
