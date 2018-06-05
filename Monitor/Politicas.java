package Monitor;

import org.apache.commons.math3.linear.*;

public class Politicas {
    private Array2DRowRealMatrix matrizDePrioridades;

    public Politicas(ArrayRealVector vectorPrioridades) {
        hacerMatrizDePrioridades(vectorPrioridades);
    }

    private void hacerMatrizDePrioridades(ArrayRealVector vectorPrioridades) {
        matrizDePrioridades = new Array2DRowRealMatrix(vectorPrioridades.getDimension(), vectorPrioridades.getDimension());
        for (int i = 0; i < matrizDePrioridades.getRowDimension(); i++) {
            for (int j = 0; j < matrizDePrioridades.getColumnDimension(); j++) {
                if (vectorPrioridades.getEntry(i) == j) {
                    matrizDePrioridades.setEntry(i, j, 1);
                } else {
                    matrizDePrioridades.setEntry(i, j, 0);
                }
            }
        }
    }

    public int masPrioridad(ArrayRealVector hilosPosiblesADisparar) {
        RealVector vectorPrioritario = matrizDePrioridades.operate(hilosPosiblesADisparar);
        int num = 0;
        for (int i = vectorPrioritario.getDimension()-1; i >= 0; i--) {
            if (vectorPrioritario.getEntry(i) > 0) {
                num = i;
            }
            vectorPrioritario.setEntry(i, 0);
        }
        RealVector transPrioritaria = matrizDePrioridades.getRowVector(num);
        return transPrioritaria.getMaxIndex();
    }

    public void setPolitica(ArrayRealVector vectorPrioridades) {
        hacerMatrizDePrioridades(vectorPrioridades);
    }

    public Array2DRowRealMatrix getMatrizdePrioridades(){
        return matrizDePrioridades;
    }
}
