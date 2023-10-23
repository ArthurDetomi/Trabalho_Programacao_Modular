package edu.ufsj.trabalho.controladores;

import edu.ufsj.trabalho.robos.Movimentacao;
import edu.ufsj.trabalho.robos.Robo;
import edu.ufsj.trabalho.terrenos.CelulaAdjacente;
import edu.ufsj.trabalho.terrenos.Terreno;

public class ControladorSegundo extends Controlador{
    private Robo robo;
    private double concentracaoHelio;
    private boolean resultadoColeta;
    private CelulaAdjacente proximaCelula;
    private boolean resultado;

    public ControladorSegundo(){

    }

    public ControladorSegundo(Robo robo) {
        this.robo = robo;
        robo.setControlador(this);
    }

    @Override
    public void iniciarEstrategia(Terreno terreno) {

        concentracaoHelio = getConcentracaoHelioPosicaoAtualRobo();

        if (concentracaoHelio > 0.7d) {

            resultadoColeta = realizarSonda();

            if (!resultadoColeta) {

                if(robo.getTempoDecorridoSegundos() == robo.tempoTotalColeta()){

                    System.out.println("[Prospecção iniciada Robo: " + robo.getId() + "]\n\n[Tempo de coleta: " + (robo.getTempoDecorridoSegundos() + 1)+ " segundos]\n");
                }
            } else {
                System.out.println("[Helio coletado]\n");
                robo.imprimirDadosRobo();
            }

        } else {
            proximaCelula = getRugosidadeRegiao(terreno);

            if (proximaCelula.isVazia() || proximaCelula.isTemRobo()) {

                robo.movimentar(Movimentacao.ESQUERDA, terreno);

            } else if (proximaCelula.getRugosidade() < 0.5d) {

                resultado = movimentarRobo(Movimentacao.ANDA, terreno);

                if (!resultado) {
                    if(robo.getTempoDecorridoSegundos() == robo.tempoTotalMovimento(terreno)){

                        System.out.println("[Direção Atual Robo: " + robo.getId() + ":"+ robo.getDirecaoAtual() + "]\n\n[tempo do comando de movimento: " + (robo.getTempoDecorridoSegundos() + 1) + " segundos]\n");
                    }
                } else {

                    robo.imprimirDadosRobo();
                }
            }
        }
    }


}
