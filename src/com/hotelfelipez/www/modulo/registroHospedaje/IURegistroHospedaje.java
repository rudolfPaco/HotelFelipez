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
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Hora;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CRegistroHospedaje;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author hotel-felipez
 */
public class IURegistroHospedaje extends IUVentanaT{
    
    private CRegistroHospedaje control;
    private Habitacion hab;
    private Fecha fechaLlegada;
    private Fecha fechaSalida;
    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanel primerPanel;
    private IUEtiqueta fechaHoy;
    private IUEtiquetaR relojHoy;
    private IUPanelBD segundoPanel;
    private IUPanelCT panelHabitacion;
    private IUPanelCTU panelPrecio;
    private IUPanelBD tercerPanel;
    private IUPanelCT panelFechaIngreso;
    private IUPanelCTU panelHoraIngreso;
    private IUPanelCT panelFechaSalida;
    private IUPanelCTU panelHoraSalida;
    private IUEtiqueta tituloNoches;
    private IUEtiqueta numeroNoches;
    
    private IUPanel cuartoPanel;
    private IUBoton botonRegistroPersonas;
    private IUBoton botonRegistroHospedaje;
    private IUBoton botonReservado;
    private CardLayout administrador;
    private IUPanelBD panelAdministrador;
    private IUPanelRegistroPersonal panelRegistroPersonas;
    private IUPanel panelRegistroHospedaje;
    private IUPanelBD panelReservas;
    private IUBoton botonGuardarRegistro;
    private IUBoton botonSalirRegistro;
    
    private boolean estado;
    
