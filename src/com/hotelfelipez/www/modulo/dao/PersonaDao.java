/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Persona;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hotel-felipez
 */
public class PersonaDao {
    
    private Conexion conexion;

    public PersonaDao(Conexion conexion) {
        this.conexion = conexion;
    }
    public boolean seGuardoPersona(Persona p){
        boolean verificador = false;
        String sql = "insert into persona values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNombres());
            ps.setString(3, p.getApellidos());
            ps.setString(4, p.getFechaNacimiento());
            ps.setString(5, p.getTipoDocumento());
            ps.setString(6, p.getCarnetIdentidad());
            ps.setString(7, p.getOrigen());
            ps.setString(8, p.getFechaCaducidad());
            ps.setString(9, p.getCiudad());
            ps.setString(10, p.getPais());
            ps.setString(11, p.getEstadoCivil());
            ps.setString(12, p.getProfesion());
            ps.setString(13, p.getDireccion());
            ps.setString(14, p.getProcedencia());
            ps.setString(15, p.getDestino());
            ps.setString(16, p.getTelefono());
            ps.setString(17, p.getEmail());
            ps.setString(18, p.getTipoPersona());
            ps.setString(19, p.getObservacion());
                        
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error PersonaDao.seGuardoPeresona(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seGuardoPesona_RegistroHospedaje(int idPersona, int idRegistro){
        boolean verificador = false;
        String sql = "insert into persona_registroHospedaje values(?, ?, ?)";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, 0);
            ps.setInt(2, idPersona);
            ps.setInt(3, idRegistro);
                        
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error PersonaDao.seGuardoPeresona(): "+e.getMessage());
        }
        return verificador;
    }
}
