package edu.ufsj.trabalho.api.controladores;

public class ControladorUtils {

    public static Controlador getControladorNome(String nome, String companhiaNome) {
        switch (nome) {
            case "controlador1":
                return new Controlador(companhiaNome);
            case "controlador2":
                return new ControladorSegundo(companhiaNome);
            case "controlador3":
                return new ControladorTerceiro(companhiaNome);
            case "controlador4":
                return new ControladorQuarto(companhiaNome);
        }
        throw new IllegalArgumentException("Controlador enviado inválido");
    }

}
