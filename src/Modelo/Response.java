/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.ListaEquipos;
import Modelo.ListaRedes;
import Modelo.Red;
import java.io.Serializable;

/**
 *
 * @author desarrollo
 */
public class Response implements Serializable {
    
    private Red red;
    private String error;

    public Response(Red red, String error) {
        this.red = red;
        this.error = error;
    }

    public Red getRed() {
        return red;
    }

    public void setRed(Red red) {
        this.red = red;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
