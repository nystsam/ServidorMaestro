/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Peticion;
import Modelo.PeticionInscripcion;
import Modelo.Response;
import Utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Daniel
 */
public class EnvioRed extends Thread{
    
    private String ipEsclavo;
    private int puertoEsclavo;

    public EnvioRed(String ipEsclavo, int puertoEsclavo) {
        this.ipEsclavo = ipEsclavo;
        this.puertoEsclavo = puertoEsclavo;
    }
        
    @Override
    public void run(){
        
        Socket so;
        try {
            so = new Socket(this.ipEsclavo, this.puertoEsclavo);
            ObjectOutputStream output = new ObjectOutputStream(so.getOutputStream());
            
            PeticionInscripcion redLocal = new PeticionInscripcion("Inscripcion",Utils.red, Utils.puertoUnionEsclavos);

            output.writeObject(redLocal);
            output.flush();

            so.close();
            
        } catch (IOException ex) {
            System.out.println("Error al avisar al cliente.");
        }
    }
    
}
