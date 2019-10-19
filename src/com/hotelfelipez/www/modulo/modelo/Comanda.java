/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

import java.util.ArrayList;

/**
 *
 * @author rudolf
 */
public class Comanda {
    private int id;
    private String nroComanda;
    private String nombre;
    private String fecha;
    private String hora;
    private double total;
    private String estado;
    private int idRegistroHospedaje;
    private ArrayList<Detalle> lista;
    private boolean check;

    public Comanda(int id) {
        this.id = id;
        lista = new ArrayList<>();        
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNroComanda() {
        return nroComanda;
    }
    public void setNroComanda(String nroComanda) {
        this.nroComanda = nroComanda;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getIdRegistroHospedaje() {
        return idRegistroHospedaje;
    }
    public void setIdRegistroHospedaje(int idRegistroHospedaje) {
        this.idRegistroHospedaje = idRegistroHospedaje;
    }
    public ArrayList<Detalle> getLista() {
        return lista;
    }
    public void setLista(ArrayList<Detalle> lista) {
        this.lista = lista;
    }
    public void setDetalle(Detalle detalle){
        lista.add(detalle);
    }
    public boolean isCheck() {
        return check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", nroComanda=" + nroComanda + ", nombre=" + nombre + ", fecha=" + fecha + ", hora=" + hora + ", total=" + total + ", estado=" + estado + ", idRegistroHospedaje=" + idRegistroHospedaje + ", lista=" + lista + ", check=" + check + '}';
    }
}
