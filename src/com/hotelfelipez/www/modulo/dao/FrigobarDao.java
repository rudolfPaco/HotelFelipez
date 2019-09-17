/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Frigobar;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jesus Junior Felipez
 */
public class FrigobarDao {
    private Conexion conexion;

    public FrigobarDao(Conexion conexion) {
        this.conexion = conexion;
    }
    
    public Frigobar getFrigobar(int idhabitacion){
        Frigobar frigobar = null;
        try {
            String sql = "select * from frigobar where idhabitacion = "+idhabitacion;
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                frigobar = new Frigobar(rs.getInt("idfrigobar"));
                frigobar.setNombreFrigobar(rs.getString("nombreFrigobar"));
                frigobar.setCantidadProductos(rs.getInt("cantidadProductos"));
                frigobar.setIdhabitacion(idhabitacion);
            }
            
        } catch (SQLException e) {
            System.out.println("Error... FrigobarDao.getFrigobar(): "+e.getMessage());
        }        
        return frigobar;
    }
    public boolean seGuardoFrigobar(Frigobar m){
        boolean verificador = false;
        String sql = "insert into frigobar values(?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, m.getId());
            ps.setString(2, m.getNombreFrigobar());
            ps.setInt(3, m.getCantidadProductos());
            ps.setInt(4, conexion.getDato("idhabitacion", "SELECT idhabitacion FROM habitacion ORDER by idhabitacion DESC LIMIT 1"));
            
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error FrigobarDao.seGuardoFrigobar(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seModificoFrigobar(Frigobar m){
        boolean verificador = false;
        String sql = "UPDATE frigobar SET nombreFrigobar=?, cantidadProductos=? WHERE `idfrigobar`='"+m.getId()+"';";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            
            ps.setString(1, m.getNombreFrigobar());
            ps.setInt(2, m.getCantidadProductos());
            
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
        } catch (SQLException e) {
            System.out.println("Error FrigobarDao.seModificoFrigobar(): "+e.getMessage());
        }
        return verificador;
    }
}
