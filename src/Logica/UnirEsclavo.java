/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Equipo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.Utils;
import java.io.ObjectOutputStream;

/**
 *
 * @author Daniel
 */
public class UnirEsclavo extends Thread {
    
    private Socket so;

    public UnirEsclavo(Socket so) {
        this.so = so;
    }
    
    @Override
    public void run(){
        
        try {
            ObjectInputStream input = new ObjectInputStream(this.so.getInputStream());
            
            Equipo equipoNuevo = (Equipo) input.readObject();
            equipoNuevo.setIp(this.so.getInetAddress().getHostAddress());
            equipoNuevo.setNumero(Utils.listaEquipos.getLista().size() + 1);
            
            ObjectOutputStream output = new ObjectOutputStream(this.so.getOutputStream());
            String respuesta = "";
            
            if(Utils.listaEquipos.getLista().add(equipoNuevo))
                respuesta = "Ok";
            
            output.writeUTF(respuesta);
            output.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(UnirEsclavo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UnirEsclavo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
