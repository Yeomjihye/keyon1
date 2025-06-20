package com.keyoninc.board.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keyoninc.board.entity.Member;
import com.keyoninc.board.entity.MemberDetail;
import com.keyoninc.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member/*")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(@RequestParam(name = "userId") String userId, 
            @RequestParam(name = "userPwd") String userPwd,
            HttpSession session,
            Model model) throws Exception{

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


    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }


    @PostMapping("/join")
    public String joinSubmit(@ModelAttribute(name = "member") Member member,
    @ModelAttribute(name = "detail") MemberDetail memberDetail,
    @RequestParam String userPwdConfirm,
    Model model) {

         Member existing = memberService.findByUserId(member.getUserId());
        if (existing != null) {
            model.addAttribute("errorMsg", "이미 사용 중인 아이디입니다.");
            return "member/join";
}

        if (!member.getUserPwd().equals(userPwdConfirm)) {
            model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
            return "member/join";
        }

        try {
            memberService.insertMember(member, memberDetail);
            return "redirect:/member/login";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMsg", "이미 존재하는 아이디입니다.");
            return "member/join";
        } catch (Exception e) {
            model.addAttribute("errorMsg", "시스템 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
            return "member/join";
        }
    }
    

}
