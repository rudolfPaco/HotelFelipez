/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.etiquetas.IUEtiquetaR;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelCTU;
import com.aplicacionjava.www.paneles.IUPanelTT;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Hora;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CHabitaciones;
import com.hotelfelipez.www.modulo.controlador.CRegistroHospedaje;
import com.hotelfelipez.www.modulo.controlador.CRegistroPersona;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

/**
 *
 * @author neo
 */
public class IUVentanaRegistroHospedajeOcupado extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    private CHabitaciones controlHabitaciones;
    private Habitacion habitacion;
    private RegistroHospedaje registro;
    
    private IUPanel primerPanel;
    private IUEtiqueta tituloFecha;
    private IUEtiquetaR tituloReloj;
    private IUPanelTT iuNroRegistro;
    
    private IUPanel segundoPanel;
    private IUPanel panelFechaLlegada;
    private IUPanelCT iuFechaLlegada;
    private IUPanelCT iuHoraLlegada;
    private IUPanel panelFechaSalida;
    private IUPanelCT iuFechaSalida;
    private IUPanelCT iuHoraSalida;
    private IUPanel panelNroNoches;
    private IUEtiqueta tituloNroNoches;
    private IUEtiqueta iuNroNoches;
    private IUPanel panelHabitacion;
    private IUPanelCT iuHabitacion;
    private IUPanelBD panelPrecio;
    private IUPanelCTU iuPrecio;
    private IUBoton botonCambiarPrecio;
    
    private IUPanel tercerPanel;
    private IUBoton botonDatosPersonales;
    private IUBoton botonServicios;
    private IUBoton botonEstadoCuentas;
    private IUBoton botonEstadoReserva;
    private CardLayout administrador;
    
    private IUPanel cuartoPanel;
    private IUPanelRegistroPersonalOcupado iuRegistroDatos;
    private IUPanelBD iuServicios;
    private IUPanelBD iuEstadoCuentas;
    private IUPanelBD iuEstadoReserva;
    
    public IUVentanaRegistroHospedajeOcupado(IUVentanaHotel ventanaPrincipal, CHabitaciones controlHabitaciones, Habitacion habitacion, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlHabitaciones = controlHabitaciones;
        this.habitacion = habitacion;
        this.registro = null;
        construirPaneles(panelFondo.getLimitacion());
        setDatosCorrespondiente();
        escucharEventos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(7)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(0, limite.getPorcentajeAlto(7), limite.getAncho(), limite.getPorcentajeAlto(18)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
        
        tercerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(25), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(7)));
        //tercerPanel.setArco(10);
        panelFondo.add(tercerPanel);
        construirTercerPanel(tercerPanel.getLimitacion());
        
        administrador = new CardLayout();
        cuartoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(33), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(66)));
        cuartoPanel.setLayout(administrador);
        panelFondo.add(cuartoPanel);
        construirCuartoPanel(cuartoPanel.getLimitacion());
        
    }
    private void construirPrimerPanel(Limitacion limite){
        tituloFecha = new IUEtiqueta("Cochabamba, "+new Fecha().getFecha1(), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(65), limite.getPorcentajeAlto(98)));
        tituloFecha.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(50)));
        tituloFecha.setHorizontalAlignment(SwingConstants.CENTER);
        primerPanel.add(tituloFecha);
        
        tituloReloj = new IUEtiquetaR(new Limitacion(limite.getPorcentajeAncho(67), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(98)));
        tituloReloj.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(50)));
        tituloReloj.setHorizontalAlignment(SwingConstants.CENTER);
        primerPanel.add(tituloReloj);
        
        iuNroRegistro = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(86), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(98)), "Nro: ", "", 45, 55);
        iuNroRegistro.setFuente(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(50)), new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(50)));
        iuNroRegistro.iuTitulo.setForeground(new Color(120, 0, 0));
        iuNroRegistro.iuTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
        primerPanel.add(iuNroRegistro);
    }
    private void construirSegundoPanel(Limitacion limite){
        panelFechaLlegada = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(96)));
        segundoPanel.add(panelFechaLlegada);
        construirPanelFechaLlegada(panelFechaLlegada.getLimitacion());
        
        panelFechaSalida = new IUPanel(new Limitacion(limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(96)));
        segundoPanel.add(panelFechaSalida);
        construirPanelFechaSalida(panelFechaSalida.getLimitacion());
        
        panelNroNoches = new IUPanel(new Limitacion(limite.getPorcentajeAncho(33), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(96)));
        segundoPanel.add(panelNroNoches);
        construirPanelNroNoches(panelNroNoches.getLimitacion());
        
        panelHabitacion = new IUPanel(new Limitacion(limite.getPorcentajeAncho(69), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(96)));
        segundoPanel.add(panelHabitacion);
        construirPanelesHabitacion(panelHabitacion.getLimitacion());
        
        panelPrecio = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(85), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(96)));
        //segundoPanel.add(panelPrecio);
    }
    private void construirTercerPanel(Limitacion limite){
        botonDatosPersonales = new IUBoton("datos personales", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(70)));
        botonDatosPersonales.setSubrayado(true);
        tercerPanel.add(botonDatosPersonales);
        
        botonServicios = new IUBoton("servicios de hospedaje", new Limitacion(limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(70)));
        tercerPanel.add(botonServicios);
        
        botonEstadoCuentas = new IUBoton("estado de cuentas", new Limitacion(limite.getPorcentajeAncho(33), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(70)));
        tercerPanel.add(botonEstadoCuentas);
        
        botonEstadoReserva = new IUBoton("estado de reserva", new Limitacion(limite.getPorcentajeAncho(49), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(70)));
        tercerPanel.add(botonEstadoReserva);
    }    
    private void construirPanelFechaLlegada(Limitacion limite){
        iuFechaLlegada = new IUPanelCT("fecha de llegada", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(41)), 40, 60);
        iuFechaLlegada.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaLlegada.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaLlegada.iuTexto.setEditable(false);
        iuFechaLlegada.iuTexto.setFocusable(false);
        panelFechaLlegada.add(iuFechaLlegada);
        
        iuHoraLlegada = new IUPanelCT("hora de llegada", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(41)), 40, 60);
        iuHoraLlegada.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraLlegada.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraLlegada.iuTexto.setEditable(false);
        iuHoraLlegada.iuTexto.setFocusable(false);
        panelFechaLlegada.add(iuHoraLlegada);
    }
    private void construirPanelFechaSalida(Limitacion limite){
        iuFechaSalida = new IUPanelCT("fecha de salida", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(41)), 40, 60);
        iuFechaSalida.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaSalida.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaSalida.iuTexto.setEditable(false);
        iuFechaSalida.iuTexto.setFocusable(false);
        //iuFechaSalida.iuTitulo.setForeground(new Color(185, 142, 13));
        panelFechaSalida.add(iuFechaSalida);
        
        iuHoraSalida = new IUPanelCT("hora de salida", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(41)), 40, 60);
        iuHoraSalida.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraSalida.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraSalida.iuTexto.setEditable(false);
        iuHoraSalida.iuTexto.setFocusable(false);
        //iuHoraSalida.iuTitulo.setForeground(new Color(185, 142, 13));
        panelFechaSalida.add(iuHoraSalida);
    }
    private void construirPanelNroNoches(Limitacion limite){
        tituloNroNoches = new IUEtiqueta("nro de noches", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(7), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(20)));
        tituloNroNoches.setHorizontalAlignment(SwingConstants.CENTER);
        //tituloNroNoches.setForeground(new Color(185, 142, 13));
        tituloNroNoches.setFont(new Font("Verdana", Font.PLAIN, 19));
        //tituloNroNoches.setBorder(new LineBorder(Color.yellow));
        panelNroNoches.add(tituloNroNoches);
        
        iuNroNoches = new IUEtiqueta("", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(27), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(72)));
        iuNroNoches.setHorizontalAlignment(SwingConstants.CENTER);
        iuNroNoches.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(60)));
        panelNroNoches.add(iuNroNoches);
    }
    private void construirPanelesHabitacion(Limitacion limite){
        iuHabitacion = new IUPanelCT("habitacion", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(41)), 40, 60);
        iuHabitacion.iuTexto.setEditable(false);
        iuHabitacion.iuTexto.setFocusable(false);
        panelHabitacion.add(iuHabitacion);
        
        iuPrecio = new IUPanelCTU("precio x noche", "", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(41)), 40, 60, 50);
        iuPrecio.iuTexto.setEditable(false);
        iuPrecio.iuTexto.setFocusable(false);        
        panelHabitacion.add(iuPrecio);
         
        botonCambiarPrecio = new IUBoton("cambiar precio", new Limitacion(limite.getPorcentajeAncho(32), limite.getPorcentajeAlto(68), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(24)));
        panelHabitacion.add(botonCambiarPrecio);
    }
    private void construirCuartoPanel(Limitacion limite){
        CRegistroPersona control = new CRegistroPersona();
        iuRegistroDatos = new IUPanelRegistroPersonalOcupado(ventanaPrincipal, control, controlHabitaciones.getRegistroHospedaje(habitacion.getId()), this, limite);        
        cuartoPanel.add(iuRegistroDatos);
        
        iuServicios = new IUPanelBD(limite);
        cuartoPanel.add(iuServicios);
        
        iuEstadoCuentas = new IUPanelBD(limite);
        cuartoPanel.add(iuEstadoCuentas);
        
        iuEstadoReserva = new IUPanelBD(limite);
        cuartoPanel.add(iuEstadoReserva);
    }
    private void setDatosCorrespondiente(){
        this.registro = controlHabitaciones.getRegistroHospedaje(habitacion.getId());
        iuNroRegistro.iuTexto.setText(String.valueOf(registro.getId()));
        iuFechaLlegada.iuTexto.setText(new Fecha(registro.getFechaLlegada()).getFecha6());
        iuHoraLlegada.iuTexto.setText(new Hora(registro.getHoraLlegada()).getHora()+"   "+new Hora(registro.getHoraSalida()).getFormato());        
        iuFechaSalida.iuTexto.setText(new Fecha(registro.getFechaSalida()).getFecha6());
        iuHoraSalida.iuTexto.setText(new Hora(registro.getHoraSalida()).getHora()+"   "+new Hora(registro.getHoraSalida()).getFormato());
        iuNroNoches.setText(String.valueOf(registro.getNroNoches()));
        iuHabitacion.iuTexto.setText(habitacion.getNombreHabitacion());
        iuPrecio.iuTexto.setText(String.valueOf(Asistente.acotarNumero(habitacion.getTemporada().getPrecio(), 2)));
        iuPrecio.iuTexto.iuUnidad.setText(habitacion.getTemporada().getUnidadMoneda().getUnidad());
        iuRegistroDatos.agregarPersonas(registro.getListaPersonas());
    }
    private void escucharEventos(){
        botonDatosPersonales.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                estadoSubrayado(botonDatosPersonales.getTexto());
                administrador.first(cuartoPanel);
            }
        });
        botonServicios.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                estadoSubrayado(botonServicios.getTexto());                
                administrador.first(cuartoPanel);
                administrador.next(cuartoPanel);
            }
        });
        botonEstadoCuentas.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                estadoSubrayado(botonEstadoCuentas.getTexto());                
                administrador.last(cuartoPanel);
                administrador.previous(cuartoPanel);
            }
        });
        botonEstadoReserva.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                estadoSubrayado(botonEstadoReserva.getTexto());
                administrador.last(cuartoPanel);
            }
        });
    }
    private void estadoSubrayado(String nombreBoton){
        switch(nombreBoton){
            case "datos personales":
                botonDatosPersonales.setSubrayado(true);
                botonServicios.setSubrayado(false);
                botonEstadoCuentas.setSubrayado(false);
                botonEstadoReserva.setSubrayado(false);
            break;
            case "servicios de hospedaje":
                botonDatosPersonales.setSubrayado(false);
                botonServicios.setSubrayado(true);
                botonEstadoCuentas.setSubrayado(false);
                botonEstadoReserva.setSubrayado(false);
            break;
            case "estado de cuentas":
                botonDatosPersonales.setSubrayado(false);
                botonServicios.setSubrayado(false);
                botonEstadoCuentas.setSubrayado(true);
                botonEstadoReserva.setSubrayado(false);
            break;
            case "estado de reserva":
                botonDatosPersonales.setSubrayado(false);
                botonServicios.setSubrayado(false);
                botonEstadoCuentas.setSubrayado(false);
                botonEstadoReserva.setSubrayado(true);
            break;
        }
    }
}
