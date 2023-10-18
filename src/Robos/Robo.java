package Robos;

import Controladores.Controlador;
import Terrenos.Celula;
import Terrenos.Posicao;
import Terrenos.Terreno;

import java.util.*;

public class Robo {

    private Celula celulaAtual;
    private Controlador controlador;
    private Direcoes direcao_atual;
    private double quantidade_coletada_helio = 0;
    private Date horaInicioColeta;
    private final long TEMPO_TOTAL;

    public Robo(Controlador controlador, long tempoTotal) {
        this.controlador = controlador;
        controlador.setRobo(this);
        TEMPO_TOTAL = tempoTotal;
    }

    public Posicao getPosicaoAtual() {
        return celulaAtual.getPosicao();
    }

    public double getQuantidade_coletada_helio() {
        return quantidade_coletada_helio;
    }

    public double getRugosidadePosicaoAtual() {
        return celulaAtual.getRugosidadeTerreno();
    }

    public Map<Direcoes, Double> getRugosidadeRegiao(Terreno terrenoLeitura) {
        Map<Direcoes, Double> mapa = new HashMap<>();

        Posicao posicaoApontada = atualizarPosicaoComDirecaoAtual(getPosicaoAtual());

        Celula celulaLeitura = terrenoLeitura.getCelulaPosicao(posicaoApontada);

        mapa.put(direcao_atual, celulaLeitura.getRugosidadeTerreno());

        return mapa;
    }

    private Posicao atualizarPosicaoComDirecaoAtual(Posicao posicao) {
        int coluna = posicao.getX();
        int linha = posicao.getY();

        switch (direcao_atual) {
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
        horaInicioColeta = new Date();

        long atraso = getTempoDuracaoColeta();

        Date horaColeta = new Date(horaInicioColeta.getTime() + atraso);

        while (new Date().before(horaColeta)) {
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        this.quantidade_coletada_helio += celulaAtual.getConcentracaoHelio();
        horaInicioColeta = null;
        return true;
    }

    public boolean movimentar(Movimentacao movimento, Terreno terreno) {
        if (movimento == null || terreno == null) {
            throw new IllegalArgumentException("Parâmetros não podem ser nulos");
        }

        if (movimento == Movimentacao.ANDA) {
            Long atraso = getTempoDuracaoMovimento(terreno);

            if (atraso == null) {
                throw new IllegalArgumentException("Atraso nulo");
            }

            Date dataInicial = new Date();
            Date dataFinal = new Date(dataInicial.getTime() + atraso);

            while (new Date().before(dataFinal)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            celulaAtual = terreno.getCelulaPosicao(atualizarPosicaoComDirecaoAtual(getPosicaoAtual()));
        } else {
            atualizaDirecaoComMovimento(movimento);
        }
        return true;
    }

    private void atualizaDirecaoComMovimento(Movimentacao movimento) {
        switch (direcao_atual) {
            case CIMA:
                if (movimento == Movimentacao.DIREITA) {
                    direcao_atual = Direcoes.DIREITA;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    direcao_atual = Direcoes.ESQUERDA;
                }
                break;
            case BAIXO:
                if (movimento == Movimentacao.DIREITA) {
                    direcao_atual = Direcoes.ESQUERDA;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    direcao_atual = Direcoes.DIREITA;
                }
                break;
            case DIREITA:
                if (movimento == Movimentacao.DIREITA) {
                    direcao_atual = Direcoes.BAIXO;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    direcao_atual = Direcoes.CIMA;
                }
                break;
            case ESQUERDA:
                if (movimento == Movimentacao.DIREITA) {
                    direcao_atual = Direcoes.CIMA;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    direcao_atual = Direcoes.BAIXO;
                }
                break;
        }
    }

    private long getTempoDuracaoColeta() {
        return (long) ((getConcentracaoHelioPosicaoAtual() * TEMPO_TOTAL) / 0.1);
    }

    private Long getTempoDuracaoMovimento(Terreno terreno) {
        Map<Direcoes, Double> rugosidadeRegiao = getRugosidadeRegiao(terreno);
        Double rugosidade = rugosidadeRegiao.get(direcao_atual);
        if (rugosidade != null) {
            return (long) ((rugosidade * TEMPO_TOTAL) / 0.1);
        }
        return null;
    }

    public long getTempoDecorridoMillis() {
        if (horaInicioColeta != null) {
            long horaAtual = new Date().getTime();
            return horaAtual - horaInicioColeta.getTime();
        } else {
            return 0L;
        }
    }


    public Direcoes getDirecao_atual() {
        return direcao_atual;
    }

    public void setControlador(Controlador controlador) {
        if (this.controlador != null) {
            return;
        }
        this.controlador = controlador;
    }

    public void setDirecao_atual(Direcoes direcao_atual) {
        this.direcao_atual = direcao_atual;
    }

    public void setCelulaAtual(Celula celulaAtual) {
        this.celulaAtual = celulaAtual;
    }

}
