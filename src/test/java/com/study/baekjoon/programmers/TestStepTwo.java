package com.study.baekjoon.programmers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestStepTwo {

    @Test
    @DisplayName("JadenCase 문자열 만들기")
    void solution00() {
        //given
        String message = "3people unFollowed me";
        String expect = "3people Unfollowed Me";

        //when
        char[] array = message.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        boolean upperCheck = true;
        for (char unit : array) {
            if (Character.isWhitespace(unit)) {
                stringBuilder.append(unit);
                upperCheck = true;
                continue;
            }
            if (upperCheck) {
                stringBuilder.append(Character.toUpperCase(unit));
                upperCheck = false;
            } else {
                stringBuilder.append(Character.toLowerCase(unit));
            }
        }

        //then
        assertThat(stringBuilder.toString()).isEqualTo(expect);
    }

    @Test
    @DisplayName("최솟값 만들기 -> 효율성 테스트에서 통과못함....")
    void solution01() {
        //given
        int[] A = new int[]{1, 4, 2};
        int[] B = new int[]{5, 4, 4};
        int expect = 29;

        //when
        Arrays.sort(A);
        List<Integer> bList = Arrays.stream(B).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int sumValue = 0;
        for (int i = 0; i < A.length; i++) {
            sumValue += A[i] * bList.get(i);
        }

        //then
        assertThat(sumValue).isEqualTo(expect);
    }

    @Test
    @DisplayName("우선순위 큐")
    void solution02() {
        //given
        int[] A = new int[]{1, 4, 2};
        int[] B = new int[]{5, 4, 4};
        int expect = 29;

        //when
        int sumValue = 0;

        PriorityQueue<Integer> a = new PriorityQueue<>();
        PriorityQueue<Integer> b = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < A.length; i++) {
            a.add(A[i]);
            b.add(B[i]);
        }

        while (!a.isEmpty()) {
            sumValue += a.poll() * b.poll();
        }

        //then
        assertThat(sumValue).isEqualTo(expect);
    }

    @Test
    @DisplayName("올바른 괄호")
    void solution03() {
        //given
//        String s = "()()";
//        String s = "(())()";
        String s = ")()(";
//        String s = "(()(";
        boolean expect = false;

        //when
        boolean answer;
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        int count = 0;
        for (char unit : chars) {
            if (unit == '(') {
                stack.push('(');
                count++;
            } else {
                if (!stack.isEmpty()) {
                    count++;
                    stack.pop();
                } else {
                    break;
                }
            }
        }
        answer = count != 0 && stack.isEmpty();

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("이진 변환 반복하기")
    void solution04() {
        //given
        String message = "110010101001";
        int[] expect = new int[]{3, 8};

        //when
        int cycleCount = 0;
        int zeroRemoveCount = 0;

        while (true) {
            char[] chars = message.toCharArray();
            for (char unit : chars) {
                if (unit == '0') {
                    zeroRemoveCount++;
                }
            }
            String zeroRemoveValue = message.replace("0", "");
            String binaryValue = Integer.toBinaryString(zeroRemoveValue.length());
            message = binaryValue;

            cycleCount++;

            if (binaryValue.equals("1")) {
                break;
            }
        }

        int[] result = new int[]{cycleCount, zeroRemoveCount};

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("피보나치 수")
    void solution05() {
        //given
        int n = 5;
        int expect = 5;

        //when
        int answer;
        int[] array = new int[2];
        for (int i = 2; i < n; i++) {
            if (i == 2) {
                array[0] = 0;
                array[1] = 1;
            }
            int temp = array[0];
            array[0] = (array[1]) % 1234567;
            array[1] = (temp + array[1]) % 1234567;
        }
        answer = (array[0] + array[1]) % 1234567;

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("짝지어 제거하기")
    void solution06() {
        //given
        String s = "baabaa";
        int expect = 1;

        //when
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (stack.isEmpty()) {
                stack.push(chars[i]);
            } else {
                if (stack.peek() == chars[i]) {
                    stack.pop();
                } else {
                    stack.push(chars[i]);
                }
            }
        }

        answer = stack.isEmpty() ? 1 : 0;

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("영어 끝말잇기")
    void solution07() {
        //given
        int n = 2;
//        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        int[] expect = {1, 3};
        int[] answer = new int[2];

        //when
        List<String> useDatas = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (useDatas.contains(word)) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            if (i > 0 && !words[i - 1].substring(words[i - 1].length() - 1).equals(word.substring(0, 1))) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            useDatas.add(word);
        }

        if (useDatas.size() == words.length) {
            answer[0] = 0;
            answer[1] = 0;
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

}
