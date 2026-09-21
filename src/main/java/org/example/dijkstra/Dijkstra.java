package org.example.dijkstra;

import java.util.*;

public class Dijkstra {
    private final Map<String,List<Aresta>> listaAdjacencia;
    private final Map<String, Long> tabelaCusto;
    private final Map<String, String> tabelaPredecessor;
    private final PriorityQueue<String> filaDePrioridade;
    private final Set<String> visitados;
    private String[] aeroportos;
    {
        this.listaAdjacencia = new HashMap<>();
        this.tabelaCusto = new HashMap<>();
        this.tabelaPredecessor = new HashMap<>();
        this.visitados = new HashSet<>();
        this.filaDePrioridade = new PriorityQueue<>(Comparator.comparingLong(s -> this.tabelaCusto.get(s)));
    }
    {
        this.aeroportos = new String[]{
            "NT1", "NT2", "NORD1", "NORD2", "NORD3", "NORD4", "NORD5",
            "CENT1", "CENT2", "CENT3", "CENT4",
            "SUD1", "SUD2", "SUD3", "SUD4", "SUD5", "SUD6", "SUD7", "SUD8",
            "SUL1", "SUL2", "SUL3", "SUL4", "SUL5"
        };
    }
    {
        for (String aeroporto : aeroportos) {
            this.tabelaCusto.put(aeroporto,1000000L);
            this.tabelaPredecessor.put(aeroporto,null);
            this.listaAdjacencia.put(aeroporto, new ArrayList<>());
        }
    }
    {
        adicionaListaAdj("NT1", new Aresta("NT2", 15));
        adicionaListaAdj("NT1", new Aresta("NORD1", 25));
        adicionaListaAdj("NT1", new Aresta("NORD5", 60));
        adicionaListaAdj("NT1", new Aresta("CENT2", 28));
        adicionaListaAdj("NT1", new Aresta("SUD1", 50));
        adicionaListaAdj("NT2", new Aresta("NORD1", 40));
        adicionaListaAdj("NT2", new Aresta("CENT2", 30));
        adicionaListaAdj("NT2", new Aresta("CENT4", 25));
        adicionaListaAdj("NORD1", new Aresta("NORD2", 10));
        adicionaListaAdj("NORD1", new Aresta("NORD4", 30));
        adicionaListaAdj("NORD1", new Aresta("SUD1", 24));
        adicionaListaAdj("NORD2", new Aresta("NORD3", 12));
        adicionaListaAdj("NORD3", new Aresta("NORD4", 15));
        adicionaListaAdj("NORD3", new Aresta("SUD1", 36));
        adicionaListaAdj("NORD3", new Aresta("SUD4", 42));
        adicionaListaAdj("NORD4", new Aresta("NORD5", 18));
        adicionaListaAdj("NORD5", new Aresta("SUD1", 75));
        adicionaListaAdj("NORD5", new Aresta("SUD3", 25));
        adicionaListaAdj("NORD5", new Aresta("SUD6", 32));
        adicionaListaAdj("NORD5", new Aresta("SUL1", 75));
        adicionaListaAdj("CENT1", new Aresta("CENT2", 17));
        adicionaListaAdj("CENT1", new Aresta("CENT3", 14));
        adicionaListaAdj("CENT1", new Aresta("SUD1", 22));
        adicionaListaAdj("CENT1", new Aresta("SUL3", 30));
        adicionaListaAdj("CENT1", new Aresta("SUL5", 24));
        adicionaListaAdj("CENT2", new Aresta("SUD1", 28));
        adicionaListaAdj("CENT3", new Aresta("CENT4", 14));
        adicionaListaAdj("CENT3", new Aresta("SUL2", 41));
        adicionaListaAdj("CENT3", new Aresta("SUL3", 35));
        adicionaListaAdj("CENT3", new Aresta("SUL5", 22));
        adicionaListaAdj("SUD1", new Aresta("SUD2", 10));
        adicionaListaAdj("SUD1", new Aresta("SUD5", 15));
        adicionaListaAdj("SUD1", new Aresta("SUD7", 19));
        adicionaListaAdj("SUD1", new Aresta("SUL2", 32));
        adicionaListaAdj("SUD2", new Aresta("SUD3", 12));
        adicionaListaAdj("SUD3", new Aresta("SUD4", 16));
        adicionaListaAdj("SUD3", new Aresta("SUD8", 16));
        adicionaListaAdj("SUD6", new Aresta("SUL2", 25));
        adicionaListaAdj("SUD7", new Aresta("SUL3", 40));
        adicionaListaAdj("SUD8", new Aresta("SUL2", 38));
        adicionaListaAdj("SUL1", new Aresta("SUL2", 10));
        adicionaListaAdj("SUL2", new Aresta("SUL3", 11));
        adicionaListaAdj("SUL3", new Aresta("SUL4", 15));
        adicionaListaAdj("SUL4", new Aresta("SUL5", 12));
    }

