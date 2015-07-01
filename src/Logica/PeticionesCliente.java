/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Archivo;
import Modelo.Equipo;
import Modelo.ListaArchivos;
import Modelo.Peticion;
import Modelo.PeticionEliminacion;
import Modelo.PeticionInscripcion;
import Modelo.PeticionInsercion;
import Modelo.PeticionModificacion;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.Utils;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
            Peticion peticion = null;
            Archivo archivo;
            String respuesta = "";
            
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
                    archivo = new Archivo(datos[0],datos[1], texto);
                    PeticionModificacion modificacion = new PeticionModificacion(tipo, archivo);
                    peticion = modificacion;
                    break;
                    
                // Eliminar
                case "E":
                    archivo = new Archivo(datos[0],datos[1], texto);
                    PeticionEliminacion eliminacion = new PeticionEliminacion(tipo, archivo);
                    peticion = eliminacion;
                    break;
                                     
            }
            
            if(this.enviarAlEquipo(peticion, equipoRemoto)){
                respuesta = "Ok";
            }
            
            DataOutputStream output = new DataOutputStream(this.so.getOutputStream());
            output.writeUTF(respuesta);
            
            NotificacionCambios notificacion = new NotificacionCambios();
            notificacion.notificar();
            
            
        } catch (IOException ex) {
            Logger.getLogger(PeticionesCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e){
            System.out.println("Error, objetos distintos.");
        } catch (Exception exe){
            System.out.println("\n Error en el comando o nombre del archivo \n");
        }
        
        
  
    }
    
    private boolean enviarAlEquipo(Peticion peticion, String equipoRemoto) throws ClassNotFoundException{
        
        Equipo equipo = null;
        Socket soEsclavo;
        int pos;
        for(pos = 0; pos < Utils.listaEquipos.getLista().size(); pos++){
            
            equipo = Utils.listaEquipos.getLista().get(pos);
            if(equipo.getNumero() == Integer.parseInt(equipoRemoto)){
                break;
            }  
        }
        
        if(equipo != null){
            try {
                soEsclavo = new Socket(equipo.getIp(), equipo.getPuerto());
                ObjectOutputStream output = new ObjectOutputStream(soEsclavo.getOutputStream());

                output.writeObject(peticion);
                output.flush();

                ObjectInputStream input = new ObjectInputStream(soEsclavo.getInputStream());
                ListaArchivos listaActualizada = (ListaArchivos)input.readObject();
                
                Utils.listaEquipos.getLista().get(pos).setArchivos(listaActualizada);

                soEsclavo.close();
                return true;

            } catch (IOException ex) {
                System.out.println("Error al avisar al esclavo.");
            }
        }
        
        
        return false;
        
    }
    
    
}
