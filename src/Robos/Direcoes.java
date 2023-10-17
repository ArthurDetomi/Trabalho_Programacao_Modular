package Robos;

import Terrenos.Posicao;

public enum Direcoes {

    ESQUERDA(0), DIREITA(1), CIMA(2), BAIXO(3);

    private int valor;

    Direcoes(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
