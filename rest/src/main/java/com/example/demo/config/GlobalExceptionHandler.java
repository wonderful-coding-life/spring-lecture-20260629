package com.example.demo.config;

import com.example.demo.exception.ArticleNotFoundException;
import com.example.demo.exception.ExceptionDetails;
import com.example.demo.exception.MemberNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleMemberNotFoundException(MemberNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(404).body(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .path(getPath(request))
                .reason("해당 아이디의 회원이 없습니다.").build());
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleArticleNotFoundException(ArticleNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(404).body(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .path(getPath(request))
                .reason("해당 아이디의 게시글이 없습니다.").build());
    }

    private String getPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        String path = (query == null) ? uri : uri + "?" + query;
        return path;
    }
}
