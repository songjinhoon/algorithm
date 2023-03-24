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
        String message = "3people unFollowed     me ";
        String expect = "3people Unfollowed     Me ";
        StringBuilder stringBuilder = new StringBuilder();

        //when
        char[] array = message.toCharArray();

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
        //when-2 -> 공백떄문에 이렇게 접근하면 안됨
        /*String[] words = message.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("")) {
                stringBuilder.append(" ");
                continue;
            }
            char[] units = words[i].toCharArray();
            for (int k = 0; k < units.length; k++) {
                if (Character.isDigit(units[k])) {
                    stringBuilder.append(units[k]);
                } else {
                    if (k == 0) {
                        stringBuilder.append(Character.toUpperCase(units[k]));
                    } else {
                        stringBuilder.append(Character.toLowerCase(units[k]));
                    }
                }
            }
        }*/
        //then
        assertThat(stringBuilder.toString()).isEqualTo(expect);
    }

    @Test
    @DisplayName("최솟값 만들기 -> 효율성 테스트에서 통과못함....")
    void solution01() {
        /*
         * 1. Collection Sorting 시간복잡도는 O(NlogN)
         * 2. PriorityQueue 시간복잡도는 O(logN)
         * */
        //given
        int[] A = new int[]{1, 4, 2};
        int[] B = new int[]{5, 4, 4};
        int expect = 29;
        int answer1 = 0;
        int answer2 = 0;

        //when
        Arrays.sort(A);
        List<Integer> bList = Arrays.stream(B).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < A.length; i++) {
            answer1 += A[i] * bList.get(i);
        }
        //when -2
        PriorityQueue<Integer> a = new PriorityQueue<>();
        PriorityQueue<Integer> b = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < A.length; i++) {
            a.add(A[i]);
            b.add(B[i]);
        }

        while (!a.isEmpty()) {
            answer2 += a.poll() * b.poll();
        }

        //then
        assertThat(answer1).isEqualTo(expect);
        assertThat(answer2).isEqualTo(expect);
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
        int[] answer = {0, 0};
        int[] expect = new int[]{3, 8};

        //when1
        do {
            answer[0] += 1;
            String zeroRemove = message.replace("0", "");
            answer[1] += message.length() - zeroRemove.length();
            message = Integer.toBinaryString(zeroRemove.length());
        } while (!message.equals("1"));

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("피보나치 수")
    void solution05() {
        //given
        int n = 5;
        int expect = 5, answer = 0;

        //when
        int[] array = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i == 0 || i == 1) {
                array[i] = i % 1234567;
            } else {
                array[i] = (array[i - 2] + array[i - 1]) % 1234567;
            }
        }

        answer = array[n];

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("다음 큰 숫자")
    void solution999() {
        //given
        int n = 15;
        int expect = 23, answer = 0;

        //when
        int oneCountByN = Integer.toBinaryString(n).replace("0", "").length();
        while (true) {
            int value = Integer.toBinaryString(++n).replace("0", "").length();

            if (value == oneCountByN) {
                answer = n;
                break;
            }

        }

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

    @Test
    @DisplayName("N개의 최소공배수")
    void solution12() {
        //given
        int[] arr = {2, 6, 8, 14};
        int answer, expect = 168;

        //when
        Arrays.sort(arr);
        answer = arr[arr.length - 1];
        boolean checker = true;

        while (checker) {
            for (int i = arr.length - 2; i >= 0; i--) {
                if (answer % arr[i] != 0) {
                    answer += arr[arr.length - 1];
                    break;
                }
                if (i == 0) {
                    checker = false;
                }
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("점프와 순간이동")
    void solution13() {
        // 순간이동을 할 수 있냐 없냐
        /*
         * 5	2
         * 6	2
         * 5000	5
         * */
        //given
        int n = 5, expect = 2, answer = 1;

        //when
        int checkValue = n;
        do {
            if (checkValue % 2 != 0) {
                answer++;
            }
            checkValue = checkValue / 2;
        } while (checkValue != 1);

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("멀리 뛰기")
    void solution14() {
        //개수 체크 문제 -> DFS로했는데 .. 시간초과.. 알고보니 이거 피보나치 문제란다.... 후..
        //given
        int n = 4, expect = 5, answer = 0;

        //when
        int[] check = new int[n + 1];
        check[0] = 1;
        check[1] = 1;
        for (int i = 2; i <= n; i++) {
            check[i] = (check[i - 1] + check[i - 2]) % 1234567;
        }
        answer = check[n];

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("H-INDEX")
    void solution15() {
        // NOT UNDERSTAND ZZZ
        //given
        int[] citations = {3, 0, 6, 1, 5};
        int expect = 3, answer = 0;

        //when
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {

            if (citations[i] >= citations.length - i) {
                answer = citations.length - i;
                break;
            }

        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("괄호 회전하기")
    void solution16() {
        //given
        String s = "[](){}";
        int expect = 3, answer = 0;

        //when
        char[] chars = s.toCharArray();
        Queue<Character> storage = new LinkedList<>();
        for (char unit : chars) {
            storage.offer(unit);
        }

        for (int i = 0; i < chars.length - 1; i++) {
            Stack<Character> checkStorage = new Stack<>();

            int count = 0;
            for (Character check : storage) {
                if (check == '[' || check == '(' || check == '{') {
                    checkStorage.push(check);
                } else {
                    if (checkStorage.isEmpty()) {
                        break;
                    } else {
                        Character pop = checkStorage.pop();
                        if (check == ']' && pop != '[') {
                            break;
                        } else if (check == ')' && pop != '(') {
                            break;
                        } else if (check == '}' && pop != '{') {
                            break;
                        }
                    }
                }
                count++;
            }

            if (count == s.length() && checkStorage.isEmpty()) {
                answer++;
            }

            Character poll = storage.poll();
            storage.offer(poll);
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("[1차] 캐시")
    void solution17() {
        //given
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int cacheSize = 3;
        int hit = 1, miss = 5;
        int answer = 0, expect = 50;

        //when
        List<String> storage = new ArrayList<>();
        for (String city : cities) {
            city = city.toLowerCase();
            if (storage.contains(city)) {
                storage.remove(city);
                storage.add(city);
                answer += hit;
            } else {
                if (storage.size() == cacheSize) {
                    storage.remove(0);
                }
                storage.add(city);
                answer += miss;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("행렬의 곱셈")
    void solution18() {
        //given
        int[][] arr1 = {
                {1, 4},
                {3, 2},
                {4, 1}
        };
        int[][] arr2 = {
                {3, 3},
                {3, 3},
        };
        int[][] expect = {
                {15, 15}, // {1*3 + 4*3, 1*3 + 4*3}
                {15, 15}, // {3*3 + 3*2, 3*3 + 2*3}
                {15, 15}
        };
        int[][] answer = new int[arr1.length][arr2[0].length];

        //when
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                for (int k = 0; k < arr1[0].length; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

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
                        break;
                    } else if (array[lp] == checkValue) {
                        position = lp;
                        checkPosition++;
                        break;
                    } else {
                        jump++;
                    }
                }
                answer++;
            }

        } while (checkPosition < check.length);

        //then
        assertThat(answer).isEqualTo(expect);
    }


}
