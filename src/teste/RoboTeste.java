package teste;

import Controladores.Controlador;
import Robos.Direcoes;
import Robos.Movimentacao;
import Robos.Robo;
import Terrenos.Celula;
import Terrenos.Posicao;
import Terrenos.Terreno;

public class RoboTeste {

    public static void main(String[] args) {
        Terreno terreno = new Terreno(3, 3);

        Celula celula = terreno.getCelulaPosicao(new Posicao(0, 0));

        Controlador controlador = new Controlador();

        Robo robo = new Robo(controlador, celula, Direcoes.DIREITA);

        controlador.setRobo(robo);

        System.out.println(robo.getConcentracaoHelioPosicaoAtual() + "\n" + robo);

        robo.coletarHelio();

        System.out.println(robo);

        robo.movimentar(Movimentacao.ANDA, terreno);

        robo.movimentar(Movimentacao.ESQUERDA, terreno);

        System.out.println(robo);

        robo.movimentar(Movimentacao.ANDA, terreno);

        System.out.println(robo);


    }

}
