package com.generation.javeat.model.entities;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Restaurant")
public class Restaurant 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phone, imgUrl, name;
    private int openingHour, closingHour, positionX, positionY, maxDeliveryDistance;
    private double deliveryPricePerUnit;
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    private Owner owner; 
    

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "foodType", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "foodTypes", nullable = false)
    private List<String> foodTypes;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Delivery> deliveries; // da rivedere il nome
}
     
