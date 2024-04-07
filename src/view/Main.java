package view;

import classes.Batalha;
import classes.Dificuldade;
import java.util.Scanner;

/**
 *
 * @author carol
 */
public class Main {

    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);

        System.out.println(" x  x  x  x  x  x  x  x  x  x  o  o  o  o  o  o  o  o  o  o");
        System.out.println("          Jogo Batalha Naval - Carol Fagundes");
        System.out.println(" o  o  o  o  o  o  o  o  o  o  x  x  x  x  x  x  x  x  x  x");

        System.out.println("Escolha  dificuldade do jogo");
        System.out.println("0 - FACIL");
        System.out.println("1 - NORMAL");
        System.out.println("2 - DIFICIL");
        System.out.print("Opcao: ");
        int opcao = scann.nextInt();

        System.out.println();
        Batalha jogo = new Batalha(retornaOpcao(opcao));
        jogo.imprimirTabuleiro();
        
//        System.out.print("Digite a linha que deseja lançar o torpedo: ");
//        System.out.print("Digite a coluna que deseja lancar o torpedo: ");
        System.out.println("-----------------------");
        jogo.sortearPosicaoNavio();
        jogo.imprimirTabuleiro();
    }
    

//public static Dificuldade lerOpcao() {
//        Scanner scann = new Scanner(System.in);
//        try {
//            opcaoTeste = scann.nextInt();
//            executarOpcao(opcaoTeste);
//        } catch (Exception e) {
//            System.out.println("Opcao invalida. Por favor, insira um numero.");
//            scann.nextLine(); // Limpa o buffer do scanner
//            lerOpcao(); // Repete a leitura da opção
//        }
//    }
//
    public static Dificuldade retornaOpcao(int opcao) {
        Dificuldade nivel = Dificuldade.NORMAL;
        switch (opcao) {
            case 0 ->
                nivel = Dificuldade.FACIL;
            case 1 ->
                nivel = Dificuldade.NORMAL;
            case 2 ->
                nivel = Dificuldade.DIFICIL;
        }
        return nivel;
    }
}
