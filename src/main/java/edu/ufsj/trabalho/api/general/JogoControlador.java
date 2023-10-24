package edu.ufsj.trabalho.api.general;

import edu.ufsj.trabalho.api.companhias.Companhia;
import edu.ufsj.trabalho.api.companhias.CompanhiaComparator;

import java.util.List;

public class JogoControlador {
    private Integer duracaoJogo;
    private List<Companhia> companhiasParticipantes;

    public JogoControlador(Integer duracaoJogo, List<Companhia> companhiasParticipantes) {
        this.duracaoJogo = duracaoJogo;
        this.companhiasParticipantes = companhiasParticipantes;
    }

    public void iniciarJogo() {
        if (companhiasParticipantes == null  || companhiasParticipantes.isEmpty()) {
            throw new IllegalArgumentException("Sem companhias participantes");
        }

        int duracaoTotal = duracaoJogo;

        while (duracaoTotal > 0) {
            companhiasParticipantes.forEach(Companhia::jogar);
            duracaoTotal--;
        }

        Companhia companhiaGanhadora = getCompanhiaGanhadora();
        System.out.println("Companhia ganhadora " + companhiaGanhadora.getNome() +
                " parabéns!!");
    }

    private Companhia getCompanhiaGanhadora() {
        return companhiasParticipantes.stream().max(CompanhiaComparator.porTotalHelioProspectado).get();
    }

}
