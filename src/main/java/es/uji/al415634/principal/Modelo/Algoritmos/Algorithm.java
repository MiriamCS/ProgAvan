package es.uji.al415634.principal.Modelo.Algoritmos;

import es.uji.al415634.principal.Modelo.Excepcion.NumeroClusterNoValidoException;
import es.uji.al415634.principal.Modelo.Tablas.Table;

import java.util.List;

public interface Algorithm<V extends List<Double>, W extends Number>{
    void train(Table data) throws NumeroClusterNoValidoException;

   W estimate(V sample);

}
