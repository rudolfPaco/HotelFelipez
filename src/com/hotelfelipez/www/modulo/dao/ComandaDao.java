/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Comanda;
import com.hotelfelipez.www.modulo.modelo.Detalle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rudolf
 */
public class ComandaDao {

    private Conexion conexion;

    public ComandaDao(Conexion conexion) {
        this.conexion = conexion;
    }
    public boolean seModificoComanda(Comanda c){
        boolean verificador = false;
        String sql = "UPDATE comanda SET nroComanda=?, nombre=?, fecha=?, hora=?, total=?, estado=? WHERE `idcomanda`='"+c.getId()+"';";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            
            ps.setString(1, c.getNroComanda());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getFecha());
            ps.setString(4, c.getHora());
            ps.setDouble(5, c.getTotal());
            ps.setString(6, c.getEstado());
            
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
        } catch (SQLException e) {
            System.out.println("Error ComandaDao.seModificoComanda(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seGuardoComanda(Comanda c){
        boolean verificador = false;
        String sql = "insert into comanda values(?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, c.getId());
            ps.setString(2, c.getNroComanda());
            ps.setString(3, c.getNombre());
            ps.setString(4, c.getFecha());
            ps.setString(5, c.getHora());
            ps.setDouble(6, c.getTotal());
            ps.setString(7, c.getEstado());
            ps.setInt(8, c.getIdRegistroHospedaje());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error ComandaDao.seGuardoComanda(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seGuardoDetalle(Detalle d){
        boolean verificador = false;
        String sql = "insert into detalle values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, d.getId());
            ps.setString(2, d.getDescripcion());
            ps.setDouble(3, d.getPrecio());
            ps.setDouble(4, d.getCantidad());
            ps.setString(5, d.getUnidad());
            ps.setDouble(6, d.getTotal());
            ps.setInt(7, d.getIdProducto());
            ps.setInt(8, d.getIdComanda());
            ps.setInt(9, d.getIdRegistroHospedaje());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error ComandaDao.seGuardoDetalle(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seModificoDetalle(Detalle d){
        boolean verificador = false;
        String sql = "UPDATE detalle SET descripcion=?, precio=?, cantidad=?, unidad=?, total=?, idProducto=? WHERE `iddetalle`='"+d.getId()+"';";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            
            ps.setString(1, d.getDescripcion());
            ps.setDouble(2, d.getPrecio());
            ps.setDouble(3, d.getCantidad());
            ps.setString(4, d.getUnidad());
            ps.setDouble(5, d.getTotal());
            ps.setInt(6, d.getIdProducto());
            
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
        } catch (SQLException e) {
            System.out.println("Error DetalleDao.seModificoDetalle(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean eliminarDetalle(Detalle d){
        boolean verificador = false;
        String sql = "delete from producto where idproducto = "+d.getId();
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error ProductoDao.seEliminoProducto(): "+e.getMessage());
        }
        return verificador;
    }
    public ArrayList<Comanda> getListaComandas(int idRegistroHospedaje){
        
        ArrayList<Comanda> lista = new ArrayList<>();
        try {
            String sql = "select * from comanda where idregistroHospedaje = "+idRegistroHospedaje;
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Comanda c = new Comanda(rs.getInt("idcomanda"));
                c.setNroComanda(rs.getString("nroComanda"));
                c.setNombre(rs.getString("nombre"));                
                c.setFecha(rs.getString("fecha"));
                c.setHora(rs.getString("hora"));
                c.setTotal(rs.getDouble("total"));
                c.setEstado(rs.getString("estado"));
                if(c.getEstado().equalsIgnoreCase("PAGADO"))
                    c.setCheck(true);
                else
                    c.setCheck(false);                
                c.setIdRegistroHospedaje(rs.getInt("idregistroHospedaje"));                
                c.setLista(getListaDetalles(c.getId(), c.getIdRegistroHospedaje()));
                lista.add(c);
            }
            
        } catch (SQLException e) {
            System.out.println("Error... ComandaDao.getlistaComandas(): "+e.getMessage());
        }
        return lista;
    }
        public ArrayList<Detalle> getListaDetalles(int idcomanda, int idregistroHospedaje){
        ArrayList<Detalle> lista = new ArrayList<>();
        try {
            String sql = "select * from detalle where idregistroHospedaje = "+idregistroHospedaje+" and idcomanda = "+idcomanda;
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Detalle d = new Detalle(rs.getInt("iddetalle"));
                d.setDescripcion(rs.getString("descripcion"));
                d.setPrecio(rs.getDouble("precio"));
                d.setCantidad(rs.getDouble("cantidad"));
                d.setUnidad(rs.getString("unidad"));
                d.setTotal(rs.getDouble("total"));
                d.setIdProducto(rs.getInt("idProducto"));
                d.setIdComanda(rs.getInt("idcomanda"));
                d.setIdRegistroHospedaje(rs.getInt("idregistroHospedaje"));
                lista.add(d);
            }
            
        } catch (SQLException e) {
            System.out.println("Error... ComandaDao.getlistaDetalles(): "+e.getMessage());
        }
        return lista;
    }
}
