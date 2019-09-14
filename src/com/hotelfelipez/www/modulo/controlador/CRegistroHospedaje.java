/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.aplicacionjava.www.recursos.Documento;
import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.PersonaDao;
import com.hotelfelipez.www.modulo.dao.RegistroHospedajeDao;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import com.hotelfelipez.www.modulo.registroHospedaje.IURegistroHospedaje;

/**
 *
 * @author hotel-felipez
 */
public class CRegistroHospedaje {

    private IURegistroHospedaje iuRegistro;
    
    public CRegistroHospedaje(){
        
    }
    public void controlarRegistroHospedaje(IURegistroHospedaje iuRegistro){
        this.iuRegistro = iuRegistro;
    }
    public boolean guardarNuevoRegistroHospedaje(RegistroHospedaje registro, Habitacion habitacion){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        RegistroHospedajeDao registroHospedajeDao = new RegistroHospedajeDao(conexion);
        if(registroHospedajeDao.seGuardarRegistroHospedaje(registro)){
            int idRegistro = conexion.getDato("idregistroHospedaje", "select idregistroHospedaje from registroHospedaje ORDER by idregistrohospedaje DESC LIMIT 1");
            PersonaDao personaDao = new PersonaDao(conexion);            
            for (int i = 0; i < registro.getListaPersonas().size(); i++) {                
                Persona persona = registro.getListaPersonas().get(i);
                if(personaDao.seGuardoPersona(persona)){
                    int idPersona = conexion.getDato("idpersona", "select idpersona from persona ORDER by idpersona DESC LIMIT 1");
                    for (int j = 0; j < persona.getDocumentos().size(); j++) {
                        Documento documento = persona.getDocumentos().get(j);
                        
                    }
                }
            }            
        }
        conexion.cerrarConexion();
        return verificador;
    }
}
