package com.example.backend.services;

import com.example.backend.models.Product;
import com.example.backend.models.Rating;
import com.example.backend.repository.ProductRepo;
import com.example.backend.services.DTOs.RatingsWithoutUserDto;
import com.example.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProductById(String id, Product updatedProduct) {
        Product existingProduct = productRepo.findById(id).orElse(null);

        if (existingProduct == null) {
            return null;
        }

        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }
        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getPrice() != 0) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        if (updatedProduct.getCategories() != null && !updatedProduct.getCategories().isEmpty()) {
            existingProduct.setCategories(updatedProduct.getCategories());
        }
        if (updatedProduct.getAttributes() != null && !updatedProduct.getAttributes().isEmpty()) {
            existingProduct.setAttributes(updatedProduct.getAttributes());
        }
        if (updatedProduct.getAvailability() != null) {
            existingProduct.setAvailability(updatedProduct.getAvailability());
        }

        return productRepo.save(existingProduct);
    }

    public void deleteProductById(String id) {
        productRepo.deleteById(id);
    }

    public Product getProductById(String id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product rateProduct(String id, RatingsWithoutUserDto rating, String token) {
        String userId = JwtUtil.verifyToken(token);
        if (userId == null) {
            return null;
        }

        Product product1 = productRepo.findById(id).orElse(null);
        if (product1 == null) {
            return null;
        }

        Rating rating1 = new Rating(
                userId,
                rating.getRating(),
                rating.getComment()
        );

        List<Rating> ratings = product1.getRatings();
        ratings.add(rating1);
        product1.setRatings(ratings);

        return productRepo.save(product1);
    }
}
