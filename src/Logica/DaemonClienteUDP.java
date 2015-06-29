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
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAB_A112
 */
public class DaemonClienteUDP extends Thread{
    
    public DaemonClienteUDP(){
        
    }
    
    private Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
         ObjectInput in = new ObjectInputStream(bis)) {
        return in.readObject();
    } 
}
    
    
    public void run(){
    
        try { 
      InetAddress grupo = InetAddress.getByName("225.4.5.6");
      MulticastSocket socket = new MulticastSocket(Utils.puertoClienteUDP);

      // Se une al grupo
      socket.joinGroup(grupo);

      // Envia el mensaje
      //byte[] m = args[0].getBytes();
    //  DatagramPacket mensajeSalida =
	//new DatagramPacket(m, m.length, grupo, 6789);
      //socket.send(mensajeSalida);

      byte[] bufer = new byte[1000];

      // Se queda a la espera de mensajes al grupo, hasta recibir "Adios"
      while (true) {
	DatagramPacket mensajeEntrada = new DatagramPacket(bufer, bufer.length);
        System.out.println("Esperando cliente...");
	socket.receive(mensajeEntrada);
	//linea = new String(mensajeEntrada.getData(), 0, mensajeEntrada.getLength());
	Response respuesta = (Response)this.convertFromBytes(mensajeEntrada.getData());
        
        System.out.println("Recibido:" + respuesta.getRed().getNombre());

      }

      // Si recibe "Adios" abandona el grupo

    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(DaemonClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException e) {
      System.out.println("Socket:" + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO:" + e.getMessage());
    }
        
    }
    
    
}
