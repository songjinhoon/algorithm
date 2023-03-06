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

    @Test
    @DisplayName("카펫 - 완전탐색")
    void solution08() {
        /*
         *  x >= y && y >= 3 &&  x >= 3
         * */
        //given
        int brown = 10, yellow = 2;
        int[] expect = {4, 3};
        int[] answer = new int[2];

        //when
        int number = brown + yellow;

        for (int x = 3; x <= number; x++) {
            int y = number / x;
            boolean check = number % x == 0;
            if (x >= y && x >= 3 && y >= 3 && check && ((x - 2) * (y - 2) == yellow)) {
                answer[0] = x;
                answer[1] = y;
                break;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("구명보트 - 탐욕법? - 풀이1")
    void solution09() {
        /*
         * 100에 최대한 가깝게 구명보트를 채워야하는줄 알았는데 이렇게 하니까 효율성에서 통과를 못했다. 풀이 2에서 효율성까지 체크하겠다.
         * 조건1: 한번에 최대 2명만 탈수있다.
         * */
        //given
        int[] people = {20, 20, 60, 20}; // 80 70 50 50
        int limit = 1000;
        int expect = 2, answer = 0;

        //when
        List<Integer> datas = Arrays.stream(people).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int[] checks = new int[people.length];
        for (int i = 0; i < datas.size(); i++) {
            if (checks[i] == 1) {
                continue;
            }
            answer++;
            checks[i] = 1;
            for (int j = i + 1; j < datas.size(); j++) {
                if (datas.get(i) + datas.get(j) <= limit && checks[j] == 0) {
                    checks[j] = 1;
                    break;
                }
            }
            if (Arrays.stream(checks).boxed().noneMatch(data -> data == 0)) {
                break;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("구명보트 - 탐욕법? - 풀이2")
    void solution10() {
        // 리버스 정렬떄문에 효율성 통과 못하는듯?
        //given
        int[] people = {70, 50, 80, 50}; // 80 70 50 50
        int limit = 100;
        int expect = 3, answer = 0;

        //when
        Arrays.sort(people);
        int leftPointer = 0;
        int rightPointer = people.length - 1;

        while (leftPointer <= rightPointer) {
            if (people[leftPointer] + people[rightPointer] <= limit) {
                leftPointer++;
            }
            rightPointer--;
            answer++;
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("예상 대진표")
    void solution11() {
        //given
        int n = 8, a = 7, b = 4;
        int expect = 3, answer = 1;

        //when
        int maxRound = n / 2;
        int checkRound = 0;

        Queue<Integer> storage = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            storage.offer(i + 1);
        }

        while (true) {
            checkRound++;
            int userA = Objects.requireNonNull(storage.poll());
            int userB = Objects.requireNonNull(storage.poll());
            if (userA == a && userB == b || userB == a && userA == b) {
                break;
            } else {
                if (userB == a || userB == b) {
                    storage.offer(userB);
                } else if (userA == a || userA == b) {
                    storage.offer(userA);
                } else {
                    storage.offer(Math.max(userA, userB));
                }
            }
            if (checkRound == maxRound) {
                answer++;
                maxRound = maxRound / 2;
                checkRound = 0;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }


}
