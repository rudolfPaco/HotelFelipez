/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.DisponibilidadDao;
import com.hotelfelipez.www.modulo.disponibilidad.IUVentanaDisponibilidad;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import java.util.ArrayList;

/**
 *
 * @author rudolf
 */
public class CDisponibilidad {
    private IUVentanaDisponibilidad iuDisponibilidad;

    public CDisponibilidad() {
    }
    public void controlarVentanaDisponibilidad(IUVentanaDisponibilidad iuDisponibilidad){
        this.iuDisponibilidad = iuDisponibilidad;
    }
    public ArrayList<Habitacion> getHabitacionesDisponibles(ArrayList<String> lista){
        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        Conexion conexion = new Conexion();
        DisponibilidadDao disponibilidadDao = new DisponibilidadDao(conexion);
        habitaciones = disponibilidadDao.getListaHabitaciones("SELECT * FROM HABITACIon where tipo = 'SIMPLE'+'MATRIMONIAL'+'DOBLE SIMPLE'+'FAMILIAR'+'TRIPLE MATRIOMINAL' AND ESTADO = 'VACANTE'");
        return habitaciones;
    }
}
