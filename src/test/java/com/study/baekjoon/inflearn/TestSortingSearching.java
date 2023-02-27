package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestSortingSearching {

    @Test
    @DisplayName("선택정렬")
    void solutionA() {
        /*
         * 굳이 내장클래스를 쓰지 않고 정렬 알고리즘을 집적 구현하는 이유는 -> 정렬 알고리즘은 알고리즘을 배우는 가장 좋은 교재라고 한다.
         * */
        //given
        String message = "13 5 11 7 23 15";
        String expect = "5 7 11 13 15 23";
        String answer = "";

        //when
        int[] array = Arrays.stream(message.split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < array.length; i++) {
            for (int k = i + 1; k < array.length; k++) {
                if (array[i] > array[k]) {
                    int nowData = array[i];
                    array[i] = array[k];
                    array[k] = nowData;
                }
            }
        }
        answer = Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(" "));

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("버블정렬")
    void solutionB() {
        //given
        String message = "13 5 11 7 23 15";
        String expect = "5 7 11 13 15 23";
        String answer = "";

        //when
        int[] array = Arrays.stream(message.split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int data = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = data;
                }
            }
        }
        answer = Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(" "));

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("삽입정렬")
    void solutionC() {
        //given
        int[] array = {11, 7, 5, 6, 10, 9};
        int[] expect = {5, 6, 7, 9, 10, 11};
        int[] answer = {};

        //when
        for (int i = 1; i < array.length; i++) {
            int target = array[i], j;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > target) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = target;
        }

        //then
        assertThat(array).isEqualTo(expect);
    }

    @Test
    @DisplayName("캐시메모리")
    void solutionD() {
        //given
        int cashSize = 5;
        Integer[] todos = {1, 2, 3, 2, 6, 2, 3, 5, 7};
        Integer[] expect = {7, 5, 3, 2, 6};
        Integer[] answer = new Integer[5];

        //when
        for (Integer value : todos) {
            if (Arrays.asList(answer).contains(value)) {
                int index = 0;
                for (int j = 0; j < answer.length; j++) {
                    if (answer[j].equals(value)) {
                        index = j;
                        break;
                    }
                }
                for (int j = index; j >= 1; j--) {
                    answer[j] = answer[j - 1];
                }
            } else {
                for (int j = answer.length - 1; j >= 1; j--) {
                    answer[j] = answer[j - 1];
                }
            }
            answer[0] = value;
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("중복 확인")
    void solutionE() {
        /*
         * O(N) -> 해쉬맵 사용
         * */
        //given
        Integer[] datas = {20, 25, 52, 30, 39, 33, 43, 33};
        String expect = "D";
        String answer = "U";

        //when
        Map<Integer, Integer> storage = new HashMap<>();
        for (Integer data : datas) {
            Integer checkData = storage.get(data);
            if (checkData != null) {
                storage.put(checkData, 1);
            } else {
                answer = "D";
                break;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("중복 확인")
    void solutionF() {
        /*
         * 정렬사용 -> NlogN
         * 왜 NlogN인가???
         * */
        //given
        Integer[] datas = {20, 25, 52, 30, 39, 33, 43, 33};
        String expect = "D";
        String answer = "U";

        //when
        Arrays.sort(datas);
        for (int i = 0; i < datas.length - 1; i++) {
            if (datas[i].equals(datas[i + 1])) {
                answer = "D";
                break;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("장난꾸러기")
    void solutionG() {
        /*
         * Collection 이용하고 싶었는데, 깊은 복사를 위하여 Array 이용
         * */
        //given
        Integer[] datas = {120, 125, 152, 130, 135, 135, 143, 127, 160};
        List<Integer> expect = List.of(3, 8);
        List<Integer> answer = new ArrayList<>();

        //when
        Integer[] copy = datas.clone();
        Arrays.sort(copy);

        for (int i = 0; i < datas.length; i++) {
            if (!datas[i].equals(copy[i])) {
                answer.add(i + 1);
            }
            if (answer.size() == 2) {
                break;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("좌표정렬(compareTo)")
    void solutionH() {
        //given
        int[][] datas = {
                {2, 7},
                {1, 3},
                {1, 2},
                {2, 5},
                {3, 6}
        };

        //when
        Set<Pointer> storage = new TreeSet<>();
        for (int[] ints : datas) {
            storage.add(new Pointer(ints[0], ints[1]));
        }

        //then
        storage.forEach(data -> {
            System.out.println(data.x + " : " + data.y);
        });
    }

    @Test
    @DisplayName("이분검색")
    void solutionI() {
        //given
        List<Integer> datas = new ArrayList<>(List.of(23, 87, 65, 12, 57, 32, 99, 81));
        int target = 32;
        int expect = 3;
        int answer = 0;

        //when
        Collections.sort(datas);

        int leftPointer = 0, rightPointer = datas.size() - 1;
        while (leftPointer <= rightPointer) {
            int mid = (leftPointer + rightPointer) / 2;
            if (datas.get(mid).equals(target)) {
                answer = mid + 1;
                break;
            }
            if (datas.get(mid) > target) {
                rightPointer = mid - 1;
            } else {
                leftPointer = mid + 1;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    @Test
    @DisplayName("결정알고리즘")
    void solutionJ() {
        //given
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int size = 3;
        int expect = 17;
        int answer = 0;

        //when
        int leftPointer = Arrays.stream(array).max().getAsInt();
        int rightPointer = Arrays.stream(array).sum();
        while (leftPointer <= rightPointer) {
            int mid = (leftPointer + rightPointer) / 2;
            if (count(array, mid) > size) {
                leftPointer = mid + 1;
            } else {
                answer = mid;
                rightPointer = mid - 1;
            }
        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    private int count(int[] array, int capacity) {
        int count = 1, sum = 0;
        for (int x : array) {
            if (sum + x > capacity) {
                count++;
                sum = x;
            } else {
                sum += x;
            }
        }
        return count;
    }



}

class Pointer implements Comparable<Pointer> {

    int x;

    int y;

    public Pointer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pointer o) {
        if (this.x != o.x) {
            return this.x - o.x;
        } else {
            return this.y - o.y;
        }
    }

}