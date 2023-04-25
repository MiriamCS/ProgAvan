package es.uji.al415634.principal;

import es.uji.al415634.principal.Lectura.CSVLabeledFileReader;
import es.uji.al415634.principal.Lectura.CSVUnlabeledFileReader;
import es.uji.al415634.principal.Lectura.ReaderTemplate;
import es.uji.al415634.principal.Tablas.Row;
import es.uji.al415634.principal.Tablas.RowWithLabel;
import es.uji.al415634.principal.Tablas.Table;
import es.uji.al415634.principal.Tablas.TableWithLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    //Para la prueba 5
    private Collection<List<Double>> pruebasConL;
    private final Integer[] lineasConL = {8, 18, 48, 58, 88, 128};
    private final Double[] vect1 = {4.4, 2.9, 1.4, 0.2}; //linea 8
    private final Double[] vect2 = {5.7, 3.8, 1.7, 0.3}; //linea 18
    private final Double[] vect3 = {5.3, 3.7, 1.5, 0.2}; //linea 48
    private final Double[] vect4 = {6.6, 2.9, 4.6, 1.3}; //linea 58
    private final Double[] vect5 = {5.6, 3.0, 4.1, 1.3}; //linea 88
    private final Double[] vect6 = {6.4, 2.8, 5.6, 2.1}; //linea 128

    private Collection<List<Double>> pruebasSinL;
    private final Integer[] lineasSinL = {3, 6, 9, 10, 11};
    private final Double[] v1 = {4.6,3.1,1.5}; //linea 3
    private final Double[] v2 = {6.9,3.1,4.9}; //linea 6
    private final Double[] v3 = {5.8,2.7,5.1}; //linea 9
    private final Double[] v4 = {7.1,3.0,5.9}; //linea 10
    private final Double[] v5 = {6.3,2.9,5.6}; //linea 11



    @BeforeEach
    void setUp() {
        //Para la prueba con labels
        pruebasConL = new ArrayList<>();
        //Añadir las listas a la lista de listas
        pruebasConL.add(addElement(vect1));
        pruebasConL.add(addElement(vect2));
        pruebasConL.add(addElement(vect3));
        pruebasConL.add(addElement(vect4));
        pruebasConL.add(addElement(vect5));
        pruebasConL.add(addElement(vect6));

        //Para la prueba sin labels
        pruebasSinL = new ArrayList<>();
        //Añadir las listas a la lista de listas
        pruebasSinL.add(addElement(v1));
        pruebasSinL.add(addElement(v2));
        pruebasSinL.add(addElement(v3));
        pruebasSinL.add(addElement(v4));
        pruebasSinL.add(addElement(v5));
    }

    //El addElement añade todos los elementos de la lista
    private List<Double> addElement(Double[] vect) {
        List<Double> lista = new ArrayList<>();
        for (int i = 0; i < vect.length; i++) {
            lista.add(vect[i]);
        }
        return lista;
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void readTableLabels() throws IOException {
        ReaderTemplate<Table> csv = new CSVLabeledFileReader("src/main/java/es/uji/al415634/Files/Iris");
        TableWithLabels tablaConL = (TableWithLabels) csv.readTableFromSource();
        //Comprobar num rows
        assertEquals(150, tablaConL.listaRows.size());

        //Comprobar num columnas headers
        assertEquals(5, tablaConL.headers.size());

        //Comprobar etiquetas headers
        assertEquals("sepal length", tablaConL.headers.get(0));
        assertEquals("sepal width", tablaConL.headers.get(1));
        assertEquals("petal length", tablaConL.headers.get(2));
        assertEquals("petal width", tablaConL.headers.get(3));
        assertEquals("class", tablaConL.headers.get(4));

        //El número que se asigna a cada fila es correcto
        for (int i = 1; i < tablaConL.listaRows.size(); i++) {
            if (i < 50) {
                assertEquals(0, tablaConL.getRowAt(i).getNumberClass());
            } else if (i < 100) {
                assertEquals(1, tablaConL.getRowAt(i).getNumberClass());
            } else
                assertEquals(2, tablaConL.getRowAt(i).getNumberClass());
        }

        //Las filas que guardas en una tabla puedes recuperarlas conteniendo los mismos valores que guardaste
        int j = 0;
        for (List<Double> aComprobar : pruebasConL) {
            RowWithLabel fila = tablaConL.getRowAt(lineasConL[j]);
            assertEquals(aComprobar.get(0), fila.getData().get(0));
            assertEquals(aComprobar.get(1), fila.getData().get(1));
            assertEquals(aComprobar.get(2), fila.getData().get(2));
            assertEquals(aComprobar.get(3), fila.getData().get(3));
            j++;
        }
    }

    @Test
    void readTable() throws IOException {
        ReaderTemplate<Table> csv1 = new CSVUnlabeledFileReader("src/main/java/es/uji/al415634/Files/Prueba1");
        Table tablaSinL = csv1.readTableFromSource();
        //Comprobar num rows
        assertEquals(12, tablaSinL.listaRows.size());

        //Comprobar num columnas headers
        assertEquals(3, tablaSinL.headers.size());

        //Comprobar etiquetas headers
        assertEquals("x", tablaSinL.headers.get(0));
        assertEquals("y", tablaSinL.headers.get(1));
        assertEquals("z", tablaSinL.headers.get(2));


        //Las filas que guardas en una tabla puedes recuperarlas conteniendo los mismos valores que guardaste
        int j = 0;
        for (List<Double> aComprobar : pruebasSinL) {
            Row fila = tablaSinL.getRowAt(lineasSinL[j]);
            assertEquals(aComprobar.get(0), fila.getData().get(0));
            assertEquals(aComprobar.get(1), fila.getData().get(1));
            assertEquals(aComprobar.get(2), fila.getData().get(2));
            j++;
        }
    }
}