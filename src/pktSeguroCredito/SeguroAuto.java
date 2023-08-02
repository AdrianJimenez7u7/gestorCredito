package pktSeguroCredito;

public class SeguroAuto extends Seguro{
    private String vehiculo;
    private int modelo;
    private double seguroBase;

    public SeguroAuto(int noPoliza, String asegurado, String vehiculo, int modelo) {
        super(noPoliza, asegurado);
        this.vehiculo = vehiculo;
        this.modelo = modelo;
        this.seguroBase = 200;
    }

    @Override
    public double obtenerPago(){
        if (modelo < 2000) {
            seguroBase = (seguroBase * 2341);
        }else if (modelo > 2000 && modelo < 2009) {
            seguroBase = (seguroBase * 5227);
        }else if (modelo > 2010 && modelo < 2019) {
            seguroBase = (seguroBase * 8550);
        }else if (modelo > 2020) {
            seguroBase = (seguroBase * 10000);
        }
        return seguroBase;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public int getModelo() {
        return modelo;
    }

    
    @Override
    public String toString() {
        return super.toString() + " Vehiculo: " + vehiculo + " Modelo: " + modelo + " Seguro base: " + seguroBase;
    }
}



