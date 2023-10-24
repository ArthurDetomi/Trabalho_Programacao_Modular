package edu.ufsj.trabalho.api.controladores;

import edu.ufsj.trabalho.api.robos.Direcoes;
import edu.ufsj.trabalho.api.terrenos.CelulaAdjacente;
import edu.ufsj.trabalho.api.terrenos.Terreno;
import edu.ufsj.trabalho.api.robos.Movimentacao;

public class ControladorTerceiro extends Controlador {

    protected Direcoes direcaoInicial = Direcoes.BAIXO;

    @Override
    public void iniciarEstrategia(Terreno terreno) {
        sinalizarRoboTempoPassado();
        double concentracao = getConcentracaoHelioPosicaoAtualRobo();
        if (concentracao > 0.4d) {
            boolean resultadoColeta = realizarSonda();
            if (!resultadoColeta) {
                if(robo.getTempoDecorridoSegundos() == robo.tempoTotalColeta()){
                    System.out.println("[Prospecção iniciada Robo: " + robo.getId() + "]\n\n" +
                            "[Tempo de coleta: " + (robo.getTempoDecorridoSegundos() + 1)+
                            " segundos]\n");
                }
            } else {
                System.out.println("[Helio coletado]\n");
                robo.imprimirDadosRobo();
            }
        } else {
            CelulaAdjacente proximaCelula = getRugosidadeRegiao(terreno);
            if (proximaCelula.isVazia() || proximaCelula.isTemRobo()) {

                robo.movimentar(Movimentacao.ESQUERDA, terreno);

            } else if (proximaCelula.getRugosidade() < 0.6d) {

                boolean resultado = movimentarRobo(Movimentacao.ANDA, terreno);
                if (!resultado) {
                    if(robo.getTempoDecorridoSegundos() == robo.tempoTotalMovimento(terreno)){
                        System.out.println("[Direção Atual Robo: " + robo.getId() + ":"+ robo.getDirecaoAtual() + "]\n" +
                                "\n[tempo do comando de movimento: " + (robo.getTempoDecorridoSegundos() + 1) + " segundos]\n");
                    }
                } else {
                    robo.imprimirDadosRobo();
                }
            }
        }
    }

}
