package Robos;

import Terrenos.Celula;

public class Robo {

    private Celula celulaAtual;
    private double quantidade_coletada_helio = 0;

    public Robo() {

    }

    public Celula getCelulaAtual() {
        return celulaAtual;
    }

    public void setCelulaAtual(Celula celulaAtual) {
        this.celulaAtual = celulaAtual;
    }

    public double getQuantidade_coletada_helio() {
        return quantidade_coletada_helio;
    }

    public void setQuantidade_coletada_helio(double quantidade_coletada_helio) {
        this.quantidade_coletada_helio = quantidade_coletada_helio;
    }

    public double getRugosidadeTerreno(Celula celula) {
        return celula.getRugosidade_terreno();
    }

    public void coletarHelio(Celula celula) {
        this.quantidade_coletada_helio += celula.getConcentracao_helio();
    }

}
