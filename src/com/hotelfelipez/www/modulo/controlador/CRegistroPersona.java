/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.DocumentoDao;
import com.hotelfelipez.www.modulo.dao.PersonaDao;
import com.hotelfelipez.www.modulo.modelo.Documento;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.registroHospedaje.IUNuevaPersona;

/**
 *
 * @author rudolf
 */
public class CRegistroPersona {
    private IUNuevaPersona iuRegistroPersona;

    public CRegistroPersona() {
    }
    
    public void controlarRegistroPersona(IUNuevaPersona iuRegistroPersona){
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
    public boolean guardarNuevoDocumento(Documento d){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        DocumentoDao documentoDao = new DocumentoDao(conexion);
        if(documentoDao.seGuardoDocumento(d)){
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
    public boolean modificarDatosPersona(Persona p){
        boolean verificador = false;
        Conexion conexion = new Conexion();
                
        PersonaDao personsaDao = new PersonaDao(conexion);
        DocumentoDao documentoDao = new DocumentoDao(conexion);
        if(personsaDao.seModificoDatosPersona(p))
            for (int i = 0; i < p.getDocumentos().size(); i++) {
                Documento doc = p.getDocumentos().get(i);
                if(doc != null){
                    System.out.println("el doc: "+doc.toString());
                    //if(documentoDao.seModificoDocumento(doc)){
                        verificador = true;        
                        
                    //}
                }else
                    System.out.println(" el doc es null");
                    
            }            
            
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
