package es.uji.al415634.principal;

import java.io.IOException;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader{
    public CSVLabeledFileReader(String nombreFichero) {
        super(nombreFichero);
    }
    @Override
    void processdata(String data){
        tam = tam -1;
        String etiqueta = data.split(",")[tam];
        int index =((TableWithLabels) tabla).addToMap(etiqueta);
        tabla.addRow(new RowWithLabel(data(data), index));
    }

}
