package com.generation.javeat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.generation.javeat.model.dto.login.LoginRequest;
import com.generation.javeat.model.dto.register.RegisterRequest;
import com.generation.javeat.model.dto.user.UserDtoWWithID;
import com.generation.javeat.model.dtoservices.UserConverter;
import com.generation.javeat.model.entities.Menu;
import com.generation.javeat.model.entities.Owner;
import com.generation.javeat.model.entities.Restaurant;
import com.generation.javeat.model.entities.User;
import com.generation.javeat.model.repositories.MenuRepository;
import com.generation.javeat.model.repositories.OwnerRepository;
import com.generation.javeat.model.repositories.RestaurantRepository;
import com.generation.javeat.model.repositories.UserRepository;

import io.swagger.v3.oas.annotations.Operation;

import static com.generation.javeat.utils.Utils.*;

@RestController
public class UserController {

    @Autowired
    UserRepository repo;

    @Autowired
    OwnerRepository oRepo;

    @Autowired
    UserConverter conv;

    @Autowired
    RestaurantRepository rRepo;

    @Autowired
    MenuRepository mRepo;

    @Operation(description = "Cerco un User nel DB per il Login")
    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest request) {

        String mail = request.getMail();
        String password = request.getPassword();

        User user = repo.findByMail(mail);

        if (user != null && user.getPassword().equals(password)) {
            UserDtoWWithID userDto = conv.userToDtoWID(user);

            boolean isOwner = user instanceof Owner && ((Owner) user).getRestaurant() != null;

            userDto.setOwner(isOwner);

            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @Operation(description = "Inserisco un User nel DB")
    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest dto) {

        if (dto.isOwner()) {
            Owner o = conv.RegisterToOwner(dto);
            Restaurant r = new Restaurant();
            rRepo.save(r);

            Menu m = new Menu();
            mRepo.save(m);
            r.setMenu(m);
            o.setRestaurant(r);
            if (!isValidPassword(o.getPassword())) {
                return ResponseEntity.badRequest().body(
                        "La password deve essere lunga almeno 8 caratteri e contenere almeno un carattere speciale (@, #, $, %, &, , !).");
            }

            if (!isValidEmail(o.getMail())) {
                return ResponseEntity.badRequest().body("La email non è valida");
            }

            return ResponseEntity.ok(oRepo.save(o));
        } else {
            User q = conv.RegisterToUser(dto);
            if (!isValidPassword(q.getPassword())) {
                return ResponseEntity.badRequest().body(
                        "La password deve essere lunga almeno 8 caratteri e contenere almeno un carattere speciale (@, #, $, %, &, , !).");
            }

            if (!isValidEmail(q.getMail())) {
                return ResponseEntity.badRequest().body("La email non è valida");
            }

            return ResponseEntity.ok(repo.save(q));
        }

    }

}
