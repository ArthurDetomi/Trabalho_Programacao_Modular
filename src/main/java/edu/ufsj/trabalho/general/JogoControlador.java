package edu.ufsj.trabalho.general;

import edu.ufsj.trabalho.companhias.Companhia;

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
    }

}
