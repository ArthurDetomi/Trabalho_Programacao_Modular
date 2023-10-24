package edu.ufsj.trabalho.api.arquivo.leitura;

import edu.ufsj.trabalho.api.companhias.Companhia;

import java.util.List;

public class EstadoJogo {

    private Integer duracaoPartida;
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

    public Integer getDuracaoPartida() {
        return duracaoPartida;
    }

    public void setDuracaoPartida(Integer duracaoPartida) {
        this.duracaoPartida = duracaoPartida;
    }

    public void validarParametrosArquivo() {
        for (CompanhiaLeitura companhiaAtual : companhias) {
            if (companhiaAtual.getNome() == null || companhiaAtual.getNome().isEmpty()) {
                throw new ExceptionInInitializerError("Dados de arquivo inválido");
            }
        }
        if (duracaoPartida == null || duracaoPartida <= 0) {
            throw new ExceptionInInitializerError("Dados de arquivo inválido");
        }
        if (terreno.getQuantidadeLinhas() == null || terreno.getQuantidadeColunas() == 0 ||   terreno.getQuantidadeColunas() <= 0 || terreno.getQuantidadeLinhas() <= 0) {
            throw new ExceptionInInitializerError("Dados de arquivo inválido");
        }
    }
}