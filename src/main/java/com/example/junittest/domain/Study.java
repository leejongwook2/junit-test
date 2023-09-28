package com.example.junittest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 스프링부트 2.2 부터 Junit5를 기본으로 사용하도록 되어있음.
 * Jupiter Vintage
 * Junit Platform
 * 
 * Vintage: Junit4, Junit3 를 지원하는 TestEngine 엔진 구현체 
 * Jupiter: TestEngine API 구현체
 * Junit Platform: 테스트를 실행해주는 런처 제공. TestEngine API 제공
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Study {
    @Id
    @GeneratedValue
    private Long id;
    private StudyStatus status = StudyStatus.DRAFT;
    private int limitCount;
    private String name;
    private LocalDateTime openedDateTime;
    private Long ownerId;
    @ManyToOne
    private Member owner;

    public Study(int limitCount, String name) {
        this.limitCount = limitCount;
        this.name = name;
    }

    public Study(int limitCount) {
        if (limitCount < 0) {
            throw new IllegalArgumentException("limit은 0 보다 커야한다.");
        }
        this.limitCount = limitCount;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limitCount;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getOpenedDateTime() {
        return openedDateTime;
    }

    public void open() {
        this.openedDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;
    }

    public void publish() {
        this.openedDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;
    }

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", limitCount=" + limitCount +
                ", name='" + name + '\'' +
                '}';
    }
}
