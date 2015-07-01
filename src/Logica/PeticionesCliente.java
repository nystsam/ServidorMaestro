/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Archivo;
import Modelo.Peticion;
import Modelo.PeticionInsercion;
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
            Archivo archivo;
            
            String [] comando = input.readUTF().split(" ", 2);
            String [] datos = comando[1].split("\\.");
            String [] datosInterno = datos[3].split(" ", 2);
            String texto = datosInterno[1];
            String equipoRemoto = datosInterno[0];
            String tipo = comando[0];
            
            switch(tipo){
                
                // Insertar
                case "I":
                    archivo = new Archivo(datos[0],datos[1], texto);
                    PeticionInsercion insercion = new PeticionInsercion(tipo, archivo);
                    peticion = insercion;
                    break;
                
                // Modificar
                case "M":
                    
                    break;
                    
                // Eliminar
                case "E":
                    
                    break;
                                     
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(PeticionesCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

            
        
    }
    
    
}
