package com.keyoninc.board.service;

import org.springframework.stereotype.Service;

import com.keyoninc.board.entity.Member;
import com.keyoninc.board.entity.MemberDetail;
import com.keyoninc.board.repository.MemberDetailRepository;
import com.keyoninc.board.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberDetailRepository memberDetailRepository;

    @Override
    public Member login(String userId, String userPwd) throws Exception {

        try {
            Member member = memberRepository.findByUserId(userId);

            if(member != null && member.getUserPwd().equals(userPwd)) {
                return member;
            }
            
        } catch (Exception e) {
            log.info("login:", e);
            throw e;
        }
        return null;
        
    }


    @Transactional
    @Override
    public void insertMember(Member member, MemberDetail memberDetail) throws Exception {

        try {
            String userId = member.getUserId();
    
            memberRepository.save(member);
    
            memberDetail.setUserId(userId);

            memberDetailRepository.save(memberDetail);
            
        } catch (Exception e) {
            log.info("insertMember : ", e);

        }    
    }


    @Override
    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    
}
