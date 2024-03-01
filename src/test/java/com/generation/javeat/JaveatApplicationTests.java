package com.generation.javeat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.javeat.model.entities.Owner;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.repositories.OwnerRepository;
import com.generation.javeat.model.repositories.RestaurantRepository;

@SpringBootTest
class JaveatApplicationTests 
{

	@Autowired
	OwnerRepository repo; 
	@Autowired
	RestaurantRepository rRepo; 

	@Test
	void contextLoads() 
	{
		Restaurant r= rRepo.findById(1).get();
		Owner o= Owner.builder()
				.mail("ciao@gmail.com")
				.password("23456789#*")
				.phone("32544444")
				.positionY(50)
				.positionX(50)
				.deliveries(null)
				.restaurant(r)
				.build();
		
		repo.save(o); 


		
	}

}
