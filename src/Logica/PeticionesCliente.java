/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Peticion;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class PeticionesCliente extends Thread {
    
    private final Socket so;
    
    public PeticionesCliente(Socket so){
        
        this.so = so;
        
    }
    
    @Override
    public void run(){
          
        try {
            DataInputStream input = new DataInputStream(this.so.getInputStream());
            Peticion peticion;
            
            String [] comando = input.readUTF().split(" ", 2);
            String [] datos = comando[1].split(".");
            String texto = comando[2];
            String tipo = comando[0];
            
            switch(tipo){
                
                // Insertar
                case "i":
                    //peticion = new Peticion(tipo, )
                    break;
                
                // Modificar
                case "m":
                    
                    break;
                    
                // Eliminar
                case "e":
                    
                    break;
                    
                // Leer
                case "l":
                    
                    break;                    
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(PeticionesCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

            
        
    }
    
    
}
