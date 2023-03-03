package com.study.baekjoon.inflearn;

import com.study.baekjoon.serach.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        System.out.println(customFuncC(number));
    }

    private int customFuncC(int number) {
        if (number != 1) {
            return number * customFuncC(number - 1);
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
    @DisplayName("BFS(이진트리순회)")
    void solutionG() {
        Node root = new Node(1);
        root.leftNode = new Node(2);
        root.rightNode = new Node(3);
        root.leftNode.leftNode = new Node(4);
        root.leftNode.rightNode = new Node(5);
        root.rightNode.leftNode = new Node(6);
        root.rightNode.rightNode = new Node(7);
        root.breadthFirstSearch();
    }

    @Test
    @DisplayName("BFS(상태트리탐색) - 송아지찾기")
    void solutionH() {
        // 1. 상태트리란?
        //given
        int answer = 0, expect = 3;
        int[] distance = {-1, 1, 5};
        int[] ch = new int[10000];
        int s = 5, e = 14;

        //when
        Queue<Integer> storage = new LinkedList<>();
        ch[s] = 1;
        storage.offer(s);

        while (!storage.isEmpty()) { // 5
            int size = storage.size();
            for (int i = 0; i < size; i++) {
                int userLocation = storage.poll();
                for (int j = 0; j < distance.length; j++) {
                    int location = userLocation + distance[j];
                    if (location == e) {
                        storage.clear();
                        break;
                    }
                    if (ch[location] == 0) {
                        ch[location] = 1;
                        storage.offer(location);
                    }
                }
                if (storage.isEmpty()) {
                    break;
                }
            }
            answer++;
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("BFS(넓이우선탐색) - 말단 노드까지의 가장 짧은 경로 길이 구하기")
    void solutionI() {
        //말단노드는 자식이 없는 부모노드를 말함
        //given
        int expect = 1, answer = 0;
        Node root = new Node(1);
        root.leftNode = new Node(2);
        root.rightNode = new Node(3);
        root.leftNode.leftNode = new Node(4);
        root.leftNode.rightNode = new Node(5);

        //when
        Queue<Node> storage = new LinkedList<>();
        storage.offer(root);

        while (!storage.isEmpty()) { // 1
            int size = storage.size();
            for (int i = 0; i < size; i++) {
                Node node = Objects.requireNonNull(storage.poll());
                if (node.leftNode == null && node.rightNode == null) {
                    storage.clear();
                    break;
                }
                if (node.leftNode != null) {
                    storage.offer(node.leftNode);
                }
                if (node.rightNode != null) {
                    storage.offer(node.rightNode);
                }
            }
            if (!storage.isEmpty()) {
                answer++;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }


    int solutionAnswerJ = 0;
    int[] ch = new int[5 + 1];

    @Test
    @DisplayName("DFS(경로탐색)")
    void solutionJ() {
        /*
         * n -> 정점의 수
         * m -> 간선의 수
         * */
        //given
        int n = 5, expect = 6;
        int[][] graph = new int[n + 1][n + 1];
        graph[1][2] = 1;
        graph[1][3] = 1;
        graph[1][4] = 1;
        graph[2][1] = 1;
        graph[2][3] = 1;
        graph[2][5] = 1;
        graph[3][4] = 1;
        graph[4][2] = 1;
        graph[4][5] = 1;
        ch[1] = 1;

        //when
        customFuncJ(graph, 1, n);

        //then
        assertThat(solutionAnswerJ).isEqualTo(expect);
    }

    private void customFuncJ(int[][] graph, int v, int n) {
        if (v == n) {
            solutionAnswerJ++;
        } else {
            for (int i = 1; i <= n; i++) {
                if (graph[v][i] == 1 && ch[i] == 0) {
                    ch[i] = 1;
                    customFuncJ(graph, i, n);
                    // 백지점
                    ch[i] = 0;
                }
            }
        }
    }

}
