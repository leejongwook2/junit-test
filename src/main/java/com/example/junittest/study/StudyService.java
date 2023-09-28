package com.example.junittest.study;

import com.example.junittest.domain.Member;
import com.example.junittest.domain.Study;
import com.example.junittest.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null; // null 인 경우 assert-exception 이 발생한다.
        assert repository != null; // null 인 경우 assert-exception 이 발생한다.
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        if (member.isPresent()) {
            study.setOwnerId(memberId);
            study.setOwner(member.get());
        } else {
            throw new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'");
        }

        Study newstudy = repository.save(study);
        memberService.notify(newstudy);
        memberService.notify(member.get());

        // memberService.validate(1L); verifyNoMoreInteractions 기능 테스트를 위해 억지로 넣어봄
        return newstudy;
    }

    public Study openStudy(Study study) {
        study.open(); // study의 상태가 open 으로 바뀐다. 그리고 시간 업데이트
        Study openedStudy = repository.save(study); // 스터디 저장
        memberService.notify(openedStudy); // 알람
        return openedStudy;
    }

    public void hi() {

    }
}
