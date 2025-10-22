package com.project.acre.service;

import com.project.acre.controller.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class MemberServiceTest {

    @Test
    public void joinPasswordEncrypt() throws NoSuchAlgorithmException {
        MemberDto memberDto = new MemberDto();
        memberDto.setPassword(new MemberService.SHA256().encrypt("test1"));

        Assertions.assertThat(memberDto).extracting("password").isEqualTo(new MemberService.SHA256().encrypt("test1"));
    }
}
