package com.generation.javeat.model.entities;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//ciao
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

        if(dishesDeliveries != null)
        {
        for(DishToDelivery d: dishesDeliveries)
            totDishesPrice+=d.getDish().getPrice(); 
        }
        
        return totDishesPrice; 
    }

   
    public double getRiderRevenue()
    {

        return restaurant.getDeliveryPricePerUnit()* getDistance(); 
        /*int numeroConsegne=0; 

        for (int i=0; i<dishesDeliveries.size(); i++ ) 
               numeroConsegne++; 
        
        double guadagnoTotale=(numeroConsegne * costoFissoPerConsegna) + (distance*costoPerUnitàDistanza);

        return guadagnoTotale;*/
    }

    /*public double getRiderRevenue(Delivery d) 
    {
        int costoFissoPerConsegna=2;
        int numeroConsegne=0;

        for(int i=0; i<d.getUser().getDeliveries().size(); i++)
            numeroConsegne++;
        // Guadagno del rider
        double riderRevenue = (d.getRestaurant().getDeliveryPricePerUnit()* distance) + (numeroConsegne*costoFissoPerConsegna);
    
        return riderRevenue;
    }*/
    

    public double getTotalPrice()
    {

        return getDishesPrice()+getRiderRevenue(); 
    }


    
   
   /* public LocalTime calculateExpectedDeliveryTime() 
    {
        // Calcoliamo il tempo aggiuntivo basato sulla distanza (2 min per unità di distanza)
        int additionalTimeMinutes = distance * 2;

        // Otteniamo l'orario attuale
        LocalDateTime now = LocalDateTime.now();

        // Aggiungiamo il tempo aggiuntivo
        LocalDateTime expectedDeliveryDateTime = expected_arrival.minusMinutes(additionalTimeMinutes);

        // Arrotondiamo l'orario alla prossima finestra temporale di 15 minuti
        int minutesToAdd = (15 - (expectedDeliveryDateTime.getMinute() % 15)) % 15;
        expectedDeliveryDateTime = expectedDeliveryDateTime.plusMinutes(minutesToAdd);

        // Ritorniamo solo l'orario di consegna
        return expectedDeliveryDateTime.toLocalTime();
    }

     // Metodo per calcolare l'orario di consegna previsto
     public LocalTime calculateExpectedDeliveryTime() {
        // Calcoliamo il tempo aggiuntivo basato sulla distanza (2 min per unità di distanza)
        int additionalTimeMinutes = distance * 2;

        // Sottraiamo il tempo aggiuntivo dall'orario di arrivo previsto
        LocalDateTime expectedDeliveryDateTime = expected_arrival.minusMinutes(additionalTimeMinutes);

        // Arrotondiamo l'orario alla prossima finestra temporale di 15 minuti
        int minute = expectedDeliveryDateTime.getMinute();
        int minuteRounded = (minute / 15) * 15 + ((minute % 15 > 0) ? 15 : 0);
        expectedDeliveryDateTime = expectedDeliveryDateTime.withMinute(minuteRounded);

        // Ritorniamo solo l'orario di consegna
        return expectedDeliveryDateTime.toLocalTime();*/
}

