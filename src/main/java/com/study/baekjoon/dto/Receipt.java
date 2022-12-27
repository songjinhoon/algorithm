package com.study.baekjoon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Receipt {

    private int totalPrice;

    private List<Order> orders;

    public static Receipt of(int totalPrice, List<Order> order) {
        return Receipt.builder()
                .totalPrice(totalPrice)
                .orders(order)
                .build();
    }

    @Builder
    @Getter
    @Setter
    public static class Order {

        private int price;

        private int quantity;

        public static Order of(int price, int quantity) {
            return Order.builder()
                    .price(price)
                    .quantity(quantity)
                    .build();
        }

    }

}