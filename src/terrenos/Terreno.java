package terrenos;

public class Terreno {

    private final int quantidadeLinhas;
    private final int quantidadeColunas;

    private Celula[][] terreno;

    public Terreno(int quantidadeLinhas, int quantidadeColunas) {
        this.quantidadeLinhas = quantidadeLinhas;
        this.quantidadeColunas = quantidadeColunas;

        if (quantidadeColunas <= 0 || quantidadeLinhas <= 0) {
            throw new IllegalArgumentException("Quantidade de linhas e colunas invalidas");
        }

        inicializarTerreno();
    }

    public void inicializarTerreno() {
        terreno = new Celula[quantidadeLinhas][quantidadeColunas];

        for (int j = quantidadeLinhas -1; j >= 0; j--) { // o (0,0) tem que ser a esquerda inferior e tem que see(x,y), e estava (y,x)
            for (int i = 0; i < quantidadeColunas; i++) {
                terreno[j][i] = new Celula(new Posicao(j, i));
            }
        }
    }

    public void imprimirTerreno() {
        for (int j = quantidadeLinhas -1; j >= 0; j--) {
            for (int i = 0; i < quantidadeColunas; i++) {
                System.out.print(terreno[j][i]);
            }
            System.out.println();
        }
    }

    private boolean posicaoNaoEhValida(Posicao posicao) {
        return posicao.getColuna() < 0 || posicao.getColuna() >= quantidadeColunas ||
                posicao.getLinha() < 0 || posicao.getLinha() >= quantidadeLinhas;
    }

    public Celula getCelulaPosicao(Posicao posicao) {
        if (posicaoNaoEhValida(posicao)) {
            return null;
        }
        return terreno[posicao.getColuna()][posicao.getLinha()];
    }

    public int getQuantidadeLinhas() {
        return quantidadeLinhas;
    }

    public int getQuantidadeColunas() {
        return quantidadeColunas;
    }


}