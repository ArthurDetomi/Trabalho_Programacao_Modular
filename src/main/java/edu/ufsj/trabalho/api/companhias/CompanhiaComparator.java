package edu.ufsj.trabalho.api.companhias;

import java.util.Comparator;

public class CompanhiaComparator {
    public static Comparator<Companhia> porTotalHelioProspectado = new Comparator<Companhia>() {
        @Override
        public int compare(Companhia o1, Companhia o2) {
            if (o1.getTotalHelioProspectado() < o2.getTotalHelioProspectado()) {
                return -1;
            } else if (o1.getTotalHelioProspectado() > o2.getTotalHelioProspectado()) {
                return 1;
            } else {
                return 0;
            }
        }

    };
}

