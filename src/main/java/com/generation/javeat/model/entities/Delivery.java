package com.generation.javeat.model.entities;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        double totalRevenue = 0.0;
        for (DishToDelivery dishDelivery : dishesDeliveries) 
                totalRevenue += dishDelivery.getPrice();
        
        
        double distanceCost= restaurant.getDeliveryPricePerUnit() * distance;

        return totalRevenue-distanceCost;
    }



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

