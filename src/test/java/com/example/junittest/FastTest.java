package com.example.junittest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 이 어노테이션은 사용한 코드가 런타임 까지 유지를 해야한다 라는 설정
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // 메소드에 쓸 수 있다
@Test
@Tag("fast")
public @interface FastTest {
}
