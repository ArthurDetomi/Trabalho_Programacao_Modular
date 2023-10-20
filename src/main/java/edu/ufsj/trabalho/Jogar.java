import edu.ufsj.trabalho.controladores.Controlador;
import edu.ufsj.trabalho.robos.Robo;
import edu.ufsj.trabalho.terrenos.Celula;
import edu.ufsj.trabalho.terrenos.Terreno;
import edu.ufsj.trabalho.robos.Direcoes;
import edu.ufsj.trabalho.terrenos.Posicao;

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
