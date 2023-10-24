package edu.ufsj.trabalho.api.controladores;

public class ControladorUtils {

    public static Controlador getControladorNome(String nome, String companhiaNome) {
        switch (nome) {
            case "controladorPrimeiro":
                return new Controlador(companhiaNome);
            case "controladorSegundo":
                return new ControladorSegundo(companhiaNome);
            case "controladorTerceiro":
                return new ControladorTerceiro(companhiaNome);
            case "controladorQuarto":
                return new ControladorQuarto(companhiaNome);
        }
        throw new IllegalArgumentException("Controlador enviado inválido");
    }

}
