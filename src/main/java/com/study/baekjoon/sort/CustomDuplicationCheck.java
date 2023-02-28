package com.study.baekjoon.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CustomDuplicationCheck {

    // O(N)
    public boolean checkByHashMap(Integer[] datas) {
        boolean isDuplicate = false;
        Map<Integer, Integer> storage = new HashMap<>();
        for (Integer data : datas) {
            if (storage.get(data) != null) {
                storage.put(data, 1);
            } else {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }

    // O(NlogN) - O(N^2) -> 정렬이 들어가기 때문에 해쉬맵에 비해 성능이 안좋은거같음
    public boolean checkBySort(Integer[] datas) {
        boolean isDuplicate = false;
        Arrays.sort(datas);
        for (int i = 0; i < datas.length; i++) {
            if (datas[i].equals(datas[i + 1])) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }

}
