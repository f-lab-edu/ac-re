package com.project.acre.service;

import com.project.acre.controller.MemberDto;
import com.project.acre.util.SHA256;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class MemberServiceTest {

    @Test
    public void joinPasswordEncrypt() throws NoSuchAlgorithmException {
        MemberDto memberDto = new MemberDto();
        SHA256 sha256 = SHA256.getInstance();
        memberDto.setPassword(sha256.encrypt("test1"));

        Assertions.assertThat(memberDto).extracting("password").isEqualTo(sha256.encrypt("test1"));
    }

    @Test
    public void singletonTest() {
        SHA256 sha256_1 = SHA256.getInstance();
        SHA256 sha256_2 = SHA256.getInstance();

        Assertions.assertThat(sha256_1).isSameAs(sha256_2);
    }
}
