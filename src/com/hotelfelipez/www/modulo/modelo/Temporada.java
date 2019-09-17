/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

/**
 *
 * @author neo
 */
public class Temporada {

    private int id;
    private String tipoTemporada;
    private double precio;
    private Moneda unidadMoneda;
    private String motivo;

    public Temporada(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipoTemporada() {
        return tipoTemporada;
    }
    public void setTipoTemporada(String tipoTemporada) {
        this.tipoTemporada = tipoTemporada;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public Moneda getUnidadMoneda() {
        return unidadMoneda;
    }
    public void setUnidadMoneda(Moneda unidadMoneda) {
        this.unidadMoneda = unidadMoneda;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    @Override
    public String toString() {
        return "Temporada{" + "id=" + id + ", tipoTemporada=" + tipoTemporada + ", precio=" + precio + ", unidadMoneda=" + unidadMoneda + ", motivo=" + motivo + '}';
    }
}