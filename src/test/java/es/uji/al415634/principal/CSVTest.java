package es.uji.al415634.principal;

import es.uji.al415634.principal.Lectura.CSV;
import es.uji.al415634.principal.Tablas.RowWithLabel;
import es.uji.al415634.principal.Tablas.TableWithLabels;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    //Para la prueba 5
    private Collection<List<Double>> pruebas;
    private final Integer[] lineas = {8,18,48,58,88,128};
    private final Double[] vect1 = {4.4,2.9,1.4,0.2}; //linea 8
    private final Double[] vect2 = {5.7,3.8,1.7,0.3}; //linea 18
    private final Double[] vect3 = {5.3,3.7,1.5,0.2}; //linea 48
    private final Double[] vect4 = {6.6,2.9,4.6,1.3}; //linea 58
    private final Double[] vect5 = {5.6,3.0,4.1,1.3}; //linea 88
    private final Double[] vect6 = {6.4,2.8,5.6,2.1}; //linea 128
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        //Para la prueba5
        pruebas=new ArrayList<>();
        //Añadir las listas a la lista de listas
        pruebas.add(addElement(vect1));
        pruebas.add(addElement(vect2));
        pruebas.add(addElement(vect3));
        pruebas.add(addElement(vect4));
        pruebas.add(addElement(vect5));
        pruebas.add(addElement(vect6));
    }
    //El addElement añade todos los elementos de la lista
    private List<Double> addElement(Double[] vect){
        List<Double> lista = new ArrayList<>();
        for(int i=0; i<vect.length;i++){
            lista.add(vect[i]);
        }
        return lista;
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void readTableLabels() throws IOException {
        CSV csv = new CSV();
        TableWithLabels tabla = csv.readTableLabels("src/main/java/es/uji/al415634/Files/Iris");
        //Comprobar num rows
        assertEquals(150, tabla.listaRows.size());

        //Comprobar num columnas headers
        assertEquals(4, tabla.headers.size());

        //Comprobar etiquetas headers
        assertEquals("sepal length", tabla.headers.get(0));
        assertEquals("sepal width", tabla.headers.get(1));
        assertEquals("petal length", tabla.headers.get(2));
        assertEquals("petal width", tabla.headers.get(3));

        //El número que se asigna a cada fila es correcto
        for(int i = 1; i<tabla.listaRows.size(); i++){
            if(i<50){
                assertEquals(0, tabla.getRowAt(i).getNumberClass());
            } else
            if (i<100) {
                assertEquals(1, tabla.getRowAt(i).getNumberClass());
            }else
                assertEquals(2, tabla.getRowAt(i).getNumberClass());
        }

        //Las filas que guardas en una tabla puedes recuperarlas conteniendo los mismos valores que guardaste
        int j=0;
        for(List<Double> aComprobar: pruebas){
            RowWithLabel fila = tabla.getRowAt(lineas[j]);
            assertEquals(aComprobar.get(0), fila.getData().get(0));
            assertEquals(aComprobar.get(1), fila.getData().get(1));
            assertEquals(aComprobar.get(2), fila.getData().get(2));
            assertEquals(aComprobar.get(3), fila.getData().get(3));
            j++;
        }
    }
}