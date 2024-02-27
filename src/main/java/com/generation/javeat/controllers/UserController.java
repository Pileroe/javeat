package com.generation.javeat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.generation.javeat.model.dto.register.RegisterRequest;
import com.generation.javeat.model.dtoservices.UserConverter;
import com.generation.javeat.model.entities.User;
import com.generation.javeat.model.repositories.UserRepository;

public class UserController {


    @Autowired
    UserConverter uConv;

    @Autowired
    UserRepository uRepo;

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest dto) {

        User q = uConv.RegisterToUser(dto);
        uRepo.save(q);
        
        if (rankParty >= rankQuest) {
            q.setStatus("PENDING");
            return new ResponseEntity<Quest>(qRepo.save(q), HttpStatus.OK);
        }

        else
            return new ResponseEntity<String>("Rank incompatibile", HttpStatus.BAD_REQUEST);

    }
}
