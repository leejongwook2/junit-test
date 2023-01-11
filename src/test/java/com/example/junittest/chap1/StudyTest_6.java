package com.example.junittest.chap1;

import com.example.junittest.Study;
import com.example.junittest.annotation.FastTest;
import com.example.junittest.annotation.SlowTest;
import com.example.junittest.util.FindSlowTestExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;


// 확장기능 구현하기
//@ExtendWith(FindSlowTestExtension.class) // FindSlowTestExtension 인스턴스를 직접 생성 시 이 코드는 주석처리해야 함
public class StudyTest_6 {
    private static int value = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @SlowTest
    void create_method_test_1() throws InterruptedException {
        Thread.sleep(1000L);
        System.out.println(this);
        Study study = new Study(value);
        System.out.println(value++);
    }

    @SlowTest
    void create_method_test_2() {
        System.out.println(this);
        System.out.println(value++);
    }

    @FastTest
    void create_method_test_3() {
        System.out.println(this);
        System.out.println(value++);
    }

    @BeforeAll
    static void beforeAll() {}

    @AfterAll
    static void afterAll() {}
}
