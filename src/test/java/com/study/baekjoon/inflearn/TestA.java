package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TestA {

    @MethodSource("paramForTest001")
    @DisplayName("문자열에 특정 문자가 몇개 포함되어있는지")
    @ParameterizedTest
    void test001(String message, char key, int result) {
        // given
        int expectValue = 0;
        char[] messageArray = message.toLowerCase().toCharArray();
        key = Character.toLowerCase(key);

        // when - 1
        Map<String, Integer> map = new HashMap<>();

        for (char unit : messageArray) {
            map.merge(String.valueOf(unit), 1, Integer::sum);
        }

        for (String mapKey : map.keySet()) {
            if (mapKey.equals(String.valueOf(key))) {
                expectValue = map.get(mapKey);
            }
        }

        /*
        // when - 2 (사실 결과값만 뽑아내는 거라면 Map에 바인딩은 필요없음)
        for (char unit : messageArray) {
            if (unit == key) {
                expectValue++;
            }
        }
        */

        // then
        assertThat(expectValue).isEqualTo(result);
    }

    public static Object[] paramForTest001() {
        return new Object[]{
                new Object[]{"Computercooler", 'c', 2}
        };
    }

}
