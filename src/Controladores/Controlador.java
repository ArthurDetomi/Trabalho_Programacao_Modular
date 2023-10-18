package Controladores;

import Robos.Robo;

public class Controlador {

    private Robo robo;

    private int tempoEspera;

    public Controlador() {

    }

    public Robo getRobo() {
        return robo;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public Controlador(Robo robo) {
        this.robo = robo;
        robo.setControlador(this);
    }

    public void setRobo(Robo robo) {
        this.robo = robo;
    }
}