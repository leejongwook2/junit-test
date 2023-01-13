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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Mock 어노테이션을 사용하기 위해 MockitoExtension 를 확장하였다.
 */
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock MemberService memberService;

    @Mock StudyRepository studyRepository;

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