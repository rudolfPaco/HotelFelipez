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
    
    private IUPanel tercerPanel;
    private IUBoton botonDatosPersonales;
    private IUBoton botonServicios;
    private IUBoton botonEstadoCuentas;
    private IUBoton botonEstadoReserva;
    private CardLayout administrador;
    
    private IUPanel cuartoPanel;
    private IUPanelDatosPersonales iuRegistroDatos;
    private IUPanelServicios iuServicios;
    private IUPanelBD iuEstadoCuentas;
    private IUPanelBD iuEstadoReserva;
    
    public IUVentanaRegistroHospedajeOcupado(IUVentanaHotel ventanaPrincipal, CHabitaciones controlHabitaciones, Habitacion habitacion, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlHabitaciones = controlHabitaciones;
        this.habitacion = habitacion;
        this.registro = controlHabitaciones.getRegistroHospedaje(habitacion.getId());
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
        iuFechaLlegada = new IUPanelCT("fecha de llegada", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(41)), 30, 70);
        iuFechaLlegada.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaLlegada.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaLlegada.setBorder(new LineBorder(new Color(190, 146, 12)));
        iuFechaLlegada.iuTexto.setBorder(null);
        iuFechaLlegada.iuTexto.setEditable(false);
        iuFechaLlegada.iuTexto.setFocusable(false);
        segundoPanel.add(iuFechaLlegada);
        
        iuHoraLlegada = new IUPanelCT("hora de llegada", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(41)), 30, 70);
        iuHoraLlegada.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraLlegada.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraLlegada.setBorder(new LineBorder(new Color(190, 146, 12)));
        iuHoraLlegada.iuTexto.setBorder(null);
        iuHoraLlegada.iuTexto.setEditable(false);
        iuHoraLlegada.iuTexto.setFocusable(false);
        segundoPanel.add(iuHoraLlegada);        
        
        iuFechaSalida = new IUPanelCT("fecha de salida", "", new Limitacion(limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(41)), 30, 70);
        iuFechaSalida.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaSalida.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuFechaSalida.setBorder(new LineBorder(new Color(190, 146, 12)));
        iuFechaSalida.iuTexto.setBorder(null);
        iuFechaSalida.iuTexto.setEditable(false);
        iuFechaSalida.iuTexto.setFocusable(false);
        //iuFechaSalida.iuTitulo.setForeground(new Color(185, 142, 13));
        segundoPanel.add(iuFechaSalida);
        
        iuHoraSalida = new IUPanelCT("hora de salida", "", new Limitacion(limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(41)), 30, 70);
        iuHoraSalida.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraSalida.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuHoraSalida.setBorder(new LineBorder(new Color(190, 146, 12)));
        iuHoraSalida.iuTexto.setBorder(null);
        iuHoraSalida.iuTexto.setEditable(false);
        iuHoraSalida.iuTexto.setFocusable(false);
        //iuHoraSalida.iuTitulo.setForeground(new Color(185, 142, 13));
        segundoPanel.add(iuHoraSalida);        
        
        tituloNroNoches = new IUEtiqueta("nro de noches", new Limitacion(limite.getPorcentajeAncho(33), limite.getPorcentajeAlto(7), limite.getPorcentajeAncho(12), limite.getPorcentajeAlto(15)));
        tituloNroNoches.setHorizontalAlignment(SwingConstants.CENTER);
        //tituloNroNoches.setForeground(new Color(185, 142, 13));
        tituloNroNoches.setFont(new Font("Verdana", Font.PLAIN, 19));
        //tituloNroNoches.setBorder(new LineBorder(Color.yellow));
        segundoPanel.add(tituloNroNoches);
        
        iuNroNoches = new IUEtiqueta("", new Limitacion(limite.getPorcentajeAncho(33), limite.getPorcentajeAlto(22), limite.getPorcentajeAncho(12), limite.getPorcentajeAlto(71)));
        iuNroNoches.setHorizontalAlignment(SwingConstants.CENTER);
        iuNroNoches.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(60)));
        iuNroNoches.setBorder(new LineBorder(new Color(190, 146, 12)));
        segundoPanel.add(iuNroNoches);
                
        iuHabitacion = new IUPanelCT("habitacion", "", new Limitacion(limite.getPorcentajeAncho(46), limite.getPorcentajeAlto(26), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(68)), 23, 77);
        iuHabitacion.iuTexto.setEditable(false);
        iuHabitacion.iuTexto.setFocusable(false);
        iuHabitacion.iuTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(40)));
        iuHabitacion.setBorder(new LineBorder(new Color(190, 146, 12)));        
        iuHabitacion.iuTexto.setBorder(null);
        iuHabitacion.iuTexto.setBorder(null);
        segundoPanel.add(iuHabitacion);
        
        iuPrecio = new IUPanelCTU("precio x noche", "", "", new Limitacion(limite.getPorcentajeAncho(72), limite.getPorcentajeAlto(26), limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(68)), 23, 77, 50);
        iuPrecio.iuTexto.setEditable(false);
        iuPrecio.iuTexto.setFocusable(false);
        iuPrecio.iuTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(40)));
        iuPrecio.iuTexto.iuUnidad.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(40)));
        iuPrecio.setBorder(new LineBorder(new Color(190, 146, 12)));        
        iuPrecio.iuTexto.setBorder(null);
        segundoPanel.add(iuPrecio);
         
        botonCambiarPrecio = new IUBoton("cambiar precio", new Limitacion(limite.getPorcentajeAncho(91), limite.getPorcentajeAlto(68), limite.getPorcentajeAncho(8), limite.getPorcentajeAlto(24)));
        //segundoPanel.add(botonCambiarPrecio);
        
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
    private void construirCuartoPanel(Limitacion limite){
        iuRegistroDatos = new IUPanelDatosPersonales(ventanaPrincipal, new CRegistroPersona(registro), this, limite);
        cuartoPanel.add(iuRegistroDatos);
        
        iuServicios = new IUPanelServicios(limite);
        cuartoPanel.add(iuServicios);
        
        iuEstadoCuentas = new IUPanelBD(limite);
        cuartoPanel.add(iuEstadoCuentas);
        
        iuEstadoReserva = new IUPanelBD(limite);
        cuartoPanel.add(iuEstadoReserva);
    }
    private void setDatosCorrespondiente(){
        iuNroRegistro.iuTexto.setText(String.valueOf(registro.getId()));
        iuFechaLlegada.iuTexto.setText(new Fecha(registro.getFechaLlegada()).getFecha6());
        iuHoraLlegada.iuTexto.setText(new Hora(registro.getHoraLlegada()).getHora()+"   "+new Hora(registro.getHoraLlegada()).getFormato());        
        iuFechaSalida.iuTexto.setText(new Fecha(registro.getFechaSalida()).getFecha6());
        iuHoraSalida.iuTexto.setText(new Hora(registro.getHoraSalida()).getHora()+"   "+new Hora(registro.getHoraSalida()).getFormato());
        iuNroNoches.setText(String.valueOf(registro.getNroNoches()));
        iuHabitacion.iuTexto.setText(habitacion.getNombreHabitacionCorto());
        iuHabitacion.iuTitulo.setText("habitacion ("+habitacion.getTipo()+" - "+habitacion.getEstilo()+")");
        iuPrecio.iuTexto.setText(String.valueOf(Asistente.acotarNumero(habitacion.getTemporada().getPrecio(), 2)));
        iuPrecio.iuTexto.iuUnidad.setText(habitacion.getTemporada().getUnidadMoneda().getUnidad());
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
