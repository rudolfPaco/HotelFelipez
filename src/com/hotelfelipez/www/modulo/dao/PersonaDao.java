/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Documento;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ArrayList<Persona> getListaPersonas(int idregistro){
        
        ArrayList<Persona> lista = new ArrayList<>();
        try {
            String sql = "select * from persona where idpersona = (select idpersona from persona_registroHospedaje where idregistroHospedaje = "+idregistro+")";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Persona p = new Persona(rs.getInt("idpersona"));
                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                p.setFechaNacimiento(rs.getString("fechaNacimiento"));
                p.setTipoDocumento(rs.getString("tipoDocumento"));
                p.setCarnetIdentidad(rs.getString("numeroIdentidad"));
                p.setOrigen(rs.getString("origen"));
                p.setFechaCaducidad(rs.getString("fechaCaducidad"));
                p.setCiudad(rs.getString("ciudad"));
                p.setPais(rs.getString("pais"));
                p.setEstadoCivil(rs.getString("estadoCivil"));
                p.setProfesion(rs.getString("profesion"));
                p.setDireccion(rs.getString("direccion"));
                p.setProcedencia(rs.getString("procedencia"));
                p.setDestino(rs.getString("destino"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                p.setTipoPersona(rs.getString("tipoPersona"));
                p.setObservacion(rs.getString("observacion"));
                p.setDocumentos(getListaDocumentos(p.getId()));
                lista.add(p);
            }
            
        } catch (SQLException e) {
            System.out.println("Error... PersonaDao.getlistaPersonas(): "+e.getMessage());
        }
        return lista;
    }
    private ArrayList<Documento> getListaDocumentos(int idpersona){
        return new DocumentoDao(conexion).getListaDocumento(idpersona);
    }
    
}
