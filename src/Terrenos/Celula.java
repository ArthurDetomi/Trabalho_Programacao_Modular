package Terrenos;

import java.util.Random;

public class Celula {

    private Posicao posicao;
    private double concentracao_helio;
    private double rugosidade_terreno;
    private double coeficiente_aleatorio;

    public Celula(Posicao posicao) {
        this.posicao = posicao;
        this.concentracao_helio = gerarDoubleAleatorioIntervalo(0d, 1d);
        this.rugosidade_terreno = gerarDoubleAleatorioIntervalo(0d, 1d);
        this.coeficiente_aleatorio = gerarDoubleAleatorioIntervalo(0d, 0.1d);
    }

    @Override
    public String toString() {
        return String.format("(Pos(%d, %d), valores(ch = %f, rt = %f, coe = %f))",
                getPosicao().getX(),
                getPosicao().getY(),
                concentracao_helio,
                rugosidade_terreno,
                coeficiente_aleatorio
        );
    }

    private double gerarDoubleAleatorioIntervalo(double minimo, double maximo) {
        Random random = new Random();
        return minimo + (maximo - minimo + Double.MIN_VALUE)  * random.nextDouble();
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public double getConcentracao_helio() {
        return concentracao_helio;
    }

    public void setConcentracao_helio(double concentracao_helio) {
        this.concentracao_helio = concentracao_helio;
    }

    public double getRugosidade_terreno() {
        return rugosidade_terreno;
    }

    public void setRugosidade_terreno(int rugosidade_terreno) {
        this.rugosidade_terreno = rugosidade_terreno;
    }

    public double getCoeficiente_aleatorio() {
        return coeficiente_aleatorio;
    }

    public void setCoeficiente_aleatorio(double coeficiente_aleatorio) {
        this.coeficiente_aleatorio = coeficiente_aleatorio;
    }
}
