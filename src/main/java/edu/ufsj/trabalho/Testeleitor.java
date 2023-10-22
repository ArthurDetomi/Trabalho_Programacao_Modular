package edu.ufsj.trabalho;

import edu.ufsj.trabalho.companhias.Companhia;
import edu.ufsj.trabalho.controladores.Controlador;
import edu.ufsj.trabalho.leitor.LeitorJson;
import edu.ufsj.trabalho.robos.Direcoes;
import edu.ufsj.trabalho.robos.Robo;
import edu.ufsj.trabalho.terrenos.Posicao;
import edu.ufsj.trabalho.terrenos.Terreno;

public class Testeleitor {
    public static void main (String[] args){
        LeitorJson leitor = new LeitorJson("src/main/java/edu/ufsj/trabalho/JsonTeste.Json");
        Terreno terreno = leitor.getTerreno();
        terreno.inicializarTerreno();
        Companhia equipe = leitor.getCompanhias();

        Controlador controlador = new Controlador();
        Robo robo = new Robo(controlador, terreno.getCelulaPosicao(new Posicao(0,0)), Direcoes.DIREITA);

        terreno.imprimirDashboard();
        System.out.print(equipe) ;

    }
}
