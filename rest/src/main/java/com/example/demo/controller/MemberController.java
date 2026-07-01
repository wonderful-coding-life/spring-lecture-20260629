package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.exception.ExceptionDetails;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members")
    List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/members/{id}")
    Member getMemberById(@PathVariable("id") Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    Member postMembers(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PutMapping("/members/{id}")
    Member putMembers(@PathVariable("id") Long id, @RequestBody Member member) {
        memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        member.setId(id);
        return memberRepository.save(member);
    }

    @DeleteMapping("/members/{id}")
    void deleteMembers(@PathVariable("id") Long id) {
        memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        memberRepository.deleteById(id);
    }
}
