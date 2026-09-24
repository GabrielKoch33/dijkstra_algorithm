package org.example.binarytree;

public class Btree {
    private Node root;

    public Btree() {
        this.root = null;
    }

    public void inserirValor(int valor) {
        this.root = percorreLado(root, null, valor);
        System.out.println(valor+" inserido!");
    }

    private Node percorreLado(Node node, Node nodePai, int valor) {
        if (node == null) {
            return new Node(null, null, nodePai, valor);
        }
        nodePai = node;
        if (valor < node.getValue()) {
            node.setLeftNode(percorreLado(node.getLeftNode(), nodePai, valor));
            return node;
        } else if (valor > node.getValue()) {
            node.setRightNode(percorreLado(node.getRigthNode(), nodePai, valor));
            return node;
        }
        return node; // quando o valor já existe, o próprio nó já é devolvido para o de cima
    }

    public void removerValor(int valor) {

    }

    public boolean contemValor(int valor) {
        return false;
    }

    public void preOrdem(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue()+" - ");
        preOrdem(node.getLeftNode());
        preOrdem(node.getRigthNode());

    }

    public void inOrdem(Node node) {
        if (node == null) {
            return;
        }
        inOrdem(node.getLeftNode());
        System.out.print(node.getValue()+" - ");
        inOrdem(node.getRigthNode());
    }

    public void posOrdem(Node node) {
        if (node == null) {
            return;
        }
        posOrdem(node.getLeftNode());
        posOrdem(node.getRigthNode());
        System.out.print(node.getValue()+" - ");
    }

    public void folhas(Node node) {
        if (node == null) {
            return;
        }
        if (node.getLeftNode() == null && node.getRigthNode() == null) {
            System.out.println(node.getValue());
        }
        folhas(node.getLeftNode());
        folhas(node.getRigthNode());
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

    public Node getRoot() {
        return root;
    }
}
