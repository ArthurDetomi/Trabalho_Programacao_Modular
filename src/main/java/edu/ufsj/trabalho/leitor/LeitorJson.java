package edu.ufsj.trabalho.leitor;

import com.google.gson.Gson;
import edu.ufsj.trabalho.companhias.Companhia;
import edu.ufsj.trabalho.terrenos.Terreno;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class LeitorJson {
    private Gson gson;
    private EstadoJogo estadoJogo;
    public LeitorJson(String caminhoDoArquivo){
        this.gson = new Gson();
        estadoJogo = LerEstadoDoJogo(caminhoDoArquivo);
    }
    public EstadoJogo LerEstadoDoJogo(String caminhoDoArquivo){
        File arquivo = new File(caminhoDoArquivo);

        try {
            FileReader leitor = new FileReader(arquivo);
            estadoJogo = gson.fromJson(leitor, EstadoJogo.class);
            return estadoJogo;
        } catch (IOException e) {
            System.out.printf("ERRO NA LEITURA\n");
            return null;
        }
    }
    public Companhia getCompanhias(){return estadoJogo.getCompanhia();}
    public Terreno getTerreno(){return estadoJogo.getTerreno();}

}

