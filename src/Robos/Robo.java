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
    // Exemplo 10 segundos, como foi especificado no trabalho
    private final long TEMPO_TOTAL = 10L;

    public Robo(Controlador controlador, Celula celulaPouso, Direcoes direcaoInicial) {
        if (celulaPouso == null || celulaPouso.isTemRobo()) {
            throw new IllegalArgumentException("Celula de pouso inválida");
        }

        this.controlador = controlador;
        controlador.setRobo(this);
        this.celulaAtual = celulaPouso;
        this.direcao_atual = direcaoInicial;
        this.celulaAtual.setTemRobo(true);
    }

    @Override
    public String toString() {
        return "Robo{" +
                "celulaAtual=" + celulaAtual +
                ", controlador=" + controlador +
                ", direcao_atual=" + direcao_atual +
                ", quantidade_coletada_helio=" + quantidade_coletada_helio +
                ", horaInicioColeta=" + horaInicioColeta +
                ", TEMPO_TOTAL=" + TEMPO_TOTAL +
                '}';
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

        if (celulaLeitura == null) {
            mapa.put(direcao_atual, null);
        } else {
            mapa.put(direcao_atual, celulaLeitura.getRugosidadeTerreno());
        }

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
        celulaAtual.setConcentracaoHelio(0d);
        horaInicioColeta = null;

        return true;
    }

    public boolean movimentar(Movimentacao movimento, Terreno terreno) {
        if (movimento == null || terreno == null) {
            throw new IllegalArgumentException("Parâmetros não podem ser nulos");
        }

        // Se ele está fazendo uma coleta não pode se movimentar
        if (horaInicioColeta != null) {
            return false;
        }

        if (movimento == Movimentacao.ANDA) {
            Long atraso = getTempoDuracaoMovimento(terreno);

            Celula novaCelula = terreno.getCelulaPosicao(atualizarPosicaoComDirecaoAtual(getPosicaoAtual()));

            if (novaCelula == null) {
                System.out.println("Está saindo dos limites do terreno");
                return false;
            }

            if (novaCelula.isTemRobo()) {
                System.out.println("Celula já possui um robo");
                return false;
            }

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

            celulaAtual.setTemRobo(false);
            celulaAtual = novaCelula;
        } else {
            atualizaDirecaoComMovimento(movimento);
        }
        return true;
    }

    private void atualizaDirecaoComMovimento(Movimentacao movimento) {
        switch (direcao_atual) {
            case CIMA:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcao_atual = Direcoes.DIREITA;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    this.direcao_atual = Direcoes.ESQUERDA;
                }
                break;
            case BAIXO:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcao_atual = Direcoes.ESQUERDA;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    this.direcao_atual = Direcoes.DIREITA;
                }
                break;
            case DIREITA:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcao_atual = Direcoes.BAIXO;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    this.direcao_atual = Direcoes.CIMA;
                }
                break;
            case ESQUERDA:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcao_atual = Direcoes.CIMA;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    this.direcao_atual = Direcoes.BAIXO;
                }
                break;
        }
    }

    private long getTempoDuracaoColeta() {
        return (long) ((getConcentracaoHelioPosicaoAtual() * TEMPO_TOTAL) / 0.1);
    }

    private long getTempoDuracaoMovimento(Terreno terreno) {
        Map<Direcoes, Double> rugosidadeRegiao = getRugosidadeRegiao(terreno);
        Double rugosidade = rugosidadeRegiao.get(direcao_atual);
        if (rugosidade != null) {
            return (long) ((rugosidade * TEMPO_TOTAL) / 0.1);
        }
        return 0L;
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
