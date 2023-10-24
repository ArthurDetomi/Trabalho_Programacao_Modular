package edu.ufsj.trabalho.api.general;

import edu.ufsj.trabalho.api.companhias.Companhia;
import edu.ufsj.trabalho.api.companhias.CompanhiaComparator;

import java.util.List;

public class JogoControlador {
    private Integer duracaoJogoSegundos;
    private List<Companhia> companhiasParticipantes;

    public JogoControlador(Integer duracaoJogoMinutos, List<Companhia> companhiasParticipantes) {
        this.duracaoJogoSegundos = duracaoJogoMinutos * 60;
        this.companhiasParticipantes = companhiasParticipantes;
    }

    public void iniciarJogo() {
        if (companhiasParticipantes == null  || companhiasParticipantes.isEmpty()) {
            throw new IllegalArgumentException("Sem companhias participantes");
        }

        int duracaoTotal = duracaoJogoSegundos;

        for (int segundoAtual = 1; segundoAtual <= duracaoTotal; segundoAtual++){
            int segundoAtualJogo = segundoAtual;
            companhiasParticipantes.forEach(companhia -> companhia.jogar(segundoAtualJogo));
        }

        companhiasParticipantes.forEach(Companhia::fecharGravador);

        Companhia companhiaGanhadora = getCompanhiaGanhadora();
        System.out.println("Placar:");
        companhiasParticipantes.forEach(System.out::println);
        System.out.println("Companhia ganhadora " + companhiaGanhadora.getNome() +
                " parabéns!!");

    }

    private Companhia getCompanhiaGanhadora() {
        return companhiasParticipantes.stream().max(CompanhiaComparator.porTotalHelioProspectado).get();
    }

}