    public void adicionaListaAdj(String nomeVertice, Aresta aresta) {
        this.listaAdjacencia.get(nomeVertice).add(aresta);
        this.listaAdjacencia.get(aresta.getDestino()).add(new Aresta(nomeVertice, aresta.getCusto()));
    }

    public void adicionaFilaPrioridade(String aeroporto) {
        this.filaDePrioridade.add(aeroporto);
    }

    public void adicionaVisitados(String verticeAtual) {
        this.visitados.add(verticeAtual);
    }

    public void atualizaTabelaCusto(String nomeVertice, Long novoMenorCusto) {
        this.tabelaCusto.replace(nomeVertice,novoMenorCusto);
    }

    public void atualizaTabelaPredecessor(String nomeVertice, String novoPai) {
        this.tabelaPredecessor.put(nomeVertice,novoPai);
    }

    public Long retornaCusto(String verticeAtual) {
        return this.tabelaCusto.get(verticeAtual);
    }

    public void imprimeMenorCusto() {
        for (String chaveVertice : tabelaCusto.keySet()) {
            System.out.println("VÉRTICE: " + chaveVertice + " | MENOR CUSTO: " + tabelaCusto.get(chaveVertice));
        }
    }

    public void imprimePredecessores() {
        for (String chaveVertice : tabelaPredecessor.keySet()) {
            System.out.println("VÉRTICE: " + chaveVertice + " | VERT. PREDECESSOR: " + tabelaPredecessor.get(chaveVertice));
        }
    }

    public String imprimeCaminhoCompleto (String verticeAtual) {
        List<String> caminho = new ArrayList<>();
        while (verticeAtual != null) {
            caminho.add(0,verticeAtual);
            verticeAtual = this.tabelaPredecessor.get(verticeAtual);
        }
        return String.join(" -> ", caminho);
    }

    public void imprimeVertices() {
        for (String aeroporto : aeroportos) {
            System.out.println(aeroporto);
        }
    }

    public static void main(String[] args) {
        Scanner lerInput = new Scanner(System.in);
        Dijkstra d = new Dijkstra();

        d.imprimeVertices();
        System.out.println("Digite um ponto de partida: ");
        String verticeOrigem = lerInput.nextLine().toUpperCase();
        System.out.println("Digite um ponto de chegada: ");
        String verticeDestino = lerInput.nextLine().toUpperCase();

        d.atualizaTabelaCusto(verticeOrigem, 0L);
        d.adicionaFilaPrioridade(verticeOrigem);

        while (!d.filaDePrioridade.isEmpty()) {
            String verticeAtual = d.filaDePrioridade.poll();
            if (verticeAtual.equals(verticeDestino)){
                break;
            }
            if (!d.visitados.contains(verticeAtual)) {
                d.adicionaVisitados(verticeAtual);
            } else {
                continue;
            }
            for (Aresta aresta : d.listaAdjacencia.get(verticeAtual)) {
                String verticeVizinho = aresta.getDestino();
                Long somaPeso = d.retornaCusto(verticeAtual) + aresta.getCusto();
                if (somaPeso < d.retornaCusto(verticeVizinho) && (!d.visitados.contains(verticeVizinho))) {
                    d.atualizaTabelaCusto(verticeVizinho,somaPeso);
                    d.atualizaTabelaPredecessor(verticeVizinho, verticeAtual);
                    d.adicionaFilaPrioridade(verticeVizinho);
                }
            }
        }
        System.out.println("O custo para chegar em " + verticeDestino + " é: " + d.retornaCusto(verticeDestino));
        System.out.println(d.imprimeCaminhoCompleto(verticeDestino));
    }
}
