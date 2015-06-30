/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.DaemonClienteMulticast;
import Logica.DaemonEsclavosMulticast;
import Logica.DaemonUnionEsclavos;

/**
 *
 * @author Daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        DaemonUnionEsclavos listadoEsclavos = new DaemonUnionEsclavos();
        listadoEsclavos.start();
        
        DaemonEsclavosMulticast esclavosBroadcast = new DaemonEsclavosMulticast();
        esclavosBroadcast.start();
        
        DaemonClienteMulticast clientesBroadcast = new DaemonClienteMulticast();
        clientesBroadcast.start();
        
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
        
    }
    
}
