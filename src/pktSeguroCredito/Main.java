package pktSeguroCredito;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static int opc1 = 0;
    static ArrayList <Pagos> arregloPagos = new ArrayList<>();
    static Scanner teclado = new Scanner(System.in);
    static ManejoArchivo objArchivo = new ManejoArchivo(arregloPagos);
    public static void main(String[] args) {
        objArchivo.leerArchivo();
        while(opc1 != 5){
            try{
                System.out.println("Opciones:"
                        + "\n1. Agregar "
                        + "\n2. Obtener pago "
                        + "\n3. Imprimir datos "
                        + "\n4. Eliminar "
                        + "\n5. Salir");
                System.out.print("Ingrese la opción: ");
                opc1 = teclado.nextInt();

                switch(opc1){
                    case 1:
                        subMenuAgregar(); 
                        break;
                    case 2:
                        subMenuObtenerPago();
                        break;
                    case 3:
                        imprimirTodo(0); 
                        break;
                    case 4:
                        int pos = -1;
                        imprimirTodo(0);
                        while(pos <= -1 || pos>arregloPagos.size()){
                            System.out.println("Ingresa la posición: ");
                            pos = teclado.nextInt(); 
                        }
                        arregloPagos.remove(pos);
                        System.out.println("Se ha eliminado");
                        break;
                    default:
                    if(opc1 == 5){
                        objArchivo.escribeArchivo();
                        System.out.println("Cerrando sesion...");
                    }
                }//fin switch
            }catch(InputMismatchException e){
                System.out.println("\033[33m[AVISO] TIPO DE DATO INCORRECTO");
                teclado.next();
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println(e.getMessage());
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }//fin while 
    }//fin main
    
    private static void subMenuAgregar(){
        int opc2 = 0;
        
        while (opc2 != 3) {     
            System.out.println("--------------------------"
                + "\n1. Agregar seguro auto"
                + "\n--------------------------"
                + "\n2. Agregar crédito hipotecario"
                + "\n--------------------------"
                + "\n3. Salir"
                + "\n--------------------------");
        System.out.print("Ingrese la opción: ");
        opc2 = teclado.nextInt();
        
            switch(opc2){
                case 1:
                    agregarSeguroAuto(); break;
                case 2:
                    agregarCreditoHipo(); break;
                default:
                if(opc2 == 3){
                    System.out.println("Regresando...");
                }    
            }
        }   
    }//Fin submenu agregar
    
    private static void agregarSeguroAuto(){
        int noPoliza = 0, modelo;
        String asegurado, vehiculo;
        System.out.print("Ingresa el No. Poliza: ");
        try{
        noPoliza = teclado.nextInt();
        }catch(InputMismatchException e){
            System.out.println("\033[33m[AVISO] TIPO DE DATO INCORRECTO (SOLO INGRESE DATOS ENTEROS)");    
        }
        System.out.print("Ingresa el nombre del asegurado: ");
        teclado.nextLine();
        asegurado = teclado.nextLine();
        System.out.print("Ingresa el nombre del vehículo: ");
        vehiculo = teclado.nextLine();
        System.out.print("Ingresa el modelo del vehículo: ");
        modelo = teclado.nextInt();

        arregloPagos.add(new SeguroAuto(noPoliza, asegurado, vehiculo, modelo));
    }//Fin agregarSeguro
    
    private static void agregarCreditoHipo(){
        int noCliente;
        String cliente;
        double saldo;
        System.out.print("Ingresa el No. Cliente: ");
        noCliente = teclado.nextInt();
        System.out.print("Ingresa el nombre del cliente: ");
        teclado.nextLine();
        cliente = teclado.nextLine();
        System.out.print("Ingresa el saldo: ");
        saldo = teclado.nextDouble();

        arregloPagos.add(new CreditoHipotecario(noCliente, cliente, saldo));
    }//Fin agregarCreditoHipo
    
    private static void subMenuObtenerPago() throws ArrayIndexOutOfBoundsException{
        Pagos comprobar;
        int contador = 0, pos = -1;
        while(opc1 != 4){
            System.out.println("Opciones:\n1. Seguro auto "
                    + "\n2. Crédito hipotecario "
                    + "\n3. Todos\n4. Regresar");
            System.out.print("Ingresa la opción: ");
            opc1 = teclado.nextInt();

            switch(opc1){
            case 1:
                for(Pagos obtener: arregloPagos ){
                    if(obtener instanceof SeguroAuto){
                        System.out.println("[" + contador + "] Seguro auto:\n" + obtener);
                    }
                    contador++;
                }
                while(pos<0 || pos>=arregloPagos.size()){
                   System.out.println("Ingresa la posición del elemento que deseas obtener el pago: ");
                    pos = teclado.nextInt(); 
                }
                comprobar = arregloPagos.get(pos);
                if(comprobar instanceof SeguroAuto){
                    System.out.println("El monto de pago por el seguro del vehiculo es: " + comprobar.obtenerPago());
                }else{
                    System.out.println("Ingresa una posición correcta");
                    subMenuObtenerPago();
                }
                contador = 0;
                pos=-1;
                break;
            case 2:
                for(Pagos obtener: arregloPagos ){
                    if(obtener instanceof CreditoHipotecario){
                        System.out.println("[" + contador + "] Crédito hipotecario:\n" + obtener);
                    }
                    contador++;
                }
                while(pos<0 || pos>=arregloPagos.size()){
                   System.out.println("Ingresa la posición del elemento que deseas obtener el pago: ");
                    pos = teclado.nextInt(); 
                }
                comprobar = arregloPagos.get(pos);
                if(comprobar instanceof CreditoHipotecario){
                    System.out.println("Saldo actual: " + comprobar.obtenerPago());
                }else{
                    throw new ArrayIndexOutOfBoundsException("\033[33mIngresa una posición correcta");
                }
                contador = 0;
                pos =- 1;
                break;
            case 3:
                imprimirTodo(1);
                break;
            }
        }
        opc1=0;
    }//Fin subMenuObtenerPago
    
    private static void imprimirTodo(int validar){
        int cont = 0;
       for(Pagos obtener: arregloPagos ){
            if(obtener instanceof CreditoHipotecario){
                System.out.println("[" + cont + "] Crédito hipotecario:"
                        + "\n" + obtener);
                if(validar == 1){
                    System.out.println("Saldo actual: " + obtener.obtenerPago());
                }
            }else{
                System.out.println("[" + cont + "] Seguro auto:"
                        + "\n" + obtener); 
                if(validar == 1){
                    System.out.println("El total de pago por el seguro del vehiculo es: " + obtener.obtenerPago());
                }
            }
            cont++;
        } 
    }
}//Fin imprimirTodo
