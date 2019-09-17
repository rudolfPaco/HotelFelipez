/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.habitaciones;

import com.hotelfelipez.www.modulo.temporadas.IUVentanaTemporadas;
import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CFrigobar;
import com.hotelfelipez.www.modulo.controlador.CHabitaciones;
import com.hotelfelipez.www.modulo.controlador.CMonedas;
import com.hotelfelipez.www.modulo.controlador.CPrestacion;
import com.hotelfelipez.www.modulo.controlador.CTemporadas;
import com.hotelfelipez.www.modulo.frigobar.IUPanelFrigobar;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.monedas.IUVentanaMonedas;
import com.hotelfelipez.www.modulo.prestacion.IUVentanaPrestacion;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.SwingConstants;

/**
 *
 * @author neo
 */
public class IUVentanaHabitaciones extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    private CHabitaciones controlHabitaciones;
    
    private IUPanelBD primerPanel;
    private IUEtiqueta tituloPrimerPanel;
    private IUTablaHabitaciones tablaHabitaciones;
    private IUBoton botonTemporadaPrecios;
    private IUBoton botonServicios;
    private IUBoton botonMoneda;
    
    private IUPanel segundoPanel;
    private IUEtiqueta tituloEtiqueta;    
    private IUBoton botonNuevaHabitacion;
    private IUBoton botonModificarHabitacion;
    private IUBoton botonDeshabilitarHabitacion;
    private IUBoton botonHistorialMantenimiento;
    private IUBoton botonImprimirHabitacion;
    
    private IUPanelBD tercerPanel;
    private IUPanelHabitacion panelHabitacion;
    private IUPanelFrigobar panelFrigobar;
    
    private IUBoton botonDatosHabitacion;
    private IUBoton botonFrigobarHabitacion;
    private CardLayout administrador;    
    private IUPanelBD panelContenedorHabitaciones;    
        
    public IUVentanaHabitaciones(CHabitaciones controlHabitaciones, IUVentanaHotel ventanaPrincipal, String titulo) {
        super(ventanaPrincipal, titulo, new Limitacion(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height), 4);
        this.controlHabitaciones = controlHabitaciones;
        this.ventanaPrincipal = ventanaPrincipal;        
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
        restringirBotones();
        actualizarTablaHabitaciones();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(20), limite.getAlto()));
        primerPanel.setArco(0);
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(20), 0, limite.getPorcentajeAncho(80), limite.getAlto()));
        //segundoPanel.setArco(0);
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        tituloPrimerPanel = new IUEtiqueta("HAB. REGISTRADAS", new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(4)));
        tituloPrimerPanel.setFont(new Font("Verdana", Font.PLAIN, 16));
        tituloPrimerPanel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPrimerPanel.setSubrayarTexto(true);
        primerPanel.add(tituloPrimerPanel);       
        
        tablaHabitaciones = new IUTablaHabitaciones(this, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(84)));
        primerPanel.add(tablaHabitaciones.tabla.deslizador);
        
        botonServicios = new IUBoton("servicios", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(90), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(4)));
        primerPanel.add(botonServicios);
        
        botonMoneda = new IUBoton("monedas", new Limitacion(limite.getPorcentajeAncho(52), limite.getPorcentajeAlto(90), limite.getPorcentajeAncho(43), limite.getPorcentajeAlto(4)));
        primerPanel.add(botonMoneda);
        
        botonTemporadaPrecios = new IUBoton("temporada de precios", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(95), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(4)));
        primerPanel.add(botonTemporadaPrecios);
    }
    private void construirSegundoPanel(Limitacion limite){
        tituloEtiqueta = new IUEtiqueta("", new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(4)));
        tituloEtiqueta.setFont(new Font("Verdana", Font.PLAIN, 16));
        tituloEtiqueta.setForeground(new Color(120, 0, 0));
        tituloEtiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        tituloEtiqueta.setSubrayarTexto(true);
        segundoPanel.add(tituloEtiqueta);
        
        tercerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(87)));
        tercerPanel.setArco(20);
        segundoPanel.add(tercerPanel);
        construirPanelContenedorHabitacion(tercerPanel.getLimitacion());
        
        botonNuevaHabitacion = new IUBoton("crear nueva habitacion", new Limitacion(limite.getPorcentajeAncho(81), limite.getPorcentajeAlto(94), limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(4)));
        segundoPanel.add(botonNuevaHabitacion);
        
        botonModificarHabitacion = new IUBoton("modificar habitacion", new Limitacion(limite.getPorcentajeAncho(61), limite.getPorcentajeAlto(94), limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(4)));
        segundoPanel.add(botonModificarHabitacion);
        
        botonDeshabilitarHabitacion = new IUBoton("deshabilitar habitacion", new Limitacion(limite.getPorcentajeAncho(41), limite.getPorcentajeAlto(94), limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(4)));
        segundoPanel.add(botonDeshabilitarHabitacion);
        
        botonImprimirHabitacion = new IUBoton("imprimir habitacion", new Limitacion(limite.getPorcentajeAncho(21), limite.getPorcentajeAlto(94), limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(4)));
        segundoPanel.add(botonImprimirHabitacion);
        
        botonHistorialMantenimiento = new IUBoton("historial mantenimiento", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(94), limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(4)));
        segundoPanel.add(botonHistorialMantenimiento);
    }
    private void construirPanelContenedorHabitacion(Limitacion limite){
        botonDatosHabitacion = new IUBoton("datos de habitacion", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(5)));
        tercerPanel.add(botonDatosHabitacion);
        
        botonFrigobarHabitacion = new IUBoton("frigobar de habitacion", new Limitacion(limite.getPorcentajeAncho(19), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(5)));
        tercerPanel.add(botonFrigobarHabitacion);
                
        administrador = new CardLayout();
        panelContenedorHabitaciones = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(9), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(89)));
        panelContenedorHabitaciones.setArco(5);        
        panelContenedorHabitaciones.setLayout(administrador);
        tercerPanel.add(panelContenedorHabitaciones);
        construirPanelesHabitaciones(panelContenedorHabitaciones.getLimitacion());
    }
    private void construirPanelesHabitaciones(Limitacion limite){
        panelHabitacion = new IUPanelHabitacion(limite);
        panelContenedorHabitaciones.add(panelHabitacion);
        
        CFrigobar controlFrigobar = new CFrigobar();        
        panelFrigobar = new IUPanelFrigobar(controlFrigobar, ventanaPrincipal,this , limite);
        controlFrigobar.controlarPanelFrigobar(panelFrigobar);
        
        panelContenedorHabitaciones.add(panelFrigobar);
    }
    private void restringirBotones(){
        if(Asistente.existeAlgunDato("idmoneda", "select idmoneda from moneda") && Asistente.existeAlgunDato("idprestacion", "select idprestacion from prestacion")){
            botonTemporadaPrecios.setVisible(true);
            segundoPanel.setVisible(true);
        }else{
            botonTemporadaPrecios.setVisible(false);
            segundoPanel.setVisible(false);
        }
        if(Asistente.existeAlgunDato("idtemporada", "select idtemporada from temporada"))
            segundoPanel.setVisible(true);
        else
            segundoPanel.setVisible(false);
    }
    public void mostrarHabitacion(Habitacion hab){
        tituloEtiqueta.setText("HABITACION: "+hab.getNumero()+" "+hab.getSimbolo()+" ("+hab.getTipo().toUpperCase()+")");        
        panelHabitacion.setHabitacion(hab); 
        panelFrigobar.setHabitacion(hab);
    }
    private void actualizarTablaHabitaciones(){
        ArrayList<Habitacion> listaHabitaciones = controlHabitaciones.getListaHabitaciones();
        tablaHabitaciones.limpiarTabla();
        
        for (int i = 0; i < listaHabitaciones.size(); i++) {
            Habitacion hab = listaHabitaciones.get(i);
            tablaHabitaciones.setFila(hab);
        }
    }
    private void escucharEventos(){
        botonDatosHabitacion.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                administrador.first(panelContenedorHabitaciones);
            }
        });
        botonFrigobarHabitacion.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                administrador.last(panelContenedorHabitaciones);
            }
        });
        botonServicios.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ventanaPrincipal.setOpacity(0.4f);
                setOpacity(0.4f);

                CPrestacion controlPrestaciones = new CPrestacion();
                IUVentanaPrestacion iuPrestacion = new IUVentanaPrestacion(ventanaPrincipal, controlPrestaciones, "prestacion de servicios", new Limitacion(ventanaPrincipal.ANCHO/3, ventanaPrincipal.ALTO/2));
                controlPrestaciones.controlarIUPrestacion(iuPrestacion);                
                iuPrestacion.mostrarVentana();
                restringirBotones();

                ventanaPrincipal.setOpacity(1f);
                setOpacity(1f);
            }
        });
        botonMoneda.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ventanaPrincipal.setOpacity(0.4f);
                setOpacity(0.4f);

                CMonedas controlMonedas = new CMonedas();
                IUVentanaMonedas iuMonedas = new IUVentanaMonedas(ventanaPrincipal, controlMonedas, "unidad de monedas", new Limitacion(ventanaPrincipal.ANCHO/3, ventanaPrincipal.ALTO/2));
                controlMonedas.controlarIUMonedas(iuMonedas);                
                iuMonedas.mostrarVentana();
                restringirBotones();

                ventanaPrincipal.setOpacity(1f);
                setOpacity(1f);
            }
        });
        botonTemporadaPrecios.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ventanaPrincipal.setOpacity(0.4f);
                setOpacity(0.4f);

                CTemporadas controlTemporadas = new CTemporadas();
                IUVentanaTemporadas iuTemporadas = new IUVentanaTemporadas(ventanaPrincipal, controlTemporadas, "precios de temporada", new Limitacion(ventanaPrincipal.ANCHO - ventanaPrincipal.ANCHO/5, ventanaPrincipal.ALTO - ventanaPrincipal.ALTO/4));
                controlTemporadas.controlarIUTemporadas(iuTemporadas);                
                iuTemporadas.mostrarVentana();
                restringirBotones();

                ventanaPrincipal.setOpacity(1f);
                setOpacity(1f);
            }
        });
        
        botonNuevaHabitacion.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ventanaPrincipal.setOpacity(0.4f);
                setOpacity(0.4f);
                
                IUNuevaHabitacion nuevaHabitacion = new IUNuevaHabitacion(ventanaPrincipal, controlHabitaciones, "CREAR NUEVA HABITACION", new Limitacion(ventanaPrincipal.ANCHO/2, ventanaPrincipal.ALTO));
                nuevaHabitacion.mostrarVentana();
                if(nuevaHabitacion.getEstado()){
                    controlHabitaciones.agregarNuevaHabitacion(nuevaHabitacion.getHabitacion());
                    Asistente.mensajeVerificacion(ventanaPrincipal, "correcto", "en hora buena... se guardo la nueva habitacion correctamente...!", "correcto.");
                    actualizarTablaHabitaciones();
                }                
                
                ventanaPrincipal.setOpacity(1.0f);
                setOpacity(1.0f);             
            }
        });
        
    }
}
