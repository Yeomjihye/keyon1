package com.keyoninc.board.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.keyoninc.board.entity.Board;
import com.keyoninc.board.entity.Reply;

public interface BoardService {
    public void insertBoard(Board dto) throws Exception;

    public List<Board> listBoard();
    public Page<Board> listPage(String kwd, int currentPage, int size);

    public List<Board> searchBoard(String kwd);

    public Board findById(int num);

    public void updateBoard(Board dto) throws Exception;
    public void deleteBoard(int num) throws Exception;

    //댓글
    public void insertReply(Reply dto) throws Exception;
    public List<Reply> listReply(int num);
    public void deleteReply(int replyNum);

} 
