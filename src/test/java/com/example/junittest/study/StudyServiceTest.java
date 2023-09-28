package com.example.junittest.study;

import com.example.junittest.domain.Member;
import com.example.junittest.domain.Study;
import com.example.junittest.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Mock 어노테이션을 사용하기 위해 MockitoExtension 를 확장하였다.
 *
 * 테스트 스텁은 테스트 중에 만들어진 호출에 대해 미리 준비된 답변을 제공하는 것
 * 만들어진 mock 객체의 메소드를 실행했을 때 어떤 리턴 값을 리턴할지를 정의하는 것이라고 생각하면됨.
 * 더 쉽게 예를 들자면 내가 아이디가 1L 인 값으로 User를 찾을 때 너는 username = "jongwook" 인  특정 유저 객체를 반환해 라고 정의할 수 있다고.
 */
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock MemberService memberService;

    @Mock StudyRepository studyRepository;

    /**
     * 메소드가 동일한 매개변수로 여러번 호출 될 떄 각기 다른 행동을 하도록 조작할 수 있다.
     */
    @Test
    void createExceptionTest_3() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@email.com");

        // 1번째로 호출되면 member객체를 리턴
        // 2번쨰로 호출되면 RuntimeException을 리턴
        // 3번쨰 호출 시 empty 객체 반환
        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException("시간이 다돼었어요"))
                .thenReturn(Optional.empty());

        // 1번째 실행
        Optional<Member> byId = memberService.findById(1L);
        assertEquals("jongwook@email.com", byId.get().getEmail());

        // 2번째 실행. 에러를 던저라. memberService가 findById(2L) 를 호출할 때
        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        // 3번째 실행
        assertEquals(Optional.empty(), memberService.findById(3L));
    }

    @Test
    void createExceptionTest_2() {
        doThrow(new IllegalArgumentException()).when(memberService).validate(2L);
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(2L);
        });
//        memberService.validate(1L);
    }

    @Test
    void createExceptionTest() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@email.com");

        // 1L 로 Member를 Search 하게 되면 RuntimeException 에러가 발생한다.
        when(memberService.findById(1L)).thenThrow(new RuntimeException("타임 아웃 에러가 발생하였습니다."));
//        when(memberService.findById(2L)).thenReturn(Optional.of(member));

        Optional<Member> finededMember = memberService.findById(1L);
        assertEquals("jongwook@email.com", finededMember.get().getEmail());

        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
    }

    @Test
    void createStudyService_4() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@email.com");

        when (memberService.findById(any())).thenReturn(Optional.of(member));

        Study study = new Study(10, "java");

        // 어떤 값을 넣든지간에 테스트가 성공한다. 위에서 인자로 any()를 넣었기 때문에
        Optional<Member> findById = memberService.findById(4L);
//        Optional<Member> findById = memberService.findById(3L);
//        Optional<Member> findById = memberService.findById(2L);
//        Optional<Member> findById = memberService.findById(1L);
        assertEquals("jongwook@email.com", findById.get().getEmail());
    }

    @Test
    void createStudyService_3() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("jongwook@email.com");

        // 1L 이라는 값이 인자로 들어갔을 때 member 객체를 리턴해라 라고 정의를 했다.
        // 그래서 여기서 넘기는 인자 "1L" 가 중요하다.
        when (memberService.findById(1L)).thenReturn(Optional.of(member));
//        when (memberService.findById(any())).thenReturn(Optional.of(member));

        Study study = new Study(10, "java");

        Optional<Member> findById = memberService.findById(1L);
        assertEquals("jongwook@email.com", findById.get().getEmail());
    }

    /**
     * 메소드에 어노테이션 걸수도 있다.
     * @param memberService
     * @param studyRepository
     */
    @Test
    void createStudyService_2(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        // 위의 Mock 어노테이션을 사용하여 만든 memberService, studyRepository 를 사용하여 테스트를 해본다.
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    @Test
    void createStudyService() {
        /**
         * studyService를 인스턴스화 하기 위해서는 memberService, repository 인자가 필요하다.
         * 의존하고 있는 위 2개의 클래스가 필요하지만 이때 mock 을 사용하면 위 2개의 인자를 mocking 할 수 있음.
         */
        MemberService memberService = mock(MemberService.class);
//        MemberService memberService = new MemberService() {
//            @Override
//            public Optional<Member> findById(Long memberId) {
//                return Optional.empty();
//            }
//        };

        // 이렇게 Mock 을 생성하면 직접 객체를 생성할 필요가 없음.
        StudyRepository studyRepository = mock(StudyRepository.class);
//        StudyRepository studyRepository = new StudyRepository() {
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public <S extends Study> S saveAndFlush(S entity) {
//                return null;
//            }
//
//            @Override
//            public <S extends Study> List<S> saveAllAndFlush(Iterable<S> entities) {
//                return null;
//            }
//
//            @Override
//            public void deleteAllInBatch(Iterable<Study> entities) {
//
//            }
//
//            @Override
//            public void deleteAllByIdInBatch(Iterable<Long> longs) {
//
//            }
//
//            @Override
//            public void deleteAllInBatch() {
//
//            }
//
//            @Override
//            public Study getOne(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public Study getById(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public Study getReferenceById(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public <S extends Study> List<S> findAll(Example<S> example) {
//                return null;
//            }
//
//            @Override
//            public <S extends Study> List<S> findAll(Example<S> example, Sort sort) {
//                return null;
//            }
//
//            @Override
//            public <S extends Study> List<S> saveAll(Iterable<S> entities) {
//                return null;
//            }
//
//            @Override
//            public List<Study> findAll() {
//                return null;
//            }
//
//            @Override
//            public List<Study> findAllById(Iterable<Long> longs) {
//                return null;
//            }
//
//            @Override
//            public <S extends Study> S save(S entity) {
//                return null;
//            }
//
//            @Override
//            public Optional<Study> findById(Long aLong) {
//                return Optional.empty();
//            }
//
//            @Override
//            public boolean existsById(Long aLong) {
//                return false;
//            }
//
//            @Override
//            public long count() {
//                return 0;
//            }
//
//            @Override
//            public void deleteById(Long aLong) {
//
//            }
//
//            @Override
//            public void delete(Study entity) {
//
//            }
//
//            @Override
//            public void deleteAllById(Iterable<? extends Long> longs) {
//
//            }
//
//            @Override
//            public void deleteAll(Iterable<? extends Study> entities) {
//
//            }
//
//            @Override
//            public void deleteAll() {
//
//            }
//
//            @Override
//            public List<Study> findAll(Sort sort) {
//                return null;
//            }
//
//            @Override
//            public Page<Study> findAll(Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends Study> Optional<S> findOne(Example<S> example) {
//                return Optional.empty();
//            }
//
//            @Override
//            public <S extends Study> Page<S> findAll(Example<S> example, Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends Study> long count(Example<S> example) {
//                return 0;
//            }
//
//            @Override
//            public <S extends Study> boolean exists(Example<S> example) {
//                return false;
//            }
//
//            @Override
//            public <S extends Study, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//                return null;
//            }
//        };
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

}