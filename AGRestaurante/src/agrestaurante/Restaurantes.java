package agrestaurante;

import java.util.List;

public class Restaurantes {

    private String name;
    private List<String> cuisineType; // Tipo de cocina: Italiana, Mexicana, China, etc.
    private String restaurantType; // Tipo: Casual, Formal, FastFood, Gourmet, Buffet
    private String priceRange; // $, $$, $$$, $$$$
    private double rating; // Calificación de 0-10
    private String zone; // Zona de la ciudad
    private int deliveryTime; // Tiempo de entrega en minutos
    private boolean hasDelivery; // ¿Tiene servicio a domicilio?
    private List<String> services;

    // Constructor vacío
    public Restaurantes() {
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(List<String> cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isHasDelivery() {
        return hasDelivery;
    }

    public void setHasDelivery(boolean hasDelivery) {
        this.hasDelivery = hasDelivery;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Restaurante [Nombre=" + name
                + ", Tipo de Cocina=" + cuisineType
                + ", Tipo=" + restaurantType
                + ", Precio=" + priceRange
                + ", Rating=" + rating
                + ", Zona=" + zone
                + ", Tiempo Entrega=" + deliveryTime + " min"
                + ", Delivery=" + (hasDelivery ? "Si" : "No")
                + "]";
    }
}
