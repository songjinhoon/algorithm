package com.study.baekjoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BackjoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackjoonApplication.class, args);
        quest10952();
    }

    private static void quest1000() {
        Scanner scanner = new Scanner(System.in);
        int a, b;
        a = scanner.nextInt();
        b = scanner.nextInt();
        System.out.println(a + b);
    }

    private static void quest10871() {
//        List<Integer> a = new ArrayList<>();
        int a, x;

        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        x = scanner.nextInt();

        int size = a;
        int maxValue = x;

        List<Integer> datas = new ArrayList<>();

        for (int i=0; i<size; i++) {
            datas.add(scanner.nextInt());
        }

        datas.stream().filter(data -> data < maxValue).forEach(data -> System.out.print(data + " "));

    }

    public static void quest10952() {
        boolean running = true;

        while (running) {
            int a, b;
            Scanner scanner = new Scanner(System.in);
            a = scanner.nextInt();
            b = scanner.nextInt();

            if (a == 0 && b == 0) {
                running = false;
            } else {
                System.out.print(a + b);
            }

        }
    }

}
