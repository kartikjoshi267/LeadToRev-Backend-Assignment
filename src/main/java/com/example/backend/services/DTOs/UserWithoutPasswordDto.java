package com.example.backend.services.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWithoutPasswordDto {
    private String id;
    private String email;
}
