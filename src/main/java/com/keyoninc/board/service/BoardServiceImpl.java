package com.keyoninc.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keyoninc.board.entity.Board;
import com.keyoninc.board.entity.Reply;
import com.keyoninc.board.repository.BoardRepository;
import com.keyoninc.board.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public void insertBoard(Board dto) throws Exception {
        try {
            boardRepository.save(dto);
        } catch (Exception e) {
            log.info("insertBoard:", e);
        }
    }

    @Override
    public List<Board> listBoard() {
        return boardRepository.findAll();
    }

@Override
public Page<Board> listPage(String kwd, int currentPage, int size) {
    Page<Board> page = null;

    try {
        Pageable pageable = PageRequest.of(currentPage - 1, size, Sort.by(Sort.Direction.DESC, "num"));

        if (kwd == null || kwd.isBlank()) {
            page = boardRepository.findAll(pageable);
        } else {
            page = boardRepository.findBySubjectContainingOrContentContaining(kwd, kwd, pageable);
        }

    } catch (Exception e) {
        log.info("listPage : ", e);
    }

    return page;
}


    @Override
    public List<Board> searchBoard(String kwd) {
        return boardRepository.findBySubjectContainingOrNameContaining(kwd, kwd);
    }

    @Override
    public Board findById(int num) {
        return boardRepository.findById(num).orElse(null);
    }


    @Override
    public void updateBoard(Board dto) throws Exception {
        try {

            boardRepository.save(dto);

        } catch (Exception e) {
            log.info("updateBoard : ", e);
            throw e;
        }
    }

    @Override
    public void deleteBoard(int num) throws Exception {
        try {
            boardRepository.deleteById(num);
        } catch (Exception e) {
            log.info("deleteBoard : ", e);
            throw e;
        }
    }

    @Override
    public void insertReply(Reply dto) throws Exception {
        try {
            replyRepository.save(dto);
        } catch (Exception e) {
            log.info("insertReply : ", e);
        }
    }

    @Override
    public List<Reply> listReply(int num) {
        return replyRepository.findByNumOrderByReplyNumAsc(num);
    }

    @Override
    public void deleteReply(int replyNum) {
        replyRepository.deleteById(replyNum);
    }

    
}
