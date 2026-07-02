package com.example.demo.controller;

import com.example.demo.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("error", "없는 회원입니다.");
        model.addAttribute("now", LocalDateTime.now());
        return "error";
    }
}
