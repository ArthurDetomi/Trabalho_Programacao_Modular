package edu.ufsj.trabalho.controladores;

import edu.ufsj.trabalho.robos.Direcoes;
import edu.ufsj.trabalho.robos.Movimentacao;
import edu.ufsj.trabalho.robos.Robo;
import edu.ufsj.trabalho.terrenos.CelulaAdjacente;
import edu.ufsj.trabalho.terrenos.Posicao;
import edu.ufsj.trabalho.terrenos.Terreno;

public class Controlador {

    protected Robo robo;

    protected final Direcoes direcaoInicial = Direcoes.DIREITA;

    public Controlador() {

    }

    public Direcoes getDirecaoInicial() {
        return direcaoInicial;
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

    public double getQuantidadeTotalHelioColetado() {
        return this.robo.getQuantidadeColetadaHelio();
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

    protected void sinalizarRoboTempoPassado() {
        robo.sinalizarTempoPassado();
    }

    public void iniciarEstrategia(Terreno terreno) {
        sinalizarRoboTempoPassado();
        double concentracao = getConcentracaoHelioPosicaoAtualRobo();
        if (concentracao > 0.2d) {
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

                robo.movimentar(Movimentacao.DIREITA, terreno);

            } else if (proximaCelula.getRugosidade() < 0.9d) {

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