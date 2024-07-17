package com.halbu.spring_start.controller;

import com.halbu.spring_start.domain.Member;
import com.halbu.spring_start.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;

@Controller // Controller 어노테이션을 보고 스프링컨테이너에 멤버 객체를 생성해 스프링이 관리함.
public class MemberController {
    private final MemberService memberService;
    //스프링 컨테이너에 등록해 사용

    @Autowired // 멤버 컨트롤러가 생성될때 스프링 빈에 등록된 객체를 가져와 넣어줌. DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //Post방식으로 값을 받아옴
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
