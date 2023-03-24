package com.study.baekjoon.hue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestA {

    @Test
    @DisplayName("xx")
    void test01() {
        //given
        int[] s = {1, 2, 3};
        int expect = 1;
        int answer = 0;

        //when
        boolean checker = true;
        for (int i = 0; i < s.length - 1; i++) {
            if (checker) {
                if (s[i] > s[i + 1]) {
                    answer++;
                }
                checker = false;
            } else {
                if (s[i] < s[i + 1]) {
                    answer++;
                }
                checker = true;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("xx4")
    void xx4() {
        //given
        int[] array = {2, 1, 3, 1, 2, 4, 4, 3}; // 8
        int[] check = {1, 2, 3, 4}; //4
//        int[] check = {4, 3, 2, 1}; //5
        int expect = 4, answer = 0;

        //when
        int position = 0;
        int checkPosition = 0;
        do {
            int jump = 1;
            int checkValue = check[checkPosition];

            while (true) {
                if (array[position] != checkValue) {
                    int rP = position == array.length - 1 ? jump - 1 : position + jump;
                    int lp = position == 0 ? array.length - 1 - jump + 1 : position - jump;
                    if (array[rP] == checkValue) {
                        position = rP;
                        checkPosition++;
                        answer++;
                        break;
                    } else if (array[lp] == checkValue) {
                        position = lp;
                        checkPosition++;
                        answer++;
                        break;
                    } else {
                        jump++;
                    }
                }
            }

        } while (checkPosition < check.length);

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("xx5")
    void xx5() {
        //given
        int[][] envelopes = {
                {3, 4},
                {1, 3},
                {2, 5},
                {1, 2},
                {3, 5},
                {2, 3}
        };
        int expect = 3;
        int answer = 0;

        //when
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < envelopes.length; i++) {
            int[] envelope = envelopes[i];
            boolean checker = false;
            for (int k = 0; k < envelopes.length; k++) {
                if (k == i) {
                    continue;
                }
                int[] next = envelopes[k];
                if (envelope[0] > next[0] && envelope[1] > next[1]) {
                    if (map.get(k) == null) {
                        map.put(k, 1);
                        checker = true;
                    }
                }
                if (envelope[0] < next[0] && envelope[1] < next[1]) {
                    checker = false;
                    break;
                }
                if (k == envelope.length - 1) {
                    checker = true;
                }
            }
            if (checker) {
                answer++;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);

    }

}
