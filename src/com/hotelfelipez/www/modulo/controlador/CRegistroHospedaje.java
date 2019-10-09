/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.modelo.Documento;
import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.DocumentoDao;
import com.hotelfelipez.www.modulo.dao.HabitacionDao;
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
        HabitacionDao habitacionDao = new HabitacionDao(conexion);
        
        if(registroHospedajeDao.seGuardarRegistroHospedaje(registro)){
            int idRegistro = conexion.getDato("idregistroHospedaje", "select idregistroHospedaje from registroHospedaje ORDER by idregistrohospedaje DESC LIMIT 1");
            PersonaDao personaDao = new PersonaDao(conexion);     
            DocumentoDao documentoDao = new DocumentoDao(conexion);
            
            for (int i = 0; i < registro.getListaPersonas().size(); i++) {                
                Persona persona = registro.getListaPersonas().get(i);
                if(personaDao.existePersona(persona.getId()))
                    personaDao.seGuardoPesona_RegistroHospedaje(persona.getId(), idRegistro);
                else
                    if(personaDao.seGuardoPersona(persona)){
                        int idPersona = conexion.getDato("idpersona", "select idpersona from persona ORDER by idpersona DESC LIMIT 1");
                        int idDocumento = Asistente.getPostId("iddocumento", "select iddocumento from documento ORDER by iddocumento DESC LIMIT 1");

                        for (int j = 0; j < persona.getDocumentos().size(); j++) {
                            Documento documento = persona.getDocumentos().get(j);
                            documento.setIdPersona(idPersona);
                            switch(documento.getTipo()){
                                case "carnetIdentidadC":
                                    documento.setUrl(Asistente.getDirectorio().getDireccionDestino()+idDocumento+".png");                                
                                break;
                                case "carnetIdentidadE":
                                    documento.setUrl(Asistente.getDirectorio().getDireccionDestino()+idDocumento+".png");
                                break;
                                case "passporte":
                                    documento.setUrl(Asistente.getDirectorio().getDireccionDestino()+idDocumento+".png");
                                break;
                                case "certificado":
                                    documento.setUrl(Asistente.getDirectorio().getDireccionDestino()+idDocumento+".png");
                                break;
                                case "foto":
                                    documento.setUrl(Asistente.getDirectorio().getDireccionFoto()+idDocumento+".png");
                                break;
                            }
                            System.out.println("documento es: "+documento.toString());
                            if(documentoDao.seGuardoDocumento(documento)){
                                idDocumento++;
                            }                        
                        }
                        personaDao.seGuardoPesona_RegistroHospedaje(idPersona, idRegistro);
                    }
            }
            if(registroHospedajeDao.seGuardoHabitacion_RegistroHospedaje("activo", habitacion.getId(), idRegistro)){
                habitacion.setEstado("OCUPADO");
                if(habitacionDao.seModificoHabitacion(habitacion))
                    verificador = true;
            }
        }
        conexion.cerrarConexion();
        return verificador;
    }
}
