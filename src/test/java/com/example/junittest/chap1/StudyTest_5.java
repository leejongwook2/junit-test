package com.example.junittest.chap1;

import com.example.junittest.Study;
import com.example.junittest.annotation.FastTest;
import org.junit.jupiter.api.*;


/**
 * resources 폴더를 정적 자원으로 등록하려고 한다.
 * Open Module Settings > Modules 에서 Test Resources 로 등록시킨다.
 */
public class StudyTest_5 {

    private int value = 1;

    @Order(2)
    @FastTest
    @DisplayName("DisplayName이 가장 우선순위가 높아요.")
    void create_order_2() {
        System.out.println(this);
        System.out.println(value++);
    }

    @Order(1)
    @FastTest
    @Disabled
    void create_order_1() {
        System.out.println(this);
        Study study = new Study(value);
        System.out.println(value++);
    }

    @Order(3)
    @FastTest
    @Disabled
    void create_order_3() {
        System.out.println(this);
        System.out.println(value++);
    }


    @BeforeAll
    static void beforeAll() {}

    @AfterAll
    static void afterAll() {}
}
