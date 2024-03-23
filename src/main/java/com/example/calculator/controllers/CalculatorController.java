package com.example.calculator.controllers;

import com.example.calculator.models.User;
import com.example.calculator.repositores.UsersRepository;
import com.example.calculator.services.CalculationService;
import com.example.calculator.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CalculatorController {

    @Autowired
    private CalculationService calculationService;
    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/calculate")
    public String calculateExpression(@RequestParam("expression") String expression, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserIdFromUserDetails(userDetails);
        calculationService.calculateAndSave(expression, userId);
        return "redirect:/profile";
    }

    private Long getUserIdFromUserDetails(UserDetails userDetails){
        String email = userDetails.getUsername();
        return usersRepository.findByEmail(email)
                .map(User::getId).orElseThrow(() -> new UsernameNotFoundException("User not found for email" + email));
    }
}
