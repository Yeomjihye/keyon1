package com.keyoninc.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keyoninc.board.entity.Member;
import com.keyoninc.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("member/*")
public class MemberController {

    private final MemberService memberService;
    
    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }
    
    @PostMapping("/join")
    public String joinSubmit() {
        
        
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(@RequestParam(name = "userId") String userId, 
            @RequestParam(name = "userPwd") String userPwd,
            HttpSession session,
            Model model) {
        
        Member loginMember = memberService.login(userId, userPwd);

        if(loginMember !=null ) {
            session.setAttribute("loginMember", loginMember);
            return "redirect:/";
        } else {
            model.addAttribute("errorMsg", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "member/login";
        }
        
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    

}
