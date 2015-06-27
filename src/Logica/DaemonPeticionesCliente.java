/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Recursos.Util;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Daniel
 */
public class DaemonPeticionesCliente extends Thread {
    
    public DaemonPeticionesCliente(){
    
    }

    @Override
    public void run(){
        
        ServerSocket ss;
        Socket so;
        try{
            
            ss = new ServerSocket(Util.puerto);
            while (true){
                
                System.out.println("Esperando petciones del cliente por el puerto: " + Util.puerto);
                so = ss.accept();
                System.out.println( "Peticion recibida de: " +  so.getInetAddress().getHostAddress());
                
                // Atender solicitud 
                
                
            }
        }catch(Exception e ){
            this.start();
        }
        
    }
    
}
