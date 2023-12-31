package edu.ufsj.trabalho.api.terrenos;

import edu.ufsj.trabalho.api.arquivo.leitura.TerrenoLeitura;

public class Terreno {

    private int quantidadeLinhas;
    private int quantidadeColunas;

    private Celula[][] terreno;

    public Terreno(int quantidadeLinhas, int quantidadeColunas) {
        this.quantidadeLinhas = quantidadeLinhas;
        this.quantidadeColunas = quantidadeColunas;

        if (quantidadeColunas <= 0 || quantidadeLinhas <= 0) {
            throw new IllegalArgumentException("Quantidade de linhas e colunas invalidas");
        }

        inicializarTerreno();
    }

    public Terreno(TerrenoLeitura terrenoLeitura) {
        this.quantidadeLinhas = terrenoLeitura.getQuantidadeLinhas();
        this.quantidadeColunas = terrenoLeitura.getQuantidadeColunas();

        if (quantidadeColunas <= 0 || quantidadeLinhas <= 0) {
            throw new IllegalArgumentException("Quantidade de linhas e colunas invalidas");
        }

        inicializarTerreno();
    }

    private void inicializarTerreno() {
        terreno = new Celula[quantidadeLinhas][quantidadeColunas];

        for (int j = quantidadeLinhas - 1; j >= 0; j--) { // o (0,0) tem que ser a esquerda inferior e tem que see(x,y), e estava (y,x)
            for (int i = 0; i < quantidadeColunas; i++) {
                terreno[j][i] = new Celula(new Posicao(j, i));
            }
        }
    }

    public void imprimirTerreno() {
        for (int j = quantidadeLinhas - 1; j >= 0; j--) {
            for (int i = 0; i < quantidadeColunas; i++) {
                System.out.print(terreno[j][i]);
            }
            System.out.println();
        }
    }

    public void imprimirDashboard() {
        for (int i = 0; i < quantidadeLinhas; i++) {
            for (int j = 0; j < quantidadeColunas; j++) {

                System.out.printf(String.format("\t[CH:%.2f|RT:%.2f]",
                        terreno[i][j].getConcentracaoHelio(),
                        terreno[i][j].getRugosidadeTerreno()));

                if (terreno[i][j].isTemRobo()) {
                    System.out.print("(R)");
                }

            }
            System.out.println("\n");
        }
    }

    private boolean posicaoNaoEhValida(Posicao posicao) {
        return posicao.getColuna() < 0 || posicao.getColuna() >= quantidadeColunas ||
                posicao.getLinha() < 0 || posicao.getLinha() >= quantidadeLinhas;
    }

    public Celula getCelulaPosicao(Posicao posicao) {
        if (posicaoNaoEhValida(posicao)) {
            return new Celula();
        }
        return terreno[posicao.getLinha()][posicao.getColuna()];
    }

    public int getQuantidadeLinhas() {
        return quantidadeLinhas;
    }

    public int getQuantidadeColunas() {
        return quantidadeColunas;
    }

    public void setQuantidadeLinhas(int quantidadeLinhas) {
        this.quantidadeLinhas = quantidadeLinhas;
    }

    public void setQuantidadeColunas(int quantidadeColunas) {
        this.quantidadeColunas = quantidadeColunas;
    }
}
