package com.project.acre.service;

import com.project.acre.domain.Member;
import com.project.acre.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member login(String id,String password) {
        return null;
    }

    public Long join(Member member) {
        memberRepository.join(member);
        return member.getKey();
    }
}
