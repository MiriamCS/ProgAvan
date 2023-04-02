package es.uji.al415634.principal;

import java.util.List;

public class KNN implements Algorithm<List<Double>, Integer> {
    private TableWithLabels tabla;

    public KNN(){}

    public void train(Table data) {
        this.tabla = (TableWithLabels) data;
    }

    public Integer estimate(List<Double> data) {
        int closestIndex = -1;
        double closestDistance = Double.POSITIVE_INFINITY;
        //Por cada row, conseguir data y compararla con la del argumento (euclidea)
        for (int i = 0; i < tabla.listaRows.size(); i++) {
            List<Double> punto = tabla.listaRows.get(i).getData();

            double d_euclidea = euclidea(data, punto);
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

    public double euclidea(List<Double> sample, List<Double> punto) {
        double d_euclidea = 0;

        for (int j = 0; j < punto.size(); j++) {
            double diferencia = Math.pow(sample.get(j) - punto.get(j), 2);
            d_euclidea += diferencia;
        }

        return Math.sqrt(d_euclidea);
    }

}
