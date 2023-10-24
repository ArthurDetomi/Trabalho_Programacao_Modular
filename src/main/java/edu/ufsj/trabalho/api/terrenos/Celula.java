package edu.ufsj.trabalho.api.terrenos;

import edu.ufsj.trabalho.api.utils.RandomUtil;

import java.util.Random;

public class Celula {

    private Posicao posicao;
    private double concentracaoHelio;
    private double rugosidadeTerreno;
    private double coeficienteAleatorio;
    private boolean temRobo;

    public Celula() {

    }

    public Celula(Posicao posicao) {
        this.posicao = posicao;
        this.concentracaoHelio = RandomUtil.gerarDoubleAleatorioIntervalo(0d, 1d);
        this.rugosidadeTerreno = RandomUtil.gerarDoubleAleatorioIntervalo(0d, 1d);
        this.coeficienteAleatorio = RandomUtil.gerarDoubleAleatorioIntervalo(0d, 0.1d);
        temRobo = false;
    }

    public boolean isVazia() {
        return posicao == null;
    }

    @Override
    public String toString() {
        return String.format("(Pos(%d, %d), valores(ch = %f, rt = %f, coe = %f,",
                getPosicao().getLinha(),
                getPosicao().getColuna(),
                concentracaoHelio,
                rugosidadeTerreno,
                coeficienteAleatorio
        ) + "temRobo = " + temRobo + " )";
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public double getConcentracaoHelio() {
        return concentracaoHelio;
    }

    public void setConcentracaoHelio(double concentracaoHelio) {
        this.concentracaoHelio = concentracaoHelio;
    }

    public double getRugosidadeTerreno() {
        return rugosidadeTerreno;
    }

    public void setRugosidadeTerreno(int rugosidadeTerreno) {
        this.rugosidadeTerreno = rugosidadeTerreno;
    }

    public double getCoeficienteAleatorio() {
        return coeficienteAleatorio;
    }

    public void setCoeficienteAleatorio(double coeficienteAleatorio) {
        this.coeficienteAleatorio = coeficienteAleatorio;
    }

    public boolean isTemRobo() {
        return temRobo;
    }

    public void setTemRobo(boolean temRobo) {
        this.temRobo = temRobo;
    }
}
