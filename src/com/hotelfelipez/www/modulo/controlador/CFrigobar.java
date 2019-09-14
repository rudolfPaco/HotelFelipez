/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.FrigobarDao;
import com.hotelfelipez.www.modulo.dao.MonedaDao;
import com.hotelfelipez.www.modulo.dao.ProductoDao;
import com.hotelfelipez.www.modulo.frigobar.IUPanelFrigobar;
import com.hotelfelipez.www.modulo.modelo.Frigobar;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Producto;
import java.util.ArrayList;

/**
 *
 * @author Jesus Junior Felipez
 */
public class CFrigobar {

    private IUPanelFrigobar iuFrigobar;
    public CFrigobar(){
        
    }
    public void controlarPanelFrigobar(IUPanelFrigobar iuFrigobar){
        this.iuFrigobar = iuFrigobar;
    }
    public ArrayList<Moneda> getListaMonedas(){
        Conexion conexion = new Conexion();
        MonedaDao monedaDao = new MonedaDao(conexion);
        ArrayList<Moneda> listaMonedas = monedaDao.getListaMonedas();
        conexion.cerrarConexion();
        return listaMonedas;
    }
    public void agregarNuevoProducto(Producto p){
        Conexion conexion = new Conexion();
        ProductoDao productoDao = new ProductoDao(conexion);
        if(productoDao.seGuardoProducto(p))
            conexion.cerrarConexion();
    }
    public void modificarProducto(Producto p){
        Conexion conexion = new Conexion();
        ProductoDao productoDao = new ProductoDao(conexion);
        if(productoDao.seModificoProducto(p))
            conexion.cerrarConexion();
    }
    public boolean eliminarProducto(Producto p){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        ProductoDao productoDao = new ProductoDao(conexion);
        if(p.getCantidad() == 0)
            if(productoDao.seEliminoProducto(p))
                verificador = true;
        conexion.cerrarConexion();
        return verificador;
    }
    public ArrayList<Producto> getListaProductos(int idfrigobar, int idhabitacion, String tipoOrden){
        Conexion conexion = new Conexion();
        ProductoDao productoDao = new ProductoDao(conexion);
        ArrayList<Producto> lista = productoDao.getListaProductos(idfrigobar, idhabitacion, tipoOrden);
        conexion.cerrarConexion();
        return lista;
    }    
    public String[] getCategoriasFrigobar(){
        Conexion conexion = new Conexion();
        String[] lista = null;
        ArrayList<String> categorias = conexion.getColumnaTabla("categoria", "select distinct categoria from producto");
        if(categorias.isEmpty()){
            lista = new String[0];
        }            
        else{
            lista = new String[categorias.size()+1];
            lista[0] = "";
            int indice = 1;
            for (int i = 0; i < categorias.size(); i++) {
                String elemento = categorias.get(i);            
                lista[indice] = elemento;
                indice++;
            }        
        }        
        conexion.cerrarConexion();
        return lista;
    }
    public void modificarFrigobar(Frigobar frigobar){
        Conexion conexion = new Conexion();
        FrigobarDao frigobarDao = new FrigobarDao(conexion);
        if(frigobarDao.seModificoFrigobar(frigobar))
            conexion.cerrarConexion();
    }
}
