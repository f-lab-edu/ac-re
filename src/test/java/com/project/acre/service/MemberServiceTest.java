package com.project.acre.service;

import com.project.acre.domain.Member;
import com.project.acre.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void join() {
        Member member = new Member();
        member.setId("test1");
        member.setPassword("test1");
        member.setName("테스트1");
        member.setNickname("테스트1");
        member.setEmail("test1@test.com");
        member.setPhone("010-1234-5678");

        Long saveKey = memberService.join(member);

        System.out.println(saveKey);
    }
}
