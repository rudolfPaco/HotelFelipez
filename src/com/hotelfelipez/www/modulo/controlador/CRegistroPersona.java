/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.DocumentoDao;
import com.hotelfelipez.www.modulo.dao.PersonaDao;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Documento;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import com.hotelfelipez.www.modulo.registroHospedaje.IUNuevaPersona;
import java.util.ArrayList;

/**
 *
 * @author rudolf
 */
public class CRegistroPersona {
    private IUNuevaPersona iuRegistroPersona;
    private RegistroHospedaje registroHospedaje;

    public CRegistroPersona(RegistroHospedaje registroHospedaje) {
        this.registroHospedaje = registroHospedaje;
    }
    
    public void controlarRegistroPersona(IUNuevaPersona iuRegistroPersona){
        this.iuRegistroPersona = iuRegistroPersona;
    }
    public boolean guardarNuevaPersona(Persona persona){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        PersonaDao personaDao = new PersonaDao(conexion);     
        DocumentoDao documentoDao = new DocumentoDao(conexion);
        if(personaDao.seGuardoPersona(persona)){
            for (int i = 0; i < persona.getDocumentos().size(); i++) {
                Documento doc = persona.getDocumentos().get(i);
                if(conexion.getDato("iddocumento", "select iddocumento from documento where iddocumento = "+doc.getId()) > 0)
                    documentoDao.seModificoDocumento(doc);                
                else{
                    doc.setId(Asistente.getPostId("iddocumento", "select iddocumento from documento ORDER by iddocumento DESC LIMIT 1"));
                    if(doc.getTipo().equalsIgnoreCase("foto"))
                        doc.setUrl(Asistente.getDirectorio().getDireccionFoto()+doc.getId()+".png");
                    else
                        doc.setUrl(Asistente.getDirectorio().getDireccionDestino()+doc.getId()+".png");
                    doc.setIdPersona(Asistente.getId("idpersona", "select idpersona from persona ORDER by idpersona DESC LIMIT 1"));
                    documentoDao.seGuardoDocumento(doc);
                }
            }
            verificador = true;
        }
            
        conexion.cerrarConexion();
        
        return verificador;        
    }
    public boolean modificarDocumento(Documento d){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        DocumentoDao documentoDao = new DocumentoDao(conexion);
        if(documentoDao.seModificoDocumento(d))
            verificador = true;
        conexion.cerrarConexion();
        return verificador;
    }
    public boolean modificarDatosPersona(Persona persona){
        boolean verificador = false;
        Conexion conexion = new Conexion();
                
        PersonaDao personsaDao = new PersonaDao(conexion);
        DocumentoDao documentoDao = new DocumentoDao(conexion);
        if(personsaDao.seModificoDatosPersona(persona)){
            System.out.println("ENTRO A LOS DOCUMENTOS....");
            for (int i = 0; i < persona.getDocumentos().size(); i++) {                
                Documento doc = persona.getDocumentos().get(i);
                System.out.println("el doc es: "+doc.toString());
                if(conexion.getDato("iddocumento", "select iddocumento from documento where iddocumento = "+doc.getId()) > 0)
                    documentoDao.seModificoDocumento(doc);                
                else{
                    if(doc.getTipo().equalsIgnoreCase("foto"))
                        doc.setUrl(Asistente.getDirectorio().getDireccionFoto()+Asistente.getPostId("iddocumento", "select iddocumento from documento ORDER by iddocumento DESC LIMIT 1")+".png");
                    else
                        doc.setUrl(Asistente.getDirectorio().getDireccionDestino()+Asistente.getPostId("iddocumento", "select iddocumento from documento ORDER by iddocumento DESC LIMIT 1")+".png");
                    doc.setIdPersona(persona.getId());                    
                    documentoDao.seGuardoDocumento(doc);
                }                
            }
            System.out.println("SALIO DE LOS DOCUMENTOS....");
            verificador = true;
        }
        
        conexion.cerrarConexion();
        
        return verificador;
    }
    public boolean guardarPersonaRegistroHospedaje(int idpersona){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        PersonaDao personaDao = new PersonaDao(conexion);     
        if(personaDao.seGuardoPesona_RegistroHospedaje(idpersona, registroHospedaje.getId()))
            verificador = true;
        conexion.cerrarConexion();
        return verificador;
    }
    public ArrayList<Persona> getPersonasRegistradas(){
        Conexion conexion = new Conexion();
        PersonaDao personaDao = new PersonaDao(conexion);     
        ArrayList<Persona> listaPersonas = personaDao.getListaPersonas(registroHospedaje.getId());
        conexion.cerrarConexion();
        return listaPersonas;
    }    
}
