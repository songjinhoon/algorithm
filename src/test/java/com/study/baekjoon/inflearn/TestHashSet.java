package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestHashSet {

    @Test
    @DisplayName("학급 회장")
    void solutionA() {
        //given
        int size = 15;
        String message = "BACBACCACCBDEDE";
        char expect = 'C';

        //when
        char result = ' ';
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = message.toCharArray();
        for (char unit : chars) {
            map.merge(unit, 1, Integer::sum);
        }

        for (char key : map.keySet()) {
            if (Character.isWhitespace(result)) {
                result = key;
            } else {
                result = map.get(key) > map.get(result) ? key : result;
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("아나그램 판별(두 문자열의 알파벳 순서는 다르지만 구성이 동일한 경우)")
    void solutionB() {
        //given
        String messageA = "AbaAeCe";
        String messageB = "baeeACA";
        String expect = "YES";

        //when - 1
        String answer = "";
        char[] charsA = messageA.toCharArray();
        char[] charsB = messageB.toCharArray();
        Arrays.sort(charsA);
        Arrays.sort(charsB);
        answer = String.valueOf(charsA).equals(String.valueOf(charsB)) ? "YES" : "NO";

        //when-2
        String answer2 = "YES";
        Map<Character, Integer> map = new HashMap<>();
        for (char unit : messageA.toCharArray()) {
            map.put(unit, map.getOrDefault(unit, 0) + 1); // 머지가좋은듯
        }
        for (char unit : messageB.toCharArray()) {
            if (!map.containsKey(unit) || map.get(unit) == 0) {
                answer = "NO";
                break;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("매출액의 종류 - Hash, Sliding Window")
    void solutionC() {
        //given
        int n = 7;
        int k = 4;
        int[] array = {20, 12, 20, 10, 23, 17, 10};
        List<Integer> expect = List.of(3, 4, 4, 3);

        //when
        List<Integer> answer = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k - 1; i++) {
            map.merge(array[i], 1, Integer::sum);
        }

        int leftPointer = 0;
        for (int rightPointer = k - 1; rightPointer < n; rightPointer++) {
            if (rightPointer >= k) {
                map.put(array[leftPointer], map.get(array[leftPointer]) - 1);
                if (map.get(array[leftPointer]) == 0) {
                    map.remove(array[leftPointer]);
                }
                leftPointer++;
            }

            map.merge(array[rightPointer], 1, Integer::sum);
            answer.add(map.size());
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

}
