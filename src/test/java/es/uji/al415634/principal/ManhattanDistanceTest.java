package es.uji.al415634.principal;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManhattanDistanceTest {
    private final double[] s1 = {5.4,2.0,1.9,2.5}; //SAMPLE1
    private final double[] vect2 = {5.7,3.8,1.7,0.3}; //linea 18
    private final double[] vect3 = {5.3,3.7,1.5,0.2}; //linea 48
    private final double[] vect4 = {5.6,3.0,4.1,1.3}; //linea 88
    private final double[] vect5 = {0.0,0.0,0.0,0.0};
    private final List<Double> sample = new ArrayList<>();
    Distance distance = new ManhattanDistance();
    KNN knn = new KNN(distance);
    List<Double> lista = new ArrayList<>();


    @Test
    void calculateDistance() {
        for (double elem: s1){sample.add(elem);}
        //prueba 1
        for (double elem: vect2){lista.add(elem);}
        assertEquals(4.5, knn.distance.calculateDistance(sample, lista), 0.001);   //delta es precisión
        lista.clear();
        //prueba 2
        for (double elem: vect3){lista.add(elem);}
        assertEquals(4.5, knn.distance.calculateDistance(sample, lista), 0.001);
        lista.clear();
        //prueba 3
        for (double elem: vect4){lista.add(elem);}
        assertEquals(4.599, knn.distance.calculateDistance(sample, lista), 0.001);
        lista.clear();
        //prueba 4: lista con ceros
        for (double elem: vect5){lista.add(elem);}
        assertEquals(11.8, knn.distance.calculateDistance(sample, lista), 0.001);
        lista.clear();
    }
}