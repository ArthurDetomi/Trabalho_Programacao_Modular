package robos;

import controladores.Controlador;
import terrenos.Celula;
import terrenos.Posicao;
import terrenos.Terreno;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Robo {

    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private final Integer id;
    private Celula celulaAtual;
    private Controlador controlador;
    private Direcoes direcaoAtual;
    private double quantidadeColetadaHelio = 0;
    private Date horaInicioColeta;
    // Exemplo 10 segundos, como foi especificado no trabalho
    private static final long TEMPO_TOTAL = 10000L;

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

    @Override
    public String toString() {
        return "Robo{" +
                "celulaAtual=" + celulaAtual +
                ", controlador=" + controlador +
                ", direcao_atual=" + direcaoAtual +
                ", quantidade_coletada_helio=" + quantidadeColetadaHelio +
                ", horaInicioColeta=" + horaInicioColeta +
                ", TEMPO_TOTAL=" + TEMPO_TOTAL +
                '}';
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

    public Map<Direcoes, Double> getRugosidadeRegiao(Terreno terrenoLeitura) {
        Map<Direcoes, Double> mapa = new HashMap<>();

        Posicao posicaoApontada = atualizarPosicaoComDirecaoAtual(getPosicaoAtual());

        Celula celulaLeitura = terrenoLeitura.getCelulaPosicao(posicaoApontada);

        if (celulaLeitura == null) {
            mapa.put(direcaoAtual, null);
        } else {
            mapa.put(direcaoAtual, celulaLeitura.getRugosidadeTerreno());
        }

        return mapa;
    }

    private Posicao atualizarPosicaoComDirecaoAtual(Posicao posicao) {
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


        this.quantidadeColetadaHelio += celulaAtual.getConcentracaoHelio();
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
            long atraso = getTempoDuracaoMovimento(terreno);

            Celula novaCelula = terreno.getCelulaPosicao(atualizarPosicaoComDirecaoAtual(getPosicaoAtual()));

            if (novaCelula == null) {
                System.out.println("Está saindo dos limites do terreno");
                return false;
            }

            if (novaCelula.isTemRobo()) {
                System.out.println("Celula já possui um robo");
                return false;
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
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    this.direcaoAtual = Direcoes.ESQUERDA;
                }
                break;
            case BAIXO:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcaoAtual = Direcoes.ESQUERDA;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    this.direcaoAtual = Direcoes.DIREITA;
                }
                break;
            case DIREITA:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcaoAtual = Direcoes.BAIXO;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    this.direcaoAtual = Direcoes.CIMA;
                }
                break;
            case ESQUERDA:
                if (movimento == Movimentacao.DIREITA) {
                    this.direcaoAtual = Direcoes.CIMA;
                } else  if (movimento == Movimentacao.ESQUERDA) {
                    this.direcaoAtual = Direcoes.BAIXO;
                }
                break;
        }
    }

    private long getTempoDuracaoColeta() {
        return (long) getConcentracaoHelioPosicaoAtual() * TEMPO_TOTAL;
    }

    private long getTempoDuracaoMovimento(Terreno terreno) {
        Map<Direcoes, Double> rugosidadeRegiao = getRugosidadeRegiao(terreno);
        Double rugosidade = rugosidadeRegiao.get(direcaoAtual);
        if (rugosidade != null) {
            return (long) (rugosidade * TEMPO_TOTAL);
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


    public Direcoes getDirecaoAtual() {
        return direcaoAtual;
    }

    public void setControlador(Controlador controlador) {
        if (this.controlador != null) {
            return;
        }
        this.controlador = controlador;
    }

    public void setDirecaoAtual(Direcoes direcaoAtual) {
        this.direcaoAtual = direcaoAtual;
    }

    public void setCelulaAtual(Celula celulaAtual) {
        this.celulaAtual = celulaAtual;
    }

}
