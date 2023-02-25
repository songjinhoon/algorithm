package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestRecursiveTreeGraph {

    @Test
    @DisplayName("재귀함수")
    void solutionA() {
        //given
        int number = 5;

        //when
        customFunc(number);
    }

    private void customFunc(int number) {
        if (number != 0) {
            customFunc(number - 1);
            System.out.println(number);
        }
    }

    @Test
    @DisplayName("재귀함수 이진수 출력")
    void solutionB() {
        int number = 11;
        customFuncA(number);
    }

    private void customFuncA(int number) {
        if (number != 0) {
            customFuncA(number / 2);
            System.out.println(number % 2);
        }
    }

    @Test
    @DisplayName("퍅토리얼")
    void solutionC() {
        int number = 4;
        System.out.println(customfuncC(number));
    }

    private int customfuncC(int number) {
        if (number != 1) {
            return number * customfuncC(number - 1);
        } else {
            return number;
        }
    }

}
