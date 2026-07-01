package com.example.demo.service;

import com.example.demo.dto.MemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        if (memberRepository.count() == 0) {
            List<Member> members = List.of(
                    Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                    Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).build(),
                    Member.builder().name("공미영").email("MiyoungKong@hanbit.co.kr").age(21).build(),
                    Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").age(10).build()
            );
            memberRepository.saveAll(members);
        }
    }

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    public void testFindById() {
        MemberResponse memberResponse = memberService.findById(1L);
        assertThat(memberResponse.getId()).isEqualTo(1L);
        assertThat(memberResponse.getName()).isEqualTo("윤서준");
    }

    @Test
    public void testSubscribe() {
        MemberRequest memberRequest = MemberRequest.builder()
                .name("김희선")
                .email("HeesunKim@hanbit.co.kr")
                .age(18)
                .build();
        MemberResponse memberResponse = memberService.subscribe(memberRequest);

        assertThat(memberResponse.getId()).isGreaterThan(0L);
        assertThat(memberResponse.getName()).isEqualTo("김희선");
    }
}








