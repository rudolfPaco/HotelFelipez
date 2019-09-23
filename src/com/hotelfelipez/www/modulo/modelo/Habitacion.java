/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

/**
 *
 * @author hotel-felipez
 */
public class Habitacion {
    private int id;
    private String numero;
    private String simbolo;
    private String tipo;
    private String estilo;
    private String estado;
    private String numeroPiso;
    private String capacidad;
    private String numeroTelefono;
    private String detalle;
    private String descripcion;
    private String sofaCama;
    private double costoSofaCama;
    private String disponible;
    private String promocionFrigobar;
    private String tipoVenta;
    private String tipoTemporada;
    private int idtemporada;
    private int idmoneda;
    private int idprestacion;
    
    private Temporada temporada;
    private Prestacion prestacion;
    private Frigobar frigobar;
    
    private boolean check;

    public Habitacion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getSimbolo() {
        return simbolo;
    }
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getEstilo() {
        return estilo;
    }
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getNumeroPiso() {
        return numeroPiso;
    }
    public void setNumeroPiso(String numeroPiso) {
        this.numeroPiso = numeroPiso;
    }
    public String getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
    public String getNumeroTelefono() {
        return numeroTelefono;
    }
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    public String getDetalle() {
        return detalle;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getSofaCama() {
        return sofaCama;
    }
    public void setSofaCama(String sofaCama) {
        this.sofaCama = sofaCama;
    }
    public double getCostoSofaCama() {
        return costoSofaCama;
    }
    public void setCostoSofaCama(double costoSofaCama) {
        this.costoSofaCama = costoSofaCama;
    }
    public String getDisponible() {
        return disponible;
    }
    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }
    public String getPromocionFrigobar() {
        return promocionFrigobar;
    }
    public void setPromocionFrigobar(String promocionFrigobar) {
        this.promocionFrigobar = promocionFrigobar;
    }
    public String getTipoVenta() {
        return tipoVenta;
    }
    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
    public String getTipoTemporada() {
        return tipoTemporada;
    }
    public void setTipoTemporada(String tipoTemporada) {
        this.tipoTemporada = tipoTemporada;
    }
    public int getIdtemporada() {
        return idtemporada;
    }
    public void setIdtemporada(int idtemporada) {
        this.idtemporada = idtemporada;
    }
    public int getIdmoneda() {
        return idmoneda;
    }
    public void setIdmoneda(int idmoneda) {
        this.idmoneda = idmoneda;
    }
    public int getIdprestacion() {
        return idprestacion;
    }
    public void setIdprestacion(int idprestacion) {
        this.idprestacion = idprestacion;
    }
    public Temporada getTemporada() {
        return temporada;
    }
    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }
    public Prestacion getPrestacion() {
        return prestacion;
    }
    public void setPrestacion(Prestacion prestacion) {
        this.prestacion = prestacion;
    }
    public Frigobar getFrigobar() {
        return frigobar;
    }
    public void setFrigobar(Frigobar frigobar) {
        this.frigobar = frigobar;
    }
    public boolean isCheck() {
        return check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    public String getNombreHabitacion(){
        return numero+" "+simbolo+" ("+tipo+") "+estilo;
    }
    public String getNombreHabitacionCorto(){
        return numero+" "+simbolo;
    }
    @Override
    public String toString() {
        return "Habitacion{" + "id=" + id + ", numero=" + numero + ", simbolo=" + simbolo + ", tipo=" + tipo + ", nombre=" + estilo + ", estado=" + estado + ", numeroPiso=" + numeroPiso + ", capacidad=" + capacidad + ", numeroTelefono=" + numeroTelefono + ", detalle=" + detalle + ", descripcion=" + descripcion + ", sofaCama=" + sofaCama + ", costoSofaCama=" + costoSofaCama + ", disponible=" + disponible + ", promocionFrigobar=" + promocionFrigobar + ", tipoVenta=" + tipoVenta + ", tipoTemporada=" + tipoTemporada + ", idtemporada=" + idtemporada + ", idmoneda=" + idmoneda + ", idprestacion=" + idprestacion + ", temporada=" + temporada + ", prestacion=" + prestacion + ", frigobar=" + frigobar + ", check=" + check + '}';
    }
}