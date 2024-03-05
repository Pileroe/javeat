package com.generation.javeat.controllers;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.generation.javeat.model.dtoservices.DishConverter;
import com.generation.javeat.model.entities.Dish;
import com.generation.javeat.model.entities.Menu;
import com.generation.javeat.model.entities.Owner;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.repositories.DishRepository;
import com.generation.javeat.model.repositories.MenuRepository;
import com.generation.javeat.model.repositories.OwnerRepository;
import com.generation.javeat.model.repositories.RestaurantRepository;

@RestController
public class DishController 
{
    @Autowired
    DishRepository dRepo;

    @Autowired
    DishConverter dConv; 

    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    MenuRepository mRepo;

    @Autowired
    OwnerRepository oRepo;

    @GetMapping("/dishes")
    public List<Dish> getAllDishes()
    {
        return dRepo.findAll();
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<?> getAllDishesById(@PathVariable Integer id) 
    {
        Optional<Restaurant> op = rRepo.findById(id);
        if (op.isPresent()) 
        {
            Restaurant restaurant = op.get();
            Menu menu = restaurant.getMenu();
            Set<Dish> dishes = menu.getDishes();
            return new ResponseEntity<>(dishes, HttpStatus.OK);
        } 
        else 
            return new ResponseEntity<>("No restaurant with id " + id, HttpStatus.NOT_FOUND);
    }
    

    @PostMapping("/dishes/{id}")
    public ResponseEntity<?> insertNewDish(@PathVariable Integer id, @RequestBody Dish dish) 
    {
        Optional<Restaurant> op = oRepo.findById(id).map(Owner::getRestaurant);
        if (op.isPresent()) 
        {
            Restaurant restaurant = op.get();
            Menu menu = restaurant.getMenu();
        
            dish.setMenu(menu);
            Dish savedDish = dRepo.save(dish);
            
            menu.getDishes().add(savedDish);
            mRepo.save(menu);
            
            return new ResponseEntity<Dish>(savedDish, HttpStatus.CREATED);
        } 
        else
            return new ResponseEntity<String>("No restaurant with id " + id, HttpStatus.NOT_FOUND);

    }

     @PutMapping("/dishes/{id}")
     public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Dish updatedDish) 
     {
         Optional<Dish> op = dRepo.findById(id);
         if (op.isPresent()) 
         {
             Dish existingDish = op.get();
           
            
             existingDish.setName(updatedDish.getName());
             existingDish.setCategory(updatedDish.getCategory());
             existingDish.setPrice(updatedDish.getPrice());
             existingDish.setIngredients(updatedDish.getIngredients());
            
             Dish savedDish = dRepo.save(existingDish);
            
             return new ResponseEntity<Dish>(savedDish, HttpStatus.OK);
         } 
         else 
             return new ResponseEntity<String>("No dish with id " + id, HttpStatus.NOT_FOUND);
        
     }

    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) 
    {
        Optional<Dish> op = dRepo.findById(id);
        if (op.isPresent()) 
        {
            dRepo.deleteById(id);
            return new ResponseEntity<String>("Dish deleted", HttpStatus.OK);
        } 
        else 
            return new ResponseEntity<String>("No dish with id " + id, HttpStatus.NOT_FOUND);
        
    }


}
