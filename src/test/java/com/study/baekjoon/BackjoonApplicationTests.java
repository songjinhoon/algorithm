package com.study.baekjoon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BackjoonApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("문자열 중복 체크")
    void test01() {
        String x = "apple";
        assertThat(test01Check(x)).isTrue();
    }

    public boolean test01Check(String value) {
        char[] array = value.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        for (char x : array) {
            map.merge(String.valueOf(x), 1, Integer::sum);
        }
        for (String key : map.keySet()) {
            if (map.get(key) > 1) {
                return true;
            }
        }
        return false;
    }

    @Test
    @DisplayName("문자열이 두개가 주어졌을 때, 이 둘이 서로 순열관계에 있는지 검사")
    void test02() {
        String x = "abcdef";
        String y = "abcdeg";
        assertThat(test02Check(x, y)).isFalse();
    }

    public boolean test02Check(String x, String y) {
        if (x.length() != y.length()) {
            return false;
        }
        return sort(x).equals(sort(y));
    }

    public String sort(String value) {
        char[] chars = value.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Test
    @DisplayName("문자열에 포함된 문자의 출현 횟수가 같은지 검사")
    void test03() {
        String x = "abbc가가나";
        String y = "가나abbc가";
        assertThat(test03Check(x, y)).isTrue();
    }

    public boolean test03Check(String x, String y) {
        if (x.length() != y.length()) {
            return false;
        }

        //정렬
        char[] x_array = x.toCharArray();
        char[] y_array = y.toCharArray();
        Arrays.sort(x_array);
        Arrays.sort(y_array);

        // 바인딩
        LinkedHashMap<String, Integer> x_map = new LinkedHashMap<>();
        for (char item : x_array) {
            x_map.merge(String.valueOf(item), 1, Integer::sum);
        }
        LinkedHashMap<String, Integer> y_map = new LinkedHashMap<>();
        for (char item : y_array) {
            y_map.merge(String.valueOf(item), 1, Integer::sum);
        }

        // 비교
        for (String key : x_map.keySet()) {
            if (y_map.get(key) == null) {
                return false;
            }
            if (!x_map.get(key).equals(y_map.get(key))) {
                return false;
            }
        }
        return true;
    }

}
