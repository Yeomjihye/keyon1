package com.keyoninc.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.keyoninc.board.entity.Notice;
import com.keyoninc.board.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notice/*")
public class NoticeController {
    
    private final NoticeService noticeService;

    @GetMapping("list")
    public String list(Model model) {
        try {
            List<Notice> list = noticeService.listNotice();

            model.addAttribute("list", list);
        } catch (Exception e) {
            log.info("listNotice : ", e);
        }
        return "notice/list";
    }

    @GetMapping("write")
    public String writeForm(Model model) throws Exception {

        model.addAttribute("mode", "write");

        return "notice/write";
    }


    @PostMapping("write")
    public String writeSubmit(Notice dto) throws Exception {

        try {
            noticeService.insertNotice(dto);
        } catch (Exception e) {
            log.info("공지 등록 중 오류 발생 : ", e);
        }

        return "redirect:/notice/list";
    }

    @GetMapping("article/{num}")
    public String article(@PathVariable(name = "num")int num, Model model) {

        try {
            
            Notice dto = noticeService.findById(num);

            if (dto == null) {
                return "redirect:/notice/list";
            }

            dto.setContent(dto.getContent().replaceAll("null", "<br>"));

            model.addAttribute("dto", dto);

            return "notice/article";

        } catch (Exception e) {
            log.info("notice Article : ", e);
            return "redirect:/notice/list";
        }

    }

}
