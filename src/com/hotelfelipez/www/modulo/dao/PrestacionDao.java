/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Prestacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class PrestacionDao {

    private Conexion conexion;

    public PrestacionDao(Conexion conexion) {
        this.conexion = conexion;
    }
    public Prestacion getPrestacion(){
        Prestacion prestacion = new Prestacion(0);
        try {
            String sql = "select * from prestacion";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                prestacion = new Prestacion(rs.getInt("idprestacion"));
                prestacion.setPrimerServicio(rs.getString("primerServicio"));
                prestacion.setSegundoServicio(rs.getString("segundoServicio"));
                prestacion.setTercerServicio(rs.getString("tercerServicio"));
                prestacion.setCuartoServicio(rs.getString("cuartoServicio"));
                prestacion.setQuintoServicio(rs.getString("quintoServicio"));
                prestacion.setSextoServicio(rs.getString("sextoServicio"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error... PrestacionDao.getListaPrestaciones(): "+e.getMessage());
        }
        return prestacion;
    }    
    public boolean seGuardoPrestacion(Prestacion p){
        boolean verificador = false;
        String sql = "insert into prestacion values(?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, p.getId());
            ps.setString(2, p.getPrimerServicio());
            ps.setString(3, p.getSegundoServicio());
            ps.setString(4, p.getTercerServicio());
            ps.setString(5, p.getCuartoServicio());
            ps.setString(6, p.getQuintoServicio());
            ps.setString(7, p.getSextoServicio());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error PrestacionDao.seGuardoPrestacion(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seModificoPrestacion(Prestacion p){
        boolean verificador = false;
        String sql = "UPDATE prestacion SET primerServicio=?, segundoServicio=?, tercerServicio=?, cuartoServicio=?, quintoServicio=?, sextoServicio=? WHERE `idprestacion`='"+p.getId()+"';";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            
            ps.setString(1, p.getPrimerServicio());
            ps.setString(2, p.getSegundoServicio());
            ps.setString(3, p.getTercerServicio());
            ps.setString(4, p.getCuartoServicio());
            ps.setString(5, p.getQuintoServicio());
            ps.setString(6, p.getSextoServicio());
            
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
        } catch (SQLException e) {
            System.out.println("Error PrestacionDao.seModificoPrestacion(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seEliminoPrestacion(Prestacion p){
        boolean verificador = false;
        String sql = "delete from prestacion where idprestacion = "+p.getId();
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error PrestacionDao.seEliminoPrestacion(): "+e.getMessage());
        }
        return verificador;
    }
}
