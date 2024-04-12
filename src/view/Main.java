package view;

import classes.Batalha;
import classes.Dificuldade;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 *
 * @author carol
 */
public class Main {

    static Scanner scann = new Scanner(System.in);

    public static void main(String[] args) {

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
        System.out.println("Imprimindo tabuleiro no modo " + retornaOpcao(opcao));
        System.out.println();
        LocalDateTime inicio = LocalDateTime.now();
        Batalha jogo = new Batalha(retornaOpcao(opcao));
        System.out.println("----------------------------------");
        do {
            jogada(jogo);
        } while (!jogo.verificarVitoria());


        System.out.println("=========VOCE GANHOU=======");
        LocalDateTime fim = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(inicio, fim);
        long minutes = (ChronoUnit.MINUTES.between(inicio, fim) - (hours*60));
        long seconds = (ChronoUnit.SECONDS.between(inicio, fim)-((minutes*60) + (hours*60)));
        System.out.printf("Tempo de jogo: %d horas, %d minutos %d segundos", hours, minutes, seconds);
        System.out.println();
    }

    public static void jogada(Batalha jogo) {
        System.out.print("\nDigite a coluna que deseja lancar: ");
        int colunaTorpedo = scann.nextInt();
        System.out.print("Digite a linha que deseja lancar: ");
        int linhaTorpedo = scann.nextInt();
        System.out.println();
        jogo.lancarTorpedo(linhaTorpedo, colunaTorpedo);
        jogo.imprimirTabuleiro();
    }

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
