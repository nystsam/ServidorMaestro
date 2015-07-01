/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Red;
import Modelo.Response;
import Utils.Utils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAB_A112
 */
public class NotificacionCambios {
    
    public NotificacionCambios(){
        
    }
    
    public byte[] ObjectToByteArray(Response response) throws IOException{
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
    
    
    public void notificar(){
    
        Response resp;
        
        try {
        InetAddress group = InetAddress.getByName("225.4.5.15");

        MulticastSocket multicastSock = new MulticastSocket();
        
        Red red = new Red(Utils.red,Utils.listaEquipos);
        red.setPuerto(Utils.puertoPeticionCliente);
        resp = new Response(red, "");
        
        byte[] objeto = this.ObjectToByteArray(resp);
        DatagramPacket packet = new DatagramPacket(objeto,objeto.length, group,Utils.PuertoClient);
        multicastSock.send(packet);
        multicastSock.close();
            
        } catch (SocketException ex) {
            Logger.getLogger(NotificacionCambios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NotificacionCambios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
