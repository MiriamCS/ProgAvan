package es.uji.al415634.principal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KMeansTest {
    //Tabla prueba 1
    private final CSV csv = new CSV();
    private final Table tabla1 = csv.readTable("src/main/java/es/uji/al415634/Files/Prueba1");
    //Tabla prueba 2
    private  final CSV csv2 = new CSV();
    private final Table tabla2 = csv2.readTable("src/main/java/es/uji/al415634/Files/Prueba2");
    List<Double> puntoPruebas = new ArrayList<>(3);
    List<Double> puntoEstimate = new ArrayList<>(3);

    Distance distance = new EuclideanDistance();

    KMeansTest() throws IOException {
    }


    @BeforeEach
    void setUp() {
        puntoPruebas.add(0.0); puntoPruebas.add(0.0);puntoPruebas.add(0.0);
        puntoEstimate.add(5.1); puntoEstimate.add(3.5);puntoEstimate.add(1.4);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void train() {
        //prueba 1: debe lanzar excepcion
        KMeans kmeans = new KMeans(13,4,415634, distance);
        assertThrows(IndexOutOfBoundsException.class, ()-> kmeans.train(tabla1));
        //prueba 1: no debe lanzar excepcion
        KMeans kmeans1 = new KMeans(3,4,415634, distance);
        kmeans1.train(tabla1);
        assertEquals(3,kmeans1.repre.size());

        //prueba 2: debe lanzar excepcion
        KMeans kmeans2 = new KMeans(12, 2, 415634, distance);
        assertThrows(IndexOutOfBoundsException.class, ()-> kmeans2.train(tabla2));
        //prueba2: no sebe lanzar excepcion
        KMeans kmeans3 = new KMeans(2,4,415634, distance);
        kmeans3.train(tabla2);
        assertEquals(2,kmeans3.repre.size());
    }

    @Test
    void estimate() {
        Distance d_eucl = new EuclideanDistance();
        KMeans kmeans_eucl = new KMeans(2, 2, 415634, d_eucl);
        kmeans_eucl.train(tabla1);

        assertEquals(puntoEstimate, tabla1.listaRows.get(kmeans_eucl.estimate(puntoPruebas)).getData());

        Distance d_manh = new ManhattanDistance();
        KMeans kmeans_manh = new KMeans(2, 2, 415634, d_manh);
        kmeans_manh.train(tabla1);

        assertEquals(puntoEstimate, tabla1.listaRows.get(kmeans_manh.estimate(puntoPruebas)).getData());


    }

    @Test
    void asignar() {
        //prueba 1
        Integer[] solucionPrueba1 = {0, 2, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
        KMeans kmeans = new KMeans(3,4,415634, distance);
        kmeans.train(tabla1);
        List<Integer> aux = kmeans.asignar();
        for(int i=0; i < aux.size(); i++){
            assertEquals(solucionPrueba1[i],aux.get(i));
        }
        //prueba 2
        Integer[] solucionPrueba2 = {0, 0, 0, 1, 1, 0, 2, 2, 1, 0, 0};
        KMeans kmeans1 = new KMeans(3,4,415634, distance);
        kmeans1.train(tabla2);
        List<Integer> aux1 = kmeans1.asignar();
        for(int i=0; i < aux1.size(); i++){
            assertEquals(solucionPrueba2[i],aux1.get(i));
        }
    }
    @Test
    void nuevosCentroides() {
        //Prueba 1
        List<List<Double>> solucionPrueba1 = new ArrayList<>();
        List<Double> c1 = new ArrayList<>();
        c1.add(4.8);
        c1.add(3.266);
        c1.add(1.4);
        List<Double> c2 = new ArrayList<>();
        c2.add(6.412);
        c2.add(2.96);
        c2.add(5.087);
        List<Double> c3 = new ArrayList<>();
        c3.add(4.9);
        c3.add(3.0);
        c3.add(1.4);
        solucionPrueba1.add(c1);
        solucionPrueba1.add(c2);
        solucionPrueba1.add(c3);

        KMeans kmeans = new KMeans(3, 4, 415634, distance);
        kmeans.train(tabla1);
        List<List<Double>> aux = kmeans.nuevosCentroides(kmeans.asignar());
        int i=0;
        for(List<Double> list: aux){
            int j=0;
            for(Double element: list){
                assertEquals(solucionPrueba1.get(i).get(j),element, 0.01);
                j++;
            }
            i++;
        }

        //Prueba 2
        List<List<Double>> solucionPrueba2 = new ArrayList<>();
        List<Double> d1 = new ArrayList<>();
        d1.add(6.066);
        d1.add(2.78);
        d1.add(4.61);
        List<Double> d2 = new ArrayList<>();
        d2.add(5.0);
        d2.add(3.4);
        d2.add(1.4);
        List<Double> d3 = new ArrayList<>();
        d3.add(7.0);
        d3.add(2.7);
        d3.add(6.05);
        solucionPrueba2.add(d1);
        solucionPrueba2.add(d2);
        solucionPrueba2.add(d3);

        KMeans kmeans1 = new KMeans(3, 4, 415634, distance);
        kmeans1.train(tabla2);
        List<List<Double>> aux1 = kmeans1.nuevosCentroides(kmeans1.asignar());
        i=0;
        for(List<Double> list: aux1){
            int j=0;
            for(Double element: list){
                assertEquals(solucionPrueba2.get(i).get(j),element, 0.01);
                j++;
            }
            i++;
        }
    }
}