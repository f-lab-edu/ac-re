package com.project.acre.service;

import com.project.acre.controller.MemberDto;
import com.project.acre.domain.Member;
import com.project.acre.exception.CustomEmptyResultException;
import com.project.acre.repository.MemberRepository;
import com.project.acre.util.SHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
public class MemberService {
    private final SHA256 sha256 = SHA256.getInstance();
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto join(MemberDto memberDto) throws NoSuchAlgorithmException {
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setPassword(sha256.encrypt(memberDto.getPassword()));
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

    public MemberDto login(String id, String password) throws NoSuchAlgorithmException {
        Optional<Member> result = memberRepository.findMember(id, sha256.encrypt(password));

        if (result.isEmpty()) {
            throw new CustomEmptyResultException("사용자 정보를 찾을 수 없습니다.");
        } else {
            return new MemberDto(
                    result.get().getId(),
                    result.get().getPassword(),
                    result.get().getName(),
                    result.get().getNickname(),
                    result.get().getBirth(),
                    result.get().getEmail(),
                    result.get().getPhone(),
                    result.get().getClassify()
            );
        }
    }
}
