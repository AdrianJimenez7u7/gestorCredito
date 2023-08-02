/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pktSeguroCredito;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author ANAII
 */
public class ManejoArchivo {
    private ArrayList<Pagos> arreglo;
    private File archivo;
    private String cadena;
    private BufferedReader entrada;

    public ManejoArchivo(ArrayList<Pagos> arreglo) {
        this.arreglo=arreglo;
        archivo=new File("archivoCreditoSeguro.txt");
    }
    
    private String getDatosSeguroAuto(Pagos pago){
       String cadCta;
       if(pago instanceof SeguroAuto){
       cadena=((SeguroAuto) pago).getNoPoliza() + ","
               + ((SeguroAuto) pago).getAsegurado() + ","
               + ((SeguroAuto) pago).getVehiculo() + ","
               + ((SeguroAuto) pago).getModelo();
       }else if(pago instanceof CreditoHipotecario){
           cadena=((CreditoHipotecario) pago).getNoCredito() + ","
                   + ((CreditoHipotecario) pago).getCliente() + ","
                   + ((CreditoHipotecario) pago).getSaldo();
       }
       return cadena;
    }//fin getDatosCuenta
    
    public void escribeArchivo(){
        PrintWriter escribir=null;
        try {
            //Abre el archivo
            escribir=new PrintWriter(new FileWriter(archivo));
            //Recorro todo el ArrayList
            for (int ren=0;ren<arreglo.size();ren++){
                //Obtengo los datos de un registro del arreglo
                cadena=getDatosSeguroAuto(arreglo.get(ren));
                //Almacena el registro en el archivo
                escribir.println(cadena);
            }//fin for
            //Agrego un 0 en el archivo para limitar la cantidad 
            //de registros
            escribir.println("0");
            if (escribir!=null){
                //Cierra el archivo
                escribir.close();
                System.out.println("Datos almacenados en el archivo");
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }//fin catch        
    }//fin escribeArchivo
    
    private Pagos getPagosArchivo(String ren){
        String fila[]=ren.split(",");
        int noPoliza, modelo,noCredito ;
        double saldo;
        String asegurado, vehiculo, nombre;
        if (fila.length==3){//Cuenta
            noCredito=Integer.parseInt(fila[0]);
            nombre=fila[1];
            saldo=Double.parseDouble(fila[2]);
            return new CreditoHipotecario(noCredito, cadena, saldo);
        }else{ //seguro auto
            noPoliza=Integer.parseInt(fila[0]);
            asegurado=fila[1];
            vehiculo= fila[2];
            modelo=Integer.parseInt(fila[3]);
            return new SeguroAuto(noPoliza, asegurado, vehiculo, modelo);
        }
    }//fin getCuentaArchivo
    
    public void leerArchivo(){
        try{
            //limpiar arreglo
            //arreglo.clear();
            //Crear buffer leyendo el archivo
            entrada=new BufferedReader(new FileReader(archivo));
            //posiciona en la primer linea del archivo
            cadena=entrada.readLine();
            while(!"0".equals(cadena)){
                //Agrega al arreglo un objeto
                //almacenada en el archivo
                arreglo.add(getPagosArchivo(cadena));
                cadena=entrada.readLine();
            }//fin while
            if (entrada!=null){
                entrada.close(); //Cierra el archivo
                System.out.println("Archivo cargado al arreglo");
            }//fin if
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }//fin catch
    }//fin leerArchivo
    
}//fin clase
