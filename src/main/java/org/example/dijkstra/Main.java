package org.example.dijkstra;

import java.util.Scanner;
import org.example.dijkstra.Dijkstra;
import org.example.dijkstra.Aresta;

public class Main {
    public static void main(String[] args) {
        Scanner lerInput = new Scanner(System.in);
        Dijkstra d = new Dijkstra();
        String verticeOrigem;
        String verticeDestino;

        d.imprimeVertices();
        while (true) {
            System.out.println("============================");
            System.out.print("Digite um ponto de partida\nR: ");
            verticeOrigem = lerInput.nextLine().toUpperCase();
            System.out.print("Digite um ponto de chegada\nR: ");
            verticeDestino = lerInput.nextLine().toUpperCase();
            System.out.println("============================");
            if (d.existeVertice(verticeOrigem) && d.existeVertice(verticeDestino)) {
                break;
            }
            System.out.println("Informe valores válidos!");
        }

        d.atualizaTabelaCusto(verticeOrigem, 0L);
        d.adicionaFilaPrioridade(verticeOrigem);

        while (!d.filaPrioridadeIsEmpty()) {
            String verticeAtual = d.removeFilaPrioridade();
            if (verticeAtual.equals(verticeDestino)){
                break;
            }
            if (d.jaFoiVisitado(verticeAtual)) {
                continue;
            }
            d.adicionaVisitados(verticeAtual);
            for (Aresta aresta : d.verticeAtual(verticeAtual)) {
                String verticeVizinho = aresta.getDestino();
                Long somaPeso = d.retornaCusto(verticeAtual) + aresta.getCusto();
                if (somaPeso < d.retornaCusto(verticeVizinho) && (!d.jaFoiVisitado(verticeVizinho))) {
                    d.atualizaTabelaCusto(verticeVizinho,somaPeso);
                    d.atualizaTabelaPredecessor(verticeVizinho, verticeAtual);
                    d.adicionaFilaPrioridade(verticeVizinho);
                }
            }
        }
        System.out.println("O custo para chegar em " + verticeDestino + ", partindo de " + verticeOrigem + ", é: " + d.retornaCusto(verticeDestino));
        System.out.println(d.imprimeCaminhoCompleto(verticeDestino));
    }
}
