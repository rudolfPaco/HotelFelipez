/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.PersonaDao;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.registroHospedaje.IUVentanaRegistroNuevaPersona;

/**
 *
 * @author rudolf
 */
public class CRegistroPersona {
    private IUVentanaRegistroNuevaPersona iuRegistroPersona;

    public CRegistroPersona() {
    }
    
    public void controlarRegistroPersona(IUVentanaRegistroNuevaPersona iuRegistroPersona){
        this.iuRegistroPersona = iuRegistroPersona;
    }
    public boolean guardarNuevaPersona(Persona p){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        PersonaDao personaDao = new PersonaDao(conexion);     
        if(personaDao.seGuardoPersona(p))
            verificador = true;
        conexion.cerrarConexion();
        
        return verificador;        
    }
    public boolean guardarPersonaRegistroHospedaje(int idpersona, int idregistroHospedaje){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        PersonaDao personaDao = new PersonaDao(conexion);     
        if(personaDao.seGuardoPesona_RegistroHospedaje(idpersona, idregistroHospedaje))
            verificador = true;
        conexion.cerrarConexion();
        return verificador;
    }
}
