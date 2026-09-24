package org.example.binarytree;

public class Node {
    private Node leftNode;
    private Node rigthNode;
    private Node parentNode;
    private int value;

    public Node(Node left, Node right, Node parent, int value) {
        this.leftNode = left;
        this.rigthNode = right;
        this.parentNode = parent;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRigthNode() {
        return rigthNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public int getValue() {
        return value;
    }
}
