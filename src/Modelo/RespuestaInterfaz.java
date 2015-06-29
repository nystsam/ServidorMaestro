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
public class RespuestaInterfaz implements Serializable{
    
    private String Red;
    private String Equipo;
    private String Mensaje;

    public RespuestaInterfaz(String Red, String Equipo, String Mensaje) {
        this.Red = Red;
        this.Equipo = Equipo;
        this.Mensaje = Mensaje;
    }

    public String getRed() {
        return Red;
    }

    public void setRed(String Red) {
        this.Red = Red;
    }

    public String getEquipo() {
        return Equipo;
    }

    public void setEquipo(String Equipo) {
        this.Equipo = Equipo;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }
    
}
