package com.example.demo.config;

import com.example.demo.entity.Article;
import com.example.demo.entity.Member;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Member> members = List.of(
                Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).build(),
                Member.builder().name("공미영").email("MiyoungKong@hanbit.co.kr").age(21).build(),
                Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").age(10).build()
        );
        memberRepository.saveAll(members);

        var member = memberRepository.findByEmail("SeojunYoon@hanbit.co.kr").orElseThrow();
        for (int i = 0; i < 100; i++) {
            var article = Article.builder()
                    .title("제목 " + i)
                    .description("본문 " + i)
                    .member(member).build();
            articleRepository.save(article);
        }
    }
}
