/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import Utils.Utils;

/**
 *
 * @author LAB_A112
 */
public class PeticionInscripcion extends Peticion implements Serializable {
    
    private String nombreRed;
    private int puertoMaestro;

    public PeticionInscripcion(String nombrePeticion, String nombreRed,int puertoMaestro) {
        super(nombrePeticion);
        this.nombreRed = nombreRed;
        this.puertoMaestro = puertoMaestro;
    }
    
    public PeticionInscripcion(String nombrePeticion){
        super(nombrePeticion);
    }
    
    @Override
    public boolean ejecutarPeticion(){
        
        Red red = new Red(this.nombreRed, null);
        
        red.setIp(Utils.ipMaestroLlegada);
        red.setPuerto(this.puertoMaestro);
        
        Utils.listaRedes.getLista().add(red);
        Utils.listaServidores.addItem(this.nombreRed);
        
        return true;
    }

    public String getNombreRed() {
        return nombreRed;
    }

    public int getPuertoMaestro() {
        return puertoMaestro;
    }

    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }

    public void setPuertoMaestro(int puertoMaestro) {
        this.puertoMaestro = puertoMaestro;
    }
    
    
    
}
