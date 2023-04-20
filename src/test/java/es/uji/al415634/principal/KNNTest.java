package es.uji.al415634.principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KNNTest {
    ReaderTemplate<Table> csv = new CSVLabeledFileReader("src/main/java/es/uji/al415634/Files/Iris");
    TableWithLabels tabla = (TableWithLabels) csv.readTableFromSource();
    private final double[] s1 = {5.4,2.0,1.9,2.5}; //SAMPLE1
    private final double[] s2 = {4.5,3.0,4.8,1.2}; //SAMPLE2
    private final double[] s3 = {6.4,3.9,1.5,0.3}; //SAMPLE3
    private final List<Double> sample = new ArrayList<>();
    Distance d_eucl = new EuclideanDistance();
    KNN knn_eucl = new KNN(d_eucl);

    Distance d_manh = new ManhattanDistance();
    KNN knn_manh = new KNN(d_manh);

    KNNTest() throws IOException {
    }

    @org.junit.jupiter.api.Test
    void train() {
    }

    @org.junit.jupiter.api.Test
    void estimate() {
        //Pruebas con euclidea
        knn_eucl.train(tabla);
        //prueba 1
        for (double elem: s1){sample.add(elem);}
        assertEquals(1, knn_eucl.estimate(sample));
        sample.clear();
        //prueba 2
        for (double elem: s2){sample.add(elem);}
        assertEquals(2, knn_eucl.estimate(sample));
        sample.clear();
        //prueba 3
        for (double elem: s3){sample.add(elem);}
        assertEquals(0, knn_eucl.estimate(sample));

        //Pruebas con manhattan
        knn_manh.train(tabla);
        //prueba 1
        for (double elem: s1){sample.add(elem);}
        assertEquals(0, knn_manh.estimate(sample));
        sample.clear();
        //prueba 2
        for (double elem: s2){sample.add(elem);}
        assertEquals(1, knn_manh.estimate(sample));
        sample.clear();
        //prueba 3
        for (double elem: s3){sample.add(elem);}
        assertEquals(0, knn_manh.estimate(sample));
    }

}