package com.keyoninc.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member1")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberIdx")
    private int memberIdx;

    @Column(name = "userId", nullable = false, unique = true, length = 50, insertable = false, updatable = false)
    private String userId;

    @Column(name = "userPwd", nullable = false, length = 100)
    private String userPwd;

    @Column(name = "userName", nullable = false, length = 50)
    private String userName;

    @Column(name = "userLevel", columnDefinition = "INT DEFAULT 1")
    private int userLevel = 1;

    @Column(name = "enabled", columnDefinition = "TINYINT DEFAULT 1")
    private int enabled = 1;

    @Column(name = "register_date", insertable = false, updatable = false)
    private LocalDateTime registerDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private MemberDetail memberDetail;
}
