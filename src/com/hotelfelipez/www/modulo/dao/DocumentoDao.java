/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Documento;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
}
