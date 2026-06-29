package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class DripCoffeeMachine implements CoffeeMachine {
    public String brew() {
        return "brewing with Drip coffee machine";
    }
}
