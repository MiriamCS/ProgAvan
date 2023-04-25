package es.uji.al415634.principal.Tablas;

import es.uji.al415634.principal.Tablas.Row;

import java.util.ArrayList;
import java.util.List;
public class Table {
    public List<String> headers;
    public List<Row> listaRows;

    //Constructor
    public Table(){
        headers= new ArrayList<>();
        listaRows = new ArrayList<>();
    }

    public void addHeader(String header){
        headers.add(header);
    }
    public void addRow(Row row){
        listaRows.add(row);
    }


    public Row getRowAt(int rowNumber){
        return listaRows.get(rowNumber);
    }
    public int getNumRows(){
        return listaRows.size();
    }
}
