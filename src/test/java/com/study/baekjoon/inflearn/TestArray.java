package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TestArray {

    @Test
    @DisplayName("배열에서 가장 큰 수 출력하기")
    void findMaxValue() {
        //given
        int[] ints = new int[]{7, 3, 9, 5, 6, 50};
        int expect = 12, result;

        //when
        result = Arrays.stream(ints).max().orElseGet(() -> 0);

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("학생이 일렬로 서있을 때 선생님이 볼 수 있는 학생의 수")
    void checkArray() {
        //given
        int[] studentKeys = new int[]{130, 135, 148, 140, 145, 150, 150, 153};
        int expect = 5, result;

        //when
        int checkHeight = studentKeys[0];
        int count = 1;
        for (int i = 1; i < studentKeys.length; i++) {
            if (checkHeight < studentKeys[i]) {
                checkHeight = studentKeys[i];
                count++;
            }
        }
        result = count;

        //then
        assertThat(result).isEqualTo(count);
    }

    @Test
    @DisplayName("두 배열 크기 비교 - 가위바위보")
    void twoArrayCompare() {
        //given
        int[] aArray = new int[]{2, 3, 3, 1, 3};
        int[] bArray = new int[]{1, 1, 2, 2, 3};
        char[] expect = new char[]{'A', 'B', 'A', 'B', 'D'};
        char[] result = new char[5];

        //when
        // 경우의 수 3개
        for (int i = 0; i < 5; i++) {
            if (aArray[i] == bArray[i]) {
                result[i] = 'D';
            } else if (aArray[i] == 1 && bArray[i] == 3) {
                result[i] = 'A';
            } else if (aArray[i] == 2 && bArray[i] == 1) {
                result[i] = 'A';
            } else if (aArray[i] == 3 && bArray[i] == 2) {
                result[i] = 'A';
            } else {
                result[i] = 'B';
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("피보나치 수열")
    void fibonacci() {
        //given
        int size = 10;
        int[] expect = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        int[] result = new int[size];

        //when
        for (int i = 0; i < size; i++) {
            if (i == 0 || i == 1) {
                result[i] = 1;
            } else {
                result[i] = result[i - 1] + result[i - 2];
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("소수(에라토스테네스 체)")
    void findPrimeNumber() {
        //given
        // 2,3,5,7,11,13,17,19
        int range = 20;
        int expect = 8, result = 0;

        //when
        int[] check = new int[range + 1];
        for (int i = 2; i <= range; i++) {
            if (check[i] == 0) {
                result++;
                for (int j = i; j <= range; j = j + i) {
                    check[j] = 1;
                }
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("뒤집은 소수")
    void checkPrimeNumber() {
        //given
        int[] array = new int[]{32, 55, 62, 20, 250, 370, 200, 30, 100};
        List<Integer> expect = List.of(23, 2, 73, 2, 3);

        //when
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            int temp = array[i], res = 0;
            while (temp > 0) {
                int t = temp % 10;
                res = res * 10 + t;
                temp = temp / 10;
            }

            if (isPrime(res)) {
                result.add(res);
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    private boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
