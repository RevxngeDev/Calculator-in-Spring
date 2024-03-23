package com.example.calculator.services;

import com.example.calculator.models.Calculation;
import com.example.calculator.models.User;
import com.example.calculator.repositores.CalculatorRepository;
import com.example.calculator.repositores.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@Component
public class CalculatorServiceImp implements CalculationService {
    @Autowired
    private CalculatorRepository calculatorRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public String calculateAndSave(String expression, Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        String result = performCalculation(expression);
        Calculation calculation = new Calculation();
        calculation.setExpression(expression);
        calculation.setResult(result);
        calculation.setUser(user);
        calculatorRepository.save(calculation);
        return result;
    }
    private String performCalculation(String expression) {
        String[] parts = expression.split(" ");
        Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String part : parts) {
            if (isNumber(part)) {
                numbers.push(Double.parseDouble(part));
            } else if (part.equals("^2") || part.equals("sqrt")) {
                operators.push(part);
            } else if (part.equals("*") || part.equals("/")) {
                while (!operators.isEmpty() && (operators.peek().equals("^2") || operators.peek().equals("sqrt"))) {
                    applyOperator(numbers, operators.pop());
                }
                operators.push(part);
            } else if (part.equals("+") || part.equals("-")) {
                while (!operators.isEmpty()) {
                    applyOperator(numbers, operators.pop());
                }
                operators.push(part);
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(numbers, operators.pop());
        }

        if (numbers.size() == 1) {
            return Double.toString(numbers.pop());
        } else {
            return "Error: Invalid expression";
        }
    }
    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void applyOperator(Stack<Double> numbers, String operator) {
        if (operator.equals("^2")) {
            double num = numbers.pop();
            numbers.push(num * num);
        } else if (operator.equals("sqrt")) {
            double num = numbers.pop();
            numbers.push(Math.sqrt(num));
        } else {
            double num2 = numbers.pop();
            double num1 = numbers.pop();
            if (operator.equals("+")) {
                numbers.push(num1 + num2);
            } else if (operator.equals("-")) {
                numbers.push(num1 - num2);
            } else if (operator.equals("*")) {
                numbers.push(num1 * num2);
            } else if (operator.equals("/")) {
                if (num2 == 0) {
                    throw new ArithmeticException("Error: Division by zero");
                }
                numbers.push(num1 / num2);
            }
        }
    }

}
