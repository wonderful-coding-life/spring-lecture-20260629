package com.example.demo;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.json.JsonWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class JpaApplication implements ApplicationRunner {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Member> members = List.of(
                Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).build(),
                Member.builder().name("공미영").email("MiyoungKong@hanbit.co.kr").age(21).build(),
                Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").age(10).build()
        );
        memberRepository.saveAll(members);
        test3();
    }

    private void test1() {
        Member member = memberRepository.findById(1L).orElseThrow();
        log.info("1번 회원 {}", member);

        member = memberRepository.findById(2L).orElseThrow();
        log.info("2번 회원 {}", member);

        memberRepository.delete(member);

        List<Member> members = memberRepository.findAll();
        for (Member member1 : members) {
            log.info("회원 {}", member1);
        }
    }

    private void test2() {
        Member member = memberRepository.findByEmail("SeojunYoon@hanbit.co.kr").orElseThrow();
        log.info("SeojunYoon => {}", member);
    }

    private void test3() {
        List<Member> members = memberRepository.findByAgeGreaterThanEqual(13);
        for (Member member : members) {
            log.info("회원 {}", member);
        }
    }
}
