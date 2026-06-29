package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class MochaCoffeeMachine implements CoffeeMachine {
    @Override
    public String brew() {
        return "brewing coffee with Mocha coffee machine";
    }
}
