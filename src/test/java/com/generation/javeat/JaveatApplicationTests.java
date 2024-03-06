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
		Restaurant r= rRepo.findById(2).get();
		Owner o= Owner.builder()
				.mail("addio@gmail.com")
				.password("239393#*")
				.phone("33824444")
				.positionY(20)
				.positionX(20)
				.deliveries(null)
				.restaurant(r)
				.build();
		
		repo.save(o); 


		
	}

}
