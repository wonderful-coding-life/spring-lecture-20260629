package com.example.demo.controller;

import com.example.demo.dto.MemberForm;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class HomeController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String getSignup(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(@Valid @ModelAttribute("memberForm") MemberForm memberForm, BindingResult bindingResult) {

        // 패스워드 확인 검증
        if (!Objects.equals(memberForm.getPassword(), memberForm.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "error.signup.password.mismatch", "비밀번호가 일치하지 않습니다.");
        }

        // 이메일 중복 검증
        if (memberRepository.findByEmail(memberForm.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "AlreadyExist", "사용중인 이메일입니다");
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        var member = Member.builder()
                .name(memberForm.getName())
                .email(memberForm.getEmail())
                .password(memberForm.getPassword()).build();
        memberRepository.save(member);
        return "redirect:/member/list";
    }
}
