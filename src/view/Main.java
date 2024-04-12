package view;

import classes.Batalha;
import classes.Dificuldade;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
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
        long minutes = (ChronoUnit.MINUTES.between(inicio, fim) - (hours * 60));
        long seconds = (ChronoUnit.SECONDS.between(inicio, fim) - ((minutes * 60) + (hours * 60)));
        System.out.printf("Tempo de jogo: %d horas, %d minutos %d segundos", hours, minutes, seconds);
        System.out.println();
    }

    public static void jogada(Batalha jogo) {
        Scanner scanner = new Scanner(System.in);
        int colunaTorpedo;
        int linhaTorpedo;

        while (true) {
            try {
                System.out.print("\nDigite a coluna que deseja lancar (0 a 9): ");
                colunaTorpedo = scann.nextInt();
                if (colunaTorpedo >= 0 && colunaTorpedo <= 9) {
                    break; // Sai do loop se a entrada for válida
                } else {
                    System.out.println("Por favor, insira um número entre 0 e 9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        }

        while (true) {
            try {
                System.out.println();
                System.out.print("Digite a linha que deseja lancar (0 a 9): ");
                linhaTorpedo = scann.nextInt();
                if (linhaTorpedo >= 0 && linhaTorpedo <= 9) {
                    break; // Sai do loop se a entrada for válida
                } else {
                    System.out.println("Por favor, insira um número entre 0 e 9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira apenas números inteiros.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        }

        System.out.println();

        jogo.lancarTorpedo(linhaTorpedo, colunaTorpedo);
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
