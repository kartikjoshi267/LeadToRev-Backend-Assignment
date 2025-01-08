package com.example.backend.models;

import lombok.Data;

@Data
public class Availability{
    private boolean inStock;
    private int quantity;
}