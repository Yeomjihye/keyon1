package com.keyoninc.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyoninc.board.entity.MemberDetail;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, String> {
    
}
