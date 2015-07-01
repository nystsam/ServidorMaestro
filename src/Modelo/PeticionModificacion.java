/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Utils.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Daniel
 */
public class PeticionModificacion extends Peticion implements Serializable {
    
    private Archivo archivo;
    private String nombreGeneralArchivo;
    
    public PeticionModificacion(String nombrePeticion) {
        super(nombrePeticion);
    }
    
    public PeticionModificacion(String nombrePeticion, Archivo archivo) {
        super(nombrePeticion);
        this.archivo = archivo;
        this.nombreGeneralArchivo = archivo.getNombre() + "." + archivo.getExtension();

    }

    @Override
    public boolean ejecutarPeticion(){
        
        String directorio = "C:/Archivos-SD/";
        // Indica si el arhivo esta siendo utilizado por otro usuario.
        if(!Utils.enUso){
        
            try {
                Utils.enUso = true;
                File archivoLocal = new File(directorio+this.nombreGeneralArchivo);

                if (archivoLocal.exists()) {
                    archivoLocal.createNewFile();
                    FileWriter fw = new FileWriter(archivoLocal.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(this.archivo.getContenido());
                    bw.close();

                    for(int i = 0; i < Utils.listaArchivos.getLista().size(); i++){

                        Archivo archivoAlmacenado = Utils.listaArchivos.getLista().get(i);
                        if(this.archivo.getNombre().equals(archivoAlmacenado.getNombre())){
                            Utils.listaArchivos.getLista().get(i).setContenido(this.archivo.getContenido());
                            break;
                        }               
                    }                 
                    System.out.println("Se modifico el archivo: " + this.nombreGeneralArchivo + "\n");
                    Utils.enUso = false;

                        return true;
                }
                
            } catch (IOException e) {
                    e.printStackTrace();
            } catch (Exception ex){
                System.out.println("Ocurrio un error en la modificacion del archivo.");
            }
        }
        
        Utils.enUso = false;
        return false;
        
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public String getNombreGeneralArchivo() {
        return nombreGeneralArchivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public void setNombreGeneralArchivo(String nombreGeneralArchivo) {
        this.nombreGeneralArchivo = nombreGeneralArchivo;
    }
    
    
}
