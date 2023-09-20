package Robos;

import Terrenos.Celula;
import utils.Posicao;

public class Robo {

    private Posicao posicao_atual;

    private int quantidade_coletada_helio = 0;

    public Robo() {

    }

    public Robo(Posicao posicao) {
        this.posicao_atual = posicao;
    }

    public int getRugosidadeTerreno(Celula celula) {
        return celula.getRugosidade_terreno();
    }

    public void coletarHelio(Celula celula) {
        this.quantidade_coletada_helio += celula.getConcentracao_helio();
    }

    public Posicao getPosicao_atual() {
        return posicao_atual;
    }

}
