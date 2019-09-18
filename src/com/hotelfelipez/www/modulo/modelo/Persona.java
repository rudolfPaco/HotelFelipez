/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

import com.aplicacionjava.www.recursos.Fecha;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class Persona {
    private int id;

    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String carnetIdentidad;
    private String origen;
    private String fechaCaducidad;
    private String fechaNacimiento;
    private String ciudad;
    private String pais;
    private String estadoCivil;
    private String profesion;
    private String direccion;
    private String procedencia;
    private String destino;
    private String telefono;
    private String email;
    private String tipoPersona;
    private String observacion;
    
    private String estado;
    private String estadoDocumentos;
    
    private ArrayList<Documento> documentos;
    
    public Persona(int id) {
        this.id = id;
        this.documentos = new ArrayList<>();
        this.estadoDocumentos = "NO";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public String getCarnetIdentidad() {
        return carnetIdentidad;
    }
    public void setCarnetIdentidad(String carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
    }
    public String getOrigen() {
        return origen;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }
    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getEstadoCivil() {
        return estadoCivil;
    }
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public String getProfesion() {
        return profesion;
    }
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getProcedencia() {
        return procedencia;
    }
    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTipoPersona() {
        return tipoPersona;
    }
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getEstado() {        
        if(!nombres.isEmpty() && !apellidos.isEmpty() && !fechaNacimiento.isEmpty() && !tipoDocumento.isEmpty() && !carnetIdentidad.isEmpty() && !origen.isEmpty() && !fechaCaducidad.isEmpty())
            estado = "COMPLETO";
        else
            estado = "INCOMPLETO";
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }
    public void setDocumentos(ArrayList<Documento> documentos) {
        this.documentos = documentos;
    }
    public void setDocumento(Documento documento){
        this.documentos.add(documento);
    }
    public String getEstadoDocumentos() {
        return estadoDocumentos;
    }
    public void setEstadoDocumentos(String estadoDocumentos) {
        this.estadoDocumentos = estadoDocumentos;
    }
    public int getEdad(){
        int edad = 0;
        if(!fechaNacimiento.isEmpty())
            edad = new Fecha(fechaNacimiento).getEdad();
        return edad;
    }
    public String getCaducidad(){
        String respuesta = "VALIDO";
        if(!fechaCaducidad.isEmpty()){
            if(new Fecha(fechaCaducidad).esMenor(new Fecha()))
                respuesta = "INVALIDO";
        }else
            respuesta = "";
        return respuesta;
    }    
    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", tipoDocumento=" + tipoDocumento + ", carnetIdentidad=" + carnetIdentidad + ", origen=" + origen + ", fechaCaducidad=" + fechaCaducidad + ", fechaNacimiento=" + fechaNacimiento + ", ciudad=" + ciudad + ", pais=" + pais + ", estadoCivil=" + estadoCivil + ", profesion=" + profesion + ", direccion=" + direccion + ", procedencia=" + procedencia + ", destino=" + destino + ", telefono=" + telefono + ", email=" + email + ", tipoPersona=" + tipoPersona + ", observacion=" + observacion + ", estado=" + estado + ", estadoDocumentos=" + estadoDocumentos + ", documentos=" + documentos + '}';
    }
}