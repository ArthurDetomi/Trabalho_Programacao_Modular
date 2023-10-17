package Robos;

import Controladores.Controlador;
import Terrenos.Celula;
import Terrenos.Posicao;
import Terrenos.Terreno;

import java.util.*;

public class Robo {

    private Celula celulaAtual;
    private Controlador controlador;
    private Direcoes direcao_atual;
    private double quantidade_coletada_helio = 0;
    private Date horaInicioColeta;
    private final long TEMPO_TOTAL;

    public Robo(Controlador controlador, long tempoTotal) {
        this.controlador = controlador;
        controlador.setRobo(this);
        TEMPO_TOTAL = tempoTotal;
    }

    public Posicao getPosicaoAtual() {
        return celulaAtual.getPosicao();
    }

    public double getQuantidade_coletada_helio() {
        return quantidade_coletada_helio;
    }

    public double getRugosidadePosicaoAtual() {
        return celulaAtual.getRugosidade_terreno();
    }

    public Map<Direcoes, Double> getRugosidadeRegiao(Terreno terrenoLeitura) {
        Map<Direcoes, Double> mapa = new HashMap<>();

        Posicao posicaoApontada = atualizarPosicaoComDirecaoAtual(getPosicaoAtual());

        Celula celulaLeitura = terrenoLeitura.getCelulaPosicao(posicaoApontada);

        mapa.put(direcao_atual, celulaLeitura.getRugosidade_terreno());

        return mapa;
    }

    private Posicao atualizarPosicaoComDirecaoAtual(Posicao posicao) {
        int coluna = posicao.getX();
        int linha = posicao.getY();

        switch (direcao_atual) {
            case DIREITA:
                coluna++;
                break;
            case ESQUERDA:
                coluna--;
                break;
            case BAIXO:
                linha++;
                break;
            case CIMA:
                linha--;
                break;
        }

        return new Posicao(coluna, linha);
    }

    public double getConcentracaoHelioPosicaoAtual() {
        return celulaAtual.getConcentracao_helio() + celulaAtual.getCoeficiente_aleatorio();
    }

    public boolean coletarHelio(Celula celula) {
        horaInicioColeta = new Date();

        long atraso = getTempoDuracaoColeta();

        Date horaColeta = new Date(horaInicioColeta.getTime() + atraso);

        while (new Date().before(horaColeta)) {
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.quantidade_coletada_helio += celula.getConcentracao_helio();
        return true;
    }

    public boolean movimentar(Movimentacao movimento, Terreno terreno) {
        switch (movimento) {
            case ANDA:
                long atraso = getTempoDuracaoMovimento();

                Date horaColeta = new Date(horaInicioColeta.getTime() + atraso);

                while (new Date().before(horaColeta)) {
                    try {
                        Thread.sleep(1000); // Espera 1 segundo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.celulaAtual = terreno.getCelulaPosicao(atualizarPosicaoComDirecaoAtual(getPosicaoAtual()));
            case DIREITA:

            case ESQUERDA:
        }
        return true;
    }

    private long getTempoDuracaoColeta() {
        return (long) ((getConcentracaoHelioPosicaoAtual() * TEMPO_TOTAL) / 0.1);
    }

    private long getTempoDuracaoMovimento() {
        return (long) ((getRugosidadePosicaoAtual() * TEMPO_TOTAL) / 0.1);
    }

    public long getTempoDecorridoMillis() {
        if (horaInicioColeta != null) {
            long horaAtual = new Date().getTime();
            return horaAtual - horaInicioColeta.getTime();
        } else {
            return 0L;
        }
    }


    public Direcoes getDirecao_atual() {
        return direcao_atual;
    }

    public void setControlador(Controlador controlador) {
        if (this.controlador != null) {
            return;
        }
        this.controlador = controlador;
    }

    public void setDirecao_atual(Direcoes direcao_atual) {
        this.direcao_atual = direcao_atual;
    }

    public void setCelulaAtual(Celula celulaAtual) {
        this.celulaAtual = celulaAtual;
    }

}
