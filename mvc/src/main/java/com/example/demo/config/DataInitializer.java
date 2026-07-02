package com.example.demo.config;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (memberRepository.count() == 0) {
            var members = List.of(
                    Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").password("password").build(),
                    Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").password("password").build(),
                    Member.builder().name("공미영").email("MiyeongKong@hanbit.co.kr").password("password").build(),
                    Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").password("password").build()
            );
            memberRepository.saveAll(members);
        }
    }
}
