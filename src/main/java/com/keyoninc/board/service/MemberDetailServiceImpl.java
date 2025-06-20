package com.keyoninc.board.service;

import org.springframework.stereotype.Service;

import com.keyoninc.board.entity.MemberDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailServiceImpl implements MemberDetailService {@Override
    public MemberDetail findByUserId(String userId) {
        throw new UnsupportedOperationException("Unimplemented method 'findByUserId'");
    }

    @Override
    public void save(MemberDetail detail) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(MemberDetail detail) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
