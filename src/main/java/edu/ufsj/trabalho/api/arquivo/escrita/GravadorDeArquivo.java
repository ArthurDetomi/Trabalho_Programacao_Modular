package edu.ufsj.trabalho.api.arquivo.escrita;

import java.io.FileNotFoundException;
import java.util.Formatter;

public class GravadorDeArquivo {

    private Formatter arquivo;

    private static final String CAMINHO_ARQUIVO = "src/main/java/edu/ufsj/trabalho/output/";

    public GravadorDeArquivo(String nomeArquivo) {
        abrirArquivo(nomeArquivo);
    }

    private void abrirArquivo(String nomeArquivo) {
        try {
            arquivo = new Formatter(CAMINHO_ARQUIVO + nomeArquivo +
                    "_relatorio_robos_gravado.txt");
        } catch (SecurityException securityException) {
            System.err.println("Sem permissão para criar arquivo");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Erro em abrir ou criar arquivo");
            System.exit(1);
        }
    }

    public void fecharArquivo() {
        arquivo.close();
    }

    public void adicionarRegistro(String textoRegistro) {
        arquivo.format("%s\n", textoRegistro);
        arquivo.flush();
    }

}
