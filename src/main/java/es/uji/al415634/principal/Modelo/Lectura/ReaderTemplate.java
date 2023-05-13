package es.uji.al415634.principal.Modelo.Lectura;

import es.uji.al415634.principal.Modelo.Tablas.Table;

import java.io.IOException;

public abstract class ReaderTemplate<T extends Table>{
    public  String source;
    public  T tabla;
    //Constructor
    public ReaderTemplate(String nombreFichero){
        this.source = nombreFichero;
    }

    public final Table readTableFromSource() throws IOException {
        openSource(source);
        this.tabla = createTable();
        String headers = getNextData();
        processHeaders(headers);
        String nuevoDato=getNextData();
        while(nuevoDato != null){
            processdata(nuevoDato);
            nuevoDato = getNextData();
        }
        closeSource();
        return tabla;
    }

    //Métodos a implementar por las subclases
    public abstract void openSource(String source) throws IOException;
    public abstract void processHeaders(String headers);
    public abstract void processdata(String data) throws IOException;
    public abstract void closeSource() throws IOException;

    //comprueba si hay más datos; en nuestro caso, si hay más líneas en el fichero CSV
    public abstract boolean hasMoreData() throws IOException;

    //obtener el siguiente dato; una línea del fichero CSV en nuestro caso
    public abstract String getNextData() throws IOException;

    //método para crear la tabla
    public T createTable(){
        return tabla;
    }


}

