package edu.ufsj.trabalho.api.controladores;

import edu.ufsj.trabalho.api.robos.Direcoes;
import edu.ufsj.trabalho.api.robos.Robo;
import edu.ufsj.trabalho.api.terrenos.CelulaAdjacente;
import edu.ufsj.trabalho.api.terrenos.Terreno;
import edu.ufsj.trabalho.api.robos.Movimentacao;

public class ControladorTerceiro extends Controlador {

    protected Direcoes direcaoInicial = Direcoes.BAIXO;

    protected Movimentacao movimentacaoCasoSejaBloqueado = Movimentacao.DIREITA;

    public ControladorTerceiro(String companhiaNome) {
        super(companhiaNome);
        concentracaoMinimaParaColetar = 0.4d;
        rugosidadeMaximaParaMovimentar = 0.6d;
    }

    public ControladorTerceiro(Robo robo) {
        super(robo);
    }

}
