package Terrenos;

public class Terreno {

    private final int QUANTIDADE_LINHAS;
    private final int QUANTIDADE_COLUNAS;

    private Celula[][] terreno;

    public Terreno(int QUANTIDADE_LINHAS, int QUANTIDADE_COLUNAS) {
        this.QUANTIDADE_LINHAS = QUANTIDADE_LINHAS;
        this.QUANTIDADE_COLUNAS = QUANTIDADE_COLUNAS;

        if (QUANTIDADE_COLUNAS <= 0 || QUANTIDADE_LINHAS <= 0) {
            throw new IllegalArgumentException("Quantidade de linhas e colunas invalidas");
        }

        inicializarTerreno();
    }

    private void inicializarTerreno() {
        terreno = new Celula[QUANTIDADE_LINHAS][QUANTIDADE_COLUNAS];

        for (int i = 0; i < QUANTIDADE_LINHAS; i++) {
            for (int j = 0; j < QUANTIDADE_COLUNAS; j++) {
                terreno[i][j] = new Celula(new Posicao(i, j));
            }
        }
    }

    public void imprimirTerreno() {
        for (int i = 0; i < QUANTIDADE_LINHAS; i++) {
            for (int j = 0; j < QUANTIDADE_COLUNAS; j++) {
                System.out.print(terreno[i][j]);
            }
            System.out.println();
        }
    }

    private boolean posicaoEhValida(Posicao posicao) {
        return posicao.getX() < 0 || posicao.getX() >= QUANTIDADE_COLUNAS ||
                posicao.getY() < 0 || posicao.getY() >= QUANTIDADE_LINHAS;
    }

    public Celula getCelulaPosicao(Posicao posicao) {
        if (!posicaoEhValida(posicao)) {
            return null;
        }
        return terreno[posicao.getX()][posicao.getY()];
    }

    public int getQUANTIDADE_LINHAS() {
        return QUANTIDADE_LINHAS;
    }

    public int getQUANTIDADE_COLUNAS() {
        return QUANTIDADE_COLUNAS;
    }


}