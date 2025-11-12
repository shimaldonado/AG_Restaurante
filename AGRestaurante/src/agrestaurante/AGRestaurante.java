package agrestaurante;

import java.util.logging.Level;
import java.util.logging.Logger;


public class AGRestaurante {

    public static void main(String[] args) {
        try {
            System.out.println("SISTEMA DE RECOMENDACION DE RESTAURANTES");
            TestRestauranteFitness test = new TestRestauranteFitness(); //instancio el test

            System.out.println("BUSQUEDA 1: Comida Italiana, Tipo Casual, Precio ≤ $, Tiempo ≤ 30 min");
            System.out.println("-".repeat(120));
            test.initialize("Italiana", "Casual", "todas", "$$", 30);
            test.testSelectBestRestaurant();
            System.out.println("\n");

            System.out.println("BUSQUEDA 2: Comida Asiatica, Tipo Gourmet, Precio ≤ $$, Tiempo ≤ 45 min");
            System.out.println("-".repeat(120));
            test = new TestRestauranteFitness(); // Reiniciar
            test.initialize("Asiática", "Gourmet", "Desayuno", "$$$", 45);
            test.testSelectBestRestaurant();
            System.out.println("\n");

            System.out.println("BUSQUEDA 3: Comida Mexicana, Cualquier Tipo, Precio ≤ $, Tiempo ≤ 25 min");
            System.out.println("-".repeat(120));
            test = new TestRestauranteFitness(); // Reiniciar
            test.initialize("Mexicana", "Cualquiera", "Almuerzo", "$", 25);
            test.testSelectBestRestaurant();
            System.out.println("\n");

            System.out.println("BUSQUEDA 4: Comida Americana, Tipo FastFood, Precio ≤ $, Tiempo ≤ 20 min");
            System.out.println("-".repeat(120));
            test = new TestRestauranteFitness(); // Reiniciar
            test.initialize("Americana", "FastFood", "Café", "$", 20);
            test.testSelectBestRestaurant();
            System.out.println("\n");

            System.out.println("BUSQUEDA 5: Cualquier Cocina, Tipo Formal, Precio ≤ $$, Tiempo ≤ 50 min");
            System.out.println("-".repeat(120));
            test = new TestRestauranteFitness(); // Reiniciar
            test.initialize("Argentina Francesa", "Formal", "Cena", "$$$$", 50);
            test.testSelectBestRestaurant();

        } catch (Exception ex) {
            Logger.getLogger(AGRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
