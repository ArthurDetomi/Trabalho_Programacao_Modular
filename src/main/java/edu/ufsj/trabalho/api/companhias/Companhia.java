package edu.ufsj.trabalho.api.companhias;

import edu.ufsj.trabalho.api.arquivo.escrita.GravadorDeArquivo;
import edu.ufsj.trabalho.api.controladores.ControladorUtils;
import edu.ufsj.trabalho.api.robos.Robo;
import edu.ufsj.trabalho.api.terrenos.Celula;
import edu.ufsj.trabalho.api.terrenos.Posicao;
import edu.ufsj.trabalho.api.utils.RandomUtil;
import edu.ufsj.trabalho.api.controladores.Controlador;
import edu.ufsj.trabalho.api.arquivo.leitura.CompanhiaLeitura;
import edu.ufsj.trabalho.api.terrenos.Terreno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Companhia {

    private String nome;

    private final Controlador controladorEstrategia;
    private List<Robo> robos = new ArrayList<>();
    private final int quantidadeRobos;

    private final GravadorDeArquivo gravadorDeArquivo;

    private final Terreno terreno;

    public Companhia(String nome,
                     Controlador controladorEstrategia,
                     int quantidadeRobos,
                     Terreno terreno) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Companhia foi iniciada com nome incorreto");
        }
        this.nome = nome;
        this.controladorEstrategia = controladorEstrategia;
        this.quantidadeRobos = quantidadeRobos;
        this.terreno = terreno;
        gravadorDeArquivo = new GravadorDeArquivo(nome);
        criarRobos();
    }

    public Companhia(CompanhiaLeitura companhiaLeitura, Terreno terreno) {
        this.nome = companhiaLeitura.getNome();
        this.terreno = terreno;
        this.controladorEstrategia = ControladorUtils
                .getControladorNome(companhiaLeitura.getControlador(), companhiaLeitura.getNome());
        this.quantidadeRobos = companhiaLeitura.getQuantidadeRobos();
        gravadorDeArquivo = new GravadorDeArquivo(nome);
        criarRobos();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Companhia companhia = (Companhia) o;
        return Objects.equals(nome, companhia.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    private void criarRobos() {
        for (int i = 0; i < quantidadeRobos; i++) {
            Celula celulaPouso = null;

            while (celulaPouso == null || celulaPouso.isVazia() || celulaPouso.isTemRobo()) {
                int maximoLinhas = terreno.getQuantidadeLinhas();
                int maximoColunas = terreno.getQuantidadeColunas();

                int linha = RandomUtil.gerarInteiroAleatorioIntervalo(0, maximoLinhas);
                int coluna = RandomUtil.gerarInteiroAleatorioIntervalo(0, maximoColunas);

                celulaPouso = terreno.getCelulaPosicao(new Posicao(linha, coluna));
            }

            Robo robo = new Robo(controladorEstrategia, celulaPouso);

            adicionarRobo(robo);
        }
    }

    public void jogar(int segundoAtualJogo) {
        System.out.println("Segundo Atual Jogo " + segundoAtualJogo);
        for (Robo robo : robos) {
            Controlador controladorRoboAtual = robo.getControlador();
            controladorRoboAtual.iniciarEstrategia(terreno, gravadorDeArquivo);
        }
        terreno.imprimirDashboard();
        System.out.println("\n");
    }

    public void fecharGravador() {
        gravadorDeArquivo.fecharArquivo();
    }


    public double getTotalHelioProspectado() {
        double totalHelioProspectado = 0;
        for (Robo robo : robos) {
            Controlador controladorRoboAtual = robo.getControlador();
            totalHelioProspectado += controladorRoboAtual.getQuantidadeTotalHelioColetado();
        }
        return totalHelioProspectado;
    }
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

    public int getQuantidadeRobos() {
        return quantidadeRobos;
    }

    @Override
    public String toString() {
        return String.format("NOME DA EQUIPE: %s, QUANTIDADE DE ROBOS: %d, TOTAL HELIO COLETADO %.2f\n",
                getNome(),
                getQuantidadeRobos(),
                getTotalHelioProspectado());
    }
}