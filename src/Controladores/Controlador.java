package Controladores;

import Robos.Direcoes;
import Robos.Movimentacao;
import Robos.Robo;
import Terrenos.Posicao;
import Terrenos.Terreno;

import java.util.Map;

public class Controlador {

    private Robo robo;

    private int tempoEspera;

    public Controlador() {

    }

    public Robo getRobo() {
        return robo;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public Controlador(Robo robo) {
        this.robo = robo;
        robo.setControlador(this);
    }

    public void setRobo(Robo robo) {
        this.robo = robo;
    }

    public Posicao getPosicaoAtualRobo() {
        return robo.getPosicaoAtual();
    }

    public double getConcentracaoHelioPosicaoAtualRobo() {
        return robo.getConcentracaoHelioPosicaoAtual();
    }

    public Map<Direcoes, Double> getRugosidadeRegiao(Terreno terreno) {
        return robo.getRugosidadeRegiao(terreno);
    }

    public long getTempoDecorridoProspeccao() {
        return robo.getTempoDecorridoMillis();
    }

    public boolean movimentarRobo(Movimentacao movimento, Terreno terreno) {
        return robo.movimentar(movimento, terreno);
    }

}