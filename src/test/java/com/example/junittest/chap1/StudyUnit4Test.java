package com.example.junittest.chap1;

import org.junit.Before;
import org.junit.Test;

public class StudyUnit4Test {

    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void createTest() {
        System.out.println("test");
    }

    @Test
    public void createTest2() {
        System.out.println("test2");
    }

    @Test
    public void createTest3() {
        System.out.println("test3");
    }
}
