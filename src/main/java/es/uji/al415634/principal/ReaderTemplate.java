package es.uji.al415634.principal;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class ReaderTemplate {
    public String source;
    public Table tabla;
    //Constructor
    public ReaderTemplate(String nombreFichero){
        this.source = nombreFichero;
        this.tabla = new Table();
    }
    //Métodos
    abstract void openSource(String source) throws IOException;
    abstract void processHeaders(String headers);
    abstract void processdata(String data) throws IOException;
    abstract void closeSource() throws IOException;

    //comprueba si hay más datos; en nuestro caso, si hay más líneas en el fichero CSV
    abstract boolean hasMoreData() throws IOException;

    //obtener el siguiente dato; una línea del fichero CSV en nuestro caso
    abstract String getNextData() throws IOException;

    public final Table readTableFromSource() throws IOException {
        openSource(source);
        String headers = getNextData();
        processHeaders(headers);
        String nuevoDato;
        while((nuevoDato = getNextData()) != null)
            processdata(nuevoDato);
        closeSource();
        return tabla;
    }
}

