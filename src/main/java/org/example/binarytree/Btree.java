package org.example.binarytree;

public class Btree {
    private Node root;

    public Btree() {
        this.root = null;
    }

    public void inserirValor(int valor) {
        percorreLado(root, valor);
        System.out.println(valor+" inserido!");
    }

    private void percorreLado(Node node, int valor) {
        if (node == null) {
            node = new Node(null, null, null, valor);
            return;
        }
        if (valor < node.getValue()) {
            percorreLado(node.getLeftNode(), valor);
        } else if (valor > node.getValue()) {
            percorreLado(node.getRigthNode(), valor);
        }
    }

    public void removerValor(int valor) {

    }

    public boolean contemValor(int valor) {
        return false;
    }

    public void preOrdem() {

    }

    public void inOrdem() {

    }

    public void posOrdem() {

    }

    public void folhas() {

    }

    public int alturaArvore() {
        return -1;
    }

    public int nivelDoValor(int valor) {
        return -1;
    }

    public boolean isComplete() {
        return false;
    }
}
