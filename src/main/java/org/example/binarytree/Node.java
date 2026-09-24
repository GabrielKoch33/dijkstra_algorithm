package org.example.binarytree;

public class Node {
    private Node nodeEsquerdo;
    private Node nodeDireito;
    private Node nodePai;
    private int valor;

    public int getValor()  {
        return this.valor;
    }

    public Node getNodeEsquerdo() {
        return this.nodeEsquerdo;
    }

    public Node getNodeDireito() {
        return this.nodeDireito;
    }

    public Node getNodePai() {
        return this.nodePai;
    }
}
