package com.keyoninc.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyoninc.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByUserId(String userId);
}
