package org.example.dijkstra;

public class Aresta {
    private final int custo;
    private final String destino;

    public Aresta(String destino, int custo) {
        this.destino = destino;
        this.custo = custo;
    }

    public int getCusto() {
        return custo;
    }

    public String getDestino() {
        return destino;
    }
}
