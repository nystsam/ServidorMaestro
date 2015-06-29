/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Response;
import Utils.Utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAB_A112
 */
public class DaemonClienteMulticast extends Thread{
    
    public DaemonClienteMulticast(){
        
    }
    
    private Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        } 
    }
   
    @Override
    public void run(){
    
        try { 
            InetAddress grupo = InetAddress.getByName("225.4.5.6");
            MulticastSocket socket = new MulticastSocket(Utils.puertoClienteMulticast);

            // Se une al grupo
            socket.joinGroup(grupo);
            byte[] bufer = new byte[1000];

            // Se queda a la espera de mensajes al grupo, hasta recibir "Adios"
            while (true) {
              DatagramPacket mensajeEntrada = new DatagramPacket(bufer, bufer.length);
              System.out.println("Esperando un cliente por el puerto: "+String.valueOf(Utils.puertoClienteMulticast));
              socket.receive(mensajeEntrada);
              //linea = new String(mensajeEntrada.getData(), 0, mensajeEntrada.getLength());
              Response respuesta = (Response)this.convertFromBytes(mensajeEntrada.getData());

              System.out.println("Recibido el host: "+ mensajeEntrada.getAddress().getHostAddress() + " con puerto de escucha: " + String.valueOf(respuesta.getPuertoCliente()));

              EnvioDatosRed enviar = new EnvioDatosRed(mensajeEntrada.getAddress().getHostAddress(), respuesta.getPuertoCliente());
              enviar.start();
           }


        } catch (ClassNotFoundException ex) {
                Logger.getLogger(DaemonClienteMulticast.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException e) {
                 System.out.println("Socket:" + e.getMessage());
        } catch (IOException e) {
          System.out.println("IO:" + e.getMessage());
        }
        
    }
    
    
}
