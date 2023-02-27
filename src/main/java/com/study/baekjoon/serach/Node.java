package com.study.baekjoon.serach;

// 알고리즘의 예시이기 때문에 간단하게 작성한다.
public class Node {

    int data;

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

}
