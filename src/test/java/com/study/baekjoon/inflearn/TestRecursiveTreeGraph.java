package com.study.baekjoon.inflearn;

import com.study.baekjoon.serach.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

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
    @DisplayName("팩토리얼")
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

    @Test
    @DisplayName("피보나치수열")
    void solutionD() {
        /*
         * 풀이방법1 -> 반복문
         * 풀이방법2 -> 재귀함수
         * 성능은 반복문으로 짜는게 더 좋다. 재귀는 결국 스택프레임이 돌아가기 때문에 무겁다.
         * */
        //given
        int n = 45;
        int[] answer = new int[45];
        array = new int[n];

        //when1 - then
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == 1) {
                answer[i] = 1;
            } else {
                answer[i] = answer[i - 1] + answer[i - 2];
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(answer[i] + " ");
        }

        System.out.println();

        //when2 - then
        solutionE(n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }

    }

    static int[] array;

    private int solutionE(int i) {
        // 재귀함수에 메모이제이션을 활용
        if (array[i] > 0) {
            return array[i];
        }
        if (i == 0 || i == 1) {
            array[i] = 1;
        } else {
            array[i] = solutionE(i - 1) + solutionE(i - 2);
        }
        return array[i];
    }

    @Test
    @DisplayName("이진트리순회(DFS: Depth First Search")
    void solutionE() {
        Node root = new Node(1);
        root.leftNode = new Node(2);
        root.rightNode = new Node(3);
        root.leftNode.leftNode = new Node(4);
        root.leftNode.rightNode = new Node(5);
        root.rightNode.leftNode = new Node(6);
        root.rightNode.rightNode = new Node(7);
        root.depthFirstSearchForPre();
//        root.depthFirstSearchForIn();
//        root.depthFirstSearchForPost();
    }

    static int depth = 3;
    static int[] datas = new int[depth + 1];

    @Test
    @DisplayName("부분집합구하기(DFS)")
    void solutionF() {
        search(1);
    }

    private void search(int level) {
        if (level == depth + 1) {
            StringBuilder result = new StringBuilder();
            for (int i = 1; i <= depth; i++) {
                if (datas[i] == 1) {
                    result.append(i).append(" ");
                }
            }
            System.out.println(result);
        } else {
            datas[level] = 1;
            search(level + 1);
            datas[level] = 0;
            search(level + 1);
        }
    }

    @Test
    @DisplayName("이진트리순회(BFS)")
    void solutionG() {
        Node root = new Node(1);
        root.leftNode = new Node(2);
        root.rightNode = new Node(3);
        root.leftNode.leftNode = new Node(4);
        root.leftNode.rightNode = new Node(5);
        root.rightNode.leftNode = new Node(6);
        root.rightNode.rightNode = new Node(7);
        breadthFirstSearch(root);
    }

    private void breadthFirstSearch(Node root) {
        Queue<Node> storage = new LinkedList<>();
        storage.offer(root);
        int leftPointer = 0;
        while (!storage.isEmpty()) {
            int length = storage.size();
            System.out.println(length + " : ");
            for (int i = 0; i < length; i++) {
                Node current = storage.poll();
                System.out.print(Objects.requireNonNull(current).data);
            }
        }
    }

}
