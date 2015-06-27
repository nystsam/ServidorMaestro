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
 * @author Daniel
 */
public class ListaEquipos implements Serializable {

    private ArrayList<Equipo> lista;

    public ListaEquipos(ArrayList<Equipo> lista) {
        this.lista = lista;
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
    
    public Equipo buscarEquipo(int numeroEquipo){
        
        Equipo eq;
        
        for(Iterator<Equipo> ite = this.lista.iterator(); ite.hasNext(); ){
            
            eq = ite.next();
            if(eq.getNumero() == numeroEquipo){
                return eq;
            }
                
        }
        
        return null;
    }
    
    // GET y SET
    public ArrayList<Equipo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Equipo> lista) {
        this.lista = lista;
    }
    
    
    
}
