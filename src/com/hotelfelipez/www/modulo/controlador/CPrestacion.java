/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.PrestacionDao;
import com.hotelfelipez.www.modulo.modelo.Prestacion;
import com.hotelfelipez.www.modulo.prestacion.IUVentanaPrestacion;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class CPrestacion {
    private IUVentanaPrestacion iuPrestacion;
    
    public CPrestacion(){
        
    }
    public void controlarIUPrestacion(IUVentanaPrestacion iuPrestacion){
        this.iuPrestacion = iuPrestacion;
    }
    public boolean existeAlgunServicio(){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        if(conexion.getDato("idprestacion", "select idprestacion from prestacion") > 0)
            verificador = true;
        conexion.cerrarConexion();
        return verificador;
    }
    public Prestacion getPrestacion(){
        Conexion conexion = new Conexion();
        PrestacionDao prestacionDao = new PrestacionDao(conexion);
        Prestacion prestacion = prestacionDao.getPrestacion();
        if(prestacion.getId() < 1){
            prestacion.setPrimerServicio("");
            prestacion.setSegundoServicio("");
            prestacion.setTercerServicio("");
            prestacion.setCuartoServicio("");
            prestacion.setQuintoServicio("");
            prestacion.setSextoServicio("");
        }
        conexion.cerrarConexion();
        return prestacion;
    }
    public void agregarNuevaPrestacion(Prestacion prestacion){
        Conexion conexion = new Conexion();
        PrestacionDao prestacionDao = new PrestacionDao(conexion);
        if(prestacionDao.seGuardoPrestacion(prestacion))
            conexion.cerrarConexion();
    }
    public void modificarPrestacion(Prestacion p){
        Conexion conexion = new Conexion();
        PrestacionDao prestacionDao = new PrestacionDao(conexion);
        if(prestacionDao.seModificoPrestacion(p))
            conexion.cerrarConexion();
    }
    public boolean eliminarPrestacion(Prestacion p){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        PrestacionDao prestacionDao = new PrestacionDao(conexion);
        if(prestacionDao.seEliminoPrestacion(p))
            verificador = true;
        return verificador;
    }
}
