package RdP;

import org.apache.commons.math3.linear.ArrayRealVector;

public class SensibilizadoConTiempo {

    private double[][] ventanas;
    private double[] tiempoSensibilizada;

    public SensibilizadoConTiempo(){
        //recordar inicializar
    }

    public void setNuevoTimeStamp(ArrayRealVector vecSen){
        for(int i=0;i<vecSen.getDimension();i++){
            if (vecSen.getEntry(i)==1)
                tiempoSensibilizada[i] = System.currentTimeMillis();
            else
                tiempoSensibilizada[i]=0;
        }
    }

    public boolean testVentanaTiempo(ArrayRealVector disp){
        double tiempoActual = System.currentTimeMillis();
        boolean r = true;
        for(int i=0;i<disp.getDimension();i++){
            if (disp.getEntry(i)==1) {
                double tiempoTotal = tiempoActual - tiempoSensibilizada[i];
                if (ventanas[i][0] < tiempoTotal && tiempoTotal<ventanas[i][1])
                    r=true;
            }
            else
                r=false;
        }
        return r;
    }
}
