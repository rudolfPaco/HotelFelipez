/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.temporadas;

import com.hotelfelipez.www.modulo.temporadas.IUTablaTemporadas;
import com.hotelfelipez.www.modulo.temporadas.IUPanelTemporada;
import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CTemporadas;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author neo
 */
public class IUVentanaTemporadas extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    private CTemporadas controlTemporadas;
    
    private IUPanel primerPanel;
    private IUPanelTemporada panelTemporada;
    private IUPanel segundoPanel;
    public IUTablaTemporadas tablaTemporadas;
    private IUPanel tercerPanel;
    private IUBoton botonNuevaTemporada;
    private IUBoton botonModificarTemporada;
    private IUBoton botonEliminarTemporada;
    private IUBoton botonSalir;
    
    public IUVentanaTemporadas(IUVentanaHotel ventanaPrincipal, CTemporadas controlTemporadas, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 5);        
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlTemporadas = controlTemporadas;
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
        actualizarTablaTemporadas();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(98)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(37), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(98)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
        
        tercerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(83), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(16), limite.getPorcentajeAlto(98)));
        panelFondo.add(tercerPanel);
        construirTercerPanel(tercerPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        panelTemporada = new IUPanelTemporada(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(25)));
        primerPanel.add(panelTemporada);
    }
    private void construirSegundoPanel(Limitacion limite){
        tablaTemporadas = new IUTablaTemporadas(this, new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(96)));
        segundoPanel.add(tablaTemporadas.tabla.deslizador);
    }    
    private void construirTercerPanel(Limitacion limite){
        botonNuevaTemporada = new IUBoton("nueva Temporada", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(5)));
        tercerPanel.add(botonNuevaTemporada);
        
        botonModificarTemporada = new IUBoton("modificar Temporada", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(9), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(5)));
        tercerPanel.add(botonModificarTemporada);
        
        botonEliminarTemporada = new IUBoton("eliminar Temporada", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(5)));
        tercerPanel.add(botonEliminarTemporada);
        
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(90), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(5)));
        tercerPanel.add(botonSalir);
    }
    private void escucharEventos(){
        botonNuevaTemporada.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.2f);
                IUNuevaTemporada nuevaTemporada = new IUNuevaTemporada(ventanaPrincipal, controlTemporadas, "construir nueva temporada", new Limitacion(ventanaPrincipal.ANCHO/3, ventanaPrincipal.ALTO/2), 8);
                nuevaTemporada.mostrarVentana();
                if(nuevaTemporada.getEstado()){
                    controlTemporadas.agregarNuevaTemporada(nuevaTemporada.getTemporada());
                    actualizarTablaTemporadas();
                }
                setOpacity(1f);
            }
        });
        botonModificarTemporada.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaTemporadas.estaSeleccionado()){
                    setOpacity(0.2f);
                    IUModificarTemporada modificarTemporada = new IUModificarTemporada(ventanaPrincipal, controlTemporadas, "construir nueva temporada", new Limitacion(ventanaPrincipal.ANCHO/3, ventanaPrincipal.ALTO/2), 8);
                    modificarTemporada.setTemporada(tablaTemporadas.getTemporada());
                    modificarTemporada.mostrarVentana();
                    if(modificarTemporada.getEstado()){
                        controlTemporadas.modificarTemporada(modificarTemporada.getTemporada());
                        actualizarTablaTemporadas();
                    }
                    setOpacity(1f);
                }else{
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero debe seleccionar una fila de la tabla", "advertencia");
                }
            }
        });
        botonEliminarTemporada.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaTemporadas.estaSeleccionado()){
                    if(controlTemporadas.eliminarTemporada(tablaTemporadas.getTemporada())){
                        actualizarTablaTemporadas();
                    }else{
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no se puede eliminar esta temporada... por que esta enlazado a una tabla de B.D.", "advertencia");
                    }
                }else{
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero debe seleccionar una fila de la tabla", "advertencia");
                }
            }
        });
        botonSalir.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
    }
    private void actualizarTablaTemporadas(){
        tablaTemporadas.limpiarTabla();
        panelTemporada.setLimpiarDatosPanelTemporada();
        ArrayList<Temporada> listaTemporadas = controlTemporadas.getListaTemporadas();
        for (int i = 0; i < listaTemporadas.size(); i++) {
            Temporada temporada = listaTemporadas.get(i);
            tablaTemporadas.setFila(temporada);
        }
    }
    public void mostrarDatosTemporada(Temporada temporada){
        panelTemporada.setDatosTemporada(temporada);
    }
}
