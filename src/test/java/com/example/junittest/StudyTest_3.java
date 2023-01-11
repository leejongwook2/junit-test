package com.example.junittest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 디폴트 설정은
 * 해시코드 값이 서로 다르다.
 * 테스트 간의 의존성을 없애기 위해서 메소드간의 의존성 없이 별도로 동작한다.
 *
 * - PER_METHOD : test 함수 당 인스턴스가 생성된다. (해시코드 값이 서로 다르다)
 * - PER_CLASS : test 클래스 당 인스턴스가 생성된다. (해시 코드 값이 서로 같다)
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class StudyTest_3 {

    int value = 1; // 항상 1이라는 값을 가지고 테스트를 하게된다.

    @FastTest
    @DisplayName("increse Number1")
    void increaseNumber1() {
        System.out.println(this);
        System.out.println(value);
        Study actual = new Study(value++);
        Assertions.assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @FastTest
    @DisplayName("increse Number2")
    void increaseNumber2() {
        System.out.println(this);
        System.out.println(value);
    }

    @BeforeAll
    static void beforeAll() {}

    @AfterAll
    static void afterAll() {}
}
