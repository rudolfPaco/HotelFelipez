/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

import com.aplicacionjava.www.recursos.Fecha;

/**
 *
 * @author Jesus Junior Felipez
 */
public class Producto {
    private int id;
    private String categoria;
    private String marca;
    private String nombre;
    private double costo;
    private double precio;
    private int cantidad;
    private String fechaVencimiento;
    private String promocion;
    private double precioAnterior;
    private int idmoneda;
    private int idfrigobar;
    private int idhabitacion;

    private Moneda moneda;
    
    public Producto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getCosto() {
        return costo;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public String getPromocion() {
        return promocion;
    }
    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }
    public double getPrecioAnterior() {
        return precioAnterior;
    }
    public void setPrecioAnterior(double precioAnterior) {
        this.precioAnterior = precioAnterior;
    }
    public int getIdmoneda() {
        return idmoneda;
    }
    public void setIdmoneda(int idmoneda) {
        this.idmoneda = idmoneda;
    }
    public int getIdfrigobar() {
        return idfrigobar;
    }
    public void setIdfrigobar(int idfrigobar) {
        this.idfrigobar = idfrigobar;
    }
    public int getIdhabitacion() {
        return idhabitacion;
    }
    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }
    public Moneda getMoneda() {
        return moneda;
    }
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    public int numeroDias(){
        Fecha fecha = new Fecha(fechaVencimiento);
        return fecha.restarDias(new Fecha());
    }
    public void adicionarCantidad(int cantAdicional){
        cantidad = cantidad + cantAdicional;
    }
    public void reducirCantidad(int cantReducir){
        if(cantidad > 0){
            cantidad = cantidad - cantReducir;
            System.out.println("entro agregar cantidad....");
        }            
    }
    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", categoria=" + categoria + ", marca=" + marca + ", nombre=" + nombre + ", costo=" + costo + ", precio=" + precio + ", cantidad=" + cantidad + ", fechaVencimiento=" + fechaVencimiento + ", promocion=" + promocion + ", precioAnterior=" + precioAnterior + ", idmoneda=" + idmoneda + ", idfrigobar=" + idfrigobar + ", idhabitacion=" + idhabitacion + '}';
    }       
}