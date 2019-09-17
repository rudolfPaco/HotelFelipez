/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Temporada;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class TemporadaDao {
    
    private Conexion conexion;
    
    public TemporadaDao(Conexion conexion){
        this.conexion = conexion;
    }
    public boolean seGuardoTemporada(Temporada t){
        boolean verificador = false;
        String sql = "insert into temporada values(?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, t.getId());
            ps.setString(2, t.getTipoTemporada());
            ps.setDouble(3, t.getPrecio());
            ps.setString(4, t.getMotivo());
            ps.setInt(5, t.getUnidadMoneda().getId());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error TemporadaDao.seGuardoTemporada(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seModificoTemporada(Temporada t){
        boolean verificador = false;
        String sql = "UPDATE temporada SET tipoTemporada=?, precioTemporada=?, motivoTemporada=?, idmoneda=? WHERE `idtemporada`='"+t.getId()+"';";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            
            ps.setString(1, t.getTipoTemporada());
            ps.setDouble(2, t.getPrecio());
            ps.setString(3, t.getMotivo());
            ps.setInt(4, t.getUnidadMoneda().getId());
            
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
        } catch (SQLException e) {
            System.out.println("Error TemporadaDao.seModificoTemporada(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seEliminoTemporada(Temporada t){
        boolean verificador = false;
        String sql = "delete from temporada where idtemporada = "+t.getId();
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error TemporadaDao.seEliminoTemporada(): "+e.getMessage());
        }
        return verificador;
    }
    public ArrayList<Temporada> getListaTemporadas(){        
            
        ArrayList<Temporada> temporadas = new ArrayList<>();
        try {
            String sql = "select * from temporada";
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Temporada temporada = new Temporada(rs.getInt("idtemporada"));
                temporada.setTipoTemporada(rs.getString("tipoTemporada"));
                temporada.setPrecio(rs.getDouble("precioTemporada"));
                temporada.setMotivo(rs.getString("motivoTemporada"));
                temporada.setUnidadMoneda(getMoneda(rs.getInt("idmoneda")));
                temporadas.add(temporada);
            }
            
        } catch (SQLException e) {
            System.out.println("Error... TemporadaDao.getlistaTemporadas(): "+e.getMessage());
        }        
        return temporadas;
    }
    public Temporada getTemporada(int idtemporada){
        Temporada temporada = null;
        try {
            String sql = "select * from temporada where idtemporada = "+idtemporada;
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                temporada = new Temporada(rs.getInt("idtemporada"));
                temporada.setTipoTemporada(rs.getString("tipoTemporada"));
                temporada.setPrecio(rs.getDouble("precioTemporada"));
                temporada.setMotivo(rs.getString("motivoTemporada"));
                temporada.setUnidadMoneda(getMoneda(rs.getInt("idmoneda")));
            }
            
        } catch (SQLException e) {
            System.out.println("Error... TemporadaDao.getTemporada(): "+e.getMessage());
        }        
        return temporada;
    }
    private Moneda getMoneda(int id){
        Moneda moneda = new Moneda(id);
        MonedaDao monedaDao = new MonedaDao(conexion);
        ArrayList<Moneda> listaMonedas = monedaDao.getListaMonedas();
        boolean encontrado = false;
        int contador = 0;
        while(contador < listaMonedas.size() && !encontrado){
            if(listaMonedas.get(contador).getId() == id){
                moneda = listaMonedas.get(contador);
                encontrado = true;
            }
            contador++;
        }
        return moneda;
    }
}
