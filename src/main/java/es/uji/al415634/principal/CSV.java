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

    //Para las comprobaciones visuales
    public void saveTable(Table t, String filename) throws IOException {
        try {
            FileWriter fw = new FileWriter(filename);
            for (int i=0; i<t.listaRows.size(); i++)
            {
                Row row = t.getRowAt(i);
                List<Double> datos = row.getData();
                int j=0;
                for (; j<datos.size()-1; j++)
                {
                    fw.write(datos.get(j).toString());
                    fw.write(",");
                }
                fw.write(datos.get(j).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }

    void savePredictions() {

        KMeans kmeans = new KMeans(1,1, 200);
        kmeans.train(datos);

        Table datos_out = new Table();
        int fin = datos.listaRows.size();
        for (int i=0; i<fin-1; i++) {
            List<Double> data =datos.getRowAt(i).getData();
            data.add((double)kmeans.estimate(data));

            Row row = new Row(data);

            datos_out.addRow(row);
        }
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce un nombre: ");
            String dataset =sc.nextLine();

            saveTable(datos_out, dataset + "_out.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //PROBAR PRUEBAS VISUALES
    //Ejecutar esto para ver lo de prueba visual
    //Deberías encontrar un fichero con el nombre que has introducido, cuyo contenido será los datos de la tabla + una columna de estimación
    //Se han hecho cambios en KMeans en el método euclidea → solo se ha puesto una condición, no se ha modificado nada más
    public static void main(String[] args) throws IOException {
        //Tabla prueba
        CSV csv = new CSV();
        csv.readTable("src/main/java/es/uji/al415634/Files/PruebaVisual");
        csv.savePredictions();

    }
}
