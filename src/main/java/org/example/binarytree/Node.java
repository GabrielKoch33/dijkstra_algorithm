package org.example.binarytree;

public class Node {
    private Node leftNode;
    private Node rightNode;
    private Node parentNode;
    private int value;

    public Node(Node left, Node right, Node parent, int value) {
        this.leftNode = left;
        this.rightNode = right;
        this.parentNode = parent;
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRigthNode() {
        return rightNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public int getValue() {
        return value;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rigthNode) {
        this.rightNode = rigthNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
