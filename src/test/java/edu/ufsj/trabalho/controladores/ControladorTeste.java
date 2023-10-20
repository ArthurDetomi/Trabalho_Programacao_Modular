package edu.ufsj.trabalho.controladores;

import edu.ufsj.trabalho.robos.Direcoes;
import edu.ufsj.trabalho.robos.Robo;
import edu.ufsj.trabalho.terrenos.Celula;
import edu.ufsj.trabalho.terrenos.Posicao;
import edu.ufsj.trabalho.terrenos.Terreno;

public class ControladorTeste {

    public static void main(String[] args) {
        Terreno terreno = new Terreno(3, 3);
        Celula celula = terreno.getCelulaPosicao(new Posicao(0, 0));
        Controlador controlador = new Controlador();
        Robo robo = new Robo(controlador, celula, Direcoes.DIREITA);

        controlador.setRobo(robo);
    }


}
