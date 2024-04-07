package classes;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author carol
 */
public class Batalha {

    private ArrayList<ArrayList<Campo>> tabuleiro;
    private Dificuldade nivel;
    private ArrayList<Navio> navios;
    private int qtdNaviosAfundados;
    private int qtdTorpedosLancados;

    int linhas = 10;
    int colunas = 10;

    public Batalha(Dificuldade nivel) {
        this.nivel = nivel;
        tabuleiro = new ArrayList<>();
        navios = new ArrayList<>();
        criarTabuleiro();
    }

    private void criarTabuleiro() {
        //criando uma matriz de 10x10
        for (int i = 0; i < linhas; i++) {
            tabuleiro.add(new ArrayList<>());
            for (int j = 0; j < colunas; j++) {
                tabuleiro.get(i).add(new Campo());
            }
        }
    }

    public void posicionarNavios() {
        //criando navios
        for (TipoNavio tipo : TipoNavio.values()) {
            System.out.println("Navio");
            int tamanhoNavio = nivel.getTamanhoPadraoNavio() + tipo.getTamanhoPadraoNavio();
            Navio navio = new Navio(tipo, tamanhoNavio);
            navios.add(navio);
            sortearPosicaoNavio();
            System.out.println();
        }
        //imprimindo navios
        System.out.println(navios);
    }

    public void sortearPosicaoNavio() {
        int colunaSorteada = 3;
        int linhaSorteada = sorteador(5);
        Navio navio = new Navio(TipoNavio.PORTAAVIOES, (TipoNavio.PORTAAVIOES.getTamanhoPadraoNavio()+nivel.getTamanhoPadraoNavio()));
        //vendo os numeros sorteados
        System.out.println("Coluna " + colunaSorteada);
        System.out.println("Linha " + linhaSorteada);

        String posicao = sortearPosicao();
        if (posicao.equals("vertical")) {
            //seta coluna e percorre linha
            if (colunaSorteada + navio.getTamanho() > colunas) {
                //testa se cabe no tabuleiro
                System.out.println("Nao cabe na coluna:" + (colunaSorteada + navio.getTamanho()));
            } else {
                System.out.println("Adicionando na tabela");
                tabuleiro.get(linhaSorteada).get(colunaSorteada).setNavio(navio);  
                System.out.println("Tem navio? "+ tabuleiro.get(linhaSorteada).get(colunaSorteada).isTemNavio());
                int tamanhoNavioTabuleiro = (navio.getTamanho()+linhaSorteada);
                System.out.println("Tipo do navio: " + navio.toString());
                imprimirTabuleiro();
                System.out.println("--------------------colocando nas linhas----------------");
                System.out.println("Tamanho navio: " + tamanhoNavioTabuleiro);
                for (int i = linhaSorteada; i < tamanhoNavioTabuleiro; i++) {
                    tabuleiro.get(i).get(colunaSorteada).setNavio(navio);
                }
            }
        }
    }

    private String sortearPosicao() {
        String posicao = "vertical";
        return posicao;
    }

    public void imprimirTabuleiro() {
        //chama o metodo para imprimir
        for (int j = 0; j < colunas; j++) {
            System.out.print("    " + j + " ");
        }
        System.out.println();

        for (int i = 0; i < linhas; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < colunas; j++) {
                Campo campo = tabuleiro.get(i).get(j);
                System.out.print("  " + campo.mostra() + "   ");
            }
            System.out.println();
        }
    }

    private int sorteador(int numero) {
        Random random = new Random();
        int numeroSorteado = random.nextInt(numero);
        return numeroSorteado;
    }
}
