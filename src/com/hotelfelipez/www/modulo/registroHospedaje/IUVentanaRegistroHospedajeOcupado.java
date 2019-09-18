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
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

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
    
    private IUPanelBD tercerPanel;
    private IUBoton botonDatosPersonales;
    private IUBoton botonServicios;
    private IUBoton botonEstadoCuentas;
    private IUBoton botonEstadoReserva;
    private CardLayout administrador;
    private IUPanelBD cuartoPanel;
    
    public IUVentanaRegistroHospedajeOcupado(IUVentanaHotel ventanaPrincipal, CHabitaciones controlHabitaciones, Habitacion habitacion, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        this.controlHabitaciones = controlHabitaciones;
        this.habitacion = habitacion;
        this.registro = null;
        construirPaneles(panelFondo.getLimitacion());
        setDatosCorrespondiente();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(7)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(0, limite.getPorcentajeAlto(7), limite.getAncho(), limite.getPorcentajeAlto(18)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
        
        tercerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(25), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(7)));
        tercerPanel.setArco(10);
        panelFondo.add(tercerPanel);
        construirTercerPanel(tercerPanel.getLimitacion());
        
        cuartoPanel = new IUPanelBD(new Limitacion(0, limite.getPorcentajeAlto(33), limite.getAncho(), limite.getPorcentajeAlto(67)));
        cuartoPanel.setArco(10);
        panelFondo.add(cuartoPanel);
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
        tercerPanel.add(botonDatosPersonales);
        
        botonServicios = new IUBoton("servicios de hospedaje", new Limitacion(limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(70)));
        tercerPanel.add(botonServicios);
        
        botonEstadoCuentas = new IUBoton("estado de cuentas", new Limitacion(limite.getPorcentajeAncho(33), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(70)));
        tercerPanel.add(botonEstadoCuentas);
        
        botonEstadoReserva = new IUBoton("estado de reserva", new Limitacion(limite.getPorcentajeAncho(49), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(70)));
        tercerPanel.add(botonEstadoReserva);
    }
    private void construirPanelFechaLlegada(Limitacion limite){
        iuFechaLlegada = new IUPanelCT("fecha de llegada", new Fecha().getFecha6(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(41)), 40, 60);
        iuFechaLlegada.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaLlegada.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaLlegada.iuTexto.setEditable(false);
        iuFechaLlegada.iuTexto.setFocusable(false);
        panelFechaLlegada.add(iuFechaLlegada);
        
        iuHoraLlegada = new IUPanelCT("hora de llegada", new Hora().getHora()+"   "+new Hora().getFormato(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(41)), 40, 60);
        iuHoraLlegada.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraLlegada.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraLlegada.iuTexto.setEditable(false);
        iuHoraLlegada.iuTexto.setFocusable(false);
        panelFechaLlegada.add(iuHoraLlegada);
    }
    private void construirPanelFechaSalida(Limitacion limite){
        iuFechaSalida = new IUPanelCT("fecha de salida", new Fecha().getFecha6(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(41)), 40, 60);
        iuFechaSalida.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaSalida.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaSalida.iuTexto.setEditable(false);
        iuFechaSalida.iuTexto.setFocusable(false);
        //iuFechaSalida.iuTitulo.setForeground(new Color(185, 142, 13));
        panelFechaSalida.add(iuFechaSalida);
        
        iuHoraSalida = new IUPanelCT("hora de salida", new Hora().getHora()+"   "+new Hora().getFormato(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(41)), 40, 60);
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
        
        iuNroNoches = new IUEtiqueta("74", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(27), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(72)));
        iuNroNoches.setHorizontalAlignment(SwingConstants.CENTER);
        iuNroNoches.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(60)));
        panelNroNoches.add(iuNroNoches);
    }
    private void construirPanelesHabitacion(Limitacion limite){
        iuHabitacion = new IUPanelCT("habitacion", "204 TM (TRIPLE MATRIMONIAL)", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(41)), 40, 60);
        iuHabitacion.iuTexto.setEditable(false);
        iuHabitacion.iuTexto.setFocusable(false);
        panelHabitacion.add(iuHabitacion);
        
        iuPrecio = new IUPanelCTU("precio x noche", "280.0", "BOB.-", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(41)), 40, 60, 50);
        iuPrecio.iuTexto.setEditable(false);
        iuPrecio.iuTexto.setFocusable(false);        
        panelHabitacion.add(iuPrecio);
         
        botonCambiarPrecio = new IUBoton("cambiar precio", new Limitacion(limite.getPorcentajeAncho(32), limite.getPorcentajeAlto(68), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(24)));
        panelHabitacion.add(botonCambiarPrecio);
    }
    private void setDatosCorrespondiente(){
        this.registro = controlHabitaciones.getRegistroHospedaje(habitacion.getId());
        iuNroRegistro.iuTexto.setText(registro.getNroRegistro());
        iuFechaLlegada.iuTexto.setText(new Fecha(registro.getFechaLlegada()).getFecha6());
        iuHoraLlegada.iuTexto.setText(new Hora(registro.getHoraLlegada()).getHora()+"   "+new Hora(registro.getHoraSalida()).getFormato());        
        iuFechaSalida.iuTexto.setText(new Fecha(registro.getFechaSalida()).getFecha6());
        iuHoraSalida.iuTexto.setText(new Hora(registro.getHoraSalida()).getHora()+"   "+new Hora(registro.getHoraSalida()).getFormato());
        iuNroNoches.setText(String.valueOf(registro.getNroNoches()));
        iuHabitacion.iuTexto.setText(habitacion.getNombreHabitacion());
        iuPrecio.iuTexto.setText(String.valueOf(Asistente.acotarNumero(habitacion.getTemporada().getPrecio(), 2)));
        iuPrecio.iuTexto.iuUnidad.setText(habitacion.getTemporada().getUnidadMoneda().getUnidad());
    }
}
