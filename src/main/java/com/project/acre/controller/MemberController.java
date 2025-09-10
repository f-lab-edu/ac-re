package com.project.acre.controller;

import com.project.acre.domain.Member;
import com.project.acre.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String joinMember() {
        return "members/joinMember";
    }

    @PostMapping("/members/join")
    public String joinMember(MemberForm form) {
        Member member = new Member();
        member.setId(form.getId());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        member.setNickname(form.getNickname());
        member.setEmail(form.getEmail());
        member.setPhone(form.getPhone());

        memberService.join(member);

        return "redirect:/";
    }
}
