package agrestaurante;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

public class TestRestauranteFitness {

    private Configuration conf;
    private RestauranteFitnessFuntion fitnessFunction = null;
    public List<Restaurantes> restaurants = new ArrayList<>();
    public List<String> desiredCuisines = new ArrayList<>();
    public String desiredRestaurantType = "Cualquiera"; 
    public String desiredServiceTime = "Cualquiera";
    public String maxPrice = "$$"; 
    public int maxDeliveryTime = 45;

    private static final int MAX_ALLOWED_EVOLUTIONS = 15;
    private Chromosome restaurantChromosome = null;

    public void initialize(String cuisineTypes, String restaurantType, String serviceTime, String maxPrice, int maxDeliveryTime) throws Exception {
        Configuration.reset();

        // Separar los tipos de cocina deseados
        StringTokenizer st = new StringTokenizer(cuisineTypes);
        while (st.hasMoreElements()) {
            String cuisine = st.nextToken();
            desiredCuisines.add(cuisine);
        }
        // Establecer preferencias de tipo, precio y tiempo
        this.desiredRestaurantType = restaurantType;
        this.desiredServiceTime = serviceTime;
        this.maxPrice = maxPrice;
        this.maxDeliveryTime = maxDeliveryTime;

        // Cargar los restaurantes disponibles
        restaurants = this.loadRestaurants();

        // Configurar el algoritmo genético
        conf = new DefaultConfiguration();
        conf.setPopulationSize(10); // Población de 10 individuos

        // Crear la función de fitness
        fitnessFunction = new RestauranteFitnessFuntion(
                restaurants,
                desiredCuisines,
                desiredRestaurantType,
                maxPrice,
                maxDeliveryTime,
                desiredServiceTime
        );

        conf.setFitnessFunction(fitnessFunction);

        // Definir los genes (1 gen = 1 restaurante)
        Gene[] restaurantGenes = new Gene[1];
        restaurantGenes[0] = new IntegerGene(conf, 0, restaurants.size() - 1);
        restaurantGenes[0].setAllele(0); // Valor inicial

        // Crear cromosoma de ejemplo
        restaurantChromosome = new Chromosome(conf, restaurantGenes);
        conf.setSampleChromosome(restaurantChromosome);
    }

