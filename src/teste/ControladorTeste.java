package teste;

import Controladores.Controlador;
import Robos.Direcoes;
import Robos.Movimentacao;
import Robos.Robo;
import Terrenos.Celula;
import Terrenos.Posicao;
import Terrenos.Terreno;

public class ControladorTeste {

    public static void main(String[] args) {
        Terreno terreno = new Terreno(3, 3);
        Celula celula = terreno.getCelulaPosicao(new Posicao(0, 0));
        Controlador controlador = new Controlador();
        Robo robo = new Robo(controlador, celula, Direcoes.DIREITA);
        controlador.setRobo(robo);

        // Crie threads para realizar as ações
        Thread threadColeta = new Thread(() -> {
            boolean coletaBemSucedida = controlador.realizarSonda();
            System.out.println("Coleta bem-sucedida? " + coletaBemSucedida);
        });

        Thread threadMovimento = new Thread(() -> {
            boolean movimentoBemSucedido = controlador.movimentarRobo(Movimentacao.ANDA, terreno);
            System.out.println("Movimento bem-sucedido? " + movimentoBemSucedido);
        });

        // Inicie as threads
        threadColeta.start();
        threadMovimento.start();
    }


}
