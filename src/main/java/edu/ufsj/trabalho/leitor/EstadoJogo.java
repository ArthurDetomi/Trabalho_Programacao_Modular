package edu.ufsj.trabalho.leitor;

import edu.ufsj.trabalho.companhias.Companhia;

import edu.ufsj.trabalho.terrenos.Terreno;

import java.util.List;

public class EstadoJogo {
    private TerrenoLeitura terreno;
    private List<CompanhiaLeitura> companhias;

    public TerrenoLeitura getTerreno() {
        return terreno;
    }

    public void setTerreno(TerrenoLeitura terreno) {
        this.terreno = terreno;
    }

    public List<CompanhiaLeitura> getCompanhias() {
        return companhias;
    }

    public void setCompanhias(List<CompanhiaLeitura> companhias) {
        this.companhias = companhias;
    }
}