/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author desarrollo
 */
public class ListaEquipos implements Serializable {
    
    private ArrayList<Equipo> lista;

    public ListaEquipos(ArrayList<Equipo> lista) {
        this.lista = lista;
    }
    public ListaEquipos() {
        this.lista = new ArrayList<Equipo>();
    }
    
    public void agregarEquipo(Equipo equipo){
        
        this.lista.add(equipo);
        
    }

    public void eliminarEquipo(int pos){
        
        this.lista.remove(pos);
        
    }
    
    public void eliminarEquipo(Equipo equipo){
        
        this.lista.remove(equipo);
        
    }
    
    public String [] buscarEquipo(int numeroEquipo){
        
        String [] info = new String[2];
        
        for(Iterator<Equipo> ite = this.lista.iterator(); ite.hasNext(); ){
            
            Equipo eq = ite.next();
            if(eq.getNumero() == numeroEquipo){
                info[0] = eq.getIp();
                info[1] = Integer.toString(eq.getPuerto());
            }
                
        }
        
        return info;
    }
    
    // GET y SET
    public ArrayList<Equipo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Equipo> lista) {
        this.lista = lista;
    }

}
