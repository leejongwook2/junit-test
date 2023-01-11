package com.example.junittest;

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
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

/**
 * Ctrl + P 를 누르면 해당 인자에 들어가는 파라미터 종류를 체크할 수 있다.
 */
public class StudyTest_2 {

    @DisplayName("argumentAccessor2 테스트")
    @ParameterizedTest(name = "{displayName}, {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void argumentAccessor2(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study);
    }

    @DisplayName("argumentAccessor1 테스트")
    @ParameterizedTest(name = "{displayName}, {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void argumentAccessor(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            Study study = new Study(accessor.getInteger(0), accessor.getString(1));
            return study;
        }
    }

    @DisplayName("convertStudy 테스트")
    @ParameterizedTest(name = "{displayName}, {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void csvTest(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @DisplayName("convertStudy 테스트")
    @ParameterizedTest(name = "{displayName}, {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void convertStudy(@ConvertWith(StudyConvert.class) Study study) {
        System.out.println(study.getLimit());
    }

    static class StudyConvert extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            Assertions.assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }


    @DisplayName("convertNumber 테스트")
    @ParameterizedTest(name = "{displayName}, {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void convertNumber(Integer limi) {
        System.out.println(limi);
    }

    @DisplayName("nullEmpty 테스트")
    @ParameterizedTest(name = "{displayName}, {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
    @NullAndEmptySource
    void nullEmptyTest(String message) {
        System.out.println(message);
    }

    @DisplayName("테스트 반복하기")
    @Disabled
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test : " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("복수 파라미터를 설정하여 순회한다.")
    @Disabled
    @ParameterizedTest(name="{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
    void parameterizedTest(String message) {
        System.out.println(message);
    }

}
