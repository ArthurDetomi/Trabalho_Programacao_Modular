package edu.ufsj.trabalho.api.robos;

public enum Movimentacao {
    ANDA("Anda"), ESQUERDA("Esquerda"), DIREITA("Direita"); // remover valor

    private String descricao;
    Movimentacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
