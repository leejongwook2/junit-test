package com.example.junittest.member;

import com.example.junittest.domain.Member;
import com.example.junittest.domain.Study;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);

}
