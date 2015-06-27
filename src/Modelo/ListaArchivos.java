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
public class ListaArchivos implements Serializable {
    private ArrayList<Archivo> lista;

    public ListaArchivos(ArrayList<Archivo> lista) {
        this.lista = lista;
    }
    
    public void agregarArchivo(Archivo archivo){
        
        this.lista.add(archivo);
        
    }

    public void eliminarArchivo(int pos){
        
        this.lista.remove(pos);
        
    }
    
    public void eliminarArchivo(Archivo archivo){
        
        this.lista.remove(archivo);
        
    }
    
    public Archivo buscarArchivo(Archivo archivo){
        
        Iterator<Archivo> ite = this.lista.iterator();
           
        while (ite.hasNext())
        {
            Archivo ar = ite.next();
            if(ar.getNombre().equals(archivo.getNombre()))
                return ar;
        }
        return null;
        
    }
    
    // GET y SET
    public ArrayList<Archivo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Archivo> lista) {
        this.lista = lista;
    }
}