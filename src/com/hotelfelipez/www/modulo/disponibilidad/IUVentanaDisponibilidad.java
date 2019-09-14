/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.disponibilidad;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.botones.IUBotonCheckBox;
import com.aplicacionjava.www.calendario.IUCalendario;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelS;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CDisponibilidad;
import com.hotelfelipez.www.modulo.controlador.CHabitaciones;
import com.hotelfelipez.www.modulo.controlador.CRegistroHospedaje;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import com.hotelfelipez.www.modulo.registroHospedaje.IURegistroHospedaje;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;

/**
 *
 * @author rudolf
 */
public class IUVentanaDisponibilidad extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanelBD primerPanel;
    private IUPanelCT panelFechaLlegada;
    private IUBoton botonFechaLlegada;    
    private IUPanelCT panelFechaSalida;
    private IUBoton botonFechaSalida;
    private IUPanelS panelNoches;
    private IUBotonCheckBox botonSimple;
    private IUBotonCheckBox botonMatrimonial;
    private IUBotonCheckBox botonDobleSimple;
    private IUBotonCheckBox botonTripleMatrimonial;
    private IUBotonCheckBox botonFamiliar;
    private IUBoton botonBuscar;
    private IUPanelBD segundoPanel;
    private IUPanelBD tercerPanel;
    private IUTablaDisponibilidad tablaDisponibilidad;
    private IUPanelBD cuartoPanel;
    private IUBoton botonReservar;
    private IUBoton botonOcupar;
    private String simple;
    private String matrimonial;
    private String dobleSimple;
    private String tripleMatrimonial;
    private String familiar;
    private IUBoton boton1;
    private IUBoton boton2;
    private IUBoton boton3;
    
    private CDisponibilidad control;
    
    public IUVentanaDisponibilidad(IUVentanaHotel ventanaPrincipal, CDisponibilidad control, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.control = control;
        this.simple = "NO";
        this.matrimonial = "NO";
        this.dobleSimple = "NO";
        this.tripleMatrimonial = "NO";
        this.familiar = "NO";
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(7)));
        primerPanel.setArco(1);
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(0, limite.getPorcentajeAlto(7), limite.getAncho(), limite.getPorcentajeAlto(10)));
        segundoPanel.setArco(1);
        panelFondo.add(segundoPanel);
        
        tercerPanel = new IUPanelBD(new Limitacion(0, limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(85), limite.getPorcentajeAlto(83)));
        tercerPanel.setArco(1);
        panelFondo.add(tercerPanel);
        construirTercerPanel(tercerPanel.getLimitacion());
        
        cuartoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(85), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(83)));
        cuartoPanel.setArco(1);
        panelFondo.add(cuartoPanel);
        construirCuartoPanel(cuartoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        panelFechaLlegada = new IUPanelCT("fecha llegada", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(4), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(92)), 40, 60);
        panelFechaLlegada.iuTexto.setEditable(false);
        panelFechaLlegada.iuTexto.setFocusable(false);
        primerPanel.add(panelFechaLlegada);
        
        botonFechaLlegada = new IUBoton("llegada", new Limitacion(limite.getPorcentajeAncho(11), limite.getPorcentajeAlto(4), limite.getPorcentajeAncho(4), limite.getPorcentajeAlto(92)));
        primerPanel.add(botonFechaLlegada);
        
        panelFechaSalida = new IUPanelCT("fecha salida", "", new Limitacion(limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(4), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(92)), 40, 60);
        panelFechaSalida.iuTexto.setEditable(false);
        panelFechaSalida.iuTexto.setFocusable(false);
        primerPanel.add(panelFechaSalida);
        
        botonFechaSalida = new IUBoton("salida", new Limitacion(limite.getPorcentajeAncho(27), limite.getPorcentajeAlto(4), limite.getPorcentajeAncho(4), limite.getPorcentajeAlto(92)));
        primerPanel.add(botonFechaSalida);
        botonFechaSalida.setVisible(false);
        
        panelNoches = new IUPanelS("nro noches", 0, 1, 100, new Limitacion(limite.getPorcentajeAncho(32), limite.getPorcentajeAlto(4), limite.getPorcentajeAncho(7), limite.getPorcentajeAlto(92)), 40, 60, 20);
        panelNoches.iuTexto.setEditable(false);
        panelNoches.iuTexto.setFocusable(false);
        primerPanel.add(panelNoches);
       
        botonSimple = new IUBotonCheckBox("SIMPLE", new Limitacion(limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(4), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(46)), false);        
        botonSimple.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(30)));
        primerPanel.add(botonSimple);
        
        botonMatrimonial = new IUBotonCheckBox("MATRIMONIAL", new Limitacion(limite.getPorcentajeAncho(56), limite.getPorcentajeAlto(4), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(46)), false);
        botonMatrimonial.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(30)));
        primerPanel.add(botonMatrimonial);
        
        botonDobleSimple = new IUBotonCheckBox("DOBLE SIMPLE", new Limitacion(limite.getPorcentajeAncho(73), limite.getPorcentajeAlto(4), limite.getPorcentajeAncho(13), limite.getPorcentajeAlto(46)), false);
        botonDobleSimple.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(30)));
        primerPanel.add(botonDobleSimple);
        
        botonTripleMatrimonial = new IUBotonCheckBox("TRIPLE MATRIMONIAL", new Limitacion(limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(46)), false);
        botonTripleMatrimonial.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(30)));
        primerPanel.add(botonTripleMatrimonial);
        
        botonFamiliar = new IUBotonCheckBox("FAMILIAR", new Limitacion(limite.getPorcentajeAncho(67), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(46)), false);
        botonFamiliar.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(30)));
        primerPanel.add(botonFamiliar);
        
        botonBuscar = new IUBoton("buscar habitaciones", new Limitacion(limite.getPorcentajeAncho(88), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(11), limite.getPorcentajeAlto(60)));
        primerPanel.add(botonBuscar);
    }
    private void construirTercerPanel(Limitacion limite){
        tablaDisponibilidad = new IUTablaDisponibilidad(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(98)));
        tercerPanel.add(tablaDisponibilidad.tabla.deslizador);
    }
    private void construirCuartoPanel(Limitacion limite){
        botonReservar = new IUBoton("reservar habitaciones", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(5)));
        cuartoPanel.add(botonReservar);
        
        botonOcupar = new IUBoton("check in habitacion", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(9), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(5)));
        cuartoPanel.add(botonOcupar);
    }
    private void escucharEventos(){
        botonSimple.addItemListener((ItemEvent e) -> {
            if(botonSimple.isSelected())
                simple = "SIMPLE";
            else
                simple = "";
        });
        botonMatrimonial.addItemListener((ItemEvent e) -> {
            if(botonMatrimonial.isSelected())
                matrimonial = "MATRIMONIAL";
            else
                matrimonial = "";
        });
        botonDobleSimple.addItemListener((ItemEvent e) -> {
            if(botonDobleSimple.isSelected())
                dobleSimple = "DOBLE SIMPLE";
            else
                dobleSimple = "";
        });
        botonTripleMatrimonial.addItemListener((ItemEvent e) -> {
            if(botonTripleMatrimonial.isSelected())
                tripleMatrimonial = "TRIPLE MATRIMONIAL";
            else
                tripleMatrimonial = "";
        });
        botonFamiliar.addItemListener((ItemEvent e) -> {
            if(botonFamiliar.isSelected())
                familiar = "FAMILIAR";
            else
                familiar = "";
        });
        botonFechaLlegada.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                IUCalendario calendario = new IUCalendario(ventanaPrincipal, new Fecha().getFecha2(), new Fecha(), new Limitacion(Asistente.ANCHO/3, Asistente.ALTO/2));
                calendario.mostrarVentana();
                if(calendario.getEstado()){
                    Fecha fecha = calendario.getFecha();
                    if(new Fecha().esMayor(fecha) || new Fecha().esIgual(fecha)){
                        panelFechaLlegada.iuTexto.setText(fecha.getFecha());
                        panelNoches.iuTexto.setText("1");
                        panelFechaSalida.iuTexto.setText(fecha.getProximoDia().getFecha());
                        botonFechaSalida.setVisible(true);
                    }else{
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... no puede ingresar una fecha anterior a la fecha de hoy", "advertencia");
                    }                    
                }
            }
        });
        botonFechaSalida.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                IUCalendario calendario = new IUCalendario(ventanaPrincipal, new Fecha().getFecha2(), new Fecha(), new Limitacion(Asistente.ANCHO/2, Asistente.ALTO/2));
                calendario.mostrarVentana();
                if(calendario.getEstado()){
                    Fecha fecha = calendario.getFecha();
                    Fecha fechaLlegada = new Fecha(panelFechaLlegada.iuTexto.getText());
                    if(fechaLlegada.esMayor(fecha) || fechaLlegada.esIgual(fecha)){
                        panelFechaSalida.iuTexto.setText(fecha.getFecha());
                        panelNoches.iuTexto.setText(String.valueOf(fecha.restarDias(fechaLlegada)));                        
                    }else
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede elegir una fecha anterior a la fecha de llegada...!", "advertencia");
                }
            }
        });
        panelNoches.botonArriba.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!panelFechaLlegada.iuTexto.getText().isEmpty()){
                    Fecha fechaSalida = new Fecha(panelFechaSalida.iuTexto.getText());
                    panelFechaSalida.iuTexto.setText(fechaSalida.getFechaProximoAnterior(1).getFecha());
                    panelNoches.presionoBotonArriba();
                }                
            }
        });
        panelNoches.botonAbajo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!panelFechaLlegada.iuTexto.getText().isEmpty()){
                    Fecha fechaSalida = new Fecha(panelFechaSalida.iuTexto.getText());
                    if(Integer.parseInt(panelNoches.getTexto()) > 0){
                        panelFechaSalida.iuTexto.setText(fechaSalida.getFechaProximoAnterior(-1).getFecha());
                        panelNoches.presionoBotonAbajo();
                    }
                }                
            }
        });
        botonBuscar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                if(!panelFechaLlegada.iuTexto.getText().isEmpty() && !panelFechaSalida.iuTexto.getText().isEmpty() && (!simple.isEmpty() || !matrimonial.isEmpty() || !dobleSimple.isEmpty() || !tripleMatrimonial.isEmpty() || !familiar.isEmpty())){
                    buscarHabitacionesDisponibles(new Fecha(panelFechaLlegada.iuTexto.getText()), new Fecha(panelFechaSalida.iuTexto.getText()));
                }else{
                    setOpacity(0.3f);
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento...! NO puede buscar habitaciones disponibles.... es necesario la fecha de llegada, fecha de salida y el tipo de habitacion...!", "advertencia");
                    setOpacity(1.f);
                }
                    
                
            }
        });
        botonOcupar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ArrayList<Habitacion> lista = tablaDisponibilidad.getHabitacionesSeleccionadas();
                if(!lista.isEmpty()){
                    if(lista.size() > 1){
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... para el REGISTRO DE HOSPEDAJE, solo necesita seleccionar una habitacion", "advertencia");
                    }else{
                        if(tablaDisponibilidad.getHabitacionMarcado()){
                            ventanaPrincipal.setOpacity(0.3f);
                            setOpacity(0.3f);

                            Habitacion hab = tablaDisponibilidad.getHabitacionMarcada();
                            CRegistroHospedaje control = new CRegistroHospedaje();
                            IURegistroHospedaje iuRegistro = new IURegistroHospedaje(ventanaPrincipal, control, hab, new Fecha(panelFechaLlegada.iuTexto.getText()), new Fecha(panelFechaSalida.iuTexto.getText()), "registro de hospedaje", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/3 - Asistente.ANCHO/10, Asistente.ALTO));
                            control.controlarRegistroHospedaje(iuRegistro);
                            iuRegistro.mostrarVentana();
                            if(iuRegistro.getEstado()){
                                RegistroHospedaje registro = iuRegistro.getRegistroHospedaje();
                                hab.setEstado("OCUPADO");
                                control.guardarNuevoRegistroHospedaje(registro, hab);
                                if(Asistente.mensajeVerificacion(ventanaPrincipal, "correcto", "en hora buena... se guardo correctamente el NUEVO REGISTRO DE HOSPEDAJE...!", "advertencia")){
                                    if(Asistente.mensajeVerificacion(ventanaPrincipal, "peligro", "desea ir al NUEVO REGISTRO DE HOSPEDAJE...?", "advertencia")){
                                        //IR AL REGISTRO DE HOSPEDAJE
                                    }else{
                                        //ACTUALIZAR LA LISTA DE BUSQUEDA DE HABITACINES DISPONIBLES
                                    }
                                }
                            }

                            setOpacity(1f);
                            ventanaPrincipal.setOpacity(1f);                            
                        }
                        
                    }
                    
                }                
            }
        });
        botonReservar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaDisponibilidad.estaSeleccionado()){
                    Habitacion hab = tablaDisponibilidad.getHabitacion();
                    System.out.println(hab.toString());
                }
            }
        });
    }
    private void buscarHabitacionesDisponibles(Fecha fechaLlegada, Fecha fechaSalida){
        
        CHabitaciones control = new CHabitaciones();        
        ArrayList<Habitacion> lista = control.getListaHabitaciones(simple, matrimonial, dobleSimple, tripleMatrimonial, familiar);
        tablaDisponibilidad.limpiar();
        for (int i = 0; i < lista.size(); i++) {
            Habitacion hab = lista.get(i);
            tablaDisponibilidad.setFila(hab);
        }
    }
}