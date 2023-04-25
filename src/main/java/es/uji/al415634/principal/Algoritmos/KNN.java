package es.uji.al415634.principal.Algoritmos;

import es.uji.al415634.principal.Distancia.Distance;
import es.uji.al415634.principal.Distancia.DistanceClient;
import es.uji.al415634.principal.Tablas.RowWithLabel;
import es.uji.al415634.principal.Tablas.Table;
import es.uji.al415634.principal.Tablas.TableWithLabels;

import java.util.List;

public class KNN implements Algorithm<List<Double>, Integer>, DistanceClient {
    private TableWithLabels tabla;

    public Distance distance;

    public KNN(Distance distance){
        this.distance = distance;
    }

    public void train(Table data) {
        this.tabla = (TableWithLabels) data;
    }

    public Integer estimate(List<Double> data) {
        int closestIndex = -1;
        double closestDistance = Double.POSITIVE_INFINITY;
        //Por cada row, conseguir data y compararla con la del argumento (euclidea)
        for (int i = 0; i < tabla.listaRows.size(); i++) {
            List<Double> punto = tabla.listaRows.get(i).getData();

            double d_euclidea = distance.calculateDistance(data, punto);
            //Guardar la euclidea menor
            if (d_euclidea < closestDistance) {
                closestIndex = i;
                closestDistance = d_euclidea;
            }
        }
        RowWithLabel row = (RowWithLabel) tabla.listaRows.get(closestIndex);
        //Devolver el tipo de iris (index de etiqueta) del row de euclidea menor
        return row.getNumberClass();
    }

    public void SetDistance(Distance distance) {
        this.distance = distance;
    }
}
