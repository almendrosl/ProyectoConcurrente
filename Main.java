import RdP.LectorHTML;
import RdP.RdP;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        LectorHTML documentoMatrices = null;
        try {
            documentoMatrices = new LectorHTML("C:/Users/flopp/Desktop/prueba.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(documentoMatrices.getMatricesEstadoInicial().getMatrices().toString());
        System.out.println(documentoMatrices.getMatricesEstadoInicial().getMatrices().get("Inhibition matrix H").getRowVector(1).toString());
        System.out.println("Init");
        //RdP petrinet = new RdP(documentoMatrices.getMatricesEstadoInicial());
        //System.out.println(petrinet.getVectorDeEstado().toString());
    }
}
