package com.keyoninc.board.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keyoninc.board.entity.Board;
import com.keyoninc.board.entity.Reply;
import com.keyoninc.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/bbs/*")
public class BoardController {
    private final BoardService boardservice;
    
    @GetMapping("list")
    public String list(
        @RequestParam(name = "kwd", defaultValue = "") String kwd,
        @RequestParam(name = "page", defaultValue = "1") int currentPage,
        Model model) throws Exception{

            int pageSize = 10;
            long dataCount = 0;
            int totalPage = 0;

        try {
            kwd = URLDecoder.decode(kwd, "utf-8");

            Page<Board> pageBoard = boardservice.listPage(kwd, currentPage, pageSize);
            List<Board> list = null;

            if(pageBoard != null) {
                totalPage = pageBoard.getTotalPages();

                if(totalPage >= 1 && currentPage > totalPage) {
                    currentPage = totalPage;
                    pageBoard = boardservice.listPage(kwd, currentPage, pageSize);
                }

            }

            if(pageBoard != null) {
                dataCount = pageBoard.getTotalElements();
                list = pageBoard.getContent();
            }

            currentPage = (totalPage == 0) ? 0 : currentPage;

            model.addAttribute("list", list);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("dataCount", dataCount);
            model.addAttribute("size", pageSize);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("kwd", kwd);

        } catch (Exception e) {
            log.info("list:", e);
        }
        
        return "bbs/list";
    }

    @GetMapping("write")
    public String writeForm(Model model) throws Exception{
        model.addAttribute("mode", "write");

        return "bbs/write";
    }

    @PostMapping("write")
    public String writeSubmit(Board dto) throws Exception{
        
        try {
            boardservice.insertBoard(dto);

        } catch (Exception e) {
            log.info("writeSubmit:", e);
        }

        return "redirect:/bbs/list";
    }

    @GetMapping("article/{num}")
    public String article(@PathVariable(name = "num")int num,
        @RequestParam(name = "kwd", defaultValue = "") String kwd,
        Model model){

            try {
                kwd = URLDecoder.decode(kwd, "utf-8");
                String query = "";

                if(! kwd.isBlank()) {
                    query = "kwd=" + URLEncoder.encode(kwd, "utf-8");
                }

                Board dto = boardservice.findById(num);
                if(dto == null) {
                    return "redirect:/bbs/list" + (query.isBlank() ? "" : "?" + query);
                }

                dto.setContent(dto.getContent().replaceAll("\n", "<br>"));

                model.addAttribute("dto", dto);
                model.addAttribute("kwd", kwd);
                model.addAttribute("query", query);

                return "bbs/article";

            } catch (Exception e) {
                log.info("article:", e);
                return "redirect:/bbs/list";
            }      
    }

    @GetMapping("update/{num}")
    public String updateForm(@PathVariable(name = "num") int num, Model model) {
        
        try {
            Board dto = Objects.requireNonNull(boardservice.findById(num));

            model.addAttribute("dto", dto);
            model.addAttribute("mode", "update");

            return "bbs/write";
            
        } catch (NullPointerException e) {
        } catch (Exception e) {
            log.info("updateForm : ", e);
        }

        return "redirect:/bbs/list";
    }

    @PostMapping("update")
    public String updateSubmit(Board dto) {
        try {
            
            boardservice.updateBoard(dto);

        } catch (Exception e) {
            log.info("updateSubmit : ", e);
        }

        return "redirect:/bbs/list";
    }

    @GetMapping("delete/{num}")
    public String delete(@PathVariable(name = "num") int num,
                        @RequestParam(name = "kwd", defaultValue = "") String kwd) {

        String query = "";

        try {
            kwd = URLDecoder.decode(kwd, "utf-8");

            boardservice.deleteBoard(num);

            if (!kwd.isBlank()) {
                query = "?kwd=" + URLEncoder.encode(kwd, "utf-8");
            }

        } catch (Exception e) {
            log.info("delete : ", e);
        }

        return "redirect:/bbs/list" + query;
    }

    @PostMapping("insertReply")
    @ResponseBody
    public Map<String, Object> insertReply(Reply dto) {
        Map<String, Object> model = new HashMap<>();
        String state = "true";

        try {
            boardservice.insertReply(dto);
        } catch (Exception e) {
            e.printStackTrace();
            state = "false";
        }

        model.put("state", state);

        return model;
    }
    
    
    @GetMapping("listReply")
    public String listReply(@RequestParam("num") int num, Model model) {

        try {
            List<Reply> listReply = boardservice.listReply(num);
            model.addAttribute("listReply", listReply);

        } catch (Exception e) {
            log.info("listReply", e);
        }

        return "bbs/listReply";
    }
    
    @PostMapping("deleteReply")
    @ResponseBody
    public Map<String, Object> deleteReply(@RequestParam("replyNum") int replyNum) {
        Map<String, Object> map = new HashMap<>();
        String state = "true";

        try {
            boardservice.deleteReply(replyNum);
        } catch (Exception e) {
            e.printStackTrace();
            state = "false";
        }
        
        map.put("state", state);
        return map;
    }
    

}
