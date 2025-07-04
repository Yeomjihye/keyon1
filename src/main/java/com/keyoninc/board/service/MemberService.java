package com.keyoninc.board.service;

import com.keyoninc.board.entity.Member;
import com.keyoninc.board.entity.MemberDetail;

public interface MemberService {
    
    public Member login(String userId, String userPwd) throws Exception;

    public void insertMember(Member member, MemberDetail memberDetail) throws Exception;

    public Member findByUserId(String userId);
}
