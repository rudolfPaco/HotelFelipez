/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Frigobar;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
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
public class DisponibilidadDao {

    private Conexion conexion;
    
    public DisponibilidadDao(Conexion conexion){
        this.conexion = conexion;
    }
    public ArrayList<Habitacion> getListaHabitaciones(String sql){
        
        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Habitacion hab = new Habitacion(rs.getInt("idhabitacion"));
                hab.setNumero(rs.getString("numero"));
                hab.setSimbolo(rs.getString("simbolo"));
                hab.setTipo(rs.getString("tipo"));                
                hab.setEstilo(rs.getString("nombre"));
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
