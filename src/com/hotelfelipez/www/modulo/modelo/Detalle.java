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
public class Detalle {
    private int id;
    private String descripcion;
    private double precio;
    private double cantidad;
    private String unidad;
    private double total;
    private int idComanda;
    private int idRegistroHospedaje;
    private Producto producto;
    
    public Detalle(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
        this.total = cantidad  * precio;
    }
    public String getUnidad() {
        return unidad;
    }
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public int getIdComanda() {
        return idComanda;
    }
    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }
    public int getIdRegistroHospedaje() {
        return idRegistroHospedaje;
    }
    public void setIdRegistroHospedaje(int idRegistroHospedaje) {
        this.idRegistroHospedaje = idRegistroHospedaje;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    @Override
    public String toString() {
        return "Detalle{" + "id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + ", cantidad=" + cantidad + ", unidad=" + unidad + ", total=" + total + ", idComanda=" + idComanda + ", idRegistroHospedaje=" + idRegistroHospedaje + ", producto=" + producto + '}';
    }
}
