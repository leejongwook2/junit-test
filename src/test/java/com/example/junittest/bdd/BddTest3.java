package com.example.junittest.bdd;

import com.example.junittest.domain.Member;
import com.example.junittest.domain.Study;
import com.example.junittest.member.MemberService;
import com.example.junittest.study.StudyRepository;
import com.example.junittest.study.StudyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * 1. memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 Stubbing
 * 2. studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
 */
@ExtendWith(MockitoExtension.class)
class BddTest3 {

    @Mock MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    @DisplayName("BDD 스타일로 바꾸기")
    void createNewTest3() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@gmail.com");

        // given 스터빙이 되어있어야만 테스트가 성공적으로 성공할 수 있다.
        Study study = new Study(10, "테스트");
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // When
        // 이런 일을 했을 때
        studyService.createNewStudy(1L, study);

        // Then
        then(memberService).should(times(1)).notify(study);
        then(memberService).should(times(1)).notify(member);
        // memberService에서 nofify(Study.class)[특정 액션 이후에] 이후에 더 이상 실해시키는 작업은 없다?
        then(memberService).shouldHaveNoMoreInteractions();
    }

}