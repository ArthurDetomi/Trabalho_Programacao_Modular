package edu.ufsj.trabalho.terrenos;


import com.google.gson.Gson;
import edu.ufsj.trabalho.api.terrenos.Terreno;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class TerrenoTeste {

    public static void main(String[] args) {
        Gson gson = new Gson();
        File json = new File("src/teste/TesteTerreno.json");

        try {
            FileReader leitor = new FileReader(json);
            Terreno terreno = gson.fromJson(leitor, Terreno.class);
            terreno.imprimirTerreno();
        } catch (FileNotFoundException e) {
            System.out.printf("ERRO DE LEITURA");
        }
    }

}
