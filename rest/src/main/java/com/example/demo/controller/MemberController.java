package com.example.demo.controller;

import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.entity.Member;
import com.example.demo.exception.ExceptionDetails;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping
    List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/{id}")
    Member getMemberById(@PathVariable("id") Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MemberResponse postMembers(@RequestBody MemberRequest member) {
        return memberService.subscribe(member);
    }

    @PutMapping("/{id}")
    Member putMembers(@PathVariable("id") Long id, @RequestBody Member member) {
        memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        member.setId(id);
        return memberRepository.save(member);
    }

    @DeleteMapping("/{id}")
    void deleteMembers(@PathVariable("id") Long id) {
        memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        memberRepository.deleteById(id);
    }
}
