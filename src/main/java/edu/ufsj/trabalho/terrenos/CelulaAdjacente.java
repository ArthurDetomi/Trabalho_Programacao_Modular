package edu.ufsj.trabalho.terrenos;

import edu.ufsj.trabalho.robos.Direcoes;

public class CelulaAdjacente {

    private Direcoes direcao;

    private Double rugosidade;

    private boolean temRobo;

    private boolean vazia;

    private Celula celulaLeitura;

    public CelulaAdjacente(Direcoes direcaoAtual, Celula celulaLeitura) {
        this.direcao = direcaoAtual;
        this.temRobo = celulaLeitura.isTemRobo();
        this.rugosidade = celulaLeitura.getRugosidadeTerreno();
        this.celulaLeitura = celulaLeitura;
    }

    public boolean isVazia() {
        return direcao == null || celulaLeitura.isVazia();
    }

    public void setVazia(boolean vazia) {
        this.vazia = vazia;
    }

    public Direcoes getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcoes direcao) {
        this.direcao = direcao;
    }

    public Double getRugosidade() {
        return rugosidade;
    }

    public void setRugosidade(double rugosidade) {
        this.rugosidade = rugosidade;
    }

    public boolean isTemRobo() {
        return temRobo;
    }

    public void setTemRobo(boolean temRobo) {
        this.temRobo = temRobo;
    }
}
