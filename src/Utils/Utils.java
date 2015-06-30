package Utils;

import Modelo.ListaEquipos;
import Modelo.ListaRedes;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Daniel
 */
public class Utils {
    
    public static int puertoPeticionCliente = 55000;
    public static int puertoUnionEsclavos = 55500;
    public static String red = "Red-B";
    public static ListaEquipos listaEquipos = new ListaEquipos();
    public static int puertoEsclavosMulticast = 56000;
    public static int puertoClienteMulticast = 56500;
    public static JFrame ventana;
    
    
    // No los necesita
    public static String IpServidor = "";
    public static int PuertoServidor  = 50001;
    public static int PuertoClient = 50002;
    public static boolean AplicacionIniciada = false;
    public static ListaRedes redes = new ListaRedes();
    public static ListaRedes listaRedes = new ListaRedes();
    public static String ipMaestroLlegada;
    public static JComboBox listaServidores;

    
    
    
}
