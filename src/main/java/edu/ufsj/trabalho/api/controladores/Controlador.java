package edu.ufsj.trabalho.api.controladores;

import edu.ufsj.trabalho.api.arquivo.escrita.GravadorDeArquivo;
import edu.ufsj.trabalho.api.robos.Direcoes;
import edu.ufsj.trabalho.api.robos.Robo;
import edu.ufsj.trabalho.api.terrenos.CelulaAdjacente;
import edu.ufsj.trabalho.api.terrenos.Posicao;
import edu.ufsj.trabalho.api.terrenos.Terreno;
import edu.ufsj.trabalho.api.robos.Movimentacao;

public class Controlador {

    protected Robo robo;

    protected Direcoes direcaoInicial = Direcoes.DIREITA;

    protected String companhiaNome;

    protected double rugosidadeMaximaParaMovimentar = 0.9d;

    protected double concentracaoMinimaParaColetar = 0.1d;

    protected Movimentacao movimentacaoCasoSejaBloqueado = Movimentacao.ESQUERDA;

    public Controlador(String companhiaNome) {
        this.companhiaNome = companhiaNome;
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

    public void iniciarEstrategia(Terreno terreno, GravadorDeArquivo gravadorDeArquivo) {
        sinalizarRoboTempoPassado();
        double concentracao = getConcentracaoHelioPosicaoAtualRobo();
        if (concentracao > concentracaoMinimaParaColetar) {
            boolean resultadoColeta = realizarSonda();
            if (!resultadoColeta) {
                gravadorDeArquivo.adicionarRegistro("[Prospecção iniciada Robo]\n" +
                        "[Tempo de coleta: " + (getTempoDecorridoProspeccao()) +
                        " segundos]");
            } else {
                gravadorDeArquivo.adicionarRegistro("[Helio coletado]");
                gravadorDeArquivo.adicionarRegistro(robo.imprimirDadosRobo());
            }
            return;
        }
        CelulaAdjacente proximaCelula = getRugosidadeRegiao(terreno);

        if (proximaCelula.isVazia() || proximaCelula.isTemRobo()) {
            robo.movimentar(movimentacaoCasoSejaBloqueado, terreno);
            gravadorDeArquivo.adicionarRegistro(robo.imprimirDadosRobo() +
                    " esta bloqueado então movimentou para " + movimentacaoCasoSejaBloqueado.getDescricao()
                    + " apontando para " + getRobo().getDirecaoAtual().getDescricao());
            return;
        }

        if (proximaCelula.getRugosidade() < rugosidadeMaximaParaMovimentar) {
            boolean resultado = movimentarRobo(Movimentacao.ANDA, terreno);
            if (!resultado) {
                gravadorDeArquivo.adicionarRegistro("[Direção Atual Robo " + "direcao atual = "
                        + robo.getDirecaoAtual() + "]\n" +
                        "\n[tempo do comando de movimento: " +
                        (getTempoDecorridoProspeccao()) + " segundos]");

            } else {
                gravadorDeArquivo.adicionarRegistro(robo.imprimirDadosRobo());
            }
            return;
        }
        robo.movimentar(movimentacaoCasoSejaBloqueado, terreno);
        gravadorDeArquivo.adicionarRegistro(robo.imprimirDadosRobo() +
                " celula a frente com rugosidade muito alta movimentando para "
                + movimentacaoCasoSejaBloqueado.getDescricao()
                + " apontando para " + getRobo().getDirecaoAtual().getDescricao());
    }

    public String getCompanhiaNome() {
        return companhiaNome;
    }

    public void setCompanhiaNome(String companhiaNome) {
        this.companhiaNome = companhiaNome;
    }
}