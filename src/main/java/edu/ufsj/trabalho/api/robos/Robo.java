package edu.ufsj.trabalho.api.robos;

import edu.ufsj.trabalho.api.controladores.Controlador;
import edu.ufsj.trabalho.api.terrenos.Celula;
import edu.ufsj.trabalho.api.terrenos.CelulaAdjacente;
import edu.ufsj.trabalho.api.terrenos.Posicao;
import edu.ufsj.trabalho.api.terrenos.Terreno;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Robo {

    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private final Integer id;
    private Celula celulaAtual;
    private Controlador controlador;
    private Direcoes direcaoAtual;
    private double quantidadeColetadaHelio = 0;
    private boolean podeRealizarAcao;
    private long segundoInicioColeta;
    private int tempoGasto;
    private static final double TEMPO_TOTAL = 10L;

    public Robo(Controlador controlador, Celula celulaPouso, Direcoes direcaoInicial) {
        if (celulaPouso == null || celulaPouso.isTemRobo()) {
            throw new IllegalArgumentException("Celula de pouso inválida");
        }

        this.id = idGenerator.getAndIncrement();
        this.controlador = controlador;
        controlador.setRobo(this);
        this.celulaAtual = celulaPouso;
        this.direcaoAtual = direcaoInicial;
        this.celulaAtual.setTemRobo(true);
    }

    public Robo(Controlador controlador, Celula celulaPouso) {
        if (celulaPouso == null || celulaPouso.isTemRobo()) {
            throw new IllegalArgumentException("Celula de pouso inválida");
        }

        this.id = idGenerator.getAndIncrement();
        this.controlador = controlador;
        controlador.setRobo(this);
        this.celulaAtual = celulaPouso;
        this.direcaoAtual = controlador.getDirecaoInicial();
        this.celulaAtual.setTemRobo(true);
    }

    @Override
    public String toString() {
        return "Robo{" +
                "id = " + id +
                ", celulaAtual=" + celulaAtual +
                ", direcao_atual=" + direcaoAtual +
                ", quantidade_coletada_helio=" + quantidadeColetadaHelio +
                ", segundoDeInicio=" + segundoInicioColeta +
                ", TEMPO_TOTAL=" + TEMPO_TOTAL +
                '}';
    }

    public void imprimirDadosRobo(){
        System.out.printf("Robo:[id: %d | Posição Atual: (%d,%d) | Compartimento de Hélio: %.2f]\n\n",
                id,
                celulaAtual.getPosicao().getColuna(),
                celulaAtual.getPosicao().getLinha(),
                quantidadeColetadaHelio);
    }

    public void imprimirDadosRoboRelatorio(){
        System.out.printf("Robo:[id: %d | Compartimento de Hélio: %.2f]\n",
                id,
                quantidadeColetadaHelio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robo robo = (Robo) o;
        return Objects.equals(id, robo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public Posicao getPosicaoAtual() {
        return celulaAtual.getPosicao();
    }

    public double getQuantidadeColetadaHelio() {
        return quantidadeColetadaHelio;
    }

    public double getRugosidadePosicaoAtual() {
        return celulaAtual.getRugosidadeTerreno();
    }

    public CelulaAdjacente getRugosidadeRegiao(Terreno terrenoLeitura) {
        Posicao posicaoApontada = atualizarPosicaoComDirecaoAtual(getPosicaoAtual());

        Celula celulaLeitura = terrenoLeitura.getCelulaPosicao(posicaoApontada);

        return new CelulaAdjacente(direcaoAtual, celulaLeitura);
    }

    private Posicao atualizarPosicaoComDirecaoAtual(Posicao posicao) {
        if (posicao == null) {
            throw new IllegalArgumentException("Posição está nula");
        }
        int coluna = posicao.getColuna();
        int linha = posicao.getLinha();

        switch (direcaoAtual) {
            case DIREITA:
                coluna++;
                break;
            case ESQUERDA:
                coluna--;
                break;
            case BAIXO:
                linha++;
                break;
            case CIMA:
                linha--;
                break;
        }

        return new Posicao(coluna, linha);
    }

    public double getConcentracaoHelioPosicaoAtual() {
        return celulaAtual.getConcentracaoHelio() + celulaAtual.getCoeficienteAleatorio();
    }

    public boolean coletarHelio() {
        long atraso = getTempoDuracaoColeta();

        if (!podeColetar(atraso)) {
            return false;
        }

        this.quantidadeColetadaHelio += celulaAtual.getConcentracaoHelio();
        celulaAtual.setConcentracaoHelio(0d);

        return true;
    }

    public boolean podeColetar(long atraso) {
        if (tempoGasto >= atraso) {
            this.tempoGasto = 0;
            return true;
        }
        return false;
    }

    public void sinalizarTempoPassado() {
        this.tempoGasto++;
    }

    public boolean movimentar(Movimentacao movimento, Terreno terreno) {
        if (movimento == null || terreno == null) {
            throw new IllegalArgumentException("Parâmetros não podem ser nulos");
        }

        if (movimento == Movimentacao.ANDA) {
            long atraso = getTempoDuracaoMovimento(terreno);

            if (!podeColetar(atraso)) {
                return false;
            }

            Celula novaCelula = terreno.getCelulaPosicao(atualizarPosicaoComDirecaoAtual(getPosicaoAtual()));

            if (novaCelula.isVazia()) {
                System.out.println("Está saindo dos limites do terreno");
                return false;
            }

            if (novaCelula.isTemRobo()) {
                System.out.println("Celula já possui um robo");
                return false;
            }

            this.celulaAtual.setTemRobo(false);

            this.celulaAtual = novaCelula;
            this.celulaAtual.setTemRobo(true);
        } else {
            atualizaDirecaoComMovimento(movimento);
        }
        return true;
    }

    private void atualizaDirecaoComMovimento(Movimentacao movimento) {
        switch (direcaoAtual) {
            case CIMA:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcaoAtual = Direcoes.DIREITA;
                } else if (movimento == Movimentacao.ESQUERDA) {
                    this.direcaoAtual = Direcoes.ESQUERDA;
                }
                break;
            case BAIXO:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcaoAtual = Direcoes.ESQUERDA;
                } else if (movimento == Movimentacao.ESQUERDA) {
                    this.direcaoAtual = Direcoes.DIREITA;
                }
                break;
            case DIREITA:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcaoAtual = Direcoes.BAIXO;
                } else if (movimento == Movimentacao.ESQUERDA) {
                    this.direcaoAtual = Direcoes.CIMA;
                }
                break;
            case ESQUERDA:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcaoAtual = Direcoes.CIMA;
                } else if (movimento == Movimentacao.ESQUERDA) {
                    this.direcaoAtual = Direcoes.BAIXO;
                }
                break;
        }
    }

    private long getTempoDuracaoColeta() {
        return (long) (getConcentracaoHelioPosicaoAtual() * TEMPO_TOTAL);
    }

    private long getTempoDuracaoMovimento(Terreno terreno) {
        CelulaAdjacente rugosidadeRegiao = getRugosidadeRegiao(terreno);
        Double rugosidade = rugosidadeRegiao.getRugosidade();
        if (rugosidade != null) {
            return (long) (rugosidade * TEMPO_TOTAL);
        }
        throw new IllegalArgumentException("Rugosidade vindo nulo");
    }

    public int getTempoDecorridoSegundos() {
        return tempoGasto;
    }


    public Direcoes getDirecaoAtual() {
        return direcaoAtual;
    }

    public void setControlador(Controlador controlador) {
        if (this.controlador != null) {
            return;
        }
        this.controlador = controlador;
    }

    public int tempoTotalColeta(){
        return (int) getTempoDuracaoColeta() - 1;
    }

    public int tempoTotalMovimento(Terreno terreno){
        return (int) getTempoDuracaoMovimento(terreno) - 1;
    }

    public void setDirecaoAtual(Direcoes direcaoAtual) {
        this.direcaoAtual = direcaoAtual;
    }

    public void setCelulaAtual(Celula celulaAtual) {
        this.celulaAtual = celulaAtual;
    }

    public Controlador getControlador() {
        return controlador;
    }
}
