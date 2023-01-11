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
    void create() {
        Study actual = new Study();
        assertNotNull(study);
    }
    // 바깥에서 테스트를 실행하면 전체 테스트 실행하고
    // 테스트 메소드 안에서 실행 시 그 메소드만 테스트를 실행함
    @Test
    @Disabled
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