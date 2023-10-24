package edu.ufsj.trabalho.api.controladores;

import edu.ufsj.trabalho.api.robos.Direcoes;
import edu.ufsj.trabalho.api.robos.Robo;
import edu.ufsj.trabalho.api.terrenos.CelulaAdjacente;
import edu.ufsj.trabalho.api.terrenos.Terreno;
import edu.ufsj.trabalho.api.robos.Movimentacao;

public class ControladorSegundo extends Controlador {


    protected Direcoes direcaoInicial = Direcoes.CIMA;

    protected Movimentacao movimentacaoCasoSejaBloqueado = Movimentacao.ESQUERDA;

    public ControladorSegundo(String companhiaNome) {
        super(companhiaNome);
        concentracaoMinimaParaColetar = 0.3d;
        rugosidadeMaximaParaMovimentar = 0.5d;
    }

    public ControladorSegundo(Robo robo) {
        super(robo);
    }

}
