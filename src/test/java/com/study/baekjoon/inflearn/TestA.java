package com.study.baekjoon.inflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.regex.Pattern;

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

    @Test
    @DisplayName("중복문자제거")
    void test06() {
        //given
        String message = "ksekkset";
        String expect = "kset";
        String result = "";

        //when
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
//            System.out.printf("%s :: %s :: %s %n", chars[i], i, message.indexOf(chars[i]));
            if (i == message.indexOf(chars[i])) {
                result += chars[i];
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("회문문자열 체크 - 인덱스")
    void test07_A() {
        //given
        String message = "GOOG";
        boolean expect = true;
        boolean result = false;

        //when
        char[] chars = message.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            result = chars[i] == chars[chars.length - 1 - i];
        }

        //then
        assertThat(expect).isEqualTo(result);
    }

    @Test
    @DisplayName("회문문자열 체크 - StringBuilder")
    void test07_B() {
        //given
        String message = "GOOG";
        boolean expect = true;
        boolean result = false;

        //when
        String messageReverse = new StringBuilder(message).reverse().toString();
        if (message.equals(messageReverse)) {
            result = true;
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    // 팰린드롬이랑 회문문자열은 같은 의미를 가지는듯
    @Test
    @DisplayName("팰린드롬 - StringBuilder")
    void test08() {
        /*
         * 조건1: 대소문자 구분없고 알파벳만으로 체크
         * */
        //given
        String message = "found7, time: study; Yduts; emit, 7Dnuof";
        boolean expect = true;
        boolean result = false;

        //when
        String messageChange = message.toLowerCase().replaceAll("[^a-z]", "");
        String messageFormat = new StringBuilder(messageChange).reverse().toString();
        if (messageChange.equals(messageFormat)) {
            result = true;
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("숫자추출  - 정규식")
    void test09_A() {
        //given
        String message = "cwajli21ljil290";
        int expect = 21290;
        int result;

        //when
        StringBuilder value = new StringBuilder();
        char[] chars = message.toCharArray();
        for (char aChar : chars) {
            if (Pattern.matches("^[0-9]*$", String.valueOf(aChar))) {
                value.append(aChar);
            }
        }
        result = Integer.parseInt(value.toString());

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("숫자추출  - 아스키코드")
    void test09_B() {
        //given
        String message = "cwajli21ljil290";
        int expect = 21290;
        int result = 0;

        //when
        char[] chars = message.toCharArray();
        for (char unit : chars) {
            if (unit >= 48 && unit <= 57) {
                result = result * 10 + (unit - 48);
            }
        }

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("문자거리")
    void test10() {
        //given
        String message = "teachermode";
        char target = 'e';
        String expect = "10121012210";
        String result = "";

        //when
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            boolean loop = true;
            int distance = 0;
            while (loop) {
                if (i == 0) {
                    char checkValue = chars[i + distance];
                    if (checkValue == target) {
                        stringBuilder.append(distance);
                        loop = false;
                    } else {
                        distance++;
                    }
                } else if (i == chars.length - 1) {
                    char checkValue = chars[i - distance];
                    if (checkValue == target) {
                        stringBuilder.append(distance);
                        loop = false;
                    } else {
                        distance++;
                    }
                } else {
                    char rightCheckValue = chars[i + distance];
                    char leftCheckValue = chars[i - distance];
                    if (rightCheckValue == target || leftCheckValue == target) {
                        stringBuilder.append(distance);
                        loop = false;
                    } else {
                        distance++;
                    }
                }
            }
        }
        result = stringBuilder.toString();

        //then
        assertThat(result).isEqualTo(expect);
        System.out.println(result);
    }

    @Test
    void stringCompress() {
        //given
        String message = "KKHSSSSSSSE";
        String expect = "K2HS7E";
        StringBuilder stringBuilder = new StringBuilder();

        //when
        Map<String, Integer> map = new LinkedHashMap<>();
        char[] chars = message.toCharArray();
        for (char unit : chars) {
            map.merge(String.valueOf(unit), 1, Integer::sum);
        }

        for (String key : map.keySet()) {
            stringBuilder.append(key).append(map.get(key) == 1 ? "" : map.get(key));
        }

        //then
        assertThat(stringBuilder.toString()).isEqualTo(expect);
    }

    @Test
    void passwordSign() {
        /*
         * 1. 암호는 7자리로 구성되어있다.
         * 2. # -> 1, * -> 0 으로 2진수로 변경 -> 2진수를 10진수로 변경 -> 10진수를 아스키코드로 변경
         * */
        //given
        int unitCount = 4;
        String message = "#****###**#####**#####**##**";
        String expect = "COOL";
        StringBuilder stringBuilder = new StringBuilder();

        //when
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i += 7) {
            String password = message.substring(i, i + 7); // 암호
            String passwordTwo = password.replace("#", "1").replace("*", "0"); // 2진수
            int passwordTen = Integer.parseInt(passwordTwo, 2);
            char unit = (char) passwordTen;
            stringBuilder.append(unit);
        }

        //then
        assertThat(stringBuilder.toString()).isEqualTo(expect);
    }

}
