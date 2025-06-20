package com.keyoninc.board.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member2")
@Getter
@Setter
public class MemberDetail {
    
    @Id
    @Column(name = "userId", length = 50)
    private String userId;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "tel", length = 20)
    private String tel;

    @Column(name = "zip", length = 7)
    private String zip;

    @Column(name = "addr1", length = 100)
    private String addr1;

    @Column(name = "addr2", length = 100)
    private String addr2;

    @Column(name = "receiveEmail")
    private int receiveEmail = 1;

}
