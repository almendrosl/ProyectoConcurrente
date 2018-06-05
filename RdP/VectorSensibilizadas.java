package RdP;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class VectorSensibilizadas {

    private ArrayRealVector v;
    private boolean esperando;
    private SensibilizadoConTiempo tiempo;
    private Array2DRowRealMatrix mI;
    private Array2DRowRealMatrix mH;

    public VectorSensibilizadas(ArrayRealVector v, Array2DRowRealMatrix mI, Array2DRowRealMatrix mH){

        this.v = v;
        this.mI = mI;
        this.mH = mH;
        tiempo = new SensibilizadoConTiempo();
    }

    /*
        Este metodo destermina si la transicion que se desea disparar esta sensibilizada.
     */
    public boolean estaSensibilizado(ArrayRealVector disp){
        return !(v.dotProduct(disp) == 0);
    }

    /*
        Este metodo actualiza las transiciones sensibilizadas haciendo uso de las matriz de Incidencia y la matriz de
        Inhibicion. POR AHORA SIN IMPLEMENTACION DE TIEMPO.
     */
    public void actualiceSensibilizadoT(ArrayRealVector marcado){
        int cantT = mI.getColumnDimension();
        int cantP = mI.getRowDimension();
        for (int i=0; i<cantT; i++){
            ArrayRealVector ti = new ArrayRealVector(mI.getColumnVector(i));
            for (int j = 0;i<cantP;i++){
                if ((marcado.getEntry(j) - ti.getEntry(j)) >= 0)
                    v.setEntry(i,1);
                else
                    v.setEntry(i,0);
            }
        }
        for (int i=0; i<cantT; i++){
            ArrayRealVector ti = new ArrayRealVector(mH.getColumnVector(i));
            for (int j = 0;i<cantP;i++){
                if (ti.getEntry(j)>0 && marcado.getEntry(j) - ti.getEntry(j) < 0)
                    v.setEntry(i,1);
                else
                    v.setEntry(i,0);
            }
        }
    }

    public ArrayRealVector getV(){return v;}

}
