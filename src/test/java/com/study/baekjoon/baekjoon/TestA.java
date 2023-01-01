package com.study.baekjoon.baekjoon;

import com.study.baekjoon.dto.Receipt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

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

    /*
     * 1. enemy의 값이 가장 높은 곳에서 무적권을 쓰려고 했지만, 불가능하다. 이미 앞라운드에서 낮은 값으로 여러번의 라운드를 치루게 된다면, 무적권은 쓸 기회조차 없기 때문임.
     * 2. 그럼, 방법은 반복문을 통하여 특정 라운드에 enumy의 값이 남은 n 값보다 높아지는 시점에서 체크를 해야되는데, 그 시점에 적군의 수가 가장 높은 라운드에 무적권을 쓰게 끔 하면 된다.
     * 3. 우선순위큐를 활용하여 enemy의 값이 n보다 작을 경우까지는 큐에 추가를 해주고, 반대의 상황이 되는 경우에 큐에서 가장 큰 값을 추출하여 해당 라운드에 무족권을 쓴 형태로 가면 된다.
     * 4. 무족권을 쓸 수 있는 조건은 ( 적군의 수가 아군보다 높을경우 && 무족권의 수가 남아있을 경우 &&
     *    - enemy > n && k > 0
     * */
    @MethodSource("paramForTest003")
    @ParameterizedTest
    @DisplayName("https://school.programmers.co.kr/learn/courses/30/lessons/142085")
    void test003(int n, int k, int[] enemy, int result) { // 병사수, 무적권 수, 적, 최종 진출 라운드
        //높은 숫자가 우선 순위인 int 형 우선순위 큐 선언
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;

        for (int e : enemy) {
            pq.add(e);
            while (k > 0 && n < e && !pq.isEmpty()) {
                n += pq.poll();
                k--;
            }
            n -= e;
            if (n < 0)
                break;
            answer++;
        }

        assertThat(answer).isEqualTo(result);
    }

    public static Object[] paramForTest003() {
        return new Object[]{
                new Object[]{
                        7,
                        3,
                        new int[]{4, 2, 4, 5, 3, 3, 1},
                        5
                }
        };
    }

}
