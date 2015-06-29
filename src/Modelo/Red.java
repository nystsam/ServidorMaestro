/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author desarrollo
 */
public class Red implements Serializable {
    private String nombre;
    private ListaEquipos listaEquipos;

    public Red(String nombre, ListaEquipos listaEquipos) {
        this.nombre = nombre;
        this.listaEquipos = listaEquipos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaEquipos getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(ListaEquipos listaEquipos) {
        this.listaEquipos = listaEquipos;
    }        
    
}
