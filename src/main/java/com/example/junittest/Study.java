package com.example.junittest;

/**
 * 스프링부트 2.2 부터 Junit5를 기본으로 사용하도록 되어있음.
 * Jupiter Vintage
 * Junit Platform
 * 
 * Vintage: Junit4, Junit3 를 지원하는 TestEngine 엔진 구현체 
 * Jupiter: TestEngine API 구현체
 * Junit Platform: 테스트를 실행해주는 런처 제공. TestEngine API 제공
 */
public class Study {
    private StudyStatus status = StudyStatus.DRAFT;
    private int limit;

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0 보다 커야한다.");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }
}