    public IURegistroHospedaje(IUVentanaHotel ventanaPrincipal, CRegistroHospedaje control, Habitacion hab, Fecha fechaLlegada, Fecha fechaSalida, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.control = control;
        this.hab = hab;        
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.estado = false;
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(6)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(8), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(8)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
        
        tercerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(15)));
        panelFondo.add(tercerPanel);
        construirTercerPanel(tercerPanel.getLimitacion());
        
        cuartoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(33), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(60)));
        //cuartoPanel.setArco(5);
        panelFondo.add(cuartoPanel);
        construirCuartoPanel(cuartoPanel.getLimitacion());
        
        botonSalirRegistro = new IUBoton("salir registro", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(94), limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(5)));
        panelFondo.add(botonSalirRegistro);
        
        botonGuardarRegistro = new IUBoton("guardar registro", new Limitacion(limite.getPorcentajeAncho(52), limite.getPorcentajeAlto(94), limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(5)));
        panelFondo.add(botonGuardarRegistro);
    }
    private void construirPrimerPanel(Limitacion limite){
        fechaHoy = new IUEtiqueta(new Fecha().getFecha1(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(96)));
        fechaHoy.setHorizontalAlignment(SwingConstants.CENTER);
        fechaHoy.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(50)));
        primerPanel.add(fechaHoy);
        
        relojHoy = new IUEtiquetaR(new Limitacion(limite.getPorcentajeAncho(64), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(34), limite.getPorcentajeAlto(96)));
        relojHoy.setHorizontalAlignment(SwingConstants.CENTER);
        relojHoy.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(50)));
        primerPanel.add(relojHoy);
    }
    private void construirSegundoPanel(Limitacion limite){
        panelHabitacion = new IUPanelCT("habitacion", hab.getNombreHabitacion(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(74), limite.getPorcentajeAlto(90)), 40, 60);
        panelHabitacion.iuTexto.setFont(new Font("Verdanda", Font.PLAIN, 28));
        panelHabitacion.iuTitulo.setFont(new Font("Verdanda", Font.PLAIN, 17));
        panelHabitacion.iuTexto.setEditable(false);
        panelHabitacion.iuTexto.setFocusable(false);
        panelHabitacion.iuTexto.setBorder(null);
        segundoPanel.add(panelHabitacion);
        
        panelPrecio = new IUPanelCTU("precio", String.valueOf(Asistente.acotarNumero(hab.getTemporada().getPrecio(), 2)), hab.getTemporada().getUnidadMoneda().getUnidad(), new Limitacion(limite.getPorcentajeAncho(78), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(94)), 40, 60, 50);
        panelPrecio.setFuente(new Font("Verdana", Font.PLAIN, 16), new Font("Verdanda", Font.PLAIN, 28));
        panelPrecio.iuTexto.setEditable(false);
        panelPrecio.iuTexto.setFocusable(false);
        panelPrecio.iuTexto.setBorder(null);
        segundoPanel.add(panelPrecio);
    }
    private void construirTercerPanel(Limitacion limite){
        panelFechaIngreso = new IUPanelCT("fecha ingreso", fechaLlegada.getFecha5().toUpperCase(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(42)), 40, 60);
        panelFechaIngreso.iuTexto.setEditable(false);
        panelFechaIngreso.iuTexto.setFocusable(false);
        panelFechaIngreso.iuTexto.setBorder(null);
        tercerPanel.add(panelFechaIngreso);
        
        panelHoraIngreso = new IUPanelCTU("hora ingreso", new Hora().getHora(), new Hora().getFormato(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(42)), 40, 60, 40);
        panelHoraIngreso.iuTexto.setEditable(false);
        panelHoraIngreso.iuTexto.setFocusable(false);
        panelHoraIngreso.iuTexto.setBorder(null);
        tercerPanel.add(panelHoraIngreso);
        
        panelFechaSalida = new IUPanelCT("fecha salida", fechaSalida.getFecha5().toUpperCase(), new Limitacion(limite.getPorcentajeAncho(36), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(42)), 40, 60);
        panelFechaSalida.iuTexto.setEditable(false);
        panelFechaSalida.iuTexto.setFocusable(false);
        panelFechaSalida.iuTexto.setBorder(null);
        tercerPanel.add(panelFechaSalida);
        
        panelHoraSalida = new IUPanelCTU("hora salida", "12:00:00", new Hora().getFormato(), new Limitacion(limite.getPorcentajeAncho(36), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(42)), 40, 60, 40);
        panelHoraSalida.iuTexto.setEditable(false);
        panelHoraSalida.iuTexto.setFocusable(false);
        panelHoraSalida.iuTexto.setBorder(null);
        tercerPanel.add(panelHoraSalida);
        
        tituloNoches = new IUEtiqueta("numero noches", new Limitacion(limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(28), limite.getPorcentajeAlto(17)));
        tituloNoches.setHorizontalAlignment(SwingConstants.CENTER);
        tituloNoches.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(13)));
        tercerPanel.add(tituloNoches);
        
        numeroNoches = new IUEtiqueta(String.valueOf(fechaSalida.restarDias(fechaLlegada)), new Limitacion(limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(22), limite.getPorcentajeAncho(28), limite.getPorcentajeAlto(72)));
        numeroNoches.setBorder(new LineBorder(Color.LIGHT_GRAY));
        numeroNoches.setHorizontalAlignment(SwingConstants.CENTER);
        numeroNoches.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(50)));
        tercerPanel.add(numeroNoches);
    }
    private void construirCuartoPanel(Limitacion limite){
        botonRegistroPersonas = new IUBoton("registro de personas", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(7)));
        cuartoPanel.add(botonRegistroPersonas);
        
        botonReservado = new IUBoton("habitacion reservada", new Limitacion(limite.getPorcentajeAncho(24), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(7)));
        cuartoPanel.add(botonReservado);
        
        botonRegistroHospedaje = new IUBoton("registro de hospedaje", new Limitacion(limite.getPorcentajeAncho(46), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(7)));
        //cuartoPanel.add(botonRegistroHospedaje);
        
        administrador = new CardLayout();
        panelAdministrador = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(89)));
        panelAdministrador.setArco(10);
        panelAdministrador.setLayout(administrador);
        cuartoPanel.add(panelAdministrador);
        
        panelRegistroPersonas = new IUPanelRegistroPersonal(ventanaPrincipal, this, panelAdministrador.getLimitacion());
        panelAdministrador.add(panelRegistroPersonas);
        
        panelRegistroHospedaje = new IUPanel(panelAdministrador.getLimitacion());
        panelAdministrador.add(panelRegistroHospedaje);
        
        panelReservas = new IUPanelBD(panelAdministrador.getLimitacion());
        panelAdministrador.add(panelReservas);
    }
    private void escucharEventos(){
        botonGuardarRegistro.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(estaValidado()){
                    if(Asistente.mensajeVerificacion(ventanaPrincipal, "peligro", "esta seguro que desea guardar un NUEVO REGISTRO DE HOSPEDAJE...?", "advertencia")){
                        estado = true;
                        dispose();
                    }
                }
            }
        });
        botonSalirRegistro.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
    }
    private boolean estaValidado(){
        boolean verificador = false;
        if(panelRegistroPersonas.existePersonaRegistro()){
            verificador = true;
        }else{
            setOpacity(0.3f);
            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... para guardar el registro de hopedaje... debe tener al menos una persona registrada...!", "advertencia");            
            setOpacity(1f);
        }            
        return verificador;
    }
    public boolean getEstado(){
        return estado;
    }
    public RegistroHospedaje getRegistroHospedaje(){
        RegistroHospedaje registro = new RegistroHospedaje(0);
        registro.setFechaLlegada(fechaLlegada.getFecha());
        registro.setHoraLlegada(panelHoraIngreso.iuTexto.getText());
        registro.setFechaSalida(fechaSalida.getFecha());
        registro.setHoraSalida(panelHoraSalida.iuTexto.getText());
        registro.setTotalHospedaje(Double.parseDouble(panelPrecio.iuTexto.getText())*Integer.parseInt(numeroNoches.getText()));
        registro.setTotalServicios(0.0);
        registro.setTotalAcuenta(0.0);
        registro.setTotalPagado(0.0);
        registro.setObservacion("");
        registro.setListaPersonas(panelRegistroPersonas.getListaPersonas());
        return registro;
    }
}