package companhias;

import controladores.Controlador;
import robos.Robo;

import java.util.ArrayList;
import java.util.List;

public class Companhia {

    private String nome;

    private final Controlador controladorEstrategia;

    public Companhia(String nome, Controlador controladorEstrategia) {
        this.nome = nome;
        this.controladorEstrategia = controladorEstrategia;
    }

    List<Robo> robos = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarRobo(Robo robo) {
        robos.add(robo);
    }

    public List<Robo> getRobos() {
        return robos;
    }

    public void setRobos(List<Robo> robos) {
        this.robos = robos;
    }

    public Controlador getControladorEstrategia() {
        return controladorEstrategia;
    }
}
