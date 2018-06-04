package RdP;
import java.util.*;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;

public class RdP {

    private ArrayRealVector vectorDeEstado;
    private Array2DRowRealMatrix matrizI;
    private VectorSensibilizadas vectorSensibilizadas;
    private int transiciones;
    private int plazas;

    public RdP(EstadoInicial conjuntoInicial)
    {
        HashMap<String,Array2DRowRealMatrix> matrices = conjuntoInicial.getMatrices();
        HashMap<String,ArrayRealVector> vectores = conjuntoInicial.getVectores();
        vectorDeEstado = vectores.get("Marking");
        matrizI =  matrices.get("Combined incidence matrix I");
        ArrayRealVector vaux = vectores.get("Enabled transitions");
        vectorSensibilizadas = new VectorSensibilizadas(vaux);
        transiciones = matrizI.getColumnDimension();
        plazas = matrizI.getRowDimension();
    }

    public boolean disparar(int transicionDeseada){
        ArrayRealVector disparo = new ArrayRealVector(transiciones);
        disparo.set(0);
        disparo.setEntry(transicionDeseada,1);
        if(vectorSensibilizadas.estaSensibilizado(disparo)){
            calculoDeVectorEstado(disparo);
            vectorSensibilizadas.actualiceSensibilizadoT();
            return true;
        }
        else{
            return false;
        }
    }

    private void calculoDeVectorEstado(ArrayRealVector disparo){
        vectorDeEstado = vectorDeEstado.add(matrizI.operate(disparo));
    }

    public ArrayRealVector getVectorDeEstado() {
        return vectorDeEstado;
    }

    public VectorSensibilizadas getSensibilizadas(){
        return vectorSensibilizadas;
    }
}
