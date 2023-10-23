package edu.ufsj.trabalho.utils;

import edu.ufsj.trabalho.terrenos.Posicao;

import java.util.Random;

public class RandomUtil {

    private static Random random = new Random();

    public static int gerarInteiroAleatorioIntervalo(int minimo, int maximo) {
        return minimo + random.nextInt(maximo - minimo);
    }

}
