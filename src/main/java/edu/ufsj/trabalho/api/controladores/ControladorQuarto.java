package edu.ufsj.trabalho.api.controladores;

import edu.ufsj.trabalho.api.robos.Direcoes;
import edu.ufsj.trabalho.api.robos.Movimentacao;
import edu.ufsj.trabalho.api.robos.Robo;
import edu.ufsj.trabalho.api.terrenos.CelulaAdjacente;
import edu.ufsj.trabalho.api.terrenos.Terreno;

public class ControladorQuarto extends Controlador {


    protected Direcoes direcaoInicial = Direcoes.ESQUERDA;

    protected Movimentacao movimentacaoCasoSejaBloqueado = Movimentacao.ESQUERDA;

    public ControladorQuarto(String companhiaNome) {
        super(companhiaNome);
        concentracaoMinimaParaColetar = 0.45d;
        rugosidadeMaximaParaMovimentar = 0.7d;
    }

    public ControladorQuarto(Robo robo) {
        super(robo);
    }

}
