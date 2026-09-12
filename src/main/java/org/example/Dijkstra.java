package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    private final Map<String,Integer> hashVerticeCusto;
    private final Set<String> naoVisitados; // Open
    private final Set<String> visitados;    // Closed

    public Dijkstra() {
        this.hashVerticeCusto = new HashMap<>();
        this.naoVisitados = new HashSet<>();
        this.visitados = new HashSet<>();
    }

    public void adicionaVertice(String nomeVertice, int menorCusto) {
        this.hashVerticeCusto.put(nomeVertice, menorCusto);
        return;
    }

    public void atualizaCusto(String nomeVertice, int novoMenorCusto) {
        this.hashVerticeCusto.replace(nomeVertice,novoMenorCusto);
    }

    public void imprimeHash() {
        for (String chaveVertice : hashVerticeCusto.keySet()) {
            System.out.println("VÉRTICE: "+chaveVertice+" | "+hashVerticeCusto.get(chaveVertice));
        }
    }

    static class Aresta {
        private int custo;
        private String destino;
    }

    public static void main(String[] args) {

    }
}
