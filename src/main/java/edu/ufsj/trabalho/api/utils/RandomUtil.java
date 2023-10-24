package edu.ufsj.trabalho.api.utils;

import java.util.Random;

public class RandomUtil {

    private static Random random = new Random();

    public static int gerarInteiroAleatorioIntervalo(int minimo, int maximo) {
        return minimo + random.nextInt(maximo - minimo);
    }

    public static double gerarDoubleAleatorioIntervalo(double minimo, double maximo) {
        Random random = new Random();
        return minimo + (maximo + Double.MIN_VALUE - minimo) * random.nextDouble();
    }

}
