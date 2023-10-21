package edu.ufsj.trabalho.controladores;

import edu.ufsj.trabalho.robos.Movimentacao;
import edu.ufsj.trabalho.robos.Robo;
import edu.ufsj.trabalho.terrenos.CelulaAdjacente;
import edu.ufsj.trabalho.terrenos.Posicao;
import edu.ufsj.trabalho.terrenos.Terreno;

public class Controlador {

    private Robo robo;

    public Controlador() {

    }

    public Robo getRobo() {
        return robo;
    }

    public Controlador(Robo robo) {
        this.robo = robo;
        robo.setControlador(this);
    }

    public void setRobo(Robo robo) {
        this.robo = robo;
    }

    public synchronized boolean realizarSonda() {
        return robo.coletarHelio();
    }

    public Posicao getPosicaoAtualRobo() {
        return robo.getPosicaoAtual();
    }

    public double getConcentracaoHelioPosicaoAtualRobo() {
        return robo.getConcentracaoHelioPosicaoAtual();
    }

    public CelulaAdjacente getRugosidadeRegiao(Terreno terreno) {
        return robo.getRugosidadeRegiao(terreno);
    }

    public long getTempoDecorridoProspeccao() {
        return robo.getTempoDecorridoSegundos();
    }

    public boolean movimentarRobo(Movimentacao movimento, Terreno terreno) {
        return robo.movimentar(movimento, terreno);
    }

    public void iniciarEstrategia(Terreno terreno) {
        double concentracao = getConcentracaoHelioPosicaoAtualRobo();
        if (concentracao > 0.2d) {
            boolean resultadoColeta = realizarSonda();
            if (!resultadoColeta) {
                System.out.println("tempo gasto desde o inicio coleta = " + robo.getTempoDecorridoSegundos());
            } else {
                System.out.println("Helio foi coletado");
                System.out.println(robo);
            }
        } else {
            CelulaAdjacente proximaCelula = getRugosidadeRegiao(terreno);
            if (proximaCelula.isVazia() || proximaCelula.isTemRobo()) {
                robo.movimentar(Movimentacao.DIREITA, terreno);

                System.out.println("Robo andou para direita");
                System.out.println(robo);
            } else if (proximaCelula.getRugosidade() < 0.9) {
                boolean resultado = movimentarRobo(Movimentacao.ANDA, terreno);
                if (!resultado) {
                    System.out.println("tempo gasto desde o inicio comando movimento = " + robo.getTempoDecorridoSegundos());
                } else {
                    System.out.println(robo);
                }
            }
        }
    }

}