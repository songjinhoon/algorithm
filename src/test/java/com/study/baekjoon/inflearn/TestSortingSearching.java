package com.study.baekjoon.inflearn;

import com.study.baekjoon.sort.CustomDuplicationCheck;
import com.study.baekjoon.sort.CustomSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestSortingSearching {

    @Test
    @DisplayName("선택정렬")
    void solutionA() {
        int[] array = {13, 5, 11, 7, 23, 15};
        CustomSort customSort = new CustomSort();
        customSort.selectionSort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    @DisplayName("버블정렬")
    void solutionB() {
        int[] array = {13, 5, 11, 7, 23, 15};
        CustomSort customSort = new CustomSort();
        customSort.bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    @DisplayName("삽입정렬")
    void solutionC() {
        int[] array = {13, 5, 11, 7, 23, 15};
        CustomSort customSort = new CustomSort();
        customSort.insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    @DisplayName("퀴즈 - 캐시메모리")
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
    @DisplayName("중복 확인 - 해쉬맵")
    void solutionE() {
        Integer[] datas = {20, 25, 52, 30, 39, 33, 43, 33};
        CustomDuplicationCheck customDuplicationCheck = new CustomDuplicationCheck();
        System.out.println(customDuplicationCheck.checkByHashMap(datas));
    }

    @Test
    @DisplayName("중복 확인 - 정렬")
    void solutionF() {
        Integer[] datas = {20, 25, 52, 30, 39, 33, 43, 33};
        CustomDuplicationCheck customDuplicationCheck = new CustomDuplicationCheck();
        System.out.println(customDuplicationCheck.checkBySort(datas));
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

    @Test
    @DisplayName("마구간정하기(결정알고리즘)")
    void solutionK() {
        //given
        int[] array = {1, 2, 8, 4, 9};
        int stalls = 5, horses = 3;
        int expect = 3, answer = 0;

        //when
        Arrays.sort(array);
        int lt = 1, rt = array[stalls - 1];
        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if (countK(array, mid) >= horses) {
                answer = mid;
                lt = mid + 1;
            } else {
                rt = mid - 1;
            }

        }

        //then
        assertThat(answer).isEqualTo(expect);
    }

    private int countK(int[] array, int mid) {
        int count = 1;
        int ep = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] - ep >= mid) {
                count++;
                ep = array[i];
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