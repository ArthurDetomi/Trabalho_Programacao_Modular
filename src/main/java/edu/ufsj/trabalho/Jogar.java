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

        Terreno terreno = new Terreno(4, 4);


        Controlador controlador = new Controlador();
        Robo robo = new Robo(controlador, terreno.getCelulaPosicao(new Posicao(0,0)), Direcoes.DIREITA);


        // Iniciar loop do jogo
        int duracaoTotal = 180;

        terreno.imprimirDashboard();

        while(duracaoTotal > 0) {
            controlador.iniciarEstrategia(terreno);
            robo.sinalizarTempoPassado();

            duracaoTotal--;
        }
        */
        Terreno terreno = new Terreno(4, 4);
        Controlador controlador1 = new Controlador();
        Companhia companhia1 = new Companhia("Alfa", controlador1,
                2, terreno);

        Controlador controlador2 = new Controlador();
        Companhia companhia2 = new Companhia("Alfa", controlador2,
                2, terreno);

        int duracaoTotal = 180;


        System.out.println(companhia1.getTotalHelioProspectado());
        System.out.println(companhia2.getTotalHelioProspectado());
        System.out.println("Equipe Vencedora:");

    }

}
