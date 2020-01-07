/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Recibo;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rudolf
 */
public class ReciboDao {
    private Conexion conexion;

    public ReciboDao(Conexion conexion) {
        this.conexion = conexion;
    }
    public boolean seGuardoRecibo(Recibo r){
        boolean verificador = false;
        String sql = "insert into recibo values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, r.getId());
            ps.setString(2, r.getNroRecibo());
            ps.setString(3, r.getFecha());
            ps.setString(4, r.getNombrePersona());
            ps.setDouble(5, r.getMontoRecibido());
            ps.setString(6, r.getMontoLiteral());
            ps.setString(7, r.getDescripcion());
            ps.setDouble(8, r.getTotal());
            ps.setDouble(9, r.getPago());
            ps.setDouble(10, r.getCambio());            
            ps.setString(11, r.getEstado());
                        
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error ReciboDao.seGuardoRecibo(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seGuardoRecibo_RegistroHospedaje(int idRecibo, int idRegistroHospedaje){
        boolean verificador = false;
        String sql = "insert into recibo_registroHospedaje values(?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, 0);
            ps.setInt(2, idRecibo);
            ps.setInt(3, idRegistroHospedaje);
                        
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error ReciboDao.seGuardoRecibo_Hospedaje(): "+e.getMessage());
        }
        return verificador;
    }
}
