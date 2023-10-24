package edu.ufsj.trabalho.api.robos;

public enum Direcoes {

    ESQUERDA("Esquerda"), DIREITA("Direita"), CIMA("Cima"), BAIXO("Baixo");

    private String descricao;

    Direcoes(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
