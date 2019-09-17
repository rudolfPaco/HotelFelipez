/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.prestacion;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CPrestacion;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Prestacion;
import com.hotelfelipez.www.modulo.monedas.IUModificarMoneda;
import com.hotelfelipez.www.modulo.monedas.IUNuevaMoneda;
import com.hotelfelipez.www.modulo.monedas.IUTablaMonedas;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author neo
 */
public class IUVentanaPrestacion extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    private CPrestacion controlPrestacion;
    
    private IUPanelPrestacion panelPrestacion;    
    private IUPanel segundoPanel;
    private IUBoton botonNuevaPrestacion;
    private IUBoton botonModificarPrestacion;
    private IUBoton botonEliminarPrestacion;
    private IUBoton botonSalir;
    
    public IUVentanaPrestacion(IUVentanaHotel ventanaPrincipal, CPrestacion controlPrestacion, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 8);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlPrestacion = controlPrestacion;
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
        actualizarDatosPrestaciones();
        restringirBotones();
    }
    private void construirPaneles(Limitacion limite){
        panelPrestacion = new IUPanelPrestacion(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(96)));
        panelFondo.add(panelPrestacion);
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(64), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(34), limite.getPorcentajeAlto(90)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }    
    private void construirSegundoPanel(Limitacion limite){
        botonNuevaPrestacion = new IUBoton("nueva prestacion", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(8)));
        segundoPanel.add(botonNuevaPrestacion);
        
        botonModificarPrestacion = new IUBoton("modificar prestacion", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(15), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(8)));
        segundoPanel.add(botonModificarPrestacion);
        
        botonEliminarPrestacion = new IUBoton("eliminar prestacion", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(28), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(8)));
        segundoPanel.add(botonEliminarPrestacion);
        
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(90), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(8)));
        segundoPanel.add(botonSalir);
    }
    private void restringirBotones(){
        if(controlPrestacion.existeAlgunServicio()){
            botonNuevaPrestacion.setVisible(false);
            botonModificarPrestacion.setVisible(true);
            botonEliminarPrestacion.setVisible(true);
        }            
        else{
            botonNuevaPrestacion.setVisible(true);
            botonModificarPrestacion.setVisible(false);
            botonEliminarPrestacion.setVisible(false);
        }
    }
    private void escucharEventos(){
        botonNuevaPrestacion.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.3f);
                IUNuevaPrestacion iuNuevaPrestacion = new IUNuevaPrestacion(ventanaPrincipal, "nueva prestacion", new Limitacion(ventanaPrincipal.ANCHO/4, ventanaPrincipal.ALTO - ventanaPrincipal.ALTO/3));                
                iuNuevaPrestacion.mostrarVentana();
                if(iuNuevaPrestacion.getEstado()){
                    controlPrestacion.agregarNuevaPrestacion(iuNuevaPrestacion.getPrestacion());
                    actualizarDatosPrestaciones();
                }
                setOpacity(1f);
            }
        });
        botonModificarPrestacion.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.3f);
                IUModificarPrestacion iuModificarPrestacion = new IUModificarPrestacion(ventanaPrincipal, "modificar moneda", new Limitacion(ventanaPrincipal.ANCHO/4, ventanaPrincipal.ALTO - ventanaPrincipal.ALTO/3));
                iuModificarPrestacion.setPrestacion(controlPrestacion.getPrestacion());
                iuModificarPrestacion.mostrarVentana();
                if(iuModificarPrestacion.getEstado()){
                    controlPrestacion.modificarPrestacion(iuModificarPrestacion.getPrestacion());
                    actualizarDatosPrestaciones();
                }
                setOpacity(1f);
            }
        });
        botonEliminarPrestacion.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(controlPrestacion.eliminarPrestacion(controlPrestacion.getPrestacion())){
                    actualizarDatosPrestaciones();
                }else{
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no se puede eliminar esta moneda... por que esta enlazado a una tabla de B.D.", "advertencia");
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
    
    private void actualizarDatosPrestaciones(){        
        Prestacion prestacion = controlPrestacion.getPrestacion();
        panelPrestacion.setPrestacion(prestacion);
        restringirBotones();
    }
    
}
