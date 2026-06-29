package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class EspressoMachine implements CoffeeMachine {
    public String brew() {
        return "brewing coffee with Espresso machine";
    }
}
