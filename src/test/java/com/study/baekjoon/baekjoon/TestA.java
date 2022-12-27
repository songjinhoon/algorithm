package com.study.baekjoon.baekjoon;

import com.study.baekjoon.dto.Receipt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestA {

    @Test
    @DisplayName("25304 - 영수증")
    void test001() {
        //given
        Receipt receiptA = Receipt.of(10000, List.of(Receipt.Order.of(1000, 5), Receipt.Order.of(1000, 5)));
        Receipt receiptB = Receipt.of(10000, List.of(Receipt.Order.of(1000, 5), Receipt.Order.of(1000, 4)));

        //when
        int sumA = receiptA.getOrders().stream().mapToInt(order -> order.getPrice() * order.getQuantity()).sum();
        int sumB = receiptB.getOrders().stream().mapToInt(order -> order.getPrice() * order.getQuantity()).sum();

        //then
        assertThat(sumA).isEqualTo(receiptA.getTotalPrice());
        assertThat(sumB).isNotEqualTo(receiptB.getTotalPrice());
    }

    @MethodSource("paramForTest002")
    @ParameterizedTest
    @DisplayName("테이블 해시 함수")
    void test002(int[][] data, int col, int row_begin, int row_end, int answer) {
        /**
         * compare() 기본 정렬은 오름차순이다.
         * */
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                return Integer.compare(b[0], a[0]); // 내림차순 정렬
            } else {
                return Integer.compare(a[col - 1], b[col - 1]); //오름차순 정렬
            }
        });

        for (int key = row_begin - 1; key < row_end; key++) {
            int[] rows = data[key];
            int total = 0;
            for (int cell : rows) {
                total += (cell % (key + 1));
            }
            answer = (answer ^ total);
        }

    }

    public static Object[] paramForTest002() {
        return new Object[]{
                new Object[]{
                        new int[][]{{2, 2, 6}, {1, 5, 10}, {4, 2, 9}, {3, 8, 3}}, 2, 2, 3, 4
                },
        };
    }

}
