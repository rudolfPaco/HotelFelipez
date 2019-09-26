/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Frigobar;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Prestacion;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hotel-felipez
 */
public class HabitacionDao {
    private Conexion conexion;

    public HabitacionDao(Conexion conexion) {
        this.conexion = conexion;
    }
    public ArrayList<Habitacion> getListaHabitaciones(){
        String sql = "select * from habitacion";
        return getHabitaciones(sql);
    }
    public ArrayList<Habitacion> getListaHabitaciones(String simple, String matrimonial, String dobleSimple, String tripleMatrimonial, String familiar){
        String sql = "select * from habitacion where tipo = '"+simple+"' or tipo = '"+matrimonial+"' or tipo = '"+dobleSimple+"' or tipo = '"+tripleMatrimonial+"' or tipo = '"+familiar+"'";
        return getHabitaciones(sql);
    }
    private ArrayList<Habitacion> getHabitaciones(String sql){
        
        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        try {            
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Habitacion hab = new Habitacion(rs.getInt("idhabitacion"));
                hab.setNumero(rs.getString("numero"));
                hab.setSimbolo(rs.getString("simbolo"));
                hab.setTipo(rs.getString("tipo"));                
                hab.setEstilo(rs.getString("estilo"));
                hab.setEstado(rs.getString("estado"));
                hab.setNumeroPiso(rs.getString("numeroPiso"));
                hab.setCapacidad(rs.getString("capacidad"));
                hab.setNumeroTelefono(rs.getString("numeroTelefono"));
                hab.setDetalle(rs.getString("detalle"));
                hab.setDescripcion(rs.getString("descripcion"));
                hab.setSofaCama(rs.getString("sofaCama"));
                hab.setCostoSofaCama(rs.getDouble("costoSofaCama"));
                hab.setDisponible(rs.getString("disponible"));
                hab.setPromocionFrigobar(rs.getString("promocionFrigobar"));
                hab.setTipoVenta(rs.getString("tipoVenta"));
                hab.setTipoTemporada(rs.getString("tipoTemporada"));
                hab.setIdtemporada(rs.getInt("idtemporada"));
                hab.setIdmoneda(rs.getInt("idmoneda"));
                hab.setIdprestacion(rs.getInt("idprestacion"));
                hab.setTemporada(getTemporada(hab.getIdtemporada()));
                hab.setPrestacion(getPrestacion());
                hab.setFrigobar(getFrigobar(hab.getId()));
                hab.setCheck(false);
                habitaciones.add(hab);
            }
            
        } catch (SQLException e) {
            System.out.println("Error... HabitacionDao.getlistaHabitaciones(): "+e.getMessage());
        }
        return habitaciones;
    }    
    public boolean seGuardoHabitacion(Habitacion h){
        boolean verificador = false;
        String sql = "insert into habitacion values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, h.getId());
            ps.setString(2, h.getNumero());
            ps.setString(3, h.getSimbolo());
            ps.setString(4, h.getTipo());
            ps.setString(5, h.getEstilo());
            ps.setString(6, h.getEstado());
            ps.setString(7, h.getNumeroPiso());
            ps.setString(8, h.getCapacidad());
            ps.setString(9, h.getNumeroTelefono());
            ps.setString(10, h.getDetalle());
            ps.setString(11, h.getDescripcion());
            ps.setString(12, h.getSofaCama());
            ps.setDouble(13, h.getCostoSofaCama());
            ps.setString(14, h.getDisponible());
            ps.setString(15, h.getPromocionFrigobar());
            ps.setString(16, h.getTipoVenta());
            ps.setString(17, h.getTipoTemporada());
            ps.setInt(18, h.getIdtemporada());
            ps.setInt(19, h.getIdmoneda());
            ps.setInt(20, h.getIdprestacion());
                        
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error HabitacionDao.seGuardoHabitacion(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seModificoHabitacion(Habitacion h) {
        boolean verificador = false;
        String sql = "update habitacion set numero =?, simbolo =?, tipo =?, estilo =?, estado =?, numeroPiso =?, capacidad =?, numeroTelefono =?,"
                + " detalle =?, descripcion =?, sofaCama =?, costoSofaCama =?, disponible =?, promocionFrigobar =?, tipoVenta =?, tipoTemporada =? where idhabitacion = "+h.getId();
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            
            ps.setString(1, h.getNumero());
            ps.setString(2, h.getSimbolo());
            ps.setString(3, h.getTipo());
            ps.setString(4, h.getEstilo());
            ps.setString(5, h.getEstado());
            ps.setString(6, h.getNumeroPiso());
            ps.setString(7, h.getCapacidad());
            ps.setString(8, h.getNumeroTelefono());
            ps.setString(9, h.getDetalle());            
            ps.setString(10, h.getDescripcion());
            ps.setString(11, h.getSofaCama());
            ps.setDouble(12, h.getCostoSofaCama());
            ps.setString(13, h.getDisponible());
            ps.setString(14, h.getPromocionFrigobar());
            ps.setString(15, h.getTipoVenta());
            ps.setString(16, h.getTipoTemporada());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error HabitacionDao.seModificoHabitacion(): "+e.getMessage());
        }
        return verificador;
    }
    
    private Frigobar getFrigobar(int idhabitacion){
        return new FrigobarDao(conexion).getFrigobar(idhabitacion);
    }
    private Temporada getTemporada(int idtemporada){        
        return new TemporadaDao(conexion).getTemporada(idtemporada);
    }
    private Prestacion getPrestacion(){
        return new PrestacionDao(conexion).getPrestacion();
    }
}
