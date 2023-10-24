package edu.ufsj.trabalho.api.companhias;

import edu.ufsj.trabalho.api.controladores.ControladorUtils;
import edu.ufsj.trabalho.api.robos.Robo;
import edu.ufsj.trabalho.api.terrenos.Celula;
import edu.ufsj.trabalho.api.terrenos.Posicao;
import edu.ufsj.trabalho.api.utils.RandomUtil;
import edu.ufsj.trabalho.api.controladores.Controlador;
import edu.ufsj.trabalho.api.leitor.CompanhiaLeitura;
import edu.ufsj.trabalho.api.terrenos.Terreno;

import java.util.ArrayList;
import java.util.List;

public class Companhia {

    private String nome;

    private final Controlador controladorEstrategia;
    private List<Robo> robos = new ArrayList<>();
    private final int quantidadeRobos;

    private final Terreno terreno;

    public Companhia(String nome,
                     Controlador controladorEstrategia,
                     int quantidadeRobos,
                     Terreno terreno) {
        this.nome = nome;
        this.controladorEstrategia = controladorEstrategia;
        this.quantidadeRobos = quantidadeRobos;
        this.terreno = terreno;
        criarRobos();
    }

    public Companhia(CompanhiaLeitura companhiaLeitura, Terreno terreno) {
        this.nome = companhiaLeitura.getNome();
        this.terreno = terreno;
        this.controladorEstrategia = ControladorUtils
                .getControladorNome(companhiaLeitura.getControlador());
        this.quantidadeRobos = companhiaLeitura.getQuantidadeRobos();
        criarRobos();
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

    public void jogar() {
        for (Robo robo : robos) {
            Controlador controladorRoboAtual = robo.getControlador();
            controladorRoboAtual.iniciarEstrategia(terreno);
        }
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
        return String.format("NOME DA EQUIPE: %s, ESTRATEGIA: %s, QUANTIDADE DE ROBOS: %d\n",
                getNome(),
                getControladorEstrategia(),
                getQuantidadeRobos());
    }
}