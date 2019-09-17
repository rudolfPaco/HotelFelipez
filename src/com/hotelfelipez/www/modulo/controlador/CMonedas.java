/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.MonedaDao;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.monedas.IUVentanaMonedas;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class CMonedas {
    private IUVentanaMonedas iuMonedas;
    
    public CMonedas(){
        
    }
    public void controlarIUMonedas(IUVentanaMonedas iuMonedas){
        this.iuMonedas = iuMonedas;        
    }
    public void agregarNuevaMoneda(Moneda moneda){
        Conexion conexion = new Conexion();
        MonedaDao monedaDao = new MonedaDao(conexion);
        if(monedaDao.seGuardoMoneda(moneda))            
            conexion.cerrarConexion();
    }
    public void modificarMoneda(Moneda moneda){
        Conexion conexion = new Conexion();
        MonedaDao monedaDao = new MonedaDao(conexion);
        if(monedaDao.seModificoMoneda(moneda))            
            conexion.cerrarConexion();
    }
    public boolean eliminarMoneda(Moneda moneda){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        MonedaDao monedaDao = new MonedaDao(conexion);
        if(monedaDao.seEliminoMoneda(moneda)){
            verificador = true;
        }        
        conexion.cerrarConexion();
        return verificador;
    }
    public ArrayList<Moneda> getListaMonedas() {
        Conexion conexion = new Conexion();
        MonedaDao monedaDao = new MonedaDao(conexion);        
        ArrayList<Moneda> listaMonedas = monedaDao.getListaMonedas();        
        conexion.cerrarConexion();
        return listaMonedas;
    }
}
