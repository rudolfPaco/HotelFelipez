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
public class Prestacion {
    
    private int id;
    private String primerServicio;
    private String segundoServicio;
    private String tercerServicio;
    private String cuartoServicio;
    private String quintoServicio;
    private String sextoServicio;

    public Prestacion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPrimerServicio() {
        return primerServicio;
    }
    public void setPrimerServicio(String primerServicio) {
        this.primerServicio = primerServicio;
    }
    public String getSegundoServicio() {
        return segundoServicio;
    }
    public void setSegundoServicio(String segundoServicio) {
        this.segundoServicio = segundoServicio;
    }
    public String getTercerServicio() {
        return tercerServicio;
    }
    public void setTercerServicio(String tercerServicio) {
        this.tercerServicio = tercerServicio;
    }
    public String getCuartoServicio() {
        return cuartoServicio;
    }
    public void setCuartoServicio(String cuartoServicio) {
        this.cuartoServicio = cuartoServicio;
    }
    public String getQuintoServicio() {
        return quintoServicio;
    }
    public void setQuintoServicio(String quintoServicio) {
        this.quintoServicio = quintoServicio;
    }
    public String getSextoServicio() {
        return sextoServicio;
    }
    public void setSextoServicio(String sextoServicio) {
        this.sextoServicio = sextoServicio;
    }
    @Override
    public String toString() {
        return "Prestacion{" + "id=" + id + ", primerServicio=" + primerServicio + ", segundoServicio=" + segundoServicio + ", tercerServicio=" + tercerServicio + ", cuartoServicio=" + cuartoServicio + ", quintoServicio=" + quintoServicio + ", sextoServicio=" + sextoServicio + '}';
    }
}
