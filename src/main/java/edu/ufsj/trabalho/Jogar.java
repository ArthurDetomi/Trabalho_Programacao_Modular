package edu.ufsj.trabalho;

import edu.ufsj.trabalho.controladores.Controlador;
import edu.ufsj.trabalho.robos.Direcoes;
import edu.ufsj.trabalho.robos.Robo;
import edu.ufsj.trabalho.terrenos.Celula;
import edu.ufsj.trabalho.terrenos.Posicao;
import edu.ufsj.trabalho.terrenos.Terreno;

public class Jogar {

    public static void main(String[] args) {
        // Primeiro ler o arquivo
        // Segundo inicializar o terreno e os robo
        Controlador controlador = new Controlador();

        Controlador controlador2 = new Controlador();

        Terreno terreno = new Terreno(4, 4);

        Celula celulaPouso = terreno.getCelulaPosicao(new Posicao(0, 0));

        Controlador controlador = new Controlador();
        Robo robo = new Robo(controlador, terreno.getCelulaPosicao(new Posicao(0,0)), Direcoes.DIREITA);

        Robo robo2 = new Robo(controlador2, terreno.getCelulaPosicao(new Posicao(1, 0)), Direcoes.ESQUERDA);
        controlador2.setRobo(robo2);
        // Iniciar loop do jogo
        int duracaoTotal = 180;

        terreno.imprimirDashboard();

        while(duracaoTotal > 0) {
            controlador.iniciarEstrategia(terreno);
            robo.sinalizarTempoPassado();

            controlador2.iniciarEstrategia(terreno);
            robo2.sinalizarTempoPassado();

            duracaoTotal--;
        }
    }

}
