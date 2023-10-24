package edu.ufsj.trabalho;

import edu.ufsj.trabalho.api.companhias.Companhia;
import edu.ufsj.trabalho.api.general.JogoControlador;
import edu.ufsj.trabalho.api.arquivo.leitura.EstadoJogo;
import edu.ufsj.trabalho.api.arquivo.leitura.LeitorJson;
import edu.ufsj.trabalho.api.terrenos.Terreno;

import java.util.List;
import java.util.stream.Collectors;

public class Jogar {
    private static final String CAMINHO_ARQUIVO_ENTRADA = "src/main/java/edu/ufsj/trabalho/input/input.json";

    public static void main(String[] args) {
        LeitorJson leitorJson = new LeitorJson(CAMINHO_ARQUIVO_ENTRADA);

        EstadoJogo estadoJogo = leitorJson.getEstadoDoJogo();

        try {
            estadoJogo.validarParametrosArquivo();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Terreno terreno = new Terreno(estadoJogo.getTerreno());

        List<Companhia> companhias = estadoJogo.getCompanhias().stream()
                .map(companhiaLeitura -> new Companhia(companhiaLeitura, terreno))
                .collect(Collectors.toList());

        JogoControlador jogoControlador = new JogoControlador(estadoJogo.getDuracaoPartida(), companhias);
        jogoControlador.iniciarJogo();
    }

}