    private List<Restaurantes> loadRestaurants() {
        List<Restaurantes> list = new ArrayList<>();

        Restaurantes r1 = new Restaurantes();
        r1.setName("La Trattoria");
        r1.setCuisineType(Arrays.asList("Italiana"));
        r1.setRestaurantType("Casual");
        r1.setPriceRange("$$");
        r1.setRating(9.2);
        r1.setZone("Centro");
        r1.setDeliveryTime(25);
        r1.setHasDelivery(true);
        r1.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r2 = new Restaurantes();
        r2.setName("El Mariachi");
        r2.setCuisineType(Arrays.asList("Mexicana"));
        r2.setRestaurantType("Formal");
        r2.setPriceRange("$$$");
        r2.setRating(9.5);
        r2.setZone("Norte");
        r2.setDeliveryTime(30);
        r2.setHasDelivery(true);
        r1.setServices(Arrays.asList("Cena"));

        Restaurantes r3 = new Restaurantes();
        r3.setName("Tokyo Sushi");
        r3.setCuisineType(Arrays.asList("Japonesa", "Asiática"));
        r3.setRestaurantType("Gourmet");
        r3.setPriceRange("$$$");
        r3.setRating(9.8);
        r3.setZone("Sur");
        r3.setDeliveryTime(40);
        r3.setHasDelivery(true);
        r3.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r4 = new Restaurantes();
        r4.setName("Burger King");
        r4.setCuisineType(Arrays.asList("Americana", "Hamburguesas"));
        r4.setRestaurantType("FastFood");
        r4.setPriceRange("$");
        r4.setRating(7.5);
        r4.setZone("Centro");
        r4.setDeliveryTime(15);
        r4.setHasDelivery(true);
        r4.setServices(Arrays.asList("todas"));

        Restaurantes r5 = new Restaurantes();
        r5.setName("Gran Muralla");
        r5.setCuisineType(Arrays.asList("China", "Asiática"));
        r5.setRestaurantType("Casual");
        r5.setPriceRange("$$");
        r5.setRating(8.8);
        r5.setZone("Este");
        r5.setDeliveryTime(35);
        r5.setHasDelivery(true);
        r5.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r6 = new Restaurantes();
        r6.setName("La Pampa");
        r6.setCuisineType(Arrays.asList("Argentina", "Parrilla"));
        r6.setRestaurantType("Formal");
        r6.setPriceRange("$$$$");
        r6.setRating(9.9);
        r6.setZone("Norte");
        r6.setDeliveryTime(45);
        r6.setHasDelivery(true);
        r6.setServices(Arrays.asList("Cena"));

        Restaurantes r7 = new Restaurantes();
        r7.setName("Green Life");
        r7.setCuisineType(Arrays.asList("Vegetariana", "Saludable"));
        r7.setRestaurantType("Casual");
        r7.setPriceRange("$$");
        r7.setRating(8.5);
        r7.setZone("Centro");
        r7.setDeliveryTime(20);
        r7.setHasDelivery(true);
        r7.setServices(Arrays.asList("Desayuno", "Almuerzo", "Café"));
        
        Restaurantes r8 = new Restaurantes();
        r8.setName("Pasta Express");
        r8.setCuisineType(Arrays.asList("Italiana"));
        r8.setRestaurantType("FastFood");
        r8.setPriceRange("$");
        r8.setRating(8.0);
        r8.setZone("Sur");
        r8.setDeliveryTime(18);
        r8.setHasDelivery(true);
        r8.setServices(Arrays.asList("todas"));
        
        Restaurantes r9 = new Restaurantes();
        r9.setName("Tacos El Güero");
        r9.setCuisineType(Arrays.asList("Mexicana"));
        r9.setRestaurantType("Casual");
        r9.setPriceRange("$");
        r9.setRating(8.3);
        r9.setZone("Oeste");
        r9.setDeliveryTime(22);
        r9.setHasDelivery(true);
        r9.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r10 = new Restaurantes();
        r10.setName("Cevichería Lima");
        r10.setCuisineType(Arrays.asList("Peruana", "Mariscos"));
        r10.setRestaurantType("Gourmet");
        r10.setPriceRange("$$$");
        r10.setRating(9.3);
        r10.setZone("Centro");
        r10.setDeliveryTime(38);
        r10.setHasDelivery(true);
        r10.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r11 = new Restaurantes();
        r11.setName("Pizza Pronto");
        r11.setCuisineType(Arrays.asList("Italiana", "Pizza"));
        r11.setRestaurantType("FastFood");
        r11.setPriceRange("$");
        r11.setRating(7.8);
        r11.setZone("Este");
        r11.setDeliveryTime(25);
        r11.setHasDelivery(true);
        r11.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r12 = new Restaurantes();
        r12.setName("Le Petit Bistro");
        r12.setCuisineType(Arrays.asList("Francesa"));
        r12.setRestaurantType("Formal");
        r12.setPriceRange("$$$$");
        r12.setRating(9.7);
        r12.setZone("Norte");
        r12.setDeliveryTime(50);
        r12.setHasDelivery(false);
        r12.setServices(Arrays.asList("Cena"));

        Restaurantes r13 = new Restaurantes();
        r13.setName("Wok Express");
        r13.setCuisineType(Arrays.asList("China", "Asiática"));
        r13.setRestaurantType("FastFood");
        r13.setPriceRange("$");
        r13.setRating(7.9);
        r13.setZone("Sur");
        r13.setDeliveryTime(20);
        r13.setHasDelivery(true);
        r13.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r14 = new Restaurantes();
        r14.setName("Taberna Española");
        r14.setCuisineType(Arrays.asList("Española", "Tapas"));
        r14.setRestaurantType("Casual");
        r14.setPriceRange("$$");
        r14.setRating(8.9);
        r14.setZone("Centro");
        r14.setDeliveryTime(32);
        r14.setHasDelivery(true);
        r14.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r15 = new Restaurantes();
        r15.setName("Bangkok Street Food");
        r15.setCuisineType(Arrays.asList("Tailandesa", "Asiática"));
        r15.setRestaurantType("Casual");
        r15.setPriceRange("$$");
        r15.setRating(9.0);
        r15.setZone("Este");
        r15.setDeliveryTime(28);
        r15.setHasDelivery(true);
        r15.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r16 = new Restaurantes();
        r16.setName("Buffet Golden");
        r16.setCuisineType(Arrays.asList("Internacional", "Buffet"));
        r16.setRestaurantType("Buffet");
        r16.setPriceRange("$$");
        r16.setRating(8.2);
        r16.setZone("Norte");
        r16.setDeliveryTime(0); // No tiene delivery, es buffet presencial
        r16.setHasDelivery(false);
        r16.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r17 = new Restaurantes();
        r17.setName("Prime Cuts");
        r17.setCuisineType(Arrays.asList("Americana", "Carnes"));
        r17.setRestaurantType("Formal");
        r17.setPriceRange("$$$$");
        r17.setRating(9.6);
        r17.setZone("Centro");
        r17.setDeliveryTime(42);
        r17.setHasDelivery(true);
        r17.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r18 = new Restaurantes();
        r18.setName("Rodizio Brasil");
        r18.setCuisineType(Arrays.asList("Brasileña", "Parrilla"));
        r18.setRestaurantType("Buffet");
        r18.setPriceRange("$$$");
        r18.setRating(9.1);
        r18.setZone("Sur");
        r18.setDeliveryTime(0); // No tiene delivery, es buffet presencial
        r18.setHasDelivery(false);
        r18.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r19 = new Restaurantes();
        r19.setName("Pizzeria Napoletana");
        r19.setCuisineType(Arrays.asList("Italiana", "Pizza"));
        r19.setRestaurantType("Gourmet");
        r19.setPriceRange("$$$");
        r19.setRating(9.4);
        r19.setZone("Oeste");
        r19.setDeliveryTime(35);
        r19.setHasDelivery(true);
        r19.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r20 = new Restaurantes();
        r20.setName("Fusion Lab");
        r20.setCuisineType(Arrays.asList("Fusión", "Internacional"));
        r20.setRestaurantType("Gourmet");
        r20.setPriceRange("$$$$");
        r20.setRating(9.5);
        r20.setZone("Centro");
        r20.setDeliveryTime(40);
        r20.setHasDelivery(true);
        r20.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r21 = new Restaurantes();
        r21.setName("Sushi Roll");
        r21.setCuisineType(Arrays.asList("Japonesa", "Asiática"));
        r21.setRestaurantType("Casual");
        r21.setPriceRange("$$");
        r21.setRating(8.4);
        r21.setZone("Norte");
        r21.setDeliveryTime(30);
        r21.setHasDelivery(true);
        r21.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r22 = new Restaurantes();
        r22.setName("Shawarma House");
        r22.setCuisineType(Arrays.asList("Árabe", "Medio Oriente"));
        r22.setRestaurantType("Casual");
        r22.setPriceRange("$");
        r22.setRating(8.6);
        r22.setZone("Este");
        r22.setDeliveryTime(20);
        r22.setHasDelivery(true);
        r22.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r23 = new Restaurantes();
        r23.setName("Taj Mahal");
        r23.setCuisineType(Arrays.asList("India", "Asiática"));
        r23.setRestaurantType("Formal");
        r23.setPriceRange("$$$");
        r23.setRating(9.1);
        r23.setZone("Centro");
        r23.setDeliveryTime(35);
        r23.setHasDelivery(true);
        r23.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r24 = new Restaurantes();
        r24.setName("Pizzeria Da Vinci");
        r24.setCuisineType(Arrays.asList("Italiana", "Pizza"));
        r24.setRestaurantType("Formal");
        r24.setPriceRange("$$$");
        r24.setRating(9.3);
        r24.setZone("Sur");
        r24.setDeliveryTime(28);
        r24.setHasDelivery(true);
        r24.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r25 = new Restaurantes();
        r25.setName("Seoul Kitchen");
        r25.setCuisineType(Arrays.asList("Coreana", "Asiática"));
        r25.setRestaurantType("Casual");
        r25.setPriceRange("$$");
        r25.setRating(8.7);
        r25.setZone("Oeste");
        r25.setDeliveryTime(32);
        r25.setHasDelivery(true);
        r25.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r26 = new Restaurantes();
        r26.setName("El Pescador");
        r26.setCuisineType(Arrays.asList("Mariscos", "Mexicana"));
        r26.setRestaurantType("Casual");
        r26.setPriceRange("$$");
        r26.setRating(8.8);
        r26.setZone("Norte");
        r26.setDeliveryTime(40);
        r26.setHasDelivery(true);
        r26.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r27 = new Restaurantes();
        r27.setName("Buffalo Wings");
        r27.setCuisineType(Arrays.asList("Americana", "Alitas"));
        r27.setRestaurantType("FastFood");
        r27.setPriceRange("$");
        r27.setRating(7.9);
        r27.setZone("Centro");
        r27.setDeliveryTime(18);
        r27.setHasDelivery(true);
        r27.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r28 = new Restaurantes();
        r28.setName("Acropolis");
        r28.setCuisineType(Arrays.asList("Griega", "Mediterránea"));
        r28.setRestaurantType("Casual");
        r28.setPriceRange("$$");
        r28.setRating(8.5);
        r28.setZone("Sur");
        r28.setDeliveryTime(35);
        r28.setHasDelivery(true);
        r28.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r29 = new Restaurantes();
        r29.setName("Sabor Colombiano");
        r29.setCuisineType(Arrays.asList("Colombiana", "Latina"));
        r29.setRestaurantType("Casual");
        r29.setPriceRange("$");
        r29.setRating(8.2);
        r29.setZone("Este");
        r29.setDeliveryTime(25);
        r29.setHasDelivery(true);
        r29.setServices(Arrays.asList("Desayuno", "Almuerzo", "Cena"));

        Restaurantes r30 = new Restaurantes();
        r30.setName("Ramen-Ya");
        r30.setCuisineType(Arrays.asList("Japonesa", "Ramen"));
        r30.setRestaurantType("Casual");
        r30.setPriceRange("$$");
        r30.setRating(9.0);
        r30.setZone("Norte");
        r30.setDeliveryTime(22);
        r30.setHasDelivery(true);
        r30.setServices(Arrays.asList("todas"));

        Restaurantes r31 = new Restaurantes();
        r31.setName("The Sandwich Co.");
        r31.setCuisineType(Arrays.asList("Americana", "Sandwiches"));
        r31.setRestaurantType("Casual");
        r31.setPriceRange("$$");
        r31.setRating(8.3);
        r31.setZone("Centro");
        r31.setDeliveryTime(15);
        r31.setHasDelivery(true);
        r31.setServices(Arrays.asList("Desayuno", "Almuerzo", "Merienda"));

        Restaurantes r32 = new Restaurantes();
        r32.setName("Texas BBQ Pit");
        r32.setCuisineType(Arrays.asList("Americana", "BBQ"));
        r32.setRestaurantType("Casual");
        r32.setPriceRange("$$$");
        r32.setRating(9.2);
        r32.setZone("Oeste");
        r32.setDeliveryTime(38);
        r32.setHasDelivery(true);
        r32.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r33 = new Restaurantes();
        r33.setName("Pho Saigon");
        r33.setCuisineType(Arrays.asList("Vietnamita", "Asiática"));
        r33.setRestaurantType("Casual");
        r33.setPriceRange("$");
        r33.setRating(8.4);
        r33.setZone("Sur");
        r33.setDeliveryTime(28);
        r33.setHasDelivery(true);
        r33.setServices(Arrays.asList("todas"));

        Restaurantes r34 = new Restaurantes();
        r34.setName("Pizza Familia");
        r34.setCuisineType(Arrays.asList("Italiana", "Pizza"));
        r34.setRestaurantType("Casual");
        r34.setPriceRange("$");
        r34.setRating(7.6);
        r34.setZone("Este");
        r34.setDeliveryTime(30);
        r34.setHasDelivery(true);
        r34.setServices(Arrays.asList("todas"));

        Restaurantes r35 = new Restaurantes();
        r35.setName("Istanbul Kebab");
        r35.setCuisineType(Arrays.asList("Turca", "Medio Oriente"));
        r35.setRestaurantType("Casual");
        r35.setPriceRange("$$");
        r35.setRating(8.7);
        r35.setZone("Norte");
        r35.setDeliveryTime(25);
        r35.setHasDelivery(true);
        r35.setServices(Arrays.asList("todas"));

        Restaurantes r36 = new Restaurantes();
        r36.setName("Sabor Quiteño");
        r36.setCuisineType(Arrays.asList("Ecuatoriana", "Latina"));
        r36.setRestaurantType("Casual");
        r36.setPriceRange("$");
        r36.setRating(8.5);
        r36.setZone("Centro");
        r36.setDeliveryTime(20);
        r36.setHasDelivery(true);
        r36.setServices(Arrays.asList("Desayuno", "Almuerzo", "Merienda"));

        Restaurantes r37 = new Restaurantes();
        r37.setName("Omakase Sushi Bar");
        r37.setCuisineType(Arrays.asList("Japonesa", "Sushi"));
        r37.setRestaurantType("Gourmet");
        r37.setPriceRange("$$$$");
        r37.setRating(9.8);
        r37.setZone("Centro");
        r37.setDeliveryTime(45);
        r37.setHasDelivery(true);
        r37.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r38 = new Restaurantes();
        r38.setName("Beirut Express");
        r38.setCuisineType(Arrays.asList("Libanesa", "Medio Oriente"));
        r38.setRestaurantType("FastFood");
        r38.setPriceRange("$");
        r38.setRating(8.1);
        r38.setZone("Sur");
        r38.setDeliveryTime(18);
        r38.setHasDelivery(true);
        r38.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r39 = new Restaurantes();
        r39.setName("La Pulpería");
        r39.setCuisineType(Arrays.asList("Uruguaya", "Parrilla"));
        r39.setRestaurantType("Formal");
        r39.setPriceRange("$$$");
        r39.setRating(9.4);
        r39.setZone("Norte");
        r39.setDeliveryTime(40);
        r39.setHasDelivery(true);
        r39.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r40 = new Restaurantes();
        r40.setName("Havana Café");
        r40.setCuisineType(Arrays.asList("Cubana", "Caribeña"));
        r40.setRestaurantType("Casual");
        r40.setPriceRange("$$");
        r40.setRating(8.6);
        r40.setZone("Oeste");
        r40.setDeliveryTime(30);
        r40.setHasDelivery(true);
        r40.setServices(Arrays.asList("Almuerzo", "Cena", "Café"));

        Restaurantes r41 = new Restaurantes();
        r41.setName("Dynasty Palace");
        r41.setCuisineType(Arrays.asList("China", "Asiática"));
        r41.setRestaurantType("Formal");
        r41.setPriceRange("$$$");
        r41.setRating(9.0);
        r41.setZone("Centro");
        r41.setDeliveryTime(35);
        r41.setHasDelivery(true);
        r41.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r42 = new Restaurantes();
        r42.setName("Arepera La Reina");
        r42.setCuisineType(Arrays.asList("Venezolana", "Latina"));
        r42.setRestaurantType("Casual");
        r42.setPriceRange("$");
        r42.setRating(8.0);
        r42.setZone("Este");
        r42.setDeliveryTime(22);
        r42.setHasDelivery(true);
        r42.setServices(Arrays.asList("Desayuno", "Almuerzo", "Merienda", "cafe"));

        Restaurantes r43 = new Restaurantes();
        r43.setName("The Grill House");
        r43.setCuisineType(Arrays.asList("Americana", "Carnes"));
        r43.setRestaurantType("Casual");
        r43.setPriceRange("$$");
        r43.setRating(8.8);
        r43.setZone("Sur");
        r43.setDeliveryTime(32);
        r43.setHasDelivery(true);
        r43.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r44 = new Restaurantes();
        r44.setName("Golden Dragon Dim Sum");
        r44.setCuisineType(Arrays.asList("China", "Dim Sum"));
        r44.setRestaurantType("Casual");
        r44.setPriceRange("$$");
        r44.setRating(8.9);
        r44.setZone("Norte");
        r44.setDeliveryTime(28);
        r44.setHasDelivery(true);
        r44.setServices(Arrays.asList("Desayuno", "Almuerzo"));

        Restaurantes r45 = new Restaurantes();
        r45.setName("Doggie Style");
        r45.setCuisineType(Arrays.asList("Americana", "Hot Dogs"));
        r45.setRestaurantType("FastFood");
        r45.setPriceRange("$");
        r45.setRating(7.7);
        r45.setZone("Centro");
        r45.setDeliveryTime(15);
        r45.setHasDelivery(true);
        r45.setServices(Arrays.asList("Almuerzo", "Merienda", "Cena"));

        Restaurantes r46 = new Restaurantes();
        r46.setName("Casablanca");
        r46.setCuisineType(Arrays.asList("Marroquí", "Africana"));
        r46.setRestaurantType("Formal");
        r46.setPriceRange("$$$");
        r46.setRating(8.9);
        r46.setZone("Oeste");
        r46.setDeliveryTime(42);
        r46.setHasDelivery(true);
        r46.setServices(Arrays.asList("Cena"));

        Restaurantes r47 = new Restaurantes();
        r47.setName("Poke Paradise");
        r47.setCuisineType(Arrays.asList("Hawaiana", "Saludable"));
        r47.setRestaurantType("Casual");
        r47.setPriceRange("$$");
        r47.setRating(8.5);
        r47.setZone("Sur");
        r47.setDeliveryTime(20);
        r47.setHasDelivery(true);
        r47.setServices(Arrays.asList("todas"));

        Restaurantes r48 = new Restaurantes();
        r48.setName("Addis Ababa");
        r48.setCuisineType(Arrays.asList("Etíope", "Africana"));
        r48.setRestaurantType("Casual");
        r48.setPriceRange("$$");
        r48.setRating(8.3);
        r48.setZone("Este");
        r48.setDeliveryTime(38);
        r48.setHasDelivery(true);
        r48.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r49 = new Restaurantes();
        r49.setName("Patagonia Grill");
        r49.setCuisineType(Arrays.asList("Chilena", "Latina"));
        r49.setRestaurantType("Casual");
        r49.setPriceRange("$$");
        r49.setRating(8.6);
        r49.setZone("Norte");
        r49.setDeliveryTime(30);
        r49.setHasDelivery(true);
        r49.setServices(Arrays.asList("Almuerzo", "Cena"));

        Restaurantes r50 = new Restaurantes();
        r50.setName("Noodle Box");
        r50.setCuisineType(Arrays.asList("Asiática", "Noodles"));
        r50.setRestaurantType("FastFood");
        r50.setPriceRange("$");
        r50.setRating(7.8);
        r50.setZone("Centro");
        r50.setDeliveryTime(18);
        r50.setHasDelivery(true);
        r50.setServices(Arrays.asList("todas"));

        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(r5);
        list.add(r6);
        list.add(r7);
        list.add(r8);
        list.add(r9);
        list.add(r10);
        list.add(r11);
        list.add(r12);
        list.add(r13);
        list.add(r14);
        list.add(r15);
        list.add(r16);
        list.add(r17);
        list.add(r18);
        list.add(r19);
        list.add(r20);
        list.add(r21);
        list.add(r22);
        list.add(r23);
        list.add(r24);
        list.add(r25);
        list.add(r26);
        list.add(r27);
        list.add(r28);
        list.add(r29);
        list.add(r30);
        list.add(r31);
        list.add(r32);
        list.add(r33);
        list.add(r34);
        list.add(r35);
        list.add(r36);
        list.add(r37);
        list.add(r38);
        list.add(r39);
        list.add(r40);
        list.add(r41);
        list.add(r42);
        list.add(r43);
        list.add(r44);
        list.add(r45);
        list.add(r46);
        list.add(r47);
        list.add(r48);
        list.add(r49);
        list.add(r50);

        return list;
    }

