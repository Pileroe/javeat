package com.generation.javeat.model.entities;
import java.time.LocalDateTime;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
//ciao
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime expected_arrival;
    private int distance;
    private String paymentMethod;
    private String notes;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") // Colonna per la foreign key
    @Schema(description = "Delivery")
    private User user;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id") // Colonna per la foreign key
    private Restaurant restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER)
    private Set<DishToDelivery> dishesDeliveries;



    public double getDishesPrice()
    {
        double totDishesPrice=0;

        for(DishToDelivery d: dishesDeliveries)
            totDishesPrice+=d.getPrice(); 
        
        return totDishesPrice; 
    }

    public double getRiderRevenue()
    {  
        double distanceCost= restaurant.getDeliveryPricePerUnit() * (distance/200);

        return distanceCost;
    }



    public double getTotalPrice()
    {

        return getDishesPrice()+getRiderRevenue(); 
    }
}

