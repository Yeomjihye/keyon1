package com.keyoninc.board.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyoninc.board.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    
    Optional<Notice> findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate start, LocalDate end);

    Optional<Notice> findBySubjectContaining(String kwd);

}
