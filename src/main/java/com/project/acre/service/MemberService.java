package com.project.acre.service;

import com.project.acre.controller.MemberDto;
import com.project.acre.domain.Member;
import com.project.acre.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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

    public static class SHA256 {
        public String encrypt(String password) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return Base64.getEncoder().encodeToString(digest);
        }
    }

    public MemberDto join(MemberDto memberDto) throws NoSuchAlgorithmException {
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setPassword(new SHA256().encrypt(memberDto.getPassword()));
        member.setName(memberDto.getName());
        member.setNickname(memberDto.getNickname());
        member.setBirth(memberDto.getBirth());
        member.setEmail(memberDto.getEmail());
        member.setPhone(memberDto.getPhone());
        member.setClassify(memberDto.getClassify());

        memberRepository.join(member);

        memberDto.setKey(member.getKey());

        return memberDto;
    }
}
