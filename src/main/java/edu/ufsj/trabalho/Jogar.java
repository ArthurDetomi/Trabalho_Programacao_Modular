package edu.ufsj.trabalho;

import edu.ufsj.trabalho.api.companhias.Companhia;
import edu.ufsj.trabalho.api.general.JogoControlador;
import edu.ufsj.trabalho.api.leitor.EstadoJogo;
import edu.ufsj.trabalho.api.leitor.LeitorJson;
import edu.ufsj.trabalho.api.terrenos.Terreno;

import java.util.List;
import java.util.stream.Collectors;

public class Jogar {

    public static void main(String[] args) {
        LeitorJson leitorJson =
                new LeitorJson("src/main/java/edu/ufsj/trabalho/input/input.json");

        EstadoJogo estadoJogo = leitorJson.getEstadoDoJogo();

        Terreno terreno = new Terreno(estadoJogo.getTerreno());

        List<Companhia> companhias = estadoJogo.getCompanhias().stream()
                .map(companhiaLeitura -> new Companhia(companhiaLeitura, terreno))
                .collect(Collectors.toList());

        JogoControlador jogoControlador = new JogoControlador(280, companhias);
        jogoControlador.iniciarJogo();
    }

}
