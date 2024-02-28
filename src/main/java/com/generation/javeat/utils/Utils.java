package com.generation.javeat.utils;

import com.generation.javeat.model.entities.Restaurant;

public class Utils {

    static public Integer calculateDistanceToRestaurant(Restaurant restaurant, Integer userX, Integer userY) {
        // Coordinate del ristorante
        Integer restaurantX = restaurant.getPositionX();
        Integer restaurantY = restaurant.getPositionY();
        // Calcola la differenza tra le coordinate x e y
        Integer diffX = restaurantX - userX;
        int diffY = restaurantY - userY;
        // Calcola la somma dei quadrati delle differenze
        Integer squaredDiffX = diffX * diffX;
        Integer squaredDiffY = diffY * diffY;
        // Calcola la radice quadrata della somma dei quadrati delle differenze
        double distance = Math.sqrt(squaredDiffX + squaredDiffY);
        return (int)distance;
    }

    public static boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[@#$%&*!]).{8,}$"; 
        return password.matches(passwordPattern);
    }

    public static boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailPattern);
    }
}
