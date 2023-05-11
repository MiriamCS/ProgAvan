package es.uji.al415634.principal.Algoritmos;

import es.uji.al415634.principal.Excepcion.NumeroClusterNoValidoException;
import es.uji.al415634.principal.Tablas.Table;

import java.util.List;

public interface Algorithm<V extends List<Double>, W extends Number>{
    void train(Table data) throws NumeroClusterNoValidoException;

   W estimate(V sample);

}
