package pktSeguroCredito;

public abstract class Seguro implements Pagos{
    
    protected int noPoliza;
    protected String asegurado;

    public Seguro(int noPoliza, String asegurado) {
        this.noPoliza = noPoliza;
        this.asegurado = asegurado;
    }
    
    @Override
    public double obtenerPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return ("No. de poliza: " + noPoliza + " Asegurado por: " + asegurado);
    } 

    public int getNoPoliza() {
        return noPoliza;
    }

    public String getAsegurado() {
        return asegurado;
    }
    
}



