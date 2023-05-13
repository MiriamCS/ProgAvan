package es.uji.al415634.principal.Modelo.Lectura;

import es.uji.al415634.principal.Modelo.Tablas.Row;
import es.uji.al415634.principal.Modelo.Tablas.Table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUnlabeledFileReader extends ReaderTemplate<Table> {
    public int tam;
    public BufferedReader buffer;
    public String cadena; //Leer nuevos datos

    public CSVUnlabeledFileReader(String nombreFichero) {
        super(nombreFichero);
    }

    @Override
    public void openSource(String source) throws IOException {
        this.buffer = new BufferedReader(new FileReader(source));
    }

    @Override
    public void processHeaders(String headers) {
        //Pone los headers a la tabla
        String[] vectorHeaders = headers.split(",");
        tam = vectorHeaders.length;
        for(int i = 0; i<tam; i++){
            tabla.addHeader(vectorHeaders[i]);
        }
    }

    @Override
    public void processdata(String data) {
        tabla.addRow(new Row(data(data)));
    }

    //método auxiliar
    public List<Double> data(String data){
        String[] vectorDatos = new String[tam];
        for (int i = 0; i< tam; i++){
            vectorDatos[i] = data.split(",")[i];
        }
        List<Double> datos = new ArrayList<>();
        for(int j = 0; j<tam; j++){
            Double num = Double.parseDouble(vectorDatos[j]);
            datos.add(num);
        }
        return datos;
    }

    @Override
    public void closeSource() throws IOException {
        buffer.close();
    }
    @Override
    public boolean hasMoreData() throws IOException {
        //Crea los Rows y los va poniendo en la tabla
        String cadena;
        if((cadena = buffer.readLine()) != null){
            this.cadena = cadena;
            return true;
        }
        return false;
    }

    @Override
    public String getNextData() throws IOException {
        if(hasMoreData()){
            return cadena;
        }
        return null;
    }

    //método para crear la tabla
    @Override
    public Table createTable(){
        return new Table();
    }
}
