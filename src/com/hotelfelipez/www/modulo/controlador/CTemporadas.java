/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.MonedaDao;
import com.hotelfelipez.www.modulo.dao.TemporadaDao;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import com.hotelfelipez.www.modulo.temporadas.IUVentanaTemporadas;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class CTemporadas {

    private IUVentanaTemporadas iuTemporadas;
    
    public CTemporadas(){
        
    }
    public void controlarIUTemporadas(IUVentanaTemporadas iuTemporadas){
        this.iuTemporadas = iuTemporadas;  
        
    }
    public void agregarNuevaTemporada(Temporada temporada){
        Conexion conexion = new Conexion();
        TemporadaDao temporadaDao = new TemporadaDao(conexion);
        if(temporadaDao.seGuardoTemporada(temporada))
            conexion.cerrarConexion();
    }
    public void modificarTemporada(Temporada temporada){
        Conexion conexion = new Conexion();
        TemporadaDao temporadaDao = new TemporadaDao(conexion);
        if(temporadaDao.seModificoTemporada(temporada))
            conexion.cerrarConexion();
    }
    public boolean eliminarTemporada(Temporada temporada){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        TemporadaDao temporadaDao = new TemporadaDao(conexion);
        if(temporadaDao.seEliminoTemporada(temporada))
            verificador = true;
        return verificador;
    }
    public ArrayList<Temporada> getListaTemporadas(){
        Conexion conexion = new Conexion();
        TemporadaDao temporadaDao = new TemporadaDao(conexion);
        return temporadaDao.getListaTemporadas();
    }
    public ArrayList<Moneda> getListaMonedas(){
        Conexion conexion = new Conexion();
        MonedaDao monedaDao = new MonedaDao(conexion);
        ArrayList<Moneda> listaMonedas = monedaDao.getListaMonedas();
        conexion.cerrarConexion();
        return listaMonedas;
    }
}
