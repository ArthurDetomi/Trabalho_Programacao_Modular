package Controladores;

import Robos.Robo;

public class Controlador {

    private Robo robo;

    private int tempoEspera;

    public Controlador(Robo robo) {
        this.robo = robo;
        robo.setControlador(this);
    }

    public void setRobo(Robo robo) {
        this.robo = robo;
    }
}