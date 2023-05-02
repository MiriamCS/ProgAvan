package es.uji.al415634.principal;

import es.uji.al415634.principal.Distancia.Distance;
import es.uji.al415634.principal.Tablas.Table;
import javafx.application.Application;

public interface Grafica {
    void tipoRecomendacion(String rec);
    void tipoDistancia(Distance distancia);
    void seleccionarCancion(String texto);
}
