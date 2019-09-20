/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.DocumentoDao;
import com.hotelfelipez.www.modulo.dao.FrigobarDao;
import com.hotelfelipez.www.modulo.dao.HabitacionDao;
import com.hotelfelipez.www.modulo.dao.MonedaDao;
import com.hotelfelipez.www.modulo.dao.PersonaDao;
import com.hotelfelipez.www.modulo.dao.RegistroHospedajeDao;
import com.hotelfelipez.www.modulo.dao.TemporadaDao;
import com.hotelfelipez.www.modulo.habitaciones.IUVentanaHabitaciones;
import com.hotelfelipez.www.modulo.modelo.Frigobar;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class CHabitaciones {
    private IUVentanaHabitaciones iuHabitaciones;
    
    public CHabitaciones(){
        
    }
    public void controlarIUHabitaciones(IUVentanaHabitaciones iuHabitaciones){
        this.iuHabitaciones = iuHabitaciones;
    }        
    public ArrayList<Temporada> getListaTemporadas(){
        Conexion conexion = new Conexion();
        TemporadaDao temporadaDao = new TemporadaDao(conexion);
        ArrayList<Temporada> listaTemporadas = temporadaDao.getListaTemporadas();
        conexion.cerrarConexion();
        
        return listaTemporadas;
    }
    public ArrayList<Habitacion> getListaHabitaciones(){
        ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();        
        Conexion conexion = new Conexion();
        HabitacionDao habitacionDao = new HabitacionDao(conexion);
        listaHabitaciones = habitacionDao.getListaHabitaciones("SIMPLE", "MATRIMONIAL", "DOBLE SIMPLE", "TRIPLE MATRIMONIAL", "FAMILIAR");
        conexion.cerrarConexion();
        
        return listaHabitaciones;
    }
    public ArrayList<Habitacion> getListaHabitaciones(String simple, String matrimonial, String dobleSimple, String tripleMatrimonial, String familiar){
        ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();        
        Conexion conexion = new Conexion();
        HabitacionDao habitacionDao = new HabitacionDao(conexion);
        listaHabitaciones = habitacionDao.getListaHabitaciones(simple, matrimonial, dobleSimple, tripleMatrimonial, familiar);
        conexion.cerrarConexion();
        
        return listaHabitaciones;
    }
    public void agregarNuevaHabitacion(Habitacion habitacion){
        Conexion conexion = new Conexion();
        HabitacionDao habitacionDao = new HabitacionDao(conexion);
        FrigobarDao frigobarDao = new FrigobarDao(conexion);
        
        if(habitacionDao.seGuardoHabitacion(habitacion)) 
            if(frigobarDao.seGuardoFrigobar(habitacion.getFrigobar()))
                conexion.cerrarConexion();
    }
    public RegistroHospedaje getRegistroHospedaje(int idHabitacion){
        Conexion conexion = new Conexion();
        
        RegistroHospedajeDao registroDao = new RegistroHospedajeDao(conexion);
        PersonaDao personaDao = new PersonaDao(conexion);
        RegistroHospedaje registro = registroDao.getRegistroHospedaje(idHabitacion);        
        registro.setListaPersonas(personaDao.getListaPersonas(registro.getId()));
        
        conexion.cerrarConexion();
        return registro;
    }
}
