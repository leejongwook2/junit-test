package com.example.junittest.chap1;

import com.example.junittest.domain.Study;
import com.example.junittest.annotation.SlowTest;
import org.junit.jupiter.api.*;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyTest_4 {

    private int value = 1;

    @Order(2)
    @SlowTest
    @DisplayName("order2")
    void order2() {
        System.out.println(this);
        System.out.println(value++);
    }

    @Order(1)
    @SlowTest
    @DisplayName("order1")
    void order1() {
        System.out.println(this);
        Study study = new Study(value);
        System.out.println(value++);
    }

    @Order(3)
    @SlowTest
    @DisplayName("order3")
    void order3() {
        System.out.println(this);
        System.out.println(value++);
    }


    @BeforeAll
    static void beforeAll() {}

    @AfterAll
    static void afterAll() {}
}
