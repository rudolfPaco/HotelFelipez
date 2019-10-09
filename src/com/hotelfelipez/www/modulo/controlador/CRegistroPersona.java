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
import com.hotelfelipez.www.modulo.registroHospedaje.IUTablaPersonas;
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
            for (int i = 0; i < persona.getDocumentos().size(); i++) {
                Documento docPersona = persona.getDocumentos().get(i);
                if(conexion.getDato("iddocumento", "select iddocumento from documento where iddocumento = "+docPersona.getId()) > 0)
                    documentoDao.seModificoDocumento(docPersona);                
                else{
                    if(docPersona.getTipo().equalsIgnoreCase("foto"))
                        docPersona.setUrl(Asistente.getDirectorio().getDireccionFoto()+Asistente.getPostId("iddocumento", "select iddocumento from documento ORDER by iddocumento DESC LIMIT 1")+".png");
                    else
                        docPersona.setUrl(Asistente.getDirectorio().getDireccionDestino()+Asistente.getPostId("iddocumento", "select iddocumento from documento ORDER by iddocumento DESC LIMIT 1")+".png");
                    docPersona.setIdPersona(persona.getId());                    
                    documentoDao.seGuardoDocumento(docPersona);
                }                
            }
            ArrayList<Documento> lista = documentoDao.getListaDocumento(persona.getId());
            for (int i = 0; i < lista.size(); i++) {
                Documento docLista = lista.get(i);
                int indice = 0;
                boolean estado = false;
                while(indice < persona.getDocumentos().size() && !estado){
                    Documento docPersona = persona.getDocumentos().get(indice);
                    if(docPersona.getTipo().equalsIgnoreCase(docLista.getTipo()))
                        estado = true;                        
                    indice++;
                }
                if(!estado)
                    verificador = documentoDao.seEliminoDocumento(docLista.getId());
            }
        }
        
        conexion.cerrarConexion();
        
        return verificador;
    }
    public boolean eliminarPersonaRegistro(int idpersona){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        PersonaDao personaDao = new PersonaDao(conexion);
        if(personaDao.seEliminoPersona_RegistroHospedaje(idpersona, registroHospedaje.getId()))
            verificador = true;
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
    public ArrayList<Persona> getListaPersonas(IUTablaPersonas iuTablaPersonas){
        Conexion conexion = new Conexion();
        PersonaDao personaDao = new PersonaDao(conexion);     
        String sql = "select * from persona";
        if(iuTablaPersonas.getRowCount() > 0)
            sql = sql+" where";
        for (int i = 0; i < iuTablaPersonas.getRowCount(); i++) {
            Persona p = iuTablaPersonas.getFila(i);
            if(p.getId() > 0 && i < (iuTablaPersonas.getRowCount()-1))
                sql = sql+" idpersona != "+p.getId()+" and";
            else
                sql = sql+" idpersona != "+p.getId();
        }
        ArrayList<Persona> listaPersonas = personaDao.getListaPersonas(sql);
        conexion.cerrarConexion();
        return listaPersonas;
    }
    public ArrayList<Documento> getDocumentosPersona(int idPersona){
        Conexion conexion = new Conexion();
        PersonaDao personaDao = new PersonaDao(conexion);     
        ArrayList<Documento> listaDocumentos = personaDao.getListaDocumentos(idPersona);        
        conexion.cerrarConexion();
        return listaDocumentos;
    }
}
