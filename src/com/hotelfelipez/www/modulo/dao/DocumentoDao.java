/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Documento;
import static com.oracle.jrockit.jfr.ContentType.Bytes;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author hotel-felipez
 */
public class DocumentoDao {

    private Conexion conexion;

    public DocumentoDao(Conexion conexion) {
        this.conexion = conexion;
    }
    public boolean seGuardoDocumento(Documento d){
        boolean verificador = false;
        String sql = "insert into documento values(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            
            File file = new File(d.getUrl());
            FileInputStream archivo_flujo_entrada = new FileInputStream(file);
            
            ps.setInt(1, d.getId());
            ps.setString(2, d.getTipo());            
            ps.setBlob(3,archivo_flujo_entrada,file.length());
            ps.setString(4, d.getUrl());
            ps.setInt(5, d.getIdPersona());
                        
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;        
        } catch (SQLException | FileNotFoundException ex){
            System.out.println("Error... DocumentoDao.seGuardoDocumento(): "+ex.getMessage());
        }
        return verificador;
    }
    public boolean seModificoDocumento(Documento d){
        boolean verificador = false;
        String sql = "UPDATE documento SET tipo=?, imagen=?, url=? WHERE iddocumento="+d.getId();
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            File file = new File(d.getUrl());
            FileInputStream archivo = new FileInputStream(file);
            
            ps.setString(1, d.getTipo());            
            ps.setBinaryStream(2, archivo, file.length());
            ps.setString(3, d.getUrl());
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error... DocumentoDao.seModificoDocumento(): "+e.getMessage());
        }
        return verificador;
    }
    
    public ArrayList<Documento> getListaDocumento(int idpersona){
        ArrayList<Documento> lista = new ArrayList<>();        
        try {
            String sql = "select * from documento where idpersona = "+idpersona;
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();            
            
            while(rs.next()){               
                /*byte[] data = rs.getBytes("imagen");//blob.getBytes(1, (int)blob.length());
                ByteArrayInputStream arregloData = new ByteArrayInputStream(data);
                Iterator readers = ImageIO.getImageReadersByFormatName("png");
                ImageReader reader = (ImageReader) readers.next();
                Object source = arregloData; // File or InputStream
                ImageInputStream iis = ImageIO.createImageInputStream(source);
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();
                BufferedImage bufferedImagen = reader.read(0, param);//ImageIO.read(arregloData);
                
                File archivo = new File(rs.getString("url"));
                //System.out.println("obteniendo los huespedes... = "+archivo.getAbsolutePath());
                Blob campo = rs.getBlob("imagen");
                InputStream flujoDatos = campo.getBinaryStream();
                try (BufferedInputStream entrada = new BufferedInputStream(flujoDatos); BufferedOutputStream salida = new BufferedOutputStream(new FileOutputStream(archivo))) {
                byte[] bytes = new byte[8096];
                int len = 0;
                
                while ( (len = entrada.read( bytes ))> 0 ){
                salida.write( bytes, 0, len );
                }
                
                salida.flush();
                } catch (IOException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                File archivo = new File(rs.getString("url"));
                if(!archivo.exists()){
                    System.out.println("el archivo EXISTE...!");
                    Blob campo = rs.getBlob("imagen");
                    InputStream flujoDatos = campo.getBinaryStream();
                    try (BufferedInputStream entrada = new BufferedInputStream(flujoDatos); BufferedOutputStream salida = new BufferedOutputStream(new FileOutputStream(archivo))) {
                    byte[] bytes = new byte[8096];
                    int len = 0;

                    while ( (len = entrada.read( bytes ))> 0 ){
                    salida.write( bytes, 0, len );
                    }

                    salida.flush();
                    } catch (IOException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Documento d = new Documento(rs.getInt("iddocumento"));
                d.setTipo(rs.getString("tipo"));
                d.setUrl(rs.getString("url"));
                d.setIdPersona(rs.getInt("idpersona"));
                lista.add(d);                
            }            
        } catch (SQLException e) {
            System.out.println("Error...DocumentoDao.getListaDocumento(): "+e.getMessage());
        }
        return lista;
    }
    public boolean seEliminoDocumento(int idDocumento){
        boolean verificador = false;
        
        return verificador;
    }
}
