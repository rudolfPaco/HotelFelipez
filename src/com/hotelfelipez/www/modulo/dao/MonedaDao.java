/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Moneda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hotel-felipez
 */
public class MonedaDao {
    private Conexion conexion;

    public MonedaDao(Conexion conexion) {
        this.conexion = conexion;
    }
    public ArrayList<Moneda> getListaMonedas(){
        
        ArrayList<Moneda> lista = new ArrayList<>();
        try {
            String sql = "select * from moneda";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Moneda moneda = new Moneda(rs.getInt("idmoneda"));
                moneda.setTipo(rs.getString("tipo"));
                moneda.setUnidad(rs.getString("unidad"));
                lista.add(moneda);
            }
            
        } catch (SQLException e) {
            System.out.println("Error... MonedaDao.getlistaMonedas(): "+e.getMessage());
        }
        return lista;
    }
    
    public boolean seGuardoMoneda(Moneda m){
        boolean verificador = false;
        String sql = "insert into moneda values(?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, m.getId());
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getUnidad());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error MonedaDao.seGuardoMoneda(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seModificoMoneda(Moneda m){
        boolean verificador = false;
        String sql = "UPDATE moneda SET tipo=?, unidad=? WHERE `idmoneda`='"+m.getId()+"';";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            
            ps.setString(1, m.getTipo());
            ps.setString(2, m.getUnidad());
            
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
        } catch (SQLException e) {
            System.out.println("Error MonedaDao.seModificoMoneda(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seEliminoMoneda(Moneda m){
        boolean verificador = false;
        String sql = "delete from moneda where idmoneda = "+m.getId();
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error MonedaDao.seEliminoMoneda(): "+e.getMessage());
        }
        return verificador;
    }
}
