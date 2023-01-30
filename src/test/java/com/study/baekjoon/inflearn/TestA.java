package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @DisplayName("문자열 대소문자 치환")
    @MethodSource("paramForTest002")
    @ParameterizedTest
    void test002(String message, String result) {
        //given
        StringBuilder stringBuilder = new StringBuilder();

        //when
        char[] chars = message.toCharArray();

        for (char unit : chars) {
            if (Character.isUpperCase(unit)) {
                stringBuilder.append(Character.toLowerCase(unit));
            } else {
                stringBuilder.append(Character.toUpperCase(unit));
            }
        }

        //then
        assertThat(stringBuilder.toString()).isEqualTo(result);
    }

    public static Object[] paramForTest002() {
        return new Object[]{
                new Object[]{"STudY", "stUDy"}
        };
    }

    @Test
    @DisplayName("문장 속 단어의 길이가 가장 긴 단어 구하기")
    void test03_1() {
        //given
        String message = "it is time to study";
        String expect = "study";
        String result = "";

        //when
        String[] messageArray = message.split(" ");
        int m = Integer.MIN_VALUE;
        for (String unit : messageArray) {
            int unitLength = unit.length();
            if (unitLength > m) {
                m = unitLength;
                result = unit;
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("문장 속 단어의 길이가 가장 긴 단어 구하기")
    void test03_2() {
        //given
        String message = "it is time to study";
        String expect = "study";
        String result = "";

        //when
        int m = Integer.MIN_VALUE, pos;
        while ((pos = message.indexOf(' ')) != -1) {
            String unit = message.substring(0, pos);
            if (unit.length() > m) {
                m = unit.length();
                result = unit;
            }
            message = message.substring(pos + 1);
        }
        // 툴에서 경고가 뜨는 이유는 message가 필드에 픽스되어있어서...
        if (message.length() > m) result = message;


        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("단어 뒤집기-StringBuilder")
    void test04_1() {
        //given
        List<String> messages = List.of("good", "Time", "Big");
        List<String> expect = List.of("doog", "emiT", "giB");
        List<String> result = new ArrayList<>();

        //when
        messages.forEach(message -> {
            String reverse = new StringBuilder(message).reverse().toString();
            result.add(reverse);
        });

        //then
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i)).isEqualTo(expect.get(i));
        }
    }

    @Test
    @DisplayName("단어 뒤집기-배열인덱스 활용")
    void test04_2() {
        //given
        List<String> messages = List.of("good", "Time", "Big");
        List<String> expect = List.of("doog", "emiT", "giB");
        List<String> result = new ArrayList<>();

        //when
        messages.forEach(message -> {
            char[] chars = message.toCharArray();
            int lt = 0, rt = chars.length - 1;
            while (lt < rt) {
                char temp = chars[lt];
                chars[lt] = chars[rt];
                chars[rt] = temp;
                lt++;
                rt--;
            }
            result.add(String.valueOf(chars));
        });

        //then
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i)).isEqualTo(expect.get(i));
        }
    }

    @Test
    @DisplayName("특정 문자 뒤집기 - 특수문자 아닌거 뒤집기")
    void test05() {
        //given
        String message = "a#b!GE*T@S";
        String expect = "S#T!EG*b@a";
        String result = "";

        //when
        char[] messageArray = message.toCharArray();
        int lt = 0, rt = message.length() - 1;
        while (lt < rt) {
            if (!Character.isAlphabetic(messageArray[lt])) {
                lt++;
            } else if (!Character.isAlphabetic(messageArray[rt])) {
                rt--;
            } else {
                char temp = messageArray[lt];
                messageArray[lt] = messageArray[rt];
                messageArray[rt] = temp;
                lt++;
                rt--;
            }
        }
        result = String.valueOf(messageArray);

        //then
        assertThat(result).isEqualTo(expect);
    }


}
