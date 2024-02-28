package com.generation.javeat.utils;

import com.generation.javeat.model.entities.Restaurant;

public class Utils {

    static public double calculateDistanceToRestaurant(Restaurant restaurant, Integer userX, Integer userY) {
        // Coordinate del ristorante
        int restaurantX = restaurant.getPositionX();
        int restaurantY = restaurant.getPositionY();
        // Calcola la differenza tra le coordinate x e y
        int diffX = restaurantX - userX;
        int diffY = restaurantY - userY;
        // Calcola la somma dei quadrati delle differenze
        int squaredDiffX = diffX * diffX;
        int squaredDiffY = diffY * diffY;
        // Calcola la radice quadrata della somma dei quadrati delle differenze
        double distance = Math.sqrt(squaredDiffX + squaredDiffY);
        return distance;
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
