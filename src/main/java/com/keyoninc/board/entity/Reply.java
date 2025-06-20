package com.keyoninc.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bbsReply")
@Getter
@Setter
@NoArgsConstructor
public class Reply {
    @Id
    @Column(name = "replyNum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyNum;

    @Column(name = "num", nullable = false)
    private int num;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "reg_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP",
    insertable = false, updatable = false)
    private LocalDateTime regDate;
    
}
