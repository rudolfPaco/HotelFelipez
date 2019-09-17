/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author rudolf
 */
public class Documento {
    private int id;
    private String nombre;
    private String url;
    private String tipo;
    private int idPersona;
    
    private BufferedImage buffer;
    private Image imagen;
    
    /**
     * es una clase que guarda algunos atributos de un archivo tipo, referentemente una imagen.     
     * @param buffer
     */
    public Documento(BufferedImage buffer){                
        this.id = 0;
        this.buffer = buffer;
        this.imagen = null;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
        File file = new File(url);
        if(buffer != null)
            try {
                ImageIO.write(buffer, "png", file);
            } catch (IOException ex) {
                    System.out.println("Error... Documento.setUrl(): "+ex.getMessage());;
            }
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public BufferedImage getBuffer() {
        return buffer;
    }
    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }
    public Image getImagen() {
        return imagen;
    }
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    public int getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    @Override
    public String toString() {
        return "Documento{" + "id=" + id + ", nombre=" + nombre + ", url=" + url + ", tipo=" + tipo + ", idPersona=" + idPersona + ", buffer=" + buffer + ", imagen=" + imagen + '}';
    }
}
