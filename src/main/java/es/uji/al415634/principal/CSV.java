package es.uji.al415634.principal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CSV {
    private Table datos;
    //Constructor
    public CSV(){}
    public Table readTable(String nombreFichero) throws IOException {
        ReaderTemplate<Table> rt = new CSVUnlabeledFileReader(nombreFichero);
        Table tabla = rt.readTableFromSource();
        this.datos=tabla; //PARA LA PRUEBAS VISUALES
        return tabla;
    }


    public TableWithLabels readTableLabels(String nombreFichero) throws IOException {
        ReaderTemplate<Table> rt = new CSVLabeledFileReader(nombreFichero);
        TableWithLabels tabla = (TableWithLabels) rt.readTableFromSource();
        this.datos=tabla; //PARA LA PRUEBAS VISUALES
        return tabla;
    }

    //PRUEBAS VISUALES
    //Del AV
    public void saveTable(Table t, String filename) throws IOException {
        try {
            FileWriter fw = new FileWriter(filename);
            for (int i=0; i<t.getNumRows(); i++)
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

    public void savePredictions(Distance distancia) {
        KMeans kmeans = new KMeans(4,3, 200, distancia);
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
        csv.readTable("src/main/java/es/uji/al415634/Files/face.csv");
        Distance d_eucl = new EuclideanDistance();
        Distance d_man = new ManhattanDistance();
        csv.savePredictions(d_eucl);
        //csv.savePredictions(d_man);
    }
}
