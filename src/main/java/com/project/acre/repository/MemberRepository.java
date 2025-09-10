package com.project.acre.repository;

import com.project.acre.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member join(Member member);
    Optional<Member> findMember(String id, String password);
}
