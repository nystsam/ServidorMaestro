/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Utils.Utils;
import java.io.InputStream;
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
            
            ss = new ServerSocket(Utils.puertoPeticionCliente);
            while (true){
                
                System.out.println("Esperando petciones del cliente por el puerto: " + Utils.puertoPeticionCliente);
                so = ss.accept();
                InputStream is = so.getInputStream();
                System.out.println( "Peticion de cliente recibida de: " +  so.getInetAddress().getHostAddress()+"\n");
                
                // Atender solicitud
                PeticionesCliente peticion = new PeticionesCliente(so);
                peticion.start();
                
                
            }
        }catch(Exception e ){
            this.start();
        }
        
    }
    
}
