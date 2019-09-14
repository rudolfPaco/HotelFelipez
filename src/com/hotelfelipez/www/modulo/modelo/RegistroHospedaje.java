/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class RegistroHospedaje {
    private int id;
    private String nroRegistro;
    private String fechaLlegada;
    private String horaLlegada;
    private String fechaSalida;
    private String horaSalida;
    private double totalHospedaje;
    private double totalServicios;
    private double totalAcuenta;
    private double totalPagado;
    private String observacion;
    
    private ArrayList<Persona> listaPersonas;

    public RegistroHospedaje(int id) {
        this.id = id;
        this.listaPersonas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNroRegistro() {
        return nroRegistro;
    }
    public void setNroRegistro(String nroRegistro) {
        this.nroRegistro = nroRegistro;
    }
    public String getFechaLlegada() {
        return fechaLlegada;
    }
    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
    public String getHoraLlegada() {
        return horaLlegada;
    }
    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    public String getFechaSalida() {
        return fechaSalida;
    }
    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public String getHoraSalida() {
        return horaSalida;
    }
    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }
    public double getTotalHospedaje() {
        return totalHospedaje;
    }
    public void setTotalHospedaje(double totalHospedaje) {
        this.totalHospedaje = totalHospedaje;
    }
    public double getTotalServicios() {
        return totalServicios;
    }
    public void setTotalServicios(double totalServicios) {
        this.totalServicios = totalServicios;
    }
    public double getTotalAcuenta() {
        return totalAcuenta;
    }
    public void setTotalAcuenta(double totalAcuenta) {
        this.totalAcuenta = totalAcuenta;
    }
    public double getTotalPagado() {
        return totalPagado;
    }
    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }
    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
    public void setPersona(Persona persona){
        this.listaPersonas.add(persona);
    }
    @Override
    public String toString() {
        return "RegistroHospedaje{" + "id=" + id + ", nroRegistro=" + nroRegistro + ", fechaLlegada=" + fechaLlegada + ", horaLlegada=" + horaLlegada + ", fechaSalida=" + fechaSalida + ", horaSalida=" + horaSalida + ", totalHospedaje=" + totalHospedaje + ", totalServicios=" + totalServicios + ", totalAcuenta=" + totalAcuenta + ", totalPagado=" + totalPagado + ", observacion=" + observacion + ", listaPersonas=" + listaPersonas + '}';
    }
}
