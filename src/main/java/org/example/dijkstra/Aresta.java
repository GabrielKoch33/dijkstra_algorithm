package org.example.dijkstra;
/**
 * A classe Aresta poderia ser 'static', pois não depende de uma instância de Dijkstra para existir.<br><br>
 * A mesma não usa de atributos de instância da classe Dijkstra, é apenas auxiliar
 * que complementa a funcionalidade da classe Pai.<br><br>
 * Caso fosse public, seria necessário passar como parâmetro para 'criaTudo()' um objeto de Dijkstra para que
 * então fosse acessado a classe Aresta e seus construtores, etc.
 * **/
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
