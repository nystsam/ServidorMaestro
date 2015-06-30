/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Utils.Utils;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Daniel
 */
public class DaemonUnionEsclavos extends Thread {

    public DaemonUnionEsclavos() {

    }
    
    @Override
    public void run(){
    
        ServerSocket ss;
        Socket so;
        try{
            
            ss = new ServerSocket(Utils.puertoUnionEsclavos);
            while (true){
                
                System.out.println("Esperando petciones de union de esclavos por el puerto: " + Utils.puertoUnionEsclavos);
                so = ss.accept();
                System.out.println( "Peticion de union recibida de: " +  so.getInetAddress().getHostAddress()+"\n");
                
                // Atender solicitud 
                UnirEsclavo nuevoEsclavo = new UnirEsclavo(so);
                nuevoEsclavo.start();
                
            }
        }catch(Exception e ){
            this.start();
        }
        
    }
    
    
}
