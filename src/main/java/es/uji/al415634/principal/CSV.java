package es.uji.al415634.principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSV {
    private Table datos;
    private int tam; //Tamaño lista datos
    //Constructor
    public CSV(){}
    public Table readTable(String nombreFichero) throws IOException {
        //Leer los datos del fichero
        BufferedReader buffer = new BufferedReader(new FileReader(nombreFichero));
        Table tabla = new Table();
        //Pone los headers a la tabla
        String[] vectorHeaders = buffer.readLine().split(",");
        tam = vectorHeaders.length;
        for(int i = 0; i<tam; i++){
            tabla.addHeader(vectorHeaders[i]);
        }
        //Crea los Rows y los va poniendo en la tabla
        String cadena;
        while((cadena = buffer.readLine()) != null){
            tabla.addRow(new Row(creadorData(cadena)));
        }
        this.datos=tabla; //PARA LA PRUEBAS VISUALES
        return tabla;
    }
    public TableWithLabels readTableLabels(String nombreFichero) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(nombreFichero));
        TableWithLabels tabla = new TableWithLabels();
        //Pone los headers a la tabla
        String[] vectorHeaders = buffer.readLine().split(",");
        tam = vectorHeaders.length -1;
        for(int i = 0; i<tam; i++){
            tabla.addHeader(vectorHeaders[i]);
        }
        //Crea los Rows y los va poniendo en la tabla
        String cadena;
        while((cadena = buffer.readLine()) != null){
            String etiqueta = cadena.split(",")[tam];
            int index = tabla.addToMap(etiqueta);
            tabla.addRow(new RowWithLabel(creadorData(cadena), index));
        }
        return tabla;
    }


    private List<Double> creadorData(String cadena){
        String[] vectorDatos = new String[tam];
        for (int i = 0; i< tam; i++){
            vectorDatos[i] = cadena.split(",")[i];
        }
        List<Double> data = new ArrayList<>();
        for(int j = 0; j<tam; j++){
            Double num = Double.parseDouble(vectorDatos[j]);
            data.add(num);
        }
        return data;

    }
}
