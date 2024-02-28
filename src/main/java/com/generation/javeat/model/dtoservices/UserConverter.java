package com.generation.javeat.model.dtoservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.javeat.model.dto.register.RegisterRequest;
import com.generation.javeat.model.dto.user.UserDtoBase;
import com.generation.javeat.model.dto.user.UserDtoR;
import com.generation.javeat.model.dto.user.UserDtoWWithID;
import com.generation.javeat.model.entities.User;
import com.generation.javeat.model.repositories.UserRepository;

@Service
public class UserConverter 
{
    @Autowired
    UserRepository uRepo;

    public User dtoRToUser(UserDtoR dto) {

        return User
                .builder()
                .mail(dto.getMail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .positionX(dto.getPositionX())
                .positionY(dto.getPositionY())
                .build();
    }

    public UserDtoBase userToDtoBase (User u) {

        return UserDtoBase
                .builder()
                .mail(u.getMail())
                .password(u.getPassword())
                .phone(u.getPhone())
                .positionX(u.getPositionX())
                .positionY(u.getPositionY())
                .build();
    }

    public UserDtoWWithID userToDtoWID (User u) {

        return UserDtoWWithID
                .builder()
                .id(u.getId())
                .mail(u.getMail())
                .password(u.getPassword())
                .phone(u.getPhone())
                .positionX(u.getPositionX())
                .positionY(u.getPositionY())
                .build();
    }

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
