package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestStackQueue {

    @Test
    @DisplayName("올바른 괄호")
    void solutionA() {
        //자주나오는군
        //given
        String message = "(()(())))((";
        String expect = "NO";
        String answer = "";

        //when
        Stack<Character> stack = new Stack<>();

        for (char unit : message.toCharArray()) {
            if (unit == '(') {
                stack.push('(');
            } else {
                if (stack.isEmpty()) {
                    answer = "NO";
                    break;
                } else {
                    stack.pop();
                }
            }
        }

        answer = answer.equals("NO") || !stack.isEmpty() ? "NO" : "YES";

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("괄호문자제거")
    void solutionB() {
        //given
        String message = "(A(BC)D)EF(G(H)(IJ)K)LM(N)";
        String expect = "EFLM";

        //when
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char unit : message.toCharArray()) {
            if (unit == '(') {
                stack.push('(');
            } else if (unit == ')') {
                stack.pop();
            } else {
                if (stack.isEmpty()) {
                    stringBuilder.append(unit);
                }
            }
        }

        //then
        assertThat(stringBuilder.toString()).isEqualTo(expect);
    }

    @Test
    @DisplayName("크레인 인형뽑기(카카오)")
    void solutionC() {
        /*
         * 스택을 활용해서 풀어보겠다.
         * */
        //given
        int[][] boards = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}
        };
        int limitCount = 8;
        int[] targetNumbers = {1, 5, 3, 5, 1, 2, 1, 4};
        int expect = 4;
        int answer = 0;

        //when
        Stack<Integer> stack = new Stack<>();
        for (int targetNumber : targetNumbers) {
            int checkCellNumber = targetNumber - 1;
            int dollNumber = 0;
            for (int j = 0; j < boards.length; j++) {
                int dollCheck = boards[j][checkCellNumber];
                if (dollCheck != 0) {
                    dollNumber = dollCheck;
                    boards[j][checkCellNumber] = 0;
                    break;
                }
            }
            if (!stack.isEmpty() && stack.peek() == dollNumber) {
                stack.pop();
                answer += 2;
            } else {
                stack.push(dollNumber);
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("후위식 연산(postfix)")
    void solutionD() {
        //given
        String message = "352+*9-";
        int expect = 12;
        int answer;

        //when
        char[] chars = message.toCharArray();
        int pointer = 0;
        Stack<Integer> stack = new Stack<>();
        while (true) {
            if (Character.isDigit(chars[pointer])) {
                stack.push(Character.getNumericValue(chars[pointer]));
            } else {
                Integer x = stack.peek();
                stack.pop();
                Integer y = stack.peek();
                stack.pop();
                if (chars[pointer] == '+') {
                    stack.push(x + y);
                } else if (chars[pointer] == '*') {
                    stack.push(x * y);
                } else {
                    stack.push(y - x);
                }
            }
            if (pointer == message.length() - 1) {
                answer = stack.peek();
                break;
            }
            pointer++;
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("쇠막대기")
    void solutionE() {
        //given
        String caseA = "()(((()())(())()))(())";
        String caseB = "(((()(()()))(())()))(()())"; // (((())(())()))(()())
        int expectA = 17;
        int expectB = 24;
        int answerA = 0;
        int answerB = 0;

        //when - 1
        Stack<Character> stackA = new Stack<>();
        for (int i = 0; i < caseA.length(); i++) {
            if (caseA.charAt(i) == '(') {
                stackA.push('(');
            } else {
                stackA.pop();
                if (caseA.charAt(i - 1) == '(') {
                    answerA += stackA.size();
                } else {
                    answerA++;
                }
            }
        }

        //when - 2
        Stack<Character> stackB = new Stack<>(); // 막대기 보관
        for (int i = 0; i < caseB.length(); i++) {
            if (caseB.charAt(i) == ('(')) {
                stackB.push('('); // 막대기 푸쉬
            } else {
                if (!stackB.isEmpty()) {
                    if (caseB.charAt(i - 1) == '(') {
                        //레이저발동
                        stackB.pop();
                        answerB += stackB.size();
                    } else {
                        //막대기 제거
                        stackB.pop();
                        answerB++;
                    }
                }
            }
        }

        //then
        assertThat(answerA).isEqualTo(expectA);
        assertThat(answerB).isEqualTo(expectB);
    }

    @Test
    @DisplayName("공주 구하기")
    void solutionF() {
        //given
        int size = 8;
        int range = 3;
        int expect = 7;
        int answer = 0;

        //when
        Queue<Integer> pipe = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            pipe.offer(i);
        }

        while (!pipe.isEmpty()) {
            for (int i = 1; i < range; i++) {
                pipe.offer(pipe.poll());
            }
            pipe.poll();
            if (pipe.size() == 1) {
                answer = pipe.poll();
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("교육과정 설계")
    void solutionG() {
        //given
        String needSubject = "CBA";
        String schedule = "CBDAGE";
        String expect = "YES";
        String answer;

        //when
        Queue<Character> storage = new LinkedList<>();
        for (char unit : needSubject.toCharArray()) {
            storage.offer(unit);
        }

        for (char unit : schedule.toCharArray()) {
            if (storage.contains(unit)) {
                if (!storage.poll().equals(unit)) {
                    break;
                }
            }
        }
        answer = storage.isEmpty() ? "YES" : "NO";

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("응급실")
    void solutionH() {
        //given
        int checkNumber = 2;
        List<Integer> users = List.of(60, 50, 70, 80, 90); // 70 60 50
        int expect = 3;
        int answer = 0;

        //when
        Queue<Person> storage = new LinkedList<>();
        for (int i = 0; i < users.size(); i++) {
            storage.offer(new Person(i, users.get(i)));
        }

        while (!storage.isEmpty()) {
            Person person = storage.poll();
            for (Person storageData : storage) {
                if (storageData.value > person.value) {
                    storage.offer(person);
                    person = null;
                    break;
                }
            }
            if (person != null) {
                answer++;
                if (person.id == checkNumber) {
                    break;
                }
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

}

class Person implements Comparable<Person> {

    int id;

    int value;

    public Person(int id, int value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public int compareTo(Person o) {
        return this.id - o.id;
    }
}
