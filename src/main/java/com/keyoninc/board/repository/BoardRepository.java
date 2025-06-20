package com.keyoninc.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keyoninc.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findBySubjectContainingOrNameContaining(String subject, String name);

    Page<Board> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);

} 
