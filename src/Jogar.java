import controladores.Controlador;
import robos.Direcoes;
import robos.Robo;
import terrenos.Celula;
import terrenos.Posicao;
import terrenos.Terreno;

public class Jogar {

    public static void main(String[] args) {
        // Primeiro ler o arquivo
        // Segundo inicializar o terreno e os robo
        Controlador controlador = new Controlador();

        Terreno terreno = new Terreno(4, 4);

        Celula celulaPouso = terreno.getCelulaPosicao(new Posicao(0, 0));

        Robo robo = new Robo(controlador, celulaPouso, Direcoes.BAIXO);
        // Iniciar loop do jogo
        int duracaoTotal = 180;




        while(duracaoTotal > 0) {
            controlador.iniciarEstrategia(terreno);
            robo.sinalizarTempoPassado();

            duracaoTotal--;
        }
    }

}
