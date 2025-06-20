package com.keyoninc.board.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.keyoninc.board.entity.Notice;

public interface NoticeService {

    public void insertNotice(Notice dto) throws Exception;
    
    public List<Notice> listNotice();

    public Notice findById(int num);

    public Optional<Notice> getTodayNotice(LocalDate today);
    
    public void updateNotice(Notice dto) throws Exception;

    public void deleteNotice(int num) throws Exception;

    public Page<Notice> listPage(String kwd, int currentPage, int size);

    public List<Notice> searchNotice(String kwd);

}
