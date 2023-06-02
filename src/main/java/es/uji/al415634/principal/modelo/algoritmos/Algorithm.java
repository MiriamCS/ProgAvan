package es.uji.al415634.principal.modelo.algoritmos;

import es.uji.al415634.principal.modelo.NumeroClusterNoValidoException;
import es.uji.al415634.principal.modelo.tablas.Table;

import java.util.List;

public interface Algorithm<V extends List<Double>, W extends Number>{
    void train(Table data) throws NumeroClusterNoValidoException;

   W estimate(V sample);

}
