package com.project.acre.controller;

import com.project.acre.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/join")
    public ModelAndView joinMember() {
        return new ModelAndView("members/joinMember");
    }

    @PostMapping("members/join")
    @ResponseBody
    public ResponseEntity<MemberDto> joinMember(@RequestBody MemberDto memberDto) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(memberService.join(memberDto), HttpStatus.OK);
    }

    @ExceptionHandler(value = NoSuchAlgorithmException.class)
    public ResponseEntity<String> handleException(NoSuchAlgorithmException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @GetMapping("/members/login")
    public ModelAndView login() {
        return new ModelAndView("members/login");
    }

    @PostMapping("/members/login")
    @ResponseBody
    public ResponseEntity<MemberDto> login(@RequestBody MemberDto memberDto) throws Exception {
        return new ResponseEntity<>(memberService.login(memberDto.getId(), memberDto.getPassword()), HttpStatus.OK);
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleException(EmptyResultDataAccessException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
