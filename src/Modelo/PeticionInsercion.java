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
import Utils.Utils;

/**
 *
 * @author Daniel
 */
public class PeticionInsercion extends Peticion implements Serializable {

    private Archivo archivo;
    private String nombreGeneralArchivo;
    
    public PeticionInsercion(String nombrePeticion) {
        super(nombrePeticion);
    }
    
    public PeticionInsercion(String nombrePeticion, Archivo archivo) {
        super(nombrePeticion);
        this.archivo = archivo;
        this.nombreGeneralArchivo = archivo.getNombre() + "." + archivo.getExtension();

    }

    @Override
    public boolean ejecutarPeticion(){
        
        String directorio = "C:/Archivos-SD/";
        
        try {
            File archivoLocal = new File(directorio+this.nombreGeneralArchivo);

            if (!archivoLocal.exists()) {
                    archivoLocal.createNewFile();
            }
            else{
                // Hace una copia del archivo con nombre - copia "n", donde n es la cantidad de arhicvos iguales
                File archivosEnDirectorio = new File(directorio);
                int cuenta = 0;
                for(File archivoInterno : archivosEnDirectorio.listFiles()){
                    if(archivoInterno.getName().equals(this.archivo.getNombre()))
                        cuenta++;
                
                }
                archivoLocal = new File(directorio+this.archivo.getNombre()+" - copia "+String.valueOf(cuenta)+"."+this.archivo.getExtension());   
            }

            FileWriter fw = new FileWriter(archivoLocal.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.archivo.getContenido());
            bw.close();

            Utils.listaArchivos.getLista().add(this.archivo);
            System.out.println("Se inserto el archivo: " + this.archivo.getNombre() + "\n");

            return true;
 
        } catch (IOException e) {
                e.printStackTrace();
        } catch (Exception ex){
            System.out.println("Ocurrio un error en la creacion del archivo.");
        }
        
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
