package es.uji.al415634.principal.Modelo.Distancia;

import java.util.List;

public class ManhattanDistance implements Distance{
    public double calculateDistance(List<Double> p, List<Double> q) {
        double d_manhattan = 0;

        for (int j = 0; j < q.size(); j++) {
            double diferencia = Math.abs(p.get(j) - q.get(j));
            d_manhattan += diferencia;
        }

        return d_manhattan;
    }
}
