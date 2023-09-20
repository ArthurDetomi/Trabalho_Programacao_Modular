package Terrenos;

import utils.Posicao;

public class Celula {

    private Posicao posicao;
    private int concentracao_helio = 0;
    private int rugosidade_terreno;
    private int coeficiente_aleatorio;

    public Celula(Posicao posicao, int concentracao_helio,
                  int rugosidade_terreno, int coeficiente_aleatorio) {
        this.posicao = posicao;
        this.concentracao_helio = concentracao_helio;
        this.rugosidade_terreno = rugosidade_terreno;
        this.coeficiente_aleatorio = coeficiente_aleatorio;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public int getConcentracao_helio() {
        return concentracao_helio;
    }

    public void setConcentracao_helio(int concentracao_helio) {
        this.concentracao_helio = concentracao_helio;
    }

    public int getRugosidade_terreno() {
        return rugosidade_terreno;
    }

    public void setRugosidade_terreno(int rugosidade_terreno) {
        this.rugosidade_terreno = rugosidade_terreno;
    }

    public int getCoeficiente_aleatorio() {
        return coeficiente_aleatorio;
    }

    public void setCoeficiente_aleatorio(int coeficiente_aleatorio) {
        this.coeficiente_aleatorio = coeficiente_aleatorio;
    }
}
