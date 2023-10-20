package edu.ufsj.trabalho.terrenos;

import edu.ufsj.trabalho.robos.Direcoes;

public class CelulaAdjacente {

    Direcoes direcao;

    Double rugosidade;

    boolean temRobo;

    public CelulaAdjacente(Direcoes direcaoAtual, Celula celulaLeitura) {
        this.direcao = direcaoAtual;
        this.temRobo = celulaLeitura.isTemRobo();
        this.rugosidade = celulaLeitura.getRugosidadeTerreno();
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
