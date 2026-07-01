package com.example.demo.service;

import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public List<MemberResponse> subscribeBatch(List<MemberRequest> memberRequests) {
        List<MemberResponse> memberResponses = new ArrayList<MemberResponse>();
        for (MemberRequest memberRequest : memberRequests) {
            memberResponses.add(subscribe(memberRequest));
        }
        return memberResponses;
    }

    public MemberResponse subscribe(MemberRequest memberRequest) {
        Member member =  Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .enabled(true).build(); // default enabled is true
        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return mapToMemberResponse(member);
    }

    private MemberResponse mapToMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge())
                .build();
    }
}
