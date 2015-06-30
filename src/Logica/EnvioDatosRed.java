/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Red;
import Modelo.Response;
import Utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

/**
 *
 * @author LAB_A112
 */
public class EnvioDatosRed extends Thread {
    
    private String ipCliente;
    private int puertoCliente;

    public EnvioDatosRed(String ipCliente, int puertoCliente) {
        this.ipCliente = ipCliente;
        this.puertoCliente = puertoCliente;
    }
    
    public static byte[] ObjectToByteArray(Response response) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
          out = new ObjectOutputStream(bos);   
          out.writeObject(response);
          byte[] yourBytes = bos.toByteArray();
          return yourBytes;
        } finally {
            try {
              if (out != null) {
                out.close();
              }
            } catch (IOException ex) {
            // ignore close exception
            }
            try {
              bos.close();
            } catch (IOException ex) {
              // ignore close exception
            }
        }
    }
    
    private Red obtenerDatosRed(){
        
        Red red = new Red(Utils.red,Utils.listaEquipos);
        
        if(red.getNombre() != null){
            return red;
        }
            
        return null;
    }
    
    
    @Override
    public void run(){
        
        Socket so;
        try {
            so = new Socket(this.ipCliente, this.puertoCliente);
            DataOutputStream output = new DataOutputStream(so.getOutputStream());
            
            Response datosRed = new Response(this.obtenerDatosRed(), "");
            byte[] objeto = ObjectToByteArray(datosRed);
            output.write(objeto);
            output.flush();

            so.close();
            
        } catch (IOException ex) {
            System.out.println("Error al avisar al cliente.");
        }
    }

}
