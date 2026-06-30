package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return memberRepository.findById(id).orElseThrow();
    }

    @PostMapping("/members")
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
