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
        this.filaDePrioridade =
                new PriorityQueue<>(Comparator.comparingLong(s -> this.tabelaCusto.get(s)));
        this.visitados = new HashSet<>();
        // A primeira vez que um vértice SAI da fila pelo poll(),
        // o custo dele até a origem está 100% garantido o MENOR CUSTO POSSÍVEL.
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
        // Primeiros passos do algoritmo:
        // 1 - Definir distância inicial para qualquer vértice do grafo como INFINITA (desconhecida).
        // 2 - Definir um caminho 'desconhecido' para chegar em qualquer vértice, os valores ideais formariam a trilha para o menor custo.
        // 3 - Adicionamos todos os vértices a fila, ela liberará o com menor custo, de início será o Vértice de Origem.
        for (String aeroporto : aeroportos) {
            this.tabelaCusto.put(aeroporto,1000000L);
            this.tabelaPredecessor.put(aeroporto,null);
            this.listaAdjacencia.put(aeroporto, new ArrayList<>());
        }
    }
    {
        // chamar esse método aqui funciona, pois não depende de nenhum atributo de instância valorado
        // (valor informado pelo usuário ou no construtor)
        adicionaListaAdj("NT1", new Aresta("NT2", 15));
        adicionaListaAdj("NT1", new Aresta("NORD1", 25));
        adicionaListaAdj("NT1", new Aresta("NORD5", 60));
        adicionaListaAdj("NT1", new Aresta("CENT2", 28));
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
        // Adiciona conexão B em A
        this.listaAdjacencia.get(nomeVertice).add(aresta);
        // Adiciona conexão A em B
        this.listaAdjacencia.get(aresta.getDestino()).add(new Aresta(nomeVertice, aresta.getCusto()));
    }

    public Long retornaCusto(String verticeAtual) {
        return this.tabelaCusto.get(verticeAtual);
    }

    public void atualizaTabelaCusto(String nomeVertice, Long novoMenorCusto) {
        this.tabelaCusto.replace(nomeVertice,novoMenorCusto);
    }

    public void atualizaTabelaPredecessor(String nomeVertice, String novoPai) {
        this.tabelaPredecessor.put(nomeVertice,novoPai);
    }

    public void adicionaFilaPrioridade(String aeroporto) {
        this.filaDePrioridade.add(aeroporto);
    }

    public void adicionaVisitados(String verticeAtual) {
        this.visitados.add(verticeAtual);
    }

    public void imprimeMenorCusto() {
        for (String chaveVertice : tabelaCusto.keySet()) {
            System.out.println("VÉRTICE: " + chaveVertice +
                               " | MENOR CUSTO: " + tabelaCusto.get(chaveVertice));
        }
    }

    public void imprimePredecessores() {
        for (String chaveVertice : tabelaPredecessor.keySet()) {
            System.out.println("VÉRTICE: " + chaveVertice +
                               " | VERT. PREDECESSOR: " + tabelaPredecessor.get(chaveVertice));
        }
    }

    public String imprimeCaminhoCompleto (String verticeAtual) {
        List<String> caminho = new ArrayList<>();
        while (verticeAtual != null) {
            caminho.add(0,verticeAtual);
            verticeAtual = this.tabelaPredecessor.get(verticeAtual);
        }
        return String.join(" -> ",caminho);
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
            // Removemos o vértice de menor custo
            String verticeAtual = d.filaDePrioridade.poll();
            if (verticeAtual.equals(verticeDestino)){
                break;
            }
            // Um mesmo vértice pode entrar na PQ várias vezes, porém apenas quando é dado poll que tiramos ele para explorar
            if (!d.visitados.contains(verticeAtual)) {
                d.adicionaVisitados(verticeAtual);
            } else {
                continue;
            }

            // Acessamos os vizinhos desse vértice que acabamos de remover para encontrar o próximo vértice de menor custo
            for (Aresta aresta : d.listaAdjacencia.get(verticeAtual)) {
                String verticeVizinho = aresta.getDestino();
                Long somaPeso = d.retornaCusto(verticeAtual) + aresta.getCusto();

                // Um vértice já visitado já estará com o menor custo possível, verificar seu custo é perda de tempo
                if (somaPeso < d.retornaCusto(verticeVizinho) && !d.visitados.contains(verticeVizinho)) {
                    d.atualizaTabelaCusto(verticeVizinho,somaPeso);
                    d.atualizaTabelaPredecessor(verticeVizinho, verticeAtual);
                    d.adicionaFilaPrioridade(verticeVizinho);
                }
            }
        }
        System.out.println("O custo para chegar em " + verticeDestino + " é: " + d.retornaCusto(verticeDestino));
        System.out.println(d.imprimeCaminhoCompleto(verticeDestino));
        //d.imprimePredecessores();
        //d.imprimeMenorCusto();
    }
}
