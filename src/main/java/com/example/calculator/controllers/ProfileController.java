package com.example.calculator.controllers;

import com.example.calculator.models.User;
import com.example.calculator.repositores.UsersRepository;
import com.example.calculator.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepository usersRepository;


    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();

        Optional<User> userOptional = usersRepository.findByEmail(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            Long userId = user.getId();
            model.addAttribute("userId", userId);
            Optional<User> userWithCalculationsOptional = usersService.getUserWithCalculations(userId);
            if (userWithCalculationsOptional.isPresent()){
                User userWithCalculations = userWithCalculationsOptional.get();
                model.addAttribute("user", userWithCalculations);
            }
        } else {
            throw new UsernameNotFoundException("User not found for email: " + email);
        }
        model.addAttribute("userName", userDetails.getUsername());
        return "profile_page";
    }
}
