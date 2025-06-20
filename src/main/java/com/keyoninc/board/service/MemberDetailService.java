package com.keyoninc.board.service;

import com.keyoninc.board.entity.MemberDetail;

public interface MemberDetailService {
    MemberDetail findByUserId(String userId);

    void save(MemberDetail detail);

    void update(MemberDetail detail);
}
