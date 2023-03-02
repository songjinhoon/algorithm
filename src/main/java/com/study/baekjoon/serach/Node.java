package com.study.baekjoon.serach;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

// 알고리즘의 예시이기 때문에 간단하게 작성한다.
public class Node {

    public int data;

    public Node leftNode;

    public Node rightNode;

    public Node(int data) {
        this.data = data;
    }

    public void depthFirstSearchForPre() {
        System.out.println(data);
        if (leftNode != null) {
            leftNode.depthFirstSearchForPre();
        }
        if (rightNode != null) {
            rightNode.depthFirstSearchForPre();
        }
    }

    public void depthFirstSearchForIn() {
        if (leftNode != null) {
            leftNode.depthFirstSearchForIn();
        }
        System.out.println(data);
        if (rightNode != null) {
            rightNode.depthFirstSearchForIn();
        }
    }

    public void depthFirstSearchForPost() {
        if (leftNode != null) {
            leftNode.depthFirstSearchForPost();
        }
        if (rightNode != null) {
            rightNode.depthFirstSearchForPost();
        }
        System.out.println(data);
    }

    public void breadthFirstSearch() {
        Queue<Node> storage = new LinkedList<>();
        storage.offer(this);
        int level = 0;
        while (!storage.isEmpty()) {
            int length = storage.size();
            System.out.print(level + " : ");
            for (int i = 0; i < length; i++) {
                Node current = storage.poll();
                System.out.print(Objects.requireNonNull(current).data + " ");
                if (current.leftNode != null) {
                    storage.offer(current.leftNode);
                }
                if (current.rightNode != null) {
                    storage.offer(current.rightNode);
                }
            }
            level++;
            System.out.println();
        }
    }

}
