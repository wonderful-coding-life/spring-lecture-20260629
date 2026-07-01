package com.example.demo.controller;

import com.example.demo.dto.MemberResponse;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    public void testSubscribe() throws Exception {
        String requestBody = """
[
    {
        "name": "윤서준",
        "email": "SeojunYoon@hanbit.co.kr",
        "age": 10
    },
    {
        "name": "윤광철",
        "email": "KwangcheolYoon@hanbit.co.kr",
        "age": 43
    },
    {
        "name": "공미영",
        "email": "MiyoungKong@hanbit.co.kr",
        "age": 21
    },
    {
        "name": "김도윤",
        "email": "DoyunKim@hanbit.co.kr",
        "age": 10
    }
]
                    """;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/members")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("윤서준"))
                .andExpect(jsonPath("$[0].email").value("SeojunYoon@hanbit.co.kr"))
                .andExpect(jsonPath("$[0].age").value(10))
        ;
    }
}
