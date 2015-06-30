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
public class Equipo implements Serializable {
    private String ip;
    private int puerto;
    private int numero;
    private ListaArchivos archivos;
    
    public Equipo(String ip, int puerto,  int numero, ListaArchivos archivos) {
        this.archivos = archivos;
        this.ip = ip;
        this.puerto = puerto;
        this.numero = numero;
    }
    
    public Equipo(){
        
    }

    public ListaArchivos getArchivos() {
        return archivos;
    }

    public void setArchivos(ListaArchivos archivos) {
        this.archivos = archivos;
    }

    // GET y SET
    public String getIp() {
        return ip;
    }

    public int getPuerto() {
        return puerto;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
