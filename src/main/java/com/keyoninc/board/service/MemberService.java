package com.keyoninc.board.service;

import com.keyoninc.board.entity.Member;
import com.keyoninc.board.entity.MemberDetail;

public interface MemberService {
    
    Member login(String userId, String userPwd) throws Exception;

    public void join(Member member);

    public void insertMember(Member member, MemberDetail memberDetail) throws Exception;
}
