package com.example.backend.config;

import com.example.backend.models.Availability;
import com.example.backend.models.Product;
import com.example.backend.models.Rating;
import com.example.backend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product();
        product1.setName("Wireless Mouse");
        product1.setDescription("A high-quality wireless mouse with ergonomic design.");
        product1.setPrice(29.99);
        product1.setCategories(Arrays.asList("Electronics", "Accessories"));

        Map<String, String> attributes1 = new HashMap<>();
        attributes1.put("Brand", "Logitech");
        attributes1.put("Color", "Black");
        attributes1.put("Connectivity", "Wireless");
        product1.setAttributes(attributes1);

        Availability availability1 = new Availability();
        availability1.setInStock(true);
        availability1.setQuantity(100);
        product1.setAvailability(availability1);

//        Rating rating1 = new Rating();
//        rating1.setUserId("user1");
//        rating1.setRating(4.5);
//        rating1.setComment("Great product!");
//        product1.setRatings(List.of(rating1));


        Product product2 = new Product();
        product2.setName("Gaming Keyboard");
        product2.setDescription("A mechanical keyboard with RGB lighting.");
        product2.setPrice(79.99);
        product2.setCategories(Arrays.asList("Electronics", "Accessories"));

        Map<String, String> attributes2 = new HashMap<>();
        attributes2.put("Brand", "Razer");
        attributes2.put("Color", "Black");
        attributes2.put("Switch Type", "Mechanical");
        product2.setAttributes(attributes2);

        Availability availability2 = new Availability();
        availability2.setInStock(true);
        availability2.setQuantity(50);
        product2.setAvailability(availability2);

//        Rating rating2 = new Rating();
//        rating2.setUserId("user2");
//        rating2.setRating(4.8);
//        rating2.setComment("Perfect for gaming!");
//        product2.setRatings(List.of(rating2));

        productRepo.saveAll(Arrays.asList(product1, product2));
        System.out.println("Dummy data loaded into the database.");
    }
}
