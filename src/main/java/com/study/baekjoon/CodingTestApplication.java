package com.study.baekjoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CodingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingTestApplication.class, args);
        quest1110();
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

    private static void quest10952() {
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

    private static void quest1110() {
        Scanner scanner = new Scanner(System.in);
        int originalData = scanner.nextInt();
        int data = originalData;
        boolean running = true;
        int cycle = 1;

        while (running) {
            int x, y;
            String z = "";
            int result;

            if (data < 10) {
                x = 0;
                y = data;
                z = 0 + data + "";
                result = data;
            } else {
                x = Integer.parseInt((data + "").substring(0, 1));
                y = Integer.parseInt((data + "").substring(1));
                if ( x+y < 10) {
                    z = "0" + (x + y);
                } else {
                    z = (x + y) + "";
                }
                result = Integer.parseInt("" + y + Integer.parseInt(z.substring(1)));
            }
            if (result ==  originalData) {
                running = false;
            } else {
                data = result;
                cycle++;
            }
        }

        System.out.println(cycle);

    }

}
