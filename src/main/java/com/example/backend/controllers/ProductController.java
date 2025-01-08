package com.example.backend.controllers;

import com.example.backend.models.Product;
import com.example.backend.services.DTOs.RatingsWithoutUserDto;
import com.example.backend.services.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(
                products,
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try {
            Product product1 = productService.addProduct(product);
            return new ResponseEntity<>(
                    "Product successfully created",
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){
        try {
            Product product1 = productService.getProductById(id);
            return new ResponseEntity<>(
                    product1,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody Product product){
        try {
            Product product1 = productService.updateProductById(id, product);

            if (product1 == null) {
                return new ResponseEntity<>(
                        "Product not found",
                        HttpStatus.BAD_REQUEST
                );
            }

            return new ResponseEntity<>(
                    product1,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Failed to update product",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        try {
            productService.deleteProductById(id);
            return new ResponseEntity<>(
                    "Product deleted successfully",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Failed to delete product",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/{id}/rate")
    public ResponseEntity<?> rateProduct(@PathVariable String id,
                                         @RequestBody RatingsWithoutUserDto rating,
                                         HttpServletRequest request){
        try {
            Cookie tokenCookie = WebUtils.getCookie(request, "token");
            if (tokenCookie == null) {
                return new ResponseEntity<>(
                        "User not logged in",
                        HttpStatus.BAD_REQUEST
                );
            }
            String token = tokenCookie.getValue();

            Product product = productService.rateProduct(id, rating, token);
            if (product == null) {
                return new ResponseEntity<>(
                        "Product not found",
                        HttpStatus.BAD_REQUEST
                );
            }

            return new ResponseEntity<>(
                    product,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(
                    "Rating the product failed",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
