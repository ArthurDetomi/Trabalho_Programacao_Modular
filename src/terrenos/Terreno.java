package terrenos;

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

        for (int j = QUANTIDADE_LINHAS-1; j >= 0; j--) { // o (0,0) tem que ser a esquerda inferior e tem que see(x,y), e estava (y,x)
            for (int i = 0; i < QUANTIDADE_COLUNAS; i++) {
                terreno[j][i] = new Celula(new Posicao(j, i));
            }
        }
    }

    public void imprimirTerreno() {
        for (int j = QUANTIDADE_LINHAS-1; j >= 0; j--) {
            for (int i = 0; i < QUANTIDADE_COLUNAS; i++) {
                System.out.print(terreno[j][i]);
            }
            System.out.println();
        }
    }

    private boolean posicaoNaoEhValida(Posicao posicao) {
        return posicao.getX() < 0 || posicao.getX() >= QUANTIDADE_COLUNAS ||
                posicao.getY() < 0 || posicao.getY() >= QUANTIDADE_LINHAS;
    }

    public Celula getCelulaPosicao(Posicao posicao) {
        if (posicaoNaoEhValida(posicao)) {
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
