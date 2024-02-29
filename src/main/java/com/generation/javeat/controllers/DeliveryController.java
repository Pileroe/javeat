package com.generation.javeat.controllers;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javeat.model.dto.delivery.DeliveryInstRqstDto;
import com.generation.javeat.model.dtoservices.DeliveryConverter;
import com.generation.javeat.model.entities.Delivery;
import com.generation.javeat.model.repositories.DeliveryRepository;

@RestController
public class DeliveryController 
{
    @Autowired
    DeliveryRepository repo;
    @Autowired
    DeliveryConverter conv;


    @GetMapping("/deliveries/{id}")
    public Delivery getOne(@PathVariable Integer id)
    {
        return repo.findById(id).get();
    }


    //Metodo per post Delivery
    @PostMapping("/deliveries")
    public ResponseEntity <?> insert(@RequestBody DeliveryInstRqstDto dto )
    {
        Delivery op = conv.deliveryDtoRqstDelivery(dto);
       
        return ResponseEntity.ok(repo.save(op));
        
    }
    
}
