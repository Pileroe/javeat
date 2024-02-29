package com.generation.javeat.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javeat.model.dto.delivery.DeliveryDtoResponse;
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

    @GetMapping("/myorders/{id}")
    public List<DeliveryDtoResponse> getAll(@PathVariable Integer id)
    {
       return repo.findAll().stream().filter(e->e.getUser().getId()==id).map((f-> conv.deliveryDtoResponse(f))).toList();
    }


    //Metodo per post Delivery
    @PostMapping("/deliveries")
    public ResponseEntity <?> insert(@RequestBody DeliveryInstRqstDto dto )
    {
        Delivery op = conv.deliveryDtoRqstDelivery(dto);
       
        return ResponseEntity.ok(repo.save(op));
        
    }

    @DeleteMapping("/deliveries/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        Optional<Delivery> op= repo.findById(id);
        if(op.isPresent())
        {
            repo.deleteById(id);
            return new ResponseEntity<String>("", HttpStatus.OK);
        }
        else 
            return new ResponseEntity<String>("No delivery with id"+id, HttpStatus.NOT_FOUND);


    }
    
}
