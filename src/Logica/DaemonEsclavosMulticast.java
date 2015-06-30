/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Utils.Utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author Daniel
 */
public class DaemonEsclavosMulticast extends Thread {
    
    public DaemonEsclavosMulticast(){
        
    }
    
    @Override
    public void run(){
     
        try { 
            InetAddress grupo = InetAddress.getByName("226.4.5.6");
            MulticastSocket socket = new MulticastSocket(Utils.puertoEsclavosMulticast);

            // Se une al grupo
            socket.joinGroup(grupo);
            byte[] bufer = new byte[1000];

            // Se queda a la espera de mensajes al grupo, hasta recibir "Adios"
            while (true) {
                
                String respuesta;
                DatagramPacket mensajeEntrada = new DatagramPacket(bufer, bufer.length);
                System.out.println("Esperando multicast de esclavos por el puerto: "+String.valueOf(Utils.puertoEsclavosMulticast));
                socket.receive(mensajeEntrada);

                respuesta = new String(mensajeEntrada.getData(), 0, mensajeEntrada.getLength());
                
                System.out.println("Recibido el host: "+ mensajeEntrada.getAddress().getHostAddress() + " con puerto de escucha: " + respuesta+"\n");

                EnvioRed envio = new EnvioRed(mensajeEntrada.getAddress().getHostAddress(), Integer.parseInt(respuesta));
                envio.start();
           }

        } catch (SocketException e) {
             System.out.println("Socket:" + e.getMessage());
        } catch (IOException e) {
          System.out.println("IO:" + e.getMessage());
        }
        
    }
    
}