    public void testSelectBestRestaurant() throws Exception {
        // Crear población inicial aleatoria
        Genotype population = Genotype.randomInitialGenotype(conf);

        IChromosome bestSolutionSoFar = restaurantChromosome;

        // Evolucionar durante MAX_ALLOWED_EVOLUTIONS generaciones
        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve(10); // 10 evoluciones por generación
            IChromosome candidateBestSolution = population.getFittestChromosome();

            // Si encontramos una mejor solución, la guardamos
            if (candidateBestSolution.getFitnessValue() > bestSolutionSoFar.getFitnessValue()) {
                bestSolutionSoFar = candidateBestSolution;
            }
        }

        // Imprimir la mejor solución encontrada
        printSolution(bestSolutionSoFar, restaurants);
    }

    public void printSolution(IChromosome solution, List<Restaurantes> restaurants) {
        System.out.println("=".repeat(120));
        System.out.println("MEJOR RESTAURANTE ENCONTRADO");
        System.out.println("=".repeat(120));
        System.out.println("Valor del Fitness: " + solution.getFitnessValue());
        System.out.println();

        for (int i = 0; i < solution.size(); i++) {
            int index = (Integer) solution.getGene(i).getAllele();
            Restaurantes restaurant = restaurants.get(index);
            System.out.println(restaurant.toString());
        }

        System.out.println("=".repeat(120));
    }
}
