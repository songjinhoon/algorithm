package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    @DisplayName("모든 아나그램 찾기 - Hash, Sliding Window")
    void solutionD() {
        //given
        String s = "bacaAacba";
        String t = "abc";
        int expect = 3;
        int answer = 0;

        //when
        Map<Character, Integer> compareMap = new HashMap<>();
        for (char unit : t.toCharArray()) {
            compareMap.merge(unit, 1, Integer::sum);
        }

        char[] chars = s.toLowerCase().toCharArray();
        Map<Character, Integer> checkMap = new HashMap<>();
        for (int i = 0; i < t.length() - 1; i++) {
            checkMap.merge(chars[i], 1, Integer::sum);
        }

        int leftPointer = 0;
        for (int rightPointer = t.length() - 1; rightPointer < s.length(); rightPointer++) {
            if (rightPointer >= t.length()) {
                checkMap.put(chars[leftPointer], checkMap.get(chars[leftPointer]) - 1);
                if (checkMap.get(chars[leftPointer]) == 0) {
                    checkMap.remove(chars[leftPointer]);
                }
                leftPointer++;
            }
            checkMap.merge(chars[rightPointer], 1, Integer::sum);
            if (checkMap.equals(compareMap)) {
                answer++;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("K번쨰 큰 수")
    void solutionE() {
        /*
         * 지금 풀이는 처음에 정렬 후 타겟 인덱스에 도달하는 시점에 반복을 끝냇기 때문에 HashSet을 사용하였다.
         * 만약, 정렬을 실행하지 않고 모든 데이터의 합을 구한뒤 해당 인덱스의 값을 구하려면 TreeSet이 맞는거 같다.
         * */
        //given
        int numberLength = 10;
        int targetIndex = 3;
        List<Integer> numbers = List.of(13, 15, 34, 23, 45, 65, 33, 11, 26, 42);
        int expect = 143;
        int answer = 0;

        //when
        List<Integer> numberFormats = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()); // [65, 45, 42, 34, 33, 26, 23, 15, 13, 11]
        Set<Integer> datas = new HashSet<>();

        for (int i = 0; i < numberFormats.size(); i++) {
            for (int j = 1; j < numberFormats.size(); j++) {
                for (int k = 2; k < numberFormats.size(); k++) {
                    datas.add(numberFormats.get(i) + numberFormats.get(j) + numberFormats.get(k));
                    if (datas.size() == targetIndex) {
                        answer = numberFormats.get(i) + numberFormats.get(j) + numberFormats.get(k);
                        break;
                    }
                }
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

}
