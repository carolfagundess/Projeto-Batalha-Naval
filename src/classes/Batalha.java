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
    private int qtdDanosTotal;
    private double precisao;

    int linhas = 10;
    int colunas = 10;

    public Batalha(Dificuldade nivel) {
        this.nivel = nivel;
        tabuleiro = new ArrayList<>();
        navios = new ArrayList<>();
        precisao = 0;
        criarTabuleiro();
        posicionarNavios();
        imprimirTabuleiro();
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

    private void posicionarNavios() {
        //criando navios
        for (TipoNavio tipo : TipoNavio.values()) {
//            System.out.println("Navio");
            int tamanhoNavio = nivel.getTamanhoPadraoNavio() + tipo.getTamanhoPadraoNavio();
            Navio navio = new Navio(tipo, tamanhoNavio);
            navios.add(navio);
            sortearPosicaoNavio(navio);
        }
        //imprimindo navios
    }

    public void sortearPosicaoNavio(Navio navio) {
        boolean posicaoEncontrada = false;
        while (!posicaoEncontrada) {
            int colunaSorteada = sorteador(10);
            int linhaSorteada = sorteador(10);
//            //vendo os números sorteados
//            System.out.println("Coluna " + colunaSorteada);
//            System.out.println("Linha " + linhaSorteada);

            String posicao = sortearPosicao();
            if (posicao.equals("vertical")) {
                //testar se a posição é válida
                int tamanhoPosicao = (linhaSorteada + navio.getTamanho() + nivel.getTamanhoPadraoNavio());
                if (validarPosicaoNavio(tamanhoPosicao)) {
                    if (validarTemNavio(posicao, linhaSorteada, colunaSorteada, navio) == false) {
                        setandoNavioVertical(navio, linhaSorteada, colunaSorteada);
                        posicaoEncontrada = true;
                    }
                }
            } else if (posicao.equals("horizontal")) {
                // Estrutura para posicionar o navio na horizontal - linha fixa e coluna variável
                // Testa se cabe na linha
                int tamanhoPosicao = (colunaSorteada + navio.getTamanho() + nivel.getTamanhoPadraoNavio());
                if (validarPosicaoNavio(tamanhoPosicao)) {
                    if (validarTemNavio(posicao, linhaSorteada, colunaSorteada, navio) == false) {
                        //caso não tiver navio, então chama o método setar navio
                        setandoNavioHorizontal(navio, linhaSorteada, colunaSorteada);
                        posicaoEncontrada = true;
                    }
                }
            }
        }
    }

    private boolean validarTemNavio(String posicao, int linhaSorteada, int colunaSorteada, Navio navio) {
        int tamanhoNavio = navio.getTamanho() + nivel.getTamanhoPadraoNavio();
        boolean testeNavio;

        if (navio == navios.get(0)) {
//            System.out.println("Nao tem navio");
            return false;
        } else if (posicao.equals("vertical")) {
            // Verifica se há um navio em alguma das células do navio na posição vertical
            for (int j = linhaSorteada; j < tamanhoNavio; j++) {
                testeNavio = tabuleiro.get(j).get(colunaSorteada).isTemNavio();
                if (testeNavio) {
//                    System.out.println("tem navio");
                    return true; // Há um navio presente ou fora dos limites do tabuleiro
                }
            }
        } else if (posicao.equals("horizontal")) {
            // Verifica se há um navio em alguma das células do navio na posição horizontal
            for (int i = colunaSorteada; i < tamanhoNavio; i++) {
                testeNavio = tabuleiro.get(linhaSorteada).get(i).isTemNavio();
                if (testeNavio) {
//                    System.out.println("tem navio");
                    return true; // Há um navio presente ou fora dos limites do tabuleiro
                }
            }
        }
//        System.out.println("nao tem navio");
        return false; // Não há navio presente
    }

    private boolean validarPosicaoNavio(int tamanhoPosicao) {
        boolean posicaoValida;
//        System.out.println(tamanhoPosicao);
        // Estrutura para posicionar o navio na horizontal - linha fixa e coluna variável
        if (tamanhoPosicao < 10) {
            // Testa se cabe na linha
//            System.out.println("Posicao Valida");
            posicaoValida = true;
        } else {
            posicaoValida = false;
        }
        return posicaoValida;
    }

    private void setandoNavioVertical(Navio navio, int linhaSorteada, int colunaSorteada) {
//        System.out.println("Adicionando na tabela");
        //vai definir o ponto de partida para setar o navio 
        tabuleiro.get(linhaSorteada).get(colunaSorteada).setNavio(navio);
        //validando se o campo passou a ter um navio
//        System.out.println("Tem navio? " + tabuleiro.get(linhaSorteada).get(colunaSorteada).isTemNavio());
        //defini qnts casas o for vai andar para setar um navio
        int tamanhoNavioTabuleiro = (navio.getTamanho() + linhaSorteada);
//        //mostra o navio que foi posto nesta posicao
//        System.out.println("Tipo do navio: " + navio.toString());
//        //posiciona o navio
//        System.out.println("--------------------VERTICAL----------------");
        //mostra onde o navio vai parar
//        System.out.println("Tamanho navio: " + tamanhoNavioTabuleiro);
        for (int i = linhaSorteada; i < tamanhoNavioTabuleiro; i++) {
            tabuleiro.get(i).get(colunaSorteada).setNavio(navio);
        }
    }

    private void setandoNavioHorizontal(Navio navio, int linhaSorteada, int colunaSorteada) {
//        System.out.println("Adicionando na tabela");
        //vai definir o ponto de partida para setar o navio 
        tabuleiro.get(linhaSorteada).get(colunaSorteada).setNavio(navio);
//        System.out.println("Tem navio? " + tabuleiro.get(colunaSorteada).get(linhaSorteada).isTemNavio());
        int tamanhoNavioTabuleiro = (navio.getTamanho() + colunaSorteada);
//        System.out.println("Tipo do navio: " + navio.toString());
//        System.out.println("--------------------HORIZONTAL----------------");
//        System.out.println("Tamanho navio: " + tamanhoNavioTabuleiro);
        for (int i = colunaSorteada; i < tamanhoNavioTabuleiro; i++) {
            tabuleiro.get(linhaSorteada).get(i).setNavio(navio);
        }
    }

    public void lancarTorpedo(int linha, int coluna) {
        //incrementa os torpedos lancados
        qtdTorpedosLancados++;
        //verifica se tem navio na linha e coluna desejada
        boolean validandoCampo = tabuleiro.get(linha).get(coluna).isTemNavio();
        tabuleiro.get(linha).get(coluna).setTorpedoLancado(true);
        if (validandoCampo) {
//            System.out.println("TEM NAVIO");
            System.out.println("Voce acertou um navio! Recalculando....");
            Navio navioAtingido = tabuleiro.get(linha).get(coluna).getNavio();
            navioAtingido.atingir();
            qtdDanosTotal++;
            if (navioAtingido.afundadao()) {
                qtdNaviosAfundados++;
            }
        }
    }

    public boolean verificarVitoria() {
        boolean status = false;
        System.out.println();
        for (Navio navio : navios) {
            String afundado;
            afundado = navio.afundadao() ? "- AFUNDADO" : "";
            System.out.println(navio.getTipoNavio() + ": " + navio.getDano() + " acertos" + afundado);
        }
        if (qtdNaviosAfundados == 4) {
            status = true;
        }
        System.out.println("Torpedos Lançados: " + qtdTorpedosLancados + "  -  " + "Navios Afundados: " + qtdNaviosAfundados);
        System.out.println("Torpedos acertados: " + qtdDanosTotal + "  -  Precisao: " + calcularPrecisao());
        System.out.println();
        imprimirTabuleiro();
        return status;
    }

    public double calcularPrecisao() {
        // Verifica se houve torpedos lançados antes de calcular a precisão para evitar divisão por zero
        if (qtdTorpedosLancados > 0) {
            precisao = (double) qtdNaviosAfundados / qtdTorpedosLancados;
        } else {
            precisao = 0.0; // Se nenhum torpedo foi lançado, a precisão é definida como zero
        }
        return precisao;
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

    private String sortearPosicao() {
        int numero = sorteador(2);
        String posicao;
        if (numero % 2 == 0) {
            posicao = "vertical";
        } else {
            posicao = "horizontal";
        }
        return posicao;
    }
}
