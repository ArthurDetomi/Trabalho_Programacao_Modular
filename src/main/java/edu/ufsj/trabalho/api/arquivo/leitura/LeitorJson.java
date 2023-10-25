package edu.ufsj.trabalho.api.arquivo.leitura;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class LeitorJson {
    private String nomeArquivo;
    public LeitorJson(String caminhoDoArquivo){
        this.nomeArquivo = caminhoDoArquivo;
    }
    public ConfigJogoLeitura getEstadoDoJogo(){
        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            throw new IllegalArgumentException("Nome arquivo está nulo");
        }
        String json;
        try {
            json = String.join(" ",
                    Files.readAllLines(
                            Paths.get(nomeArquivo),
                            StandardCharsets.UTF_8)
            );
            return new Gson().fromJson(json, ConfigJogoLeitura.class);
        } catch (IOException ex) {
            System.out.println("ERRO LEITURA ARQUIVO");
            return null;
        }
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}

