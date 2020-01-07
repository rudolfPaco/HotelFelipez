/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

/**
 *
 * @author rudolf
 */
public class Recibo {

    private int id;
    private String nroRecibo;
    private String fecha;
    private String nombrePersona;
    private double montoRecibido;
    private String montoLiteral;
    private String descripcion;
    private double total;
    private double pago;
    private double cambio;
    private String estado;

    public Recibo(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNroRecibo() {
        return nroRecibo;
    }
    public void setNroRecibo(String nroRecibo) {
        this.nroRecibo = nroRecibo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getNombrePersona() {
        return nombrePersona;
    }
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }
    public double getMontoRecibido() {
        return montoRecibido;
    }
    public void setMontoRecibido(double montoRecibido) {
        this.montoRecibido = montoRecibido;
    }
    public String getMontoLiteral() {
        return montoLiteral;
    }
    public void setMontoLiteral(String montoLiteral) {
        this.montoLiteral = montoLiteral;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public double getPago() {
        return pago;
    }
    public void setPago(double pago) {
        this.pago = pago;
    }
    public double getCambio() {
        return cambio;
    }
    public void setCambio(double cambio) {
        this.cambio = cambio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    @Override
    public String toString() {
        return "Recibo{" + "id=" + id + ", nroRecibo=" + nroRecibo + ", fecha=" + fecha + ", nombrePersona=" + nombrePersona + ", montoRecibido=" + montoRecibido + ", montoLiteral=" + montoLiteral + ", descripcion=" + descripcion + ", total=" + total + ", pago=" + pago + ", cambio=" + cambio + ", estado=" + estado + '}';
    }
}