import java.util.Random;

public class Funcionalidades {

    Random random = new Random();

    final int FACIL = 6;
    final int DIFICIL = 8;

    static final String[] NOMES = {"agua", "submarino", "destroier", "cruzador", "encouracado", "portaAvioes"};
    static final int PONTOS_ACERTO = 10;
    static final int BONUS_AFUNDAR = 30;
    static final int PENALIDADE_BOMBA = 20;

    int tamanho;
    int tiros;
    int qtdBombas;

    int[] frota;

    boolean[][] navios;
    boolean[][] bombas;
    boolean[][] atirado;
    int[][] tamanhoNavio;

    int totNavios;
    int totPartes;
    int tirosUsados;
    int acertos;
    int afundados;
    int pontos;

    /*Definindo a dificuldade*/
    public Funcionalidades(boolean dificil) {
        if (dificil) {
            tamanho = DIFICIL;
            tiros = 40;
            qtdBombas = 5;

            frota = new int[]{0, 6, 2, 2, 2, 1}; /*Define a qtd de cada navio*/
        } else {
            tamanho = FACIL;
            tiros = 30;
            qtdBombas = 3;

            frota = new int[]{0, 4, 2, 1, 1, 1};
        }

        /*Cálculo das qtds de navios e suas partes*/
        for (int i = 1; i < frota.length; i++) {
            totNavios += frota[i];
            totPartes += frota[i] * (i + 1);
        }

        navios = new boolean[tamanho][tamanho];
        bombas = new boolean[tamanho][tamanho];
        atirado = new boolean[tamanho][tamanho];
        tamanhoNavio = new int[tamanho][tamanho];

    }

    /*Define a posição dos navios*/
    public boolean posicionarNavios() {
        for (int i = 1; i < frota.length; i++) {
            for (int j = 0; j < frota[i]; j++) {
                if (!posicionarNavio(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean posicionarNavio(int comprimento) {
        int tentativas = 0;
        while (tentativas < 2000) {
            int linha = random.nextInt(tamanho);
            int coluna = random.nextInt(tamanho);
            boolean horizontal = random.nextBoolean();
            tentativas++;

            /*Condição para verificar se o navio cabe na matriz*/
            if (horizontal && coluna + comprimento > tamanho) {
                continue;
            }
            if (!horizontal && linha + comprimento > tamanho) {
                continue;
            }

            boolean livre = true;
            for (int parte = 0; parte < comprimento; parte++) {
                int l = linha + (horizontal ? 0 : parte);
                int c = coluna + (horizontal ? parte : 0);

                if (navios[l][c]) {
                    livre = false;
                    break;
                }
            }
            /*Posicionar o navio*/
            if (livre) {
                for (int parte = 0; parte < comprimento; parte++) {
                    int l = linha + (horizontal ? 0 : parte);
                    int c = coluna + (horizontal ? parte : 0);
                    navios[l][c] = true;
                    tamanhoNavio[l][c] = comprimento;
                }
            }
            return true;
        }
        return false;
    }
    /*Tiros*/
    public boolean atirar(int linha,int coluna){
        if (linha < 0 || linha >= tamanho || coluna < 0 || coluna >= tamanho) {
            return false;
        }
        if (atirado[linha][coluna]) {
            return false;
        }
        atirado[linha][coluna] = true;
        tirosUsados++;

        if (navios[linha][coluna]) {
            acertos++;
            pontos += PONTOS_ACERTO;
            return true;
        }
      return false;
    }

}