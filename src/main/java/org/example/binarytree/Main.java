package org.example.binarytree;

import javax.swing.plaf.basic.BasicTextAreaUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Btree arvore = new Btree();
        int opcao;
        int valorUser;
        while (true) {
            System.out.println("[1] - Inserir nó em uma árvore");
            System.out.println("[2] - Excluir nó de uma árvore");
            System.out.println("[3] - Verificar se um elemento pertence à árvore;");
            System.out.println("[4] - Escrever as folhas de uma árvore");
            System.out.println("[5] - Verificar a altura da árvore");
            System.out.println("[6] - Verificar o nível em que se encontra um elemento da árvore");
            System.out.println("[7] - Verificar se uma árvore é completa ou não");
            System.out.println("[8] - Percorrer árvore (Pré-Ordem)");
            System.out.println("[9] - Percorrer árvore (In-Ordem)");
            System.out.println("[10] - Percorrer árvore (Pós-Ordem)");
            opcao = input.nextInt();
            if (opcao < 1 || opcao > 10) {
                continue;
            }
            break;
        }
        switch (opcao) {
            case 1 -> {
                valorUser = input.nextInt();
                arvore.inserirValor(valorUser);
            }
            case 2 -> {
                valorUser = input.nextInt();
                arvore.removerValor(valorUser);
            }
            case 3 -> {
                valorUser = input.nextInt();
                arvore.contemValor(valorUser);
            }
            case 4 -> {
                arvore.folhas();
            }
            case 5 -> {
                arvore.alturaArvore();
            }
            case 6 -> {
                valorUser = input.nextInt();
                arvore.nivelDoValor(valorUser);
            }
            case 7 -> {
                arvore.isComplete();
            }
            case 8 -> {
                arvore.preOrdem();
            }
            case 9 -> {
                arvore.inOrdem();
            }
            case 10 -> {
                arvore.posOrdem();
            }
        }
    }
}
