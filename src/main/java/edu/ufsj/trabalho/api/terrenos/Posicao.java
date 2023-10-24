package edu.ufsj.trabalho.api.terrenos;

public class Posicao {

    private int coluna;
    private int linha;

    public Posicao(int linha, int coluna) {
        this.coluna = coluna;
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

}
