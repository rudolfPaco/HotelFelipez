/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hotel-felipez
 */
public class RegistroHospedajeDao {
    private Conexion conexion;

    public RegistroHospedajeDao(Conexion conexion) {
        this.conexion = conexion;
    }
    public boolean seGuardarRegistroHospedaje(RegistroHospedaje r){
        boolean verificador = false;
        String sql = "insert into registroHospedaje values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, r.getId());
            ps.setString(2, r.getNroRegistro());
            ps.setString(3, r.getFechaLlegada());
            ps.setString(4, r.getHoraLlegada());
            ps.setString(5, r.getFechaSalida());
            ps.setString(6, r.getHoraSalida());
            ps.setDouble(7, r.getTotalHospedaje());
            ps.setDouble(8, r.getTotalServicios());
            ps.setDouble(9, r.getTotalAcuenta());
            ps.setDouble(10, r.getTotalPagado());
            ps.setString(11, r.getObservacion());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error RegistroHospedajeDao.seGuardoRegistroHospedaje(): "+e.getMessage());
        }
        return verificador;
    }
}
