package com.example.junittest.study;

import com.example.junittest.domain.Member;
import com.example.junittest.domain.Study;
import com.example.junittest.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * 1. memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 Stubbing
 * 2. studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
 */
@ExtendWith(MockitoExtension.class)
class StudyServiceTest2 {

    @Mock MemberService memberService;

    @Mock StudyRepository studyRepository;

    @Test
    void createMockTest_2(@Mock MemberService memberService,
                          @Mock StudyRepository studyRepository) {
        StudyService studyService = new StudyService(memberService, studyRepository);
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@gmail.com");

        Study study = new Study(10, "테스트");
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);

        assertEquals(member.getId(), study.getOwnerId());
    }

    @Test
    void createMockTest() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@gmail.com");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        assertEquals("jongwook@gmail.com", memberService.findById(1L).get().getEmail());
    }


}