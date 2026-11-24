public class Main{
    public static void main(String[] args){

        /*Interface jogo = new Interface();*/

        Funcionalidades partida = new Funcionalidades(false);

        System.out.println("Tabuleiro: " + partida.tamanho);
        System.out.println("Total de navios: " + partida.totNavios);
        System.out.println("Total de partes: " + partida.totPartes);
    }
}