package terrenos;

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
        this.concentracaoHelio = gerarDoubleAleatorioIntervalo(0d, 1d);
        this.rugosidadeTerreno = gerarDoubleAleatorioIntervalo(0d, 1d);
        this.coeficienteAleatorio = gerarDoubleAleatorioIntervalo(0d, 0.1d);
        temRobo = false;
    }

    @Override
    public String toString() {
        return String.format("(Pos(%d, %d), valores(ch = %f, rt = %f, coe = %f,",
                getPosicao().getColuna(),
                getPosicao().getLinha(),
                concentracaoHelio,
                rugosidadeTerreno,
                coeficienteAleatorio
        ) +"temRobo = "+ temRobo + " )";
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
