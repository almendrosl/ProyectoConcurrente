package RdP;
import java.util.HashMap;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class EstadoInicial {


    private HashMap<String,Array2DRowRealMatrix> matrices = new HashMap<String,Array2DRowRealMatrix>();
    private HashMap<String,ArrayRealVector> vectores = new HashMap<String,ArrayRealVector>();

    public void cargarMatriz(String nombre,double datos[][]) {
        Array2DRowRealMatrix matriz = new Array2DRowRealMatrix(datos);
        if (nombre == "Marking"){
            ArrayRealVector v = new ArrayRealVector(matriz.getRow(1));
            this.vectores.put(nombre,v);
        }
        else if (nombre == "Enabled transitions"){
            ArrayRealVector v = new ArrayRealVector(matriz.getRow(0));
            this.vectores.put(nombre,v);
        }
        else {
            this.matrices.put(nombre, matriz);
        }
    }

    public void eliminarMatriz(String nombre) {
        this.matrices.remove(nombre);
    }

    public void eliminarVector(String nombre) {
        this.vectores.remove(nombre);
    }

    public HashMap<String, Array2DRowRealMatrix> getMatrices() {
        return matrices;
    }

    public HashMap<String, ArrayRealVector> getVectores() {
        return vectores;
    }
}
