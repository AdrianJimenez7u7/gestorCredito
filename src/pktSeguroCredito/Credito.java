
package pktSeguroCredito;

public class Credito {
    protected int noCredito;
    protected String cliente;

    public Credito(int noCredito, String cliente) {
        this.noCredito = noCredito;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "No. de credito: " + noCredito + "\nCliente: " + cliente;
    }
}



