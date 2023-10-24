package edu.ufsj.trabalho;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.ufsj.trabalho.companhias.Companhia;
import edu.ufsj.trabalho.controladores.Controlador;
import edu.ufsj.trabalho.leitor.EstadoJogo;
import edu.ufsj.trabalho.leitor.LeitorJson;
import edu.ufsj.trabalho.robos.Direcoes;
import edu.ufsj.trabalho.robos.Robo;
import edu.ufsj.trabalho.terrenos.Posicao;
import edu.ufsj.trabalho.terrenos.Terreno;
import edu.ufsj.trabalho.terrenos.TerrenoTemp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

public class Testeleitor {
    public static void main (String[] args) throws IOException {
        /*
        LeitorJson leitor = new LeitorJson("src/main/java/edu/ufsj/trabalho/JsonTeste.json");
        Terreno terreno = leitor.getTerreno();
        Companhia equipe = leitor.getCompanhias();

        Controlador controlador = new Controlador();
        Robo robo = new Robo(controlador, terreno.getCelulaPosicao(new Posicao(0,0)), Direcoes.DIREITA);

        terreno.imprimirDashboard();
        System.out.print(equipe) ;
        */
        // Crie um objeto Gson
        String json = String.join(" ",
                Files.readAllLines(
                        Paths.get("src/main/java/edu/ufsj/trabalho/JsonTeste.json"),
                        StandardCharsets.UTF_8)
        );

        System.out.println(json);

        EstadoJogo estadoJogo = new Gson().fromJson(json, EstadoJogo.class);


        System.out.println(estadoJogo.getTerreno());
    }
}
