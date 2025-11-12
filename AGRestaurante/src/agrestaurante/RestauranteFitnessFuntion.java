package agrestaurante;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class RestauranteFitnessFuntion extends FitnessFunction {

    List<Restaurantes> restaurants = new ArrayList<>();
    List<String> cuisines = new ArrayList<>();
    String tipoRestaurante; // Tipo de restaurante deseado
    String maxPrice; // Precio máximo aceptable
    int maxDeliveryTime; // Tiempo máximo de entrega en minutos
    String servicio;

    public RestauranteFitnessFuntion(List<Restaurantes> restaurants, List<String> cuisines,
            String restaurantType, String maxPrice, int maxDeliveryTime,
            String serviceTime) {
        this.restaurants = restaurants;
        this.cuisines = cuisines;
        this.tipoRestaurante = restaurantType;
        this.maxPrice = maxPrice;
        this.maxDeliveryTime = maxDeliveryTime;
        this.servicio = serviceTime;
    }

    @Override
    protected double evaluate(IChromosome chromosome) {
        double score = 0;
        List<Integer> duplicates = new ArrayList<>();
        int badSolution = 1; // aqui es un multiplicador para que pueda invalidar soluciones malas

        // Recorro cada gen del cromosoma (cada restaurante seleccionado)
        for (int i = 0; i < chromosome.size(); i++) {
            // Obtener el índice del restaurante
            int index = (Integer) chromosome.getGene(i).getAllele();

            // Verificar duplicados
            if (duplicates.contains(index)) {
                badSolution = 0; // Solución inválida si hay duplicados
            } else {
                duplicates.add(index);
            }

            // Obtengo el restaurante
            Restaurantes restaurant = restaurants.get(index);

            // 1. EVALUO EL TIPO DE COCINA (peso: +5 puntos por coincidencia)
            double cuisineScore = getCuisineScore(restaurant);
            if (cuisineScore == 0) {
                badSolution = 0; // Descarta si no coincide el tipo de cocina
            }

            // 2. EVALUO EL  TIPO DE RESTAURANTE (peso: +3 puntos si coincide)
            double restaurantTypeScore = getRestaurantTypeScore(restaurant);
            if (restaurantTypeScore == 0 && !tipoRestaurante.equals("Cualquiera")) {
                badSolution = 0; // Descarta si no coincide (solo si se especificó un tipo)
            }
            double serviceTimeScore = getServiceTimeScore(restaurant);
            if (serviceTimeScore == 0 && !servicio.equals("Cualquiera")) {
                badSolution = 0; // Descarta si no sirve en el horario deseado
            }
            // 3. EVALUO RATING (0-10 puntos)
            double ratingScore = restaurant.getRating();

            // 4. EVALUO PRECIO (bonificación si es económico)
            double priceScore = getPriceScore(restaurant);
            if (priceScore == 0) {
                badSolution = 0; // Descarta si excede el presupuesto
            }

            // 5. EVALUO EL TIEMPO DE ENTREGA (bonificación si es rápido)
            double deliveryScore = getDeliveryScore(restaurant);
            if (deliveryScore == 0) {
                badSolution = 0; // Descarta si tarda demasiado
            }

            // PUNTUACIÓN TOTAL
            score = score + ratingScore + cuisineScore + restaurantTypeScore + priceScore + deliveryScore;
        }

        // Retorna el score multiplicado por badSolution (0 si es mala solución)
        return (score * badSolution);
    }

    private double getCuisineScore(Restaurantes restaurant) {
        double cuisineScore = 0;
        Iterator<String> it = this.cuisines.iterator();

        while (it.hasNext()) {
            String desiredCuisine = it.next();
            if (restaurant.getCuisineType().contains(desiredCuisine)) {
                cuisineScore = cuisineScore + 5; // +5 puntos por coincidencia
            }
        }

        return cuisineScore;
    }

    private double getRestaurantTypeScore(Restaurantes restaurant) {
        if (tipoRestaurante == null || tipoRestaurante.equals("Cualquiera")) {
            return 3; // Si no se especifica, todos son válidos con puntuación base
        }

        if (restaurant.getRestaurantType().equals(tipoRestaurante)) {
            return 3; // Coincide con el tipo deseado
        }

        return 0; // No coincide
    }

    private double getServiceTimeScore(Restaurantes restaurant) {
        if (servicio == null || servicio.equals("Cualquiera")) {
            return 2; // Si no se especifica, todos son válidos
        }

        // Verificar si el restaurante sirve en el horario deseado
        if (restaurant.getServices() != null
                && restaurant.getServices().contains(servicio)) {
            return 2; // +2 puntos si coincide
        }

        return 0; // No sirve en ese horario
    }

    /**
     * Calcula puntos por precio $$$$ = 1 punto (muy caro) $$$ = 2 puntos (caro)
     * $$ = 3 puntos (moderado) $ = 4 puntos (económico)
     */
    private double getPriceScore(Restaurantes restaurant) {
        String price = restaurant.getPriceRange();

        // Primero verificar si está dentro del presupuesto
        if (!isWithinBudget(price)) {
            return 0; // Fuera de presupuesto
        }

        // Dar más puntos a los más económicos
        switch (price) {
            case "$":
                return 4;
            case "$$":
                return 3;
            case "$$$":
                return 2;
            case "$$$$":
                return 1;
            default:
                return 0;
        }
    }

    /**
     * Verifica si el precio está dentro del presupuesto máximo
     */
    private boolean isWithinBudget(String restaurantPrice) {
        int maxLevel = getPriceLevel(maxPrice);
        int restaurantLevel = getPriceLevel(restaurantPrice);

        return restaurantLevel <= maxLevel;
    }

    /**
     * Convierte el símbolo de precio a un nivel numérico
     */
    private int getPriceLevel(String price) {
        switch (price) {
            case "$":
                return 1;
            case "$$":
                return 2;
            case "$$$":
                return 3;
            case "$$$$":
                return 4;
            default:
                return 0;
        }
    }

    /**
     * Calcula puntos por tiempo de entrega Mientras más rápido, más puntos
     * (máximo 3 puntos)
     */
    private double getDeliveryScore(Restaurantes restaurant) {
        int time = restaurant.getDeliveryTime();

        // Verificar si excede el tiempo máximo
        if (time > maxDeliveryTime) {
            return 0; // Tarda demasiado
        }

        // Dar más puntos a los más rápidos
        if (time <= 20) {
            return 3; // Muy rápido (≤20 min)
        } else if (time <= 35) {
            return 2; // Rápido (≤35 min)
        } else if (time <= 50) {
            return 1; // Moderado (≤50 min)
        } else {
            return 0.5; // Lento pero aceptable
        }
    }
}
