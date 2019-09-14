/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

/**
 *
 * @author Rudolf Felipez Mancilla
 */
public class Frigobar {
    private int id;
    private String nombreFrigobar;
    private int cantidadProductos;
    private int idhabitacion;

    public Frigobar(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreFrigobar() {
        return nombreFrigobar;
    }
    public void setNombreFrigobar(String nombreFrigobar) {
        this.nombreFrigobar = nombreFrigobar;
    }
    public int getCantidadProductos() {
        return cantidadProductos;
    }
    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }
    public int getIdhabitacion() {
        return idhabitacion;
    }
    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }
    @Override
    public String toString() {
        return "Frigobar{" + "id=" + id + ", nombreFrigobar=" + nombreFrigobar + ", cantidadProductos=" + cantidadProductos + ", idhabitacion=" + idhabitacion + '}';
    }
}
