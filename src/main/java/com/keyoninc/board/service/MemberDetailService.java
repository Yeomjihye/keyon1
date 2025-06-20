package com.keyoninc.board.service;

import com.keyoninc.board.entity.MemberDetail;

public interface MemberDetailService {
    public MemberDetail findByUserId(String userId);

    public void save(MemberDetail detail);

    public void update(MemberDetail detail);
}
