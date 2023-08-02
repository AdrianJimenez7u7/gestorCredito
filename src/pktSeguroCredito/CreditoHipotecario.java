package pktSeguroCredito;

public class CreditoHipotecario extends Credito implements Pagos{
    private double saldo;

    public CreditoHipotecario(int noCredito, String cliente, double saldo) {
        super(noCredito, cliente);
        this.saldo = saldo;
    }
        
    @Override
    public double obtenerPago() {
        if(saldo >= 500){
            saldo -= 500;
        }else{
            System.out.println("Saldo insuficiente"); 
        }
        return saldo;
    }

    public int getNoCredito() {
        return noCredito;
    }

    public String getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }
  
    @Override
    public String toString() {
        return super.toString() + " Saldo: " + saldo; 
    }
}



