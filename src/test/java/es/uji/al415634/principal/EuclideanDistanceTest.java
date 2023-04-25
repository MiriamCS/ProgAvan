package es.uji.al415634.principal;

import es.uji.al415634.principal.Algoritmos.KNN;
import es.uji.al415634.principal.Distancia.Distance;
import es.uji.al415634.principal.Distancia.EuclideanDistance;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {
    private final double[] s1 = {5.4,2.0,3.8,2.5}; //SAMPLE1
    private final double[] vect2 = {7.5,3.8,1.7,0.3};
    private final double[] vect3 = {5.3,9.3,1.5,0.2};
    private final double[] vect4 = {2.2,3.0,4.1,1.3};
    private final double[] vect5 = {0.0,0.0,0.0,0.0};
    private final List<Double> sample = new ArrayList<>();
    Distance distance = new EuclideanDistance();
    KNN knn = new KNN(distance);
    List<Double> lista = new ArrayList<>();

    @Test
    void calculateDistance() {
        for (double elem: s1){sample.add(elem);}
        //prueba 1
        for (double elem: vect2){lista.add(elem);}
        assertEquals(4.11, knn.distance.calculateDistance(sample, lista), 0.001);   //delta es precisión
        lista.clear();
        //prueba 2
        for (double elem: vect3){lista.add(elem);}
        assertEquals(7.992, knn.distance.calculateDistance(sample, lista), 0.001);
        lista.clear();
        //prueba 3
        for (double elem: vect4){lista.add(elem);}
        assertEquals(3.573, knn.distance.calculateDistance(sample, lista), 0.001);
        lista.clear();
        //prueba 4: lista con ceros
        for (double elem: vect5){lista.add(elem);}
        assertEquals(7.338, knn.distance.calculateDistance(sample, lista), 0.001);
        lista.clear();
    }
}