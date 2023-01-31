package com.example.junittest.study;

import com.example.junittest.domain.Member;
import com.example.junittest.domain.Study;
import com.example.junittest.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * 1. memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 Stubbing
 * 2. studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
 */
@ExtendWith(MockitoExtension.class)
class StudyServiceTest3 {

    @Mock MemberService memberService;

    @Mock StudyRepository studyRepository;


    @Test
    @DisplayName("createNewStudy 메소드가 실행된 상황에서 notify 메소드가 몇번 호출되었는지 확인한다.")
    void createNewTest() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@gmail.com");

        // when 스터빙이 되어있어야만 테스트가 성공적으로 성공할 수 있다.
        Study study = new Study(10, "테스트");
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        
        // 이런 일을 했을 때
        studyService.createNewStudy(1L, study);

        // 이 Mock에 있는 notify가 호출이 되었느냐를 확인 할 수 있다.
        // 몇 번이 정확히 호출이 되었는지, 즉 정확히 2번 notify 메소드가 어떤 인자를 가지고 호출이 되었는지...
        verify(memberService, times(1)).notify(study);

        // validate는 전혀 호출이 되지 않는다.
        verify(memberService, never()).validate(any());
    }


    @Test
    @DisplayName("메소드 실행 순서로 테스트 한다.")
    void createNewTest2() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@gmail.com");

        // when 스터빙이 되어있어야만 테스트가 성공적으로 성공할 수 있다.
        Study study = new Study(10, "테스트");
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        // 이런 일을 했을 때
        studyService.createNewStudy(1L, study);

        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study); // 이 메소드가 먼저 실행되고
        inOrder.verify(memberService).notify(member); // 이 메소드가 그 다음에 실행이 된다.
    }

    @Test
    @DisplayName("verifyNoMoreInteractions 기능 테스트")
    void createNewTest3() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@gmail.com");

        // when 스터빙이 되어있어야만 테스트가 성공적으로 성공할 수 있다.
        Study study = new Study(10, "테스트");
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        // When
        // 이런 일을 했을 때
        studyService.createNewStudy(1L, study);

        // Then
        // 이 Mock에 있는 notify가 호출이 되었느냐를 확인 할 수 있다.
        // 몇 번이 정확히 호출이 되었는지, 즉 정확히 2번 notify 메소드가 어떤 인자를 가지고 호출이 되었는지...
        verify(memberService, times(1)).notify(study);
        verify(memberService, times(1)).notify(member);
//        verify(memberService, times(1)).validate(1L);

        // memberService에서 nofify(Study.class)[특정 액션 이후에] 이후에 더 이상 실해시키는 작업은 없다?
        verifyNoMoreInteractions(memberService);
    }

}