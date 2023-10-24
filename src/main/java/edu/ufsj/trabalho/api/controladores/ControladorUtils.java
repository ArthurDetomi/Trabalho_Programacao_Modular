package edu.ufsj.trabalho.api.controladores;

public class ControladorUtils {

    public static Controlador getControladorNome(String nome) {
        switch (nome) {
            case "controlador1":
                return new Controlador();
            case "controlador2":
                return new ControladorSegundo();
            case "controlador3":
                return new ControladorTerceiro();
        }
        throw new IllegalArgumentException("Controlador enviado inválido");
    }

}
