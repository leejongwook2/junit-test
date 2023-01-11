package com.example.junittest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Web 프로젝트를 생성하지 않아도 
 * Junit-jupiter-engine 라이브러리만 설치해주면 해당 테스트를 진행할 수 있음.
 */
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) 메소드명을 대문자로 끊어서 공백으로 변환한 뒤 표시한다.
class StudyTest {

    @Test
    @DisplayName("assertThat 사용")
    void assertThatTest() {
        Study actual = new Study(10);
//        assertThat(actual.getLimit()).isGreaterThan(10);
        assertThat(actual.getLimit()).isEqualTo(10);
    }

    /**
     * assertTimeoutPreemptively ( 왠만하면 쓰지 말라고 함. ThreadLocal 관련 문제가 있다고함 )
     * assertTimeout
     * 2개의 메소드 차이점은 assertTimeoutPreemptively 는
     * 지정한 time 시간은 over 했을 때 더 이상 메소드를 진행하지 않고
     * 바로 종료시켜버림.
     * 반대로 assertTimeout 는 메소드를 다 실행시킨다.
     */
    @Test
    @DisplayName("시간 테스트")
    void create_new_test() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            // 이 코드 블록은 100ms 보다는 오래걸림..
            // 그럼 이 테스트는 실패한다.
            new Study(10);
            Thread.sleep(300);
        });
    }

    @Test
    @DisplayName("에러가 발생했을 때 테스트")
    @Disabled
    void generateErrorTest() {
        // () -> new StudyTest(-10) 이 메소드를 실행하였을 때 IllegalArgumentException.class 이 에러가 발생한다. 는 테스를 진행한다.
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        assertEquals("limit은 0 보다 커야한다.", message);
    }
    @Test
    @Disabled
    @DisplayName("스터디 만들기")
    void create() {
        Study study = new Study(10);
        /**
         * 만약 테스트 도중 중간에 어느 메소드에서 테스트가 실패되었을 때
         * 그 아래 테스트는 더이상 실행하지 않음.
         * 그러나 assertAll을 사용하면 
         * 메소드의 성공 유무와 관계없이 3개의 assert 메소드를 모두 실행시킨다.
         */
//        assertAll(
//                () -> assertNotNull(study), // Null이 아닌지 테스트 한다.
//                () -> assertEquals(StudyStatus.ENDED, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT 이여야 한다."),
//                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
//        );
    }
    // 바깥에서 테스트를 실행하면 전체 테스트 실행하고
    // 테스트 메소드 안에서 실행 시 그 메소드만 테스트를 실행함
    @Test
    @Disabled
    @DisplayName("execute create1 another method")
    void create1() {
        // @Disabled 어노테이션 붙으면 해당 테스트는 실행하지 않음.
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }


    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }
}