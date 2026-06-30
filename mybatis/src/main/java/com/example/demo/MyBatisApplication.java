package com.example.demo;

import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.model.Article;
import com.example.demo.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyBatisApplication implements ApplicationRunner {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testArticle1();
    }

    // 생성
    private void test1() {
        Member member = Member.builder()
                .name("윤서준")
                .email("SeojunYoon@hanbit.co.kr")
                .age(10).build();
        int count = memberMapper.insert(member);
        log.info("count={}, 회원 {}", count, member);
    }

    // 수정
    private void test2() {
        Member member = Member.builder()
                .name("윤서준")
                .email("SeojunYoon@google.com")
                .age(11).build();

        int count = memberMapper.update(5L, member);
        log.info("count={}", count);
    }

    // 조회
    private void test3() {
        List<Member> members = memberMapper.selectAll();
        for (Member member : members) {
            log.info("회원 {}", member);
        }
    }

    // 이메일로 조회
    private void test4() {
        Member member = memberMapper.selectByEmail("MiyeongKong@hanbit.co.kr");
        log.info("회원 {}", member);
    }

    // 삭제
    private void test5() {
        int count = memberMapper.delete(5L);
        log.info("삭제 결과 {}", count);
    }

    // 전체 게시글
    private void testArticle1() {
        List<Article> articles = articleMapper.selectAll();
        for (Article article : articles) {
            log.info("게시글 {}", article);
        }
    }
}
