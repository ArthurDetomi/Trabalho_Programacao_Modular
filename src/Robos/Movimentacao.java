package Robos;

public enum Movimentacao {

    ANDA(0), ESQUERDA(1), DIREITA(2);

    private final int valor;

    Movimentacao(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

}
