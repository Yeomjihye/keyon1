package com.keyoninc.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyoninc.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
    List<Reply> findByNumOrderByReplyNumAsc(int num);
}
