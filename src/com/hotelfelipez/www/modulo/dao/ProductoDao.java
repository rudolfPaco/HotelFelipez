/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jesus Junior Felipez
 */
public class ProductoDao {
    private Conexion conexion;

    public ProductoDao(Conexion conexion) {
        this.conexion = conexion;
    }
    
    public ArrayList<Producto> getListaProductos(int idfrigobar, int idhabitacion, String tipoOrden){
        
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            String sql = "select * from producto where idfrigobar = "+idfrigobar+" and idhabitacion = "+idhabitacion+" order by "+tipoOrden;//ordenar la lista por categoria
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Producto p = new Producto(rs.getInt("idproducto"));
                p.setCategoria(rs.getString("categoria"));
                p.setMarca(rs.getString("marca"));
                p.setNombre(rs.getString("nombre"));
                p.setCosto(rs.getDouble("costo"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setFechaVencimiento(rs.getString("fechaVencimiento"));
                p.setPromocion(rs.getString("promocion"));
                p.setPrecioAnterior(rs.getDouble("precioAnterior"));
                p.setIdmoneda(rs.getInt("idmoneda"));
                p.setIdfrigobar(rs.getInt("idfrigobar"));
                p.setIdhabitacion(rs.getInt("idhabitacion"));
                p.setMoneda(getMoneda(p.getIdmoneda()));
                lista.add(p);
            }
            
        } catch (SQLException e) {
            System.out.println("Error... ProductoDao.getlistaProductos(): "+e.getMessage());
        }
        return lista;
    }
    public Producto getProducto(int idProducto){
        Producto p = null;
        try {
            String sql = "select * from producto where idproducto = "+idProducto;
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                p = new Producto(rs.getInt("idproducto"));
                p.setCategoria(rs.getString("categoria"));
                p.setMarca(rs.getString("marca"));
                p.setNombre(rs.getString("nombre"));
                p.setCosto(rs.getDouble("costo"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setFechaVencimiento(rs.getString("fechaVencimiento"));
                p.setPromocion(rs.getString("promocion"));
                p.setPrecioAnterior(rs.getDouble("precioAnterior"));
                p.setIdmoneda(rs.getInt("idmoneda"));
                p.setIdfrigobar(rs.getInt("idfrigobar"));
                p.setIdhabitacion(rs.getInt("idhabitacion"));
                p.setMoneda(getMoneda(p.getIdmoneda()));                
            }
            
        } catch (SQLException e) {
            System.out.println("Error... ProductoDao.getlistaProductos(): "+e.getMessage());
        }
        return p;
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
    public boolean seGuardoProducto(Producto producto){
        boolean verificador = false;
        String sql = "insert into producto values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
                                    
            ps.setInt(1, producto.getId());
            ps.setString(2, producto.getCategoria());
            ps.setString(3, producto.getMarca());
            ps.setString(4, producto.getNombre());
            ps.setDouble(5, producto.getCosto());
            ps.setDouble(6, producto.getPrecio());
            ps.setInt(7, producto.getCantidad());
            ps.setString(8, producto.getFechaVencimiento());
            ps.setString(9, producto.getPromocion());
            ps.setDouble(10, producto.getPrecioAnterior());
            ps.setInt(11, producto.getIdmoneda());
            ps.setInt(12, producto.getIdfrigobar());
            ps.setInt(13, producto.getIdhabitacion());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0)
                verificador = true;
            
        } catch (SQLException e) {
            System.out.println("Error ProductoDao.seGuardoProducto(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seModificoProducto(Producto m){
        boolean verificador = false;
        String sql = "UPDATE producto SET categoria=?, marca=?, nombre=?, costo=?, precio=?, cantidad=?, fechaVencimiento=?, promocion=?, precioAnterior=?, idmoneda=? WHERE `idproducto`='"+m.getId()+"';";
        try {
            PreparedStatement ps = conexion.getConexion().prepareStatement(sql);
            
            ps.setString(1, m.getCategoria());
            ps.setString(2, m.getMarca());
            ps.setString(3, m.getNombre());
            ps.setDouble(4, m.getCosto());
            ps.setDouble(5, m.getPrecio());
            ps.setInt(6, m.getCantidad());
            ps.setString(7, m.getFechaVencimiento());
            ps.setString(8, m.getPromocion());
            ps.setDouble(9, m.getPrecioAnterior());
            ps.setInt(10, m.getIdmoneda());
            
            int estado = ps.executeUpdate();
            if(estado > 0)
                verificador = true;
        } catch (SQLException e) {
            System.out.println("Error ProductoDao.seModificoProducto(): "+e.getMessage());
        }
        return verificador;
    }
    public boolean seEliminoProducto(Producto p){
        boolean verificador = false;
        String sql = "delete from producto where idproducto = "+p.getId();
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
}
