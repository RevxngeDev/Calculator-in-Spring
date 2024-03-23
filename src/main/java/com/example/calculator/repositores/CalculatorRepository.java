package com.example.calculator.repositores;

import com.example.calculator.models.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalculatorRepository extends JpaRepository<Calculation, Long> {

}

