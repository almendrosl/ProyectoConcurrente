package RdP;
import java.util.*;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;

public class RdP {

    private VectorDeEstado vectorDeEstado;
    private Array2DRowRealMatrix matrizI;
    private VectorSensibilizadas vectorSensibilizadas;
    private int transiciones;
    private int plazas;

    public RdP(EstadoInicial conjuntoInicial)
    {
        HashMap<String,Array2DRowRealMatrix> matrices = conjuntoInicial.getMatrices();
        HashMap<String,ArrayRealVector> vectores = conjuntoInicial.getVectores();
        ArrayRealVector vaux = vectores.get("Marking");
        this.vectorDeEstado = new VectorDeEstado(vaux);
        this.matrizI =  matrices.get("Combined incidence matrix I");
        vaux = vectores.get("Enabled transitions");
        this.vectorSensibilizadas = new VectorSensibilizadas(vaux);
        this.transiciones = matrizI.getColumnDimension();
        this.plazas = matrizI.getRowDimension();
    }

    public boolean disparar(int transicionDeseada){
        ArrayRealVector disparo = new ArrayRealVector(transiciones);
        disparo.set(0);
        disparo.setEntry(transicionDeseada,1);
        if(vectorSensibilizadas.estaSensibilizado(disparo)){
            vectorDeEstado.calculoDeVectorEstado();
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayRealVector getVectorDeEstado() {
        return vectorDeEstado.getVec();
    }

    public VectorSensibilizadas getSensibilizadas(){
        return vectorSensibilizadas;
    }
}
