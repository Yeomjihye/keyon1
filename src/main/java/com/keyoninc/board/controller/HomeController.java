package com.keyoninc.board.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.keyoninc.board.entity.Notice;
import com.keyoninc.board.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final NoticeService noticeService;

    @GetMapping("/")
    public String handleHome(Model model) {

        Notice notice = noticeService.getTodayNotice(LocalDate.now()).orElse(null);

        if(notice != null) {
            model.addAttribute("notice", notice);
        }

        return "main/home";
    }
}
