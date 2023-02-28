package com.study.baekjoon.sort;

public class CustomSort {

    public CustomSort() {

    }

    // O(N^2)
    public void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int nowData = array[i];
                    array[i] = array[j];
                    array[j] = nowData;
                }
            }
        }
    }

    // O(N^2), 뒤부터 정의
    public void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int nowData = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = nowData;
                }
            }
        }
    }

    // O(N) || O(N^2)
    public void insertionSort(int[] array) {
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
    }

}
