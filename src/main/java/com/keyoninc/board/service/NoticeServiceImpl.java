package com.keyoninc.board.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.keyoninc.board.entity.Notice;
import com.keyoninc.board.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public void insertNotice(Notice dto) throws Exception {
        try {
            noticeRepository.save(dto);
        } catch (Exception e) {
            log.info("insertNotice : ", e);
        }
    }

    @Override
    public List<Notice> listNotice() {
        return noticeRepository.findAll();
    }
    
    @Override
    public Notice findById(int num) {
        return noticeRepository.findById(num).orElse(null);
    }
    
    @Override
    public Optional<Notice> getTodayNotice(LocalDate today) {
        return noticeRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today);
    }  
    
    
    @Override
    public void updateNotice(Notice dto) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'updateNotice'");
    }

    @Override
    public void deleteNotice(int num) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'deleteNotice'");
    }


    @Override
    public Page<Notice> listPage(String kwd, int currentPage, int size) {
        throw new UnsupportedOperationException("Unimplemented method 'listPage'");
    }

    @Override
    public List<Notice> searchNotice(String kwd) {
        throw new UnsupportedOperationException("Unimplemented method 'searchNotice'");
    }



    
    
    
}
