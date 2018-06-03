package RdP;
import java.util.*;

public class RdP {

    VectorDeEstado vectorDeEstado;
    MatrizI matrizI;
    VectorSensibilizadas vectorSensibilizadas;

    public RdP(VectorDeEstado vectorDeEstado, MatrizI matrizI)
    {
        this.vectorDeEstado = vectorDeEstado;
        this.matrizI = matrizI;
    }

    public boolean disparar(){

        vectorSensibilizadas.estaSensibilizado();





        return true;

    }
}
