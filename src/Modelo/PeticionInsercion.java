/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Daniel
 */
public class PeticionInsercion extends Peticion implements Serializable {

    private String archivo;
    private String texto;
    
    private String nombre;
    private String extension;
    
    public PeticionInsercion(String nombrePeticion) {
        super(nombrePeticion);
    }
    
    public PeticionInsercion(String nombrePeticion, String nombreArchivo, String extension, String texto) {
        super(nombrePeticion);
        this.nombre = nombreArchivo;
        this.extension = extension;
        
        this.archivo = nombreArchivo + "." + extension;
        this.texto = texto;
    }

    @Override
    public boolean ejecutarPeticion(){
        
        String directorio = "C:/Archivos-SD/";
        
        try {
            File archivoLocal = new File(directorio+this.archivo);

            if (!archivoLocal.exists()) {
                    archivoLocal.createNewFile();
            }
            else{
                // Hace una copia del archivo con nombre - copia "n", donde n es la cantidad de arhicvos iguales
                File archivosEnDirectorio = new File(directorio);
                int cuenta = 0;
                for(File archivoInterno : archivosEnDirectorio.listFiles()){
                    if(archivoInterno.getName().equals(this.archivo))
                        cuenta++;
                
                }
                archivoLocal = new File(directorio+this.nombre+" - copia "+String.valueOf(cuenta)+"."+this.extension);   
            }

            FileWriter fw = new FileWriter(archivoLocal.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.texto);
            bw.close();

            System.out.println("Se inserto el archivo: " + this.archivo + "\n");
            return true;
 
        } catch (IOException e) {
                e.printStackTrace();
        } catch (Exception ex){
            System.out.println("Ocurrio un erro en la creacion del archivo.");
        }
        
        return false;
    }
    
    
    public String getTexto() {
        return texto;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    
    
}
