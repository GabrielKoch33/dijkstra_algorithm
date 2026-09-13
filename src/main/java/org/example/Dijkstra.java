package org.example;

import java.util.*;

public class Dijkstra {
    private final Map<String,List<Aresta>> listaAdjacencia;
    private final Map<String, Integer> hashMenorCusto;
    private final Map<String, String> hashParente;
    private final PriorityQueue<Par> filaDePrioridade;

    public Dijkstra() {
        this.listaAdjacencia = new HashMap<>();
        this.hashMenorCusto = new HashMap<>();
        this.hashParente = new HashMap<>();
        this.filaDePrioridade = new PriorityQueue<>(
                Comparator.comparingInt(Par :: getCustoAcumulado)
        );
    }

    public void adicionaListaAdj(String nomeVertice, Aresta aresta) {
        // Adiciona conexão B em A
        this.listaAdjacencia.get(nomeVertice).add(aresta);

        // Adiciona conexão A em B
        this.listaAdjacencia.get(aresta.getDestino()).add(new Aresta(nomeVertice, aresta.getCusto()));
    }

    public void atualizaMenorCusto(String nomeVertice, int novoMenorCusto) {
        this.hashMenorCusto.replace(nomeVertice,novoMenorCusto);
    }

    public void atualizaParente(String nomeVertice, String novoPai) {
        this.hashParente.replace(nomeVertice,novoPai);
    }

    public void adicionaPQueue(Par par) {
        this.filaDePrioridade.add(par);
    }

    public void criaTudo() {
        String[] aeroportos = {
                "NT1", "NT2", "NORD1", "NORD2", "NORD3", "NORD4", "NORD5",
                "CENT1", "CENT2", "CENT3", "CENT4",
                "SUD1", "SUD2", "SUD3", "SUD4", "SUD5", "SUD6", "SUD7", "SUD8",
                "SUL1", "SUL2", "SUL3", "SUL4", "SUL5"
        };

        for (String aeroporto : aeroportos) {
            hashMenorCusto.put(aeroporto,Integer.MAX_VALUE);
            hashParente.put(aeroporto,null);
            listaAdjacencia.put(aeroporto, new ArrayList<>());
        }

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

    public void imprimeMenorCusto() {
        for (String chaveVertice : hashMenorCusto.keySet()) {
            System.out.println("VÉRTICE: " + chaveVertice +
                               " | MENOR CUSTO: " + hashMenorCusto.get(chaveVertice));
        }
    }

    public void imprimePai() {
        for (String chaveVertice : hashParente.keySet()) {
            System.out.println("VÉRTICE: " + chaveVertice +
                               " | VERT. PAI: " + hashParente.get(chaveVertice));
        }
    }

    /**
     * Aresta e Par são 'static', pois não dependem de uma instância de Dijkstra para existir.<br><br>
     * As mesmas não usam de atributos de instância da classe Dijkstra, são apenas métodos auxiliares
     * que complementam as funcionalidades da classe Pai.<br><br>
     * Caso fossem public, seria necessário passar como parâmetro para 'criaTudo()' um objeto de Dijkstra para que
     * então fosse acessado a classe Aresta e seus construtores, etc.
     * **/
    static class Aresta {
        private int custo;
        private String destino;

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

    static class Par {
        private String nomeVertice;
        private int custoAcumulado;

        public Par(String nomeVertice, int menorCustoAcumulado) {
            this.nomeVertice = nomeVertice;
            this.custoAcumulado = menorCustoAcumulado;
        }

        public int getCustoAcumulado() {
            return custoAcumulado;
        }

        public String getNomeVertice() {
            return nomeVertice;
        }
    }

    public static void main(String[] args) {
        Scanner lerInput = new Scanner(System.in);
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.criaTudo();

        String verticeOrigem = lerInput.nextLine();
        String verticeDestino = lerInput.nextLine();

        dijkstra.atualizaMenorCusto(verticeOrigem, 0);
    }
}
