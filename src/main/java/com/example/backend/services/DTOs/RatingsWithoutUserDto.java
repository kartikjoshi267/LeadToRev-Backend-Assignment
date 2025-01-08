package com.example.backend.services.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingsWithoutUserDto {
    private double rating;
    private String comment;
}
