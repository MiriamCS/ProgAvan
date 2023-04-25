package es.uji.al415634.principal.Distancia;

import java.util.List;

public class EuclideanDistance implements Distance{
    public double calculateDistance(List<Double> p, List<Double> q) {
        double d_euclidea = 0;

        for (int j = 0; j < p.size(); j++) {
            double diferencia = Math.pow(q.get(j) - p.get(j), 2);
            d_euclidea += diferencia;
        }

        return Math.sqrt(d_euclidea);

    }
}
