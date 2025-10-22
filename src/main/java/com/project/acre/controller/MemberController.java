package com.project.acre.controller;

import com.project.acre.domain.Member;
import com.project.acre.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/login")
    public String login() {
        return "members/login";
    }

    @GetMapping("/members/join")
    public ModelAndView joinMember() {
        return new ModelAndView("members/joinMember");
    }

    @PostMapping("members/join")
    @ResponseBody
    public MemberDto joinMember(@RequestBody MemberDto memberDto) {
        return memberService.join(memberDto);
    }
}
