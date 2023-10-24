package edu.ufsj.trabalho.leitor;

public class CompanhiaLeitura {
    private String nome;
    private Integer quantidadeRobos;
    private String controlador;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeRobos() {
        return quantidadeRobos;
    }

    public void setQuantidadeRobos(Integer quantidadeRobos) {
        this.quantidadeRobos = quantidadeRobos;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }
}
