package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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

    @Test
    @DisplayName("점수계산")
    void scoreCalculate() {
        //given
        int[] scores = new int[]{1, 0, 1, 1, 1, 0, 0, 1, 1, 0};
        int expect = 10;

        //when
        int result = 0;
        int point = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == 0) {
                point = 0;
            } else {
                point++;
                result += point;
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("등수구하기")
    void findPlace() {
        //given
        int[] array = new int[]{87, 89, 92, 100, 76};
        int[] expect = new int[]{4, 3, 2, 1, 5};

        //when
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int value = 1;
            for (int k = 0; k < array.length; k++) {
                if (k != i) {
                    if (array[i] < array[k]) {
                        value++;
                    }
                } else {

                }
            }
            result[i] = value;
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("격자판 최대합")
    void solution00() {
        //given
        int[][] array = new int[][]{
                {
                        10, 13, 10, 12, 15
                },
                {
                        12, 39, 30, 23, 11
                },
                {
                        11, 25, 50, 53, 15
                },
                {
                        19, 27, 29, 37, 27
                },
                {
                        19, 13, 30, 13, 19
                }
        };
        int expect = 155;

        //when
        int maxRow = 0, maxCell = 0, left = 0, right = 0;
        for (int i = 0; i < array.length; i++) {
            int sumRow = 0;
            int sumCell = 0;
            for (int k = 0; k < array.length; k++) {
                sumRow += array[i][k];
                sumCell += array[k][i];
            }
            maxRow = Math.max(maxRow, sumRow);
            maxCell = Math.max(maxCell, sumCell);
            left += array[i][i];
            right += array[i][array.length - 1 - i];
        }
        Integer result = Collections.max(List.of(maxRow, maxCell, left, right));

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("봉우리")
    void peak() {
        //given
        int[][] array = new int[][]{
                {
                        5, 3, 7, 2, 3
                },
                {
                        3, 7, 1, 6, 1
                },
                {
                        7, 2, 5, 3, 4
                },
                {
                        4, 3, 6, 4, 1
                },
                {
                        8, 7, 3, 5, 2
                }
        };
        int expect = 10;

        //when
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int k = 0; k < array.length; k++) {
                int checkValue = array[i][k];
                int top = i == 0 ? 0 : array[i - 1][k];
                int right = k == array.length - 1 ? 0 : array[i][k + 1];
                int bottom = i == array.length - 1 ? 0 : array[i + 1][k];
                int left = k == 0 ? 0 : array[i][k - 1];
                if (checkValue == Collections.max(List.of(checkValue, top, right, bottom, left))) {
                    result++;
                }
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("임시반장정하기")
    void solution11A() {
        //given
        int[][] array = new int[][]{
                {
                        2, 3, 1, 7, 3
                },
                {
                        4, 1, 9, 6, 8
                },
                {
                        5, 5, 2, 4, 4
                },
                {
                        6, 5, 2, 6, 7
                },
                {
                        8, 4, 2, 2, 2
                }
        };
        int expect = 4;

        //when
        Map<Integer, Integer> map = new HashMap<>(Map.of(1, 0, 2, 0, 3, 0, 4, 0, 5, 0));

        for (int i = 0; i < array.length; i++) { // 1번학생
            List<Integer> friends = new ArrayList<>();
            for (int j = 0; j < array.length; j++) { // 1학년
                int checkValue = array[i][j];
                for (int k = 0; k < array.length; k++) { // 2번학생, 3번학생, 4번학생~
                    if (k != i) {
                        int otherValue = array[k][j];
                        if (checkValue == otherValue && !friends.contains(k + 1)) {
                            friends.add(k + 1);
                            map.merge(i + 1, 1, Integer::sum);
                        }
                    }
                }
            }
        }

        int resultKey = 0, resultValue = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) > resultValue) {
                resultKey = key;
                resultValue = map.get(key);
            }
        }

        //then
        assertThat(resultKey).isEqualTo(expect);
    }

    @Test
    @DisplayName("임시반장정하기")
    void solution11B() {
        //given
        int[][] array = new int[][]{
                {
                        2, 3, 1, 7, 3
                },
                {
                        4, 1, 9, 6, 8
                },
                {
                        5, 5, 2, 4, 4
                },
                {
                        6, 5, 2, 6, 7
                },
                {
                        8, 4, 2, 2, 2
                }
        };
        int expect = 4;

        //when
        int studentNumber = 0, maxFriendCount = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) { // 1번학생
            int friendCount = 0;
            for (int j = 0; j < array.length; j++) { // 2번학생
                if (i != j) {
                    for (int k = 0; k < array.length; k++) { // 1학년 2학년 3학년
                        if (array[i][k] == array[j][k]) {
                            friendCount++;
                            break;
                        }
                    }
                }
            }
            if (friendCount > maxFriendCount) {
                maxFriendCount = friendCount;
                studentNumber = i + 1;
            }
        }

        //then
        assertThat(studentNumber).isEqualTo(expect);
    }

    @Test
    @DisplayName("멘토링")
    void solution12A() {
        //given
        int[][] array = new int[][]{
                {
                        3, 4, 1, 2
                },
                {
                        4, 3, 2, 1
                },
                {
                        3, 1, 4, 2
                }
        };
        int expect = 3;

        //when
        for (int i = 0; i < array.length; i++) { //학생 반복
            boolean[] students = new boolean[array.length];
            Arrays.fill(students, true);
            boolean checker = false;
            for (int j = 0; j < array.length; j++) { // 멘티 가능 학생 체크
                for (int k = 0; k < array.length; k++) {
                    if (array[j][k] == i + 1) {
                        checker = true;
                    } else {
                        students[k] = checker;
                    }
                }
            }
        }


        //then
    }

}
