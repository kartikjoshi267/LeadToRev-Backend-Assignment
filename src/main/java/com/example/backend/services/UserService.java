package com.example.backend.services;

import com.example.backend.models.User;
import com.example.backend.repository.UserRepo;
import com.example.backend.services.DTOs.UserWithoutPasswordDto;
import com.example.backend.utils.JwtUtil;
import com.example.backend.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String login(User user){
        User user1 = userRepo.findByEmail(user.getEmail());
        if (user1 == null) {
            return null;
        }

        boolean isPasswordCorrect = PasswordUtil.verifyPassword(user.getPassword(), user1.getPassword());
        if (!isPasswordCorrect) {
            return null;
        }

        return JwtUtil.generateToken(user1.getId());
    }

    public User signup(User user){
        User user1 = userRepo.findByEmail(user.getEmail());
        if (user1 != null) {
            return null;
        }

        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepo.save(user);
    }

    public UserWithoutPasswordDto getUserByToken(String token) {
        String userId = JwtUtil.verifyToken(token);
        if (userId == null) {
            return null;
        }

        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        return new UserWithoutPasswordDto(
                userId,
                user.getEmail()
        );
    }
}
