/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.principal;

import com.hotelfelipez.www.modulo.habitaciones.IUVentanaHabitaciones;
import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.etiquetas.IUEtiquetaR;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaP;
import com.hotelfelipez.www.modulo.controlador.CDisponibilidad;
import com.hotelfelipez.www.modulo.controlador.CHabitaciones;
import com.hotelfelipez.www.modulo.disponibilidad.IUVentanaDisponibilidad;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import com.hotelfelipez.www.modulo.temporadas.IUPanelTemporada;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author hotel-felipez
 */
public class IUVentanaHotel extends IUVentanaP{
    public int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    private IUPanelBD barraBotones;
    private IUEtiquetaI iuLogo;
    private final String urlLogo = "src/imagenes/logo.jpg";
    private IUBoton botonInicio;
    private IUBoton botonDisponibilidad;
    private IUBoton botonHabitaciones;
    private IUBoton botonCaja;
    private IUBoton botonHistorialHabitaciones;
    private IUEtiquetaR etiquetaReloj;
    private IUEtiqueta etiquetaFecha;
    
    private IUPanelEstadoHabitaciones panelHabitaciones;
    
    private ArrayList<IUHabitacion> listaHabitaciones = new ArrayList<>();
    private int cantidadPanelesHabitaciones; 
    
    public IUVentanaHotel(String titulo) {
        super(titulo, 4);        
        construirPaneles(panelFondo.getLimitacion());
        controlBotones();        
    }
    private void construirPaneles(Limitacion limite){
        barraBotones = new IUPanelBD(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(15)));
        barraBotones.setArco(1);
        panelFondo.add(barraBotones);        
        construirBarraBotones(barraBotones.getLimitacion());
        
        panelHabitaciones = new IUPanelEstadoHabitaciones(new Limitacion(0, limite.getPorcentajeAlto(15), limite.getAncho(), limite.getPorcentajeAlto(85)));
        panelHabitaciones.setArco(1);
        panelFondo.add(panelHabitaciones);
        construirPanelHabitaciones();
    }
    private void construirBarraBotones(Limitacion limite){
        iuLogo = new IUEtiquetaI(urlLogo, new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(13), limite.getPorcentajeAlto(90)));
        barraBotones.add(iuLogo);
        
        botonInicio = new IUBoton("inicio" ,new Limitacion(limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(7), limite.getPorcentajeAlto(90)));        
        barraBotones.add(botonInicio);
        
        botonDisponibilidad = new IUBoton("disponibilidad" ,new Limitacion(limite.getPorcentajeAncho(23), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(7), limite.getPorcentajeAlto(90)));        
        barraBotones.add(botonDisponibilidad);
        
        botonHabitaciones = new IUBoton("habitaciones" ,new Limitacion(limite.getPorcentajeAncho(31), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(7), limite.getPorcentajeAlto(90)));        
        barraBotones.add(botonHabitaciones);
        
        botonCaja = new IUBoton("caja" ,new Limitacion(limite.getPorcentajeAncho(39), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(7), limite.getPorcentajeAlto(90)));        
        barraBotones.add(botonCaja);
        
        botonHistorialHabitaciones = new IUBoton("archivos" ,new Limitacion(limite.getPorcentajeAncho(47), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(7), limite.getPorcentajeAlto(90)));        
        barraBotones.add(botonHistorialHabitaciones);
        
        etiquetaReloj = new IUEtiquetaR(new Limitacion(limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(60)));
        barraBotones.add(etiquetaReloj);
        
        etiquetaFecha = new IUEtiqueta("Cochabamba, "+new Fecha().getFecha1(), new Limitacion(limite.getPorcentajeAncho(75), limite.getPorcentajeAlto(70), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(20)));
        barraBotones.add(etiquetaFecha);        
    }
    private void construirPanelHabitaciones(){       
        
        this.listaHabitaciones.clear();
        panelHabitaciones.removeAll();//panelContenedoresPrendas
        panelHabitaciones.updateUI();//panelConteneedoresPrendas
        
        CHabitaciones controlHabitaciones = new CHabitaciones();
        ArrayList<Habitacion> lista = controlHabitaciones.getListaHabitaciones();
        int numeroElementos = lista.size();
        int limite = 9;
        
        int numeroColumnas = 9;
        int numeroFilas = 4;
        cantidadPanelesHabitaciones = Asistente.getCantidadPaneles(numeroElementos, limite);        
        int indice = 0;
        
        //indicePanelesPrendas.setText(numeroPanelPrenda+"/"+cantidadPanelesHabitaciones);
        /*if(cantidadPanelesHabitaciones > 1){
            iconoAtrasPrendas.setVisible(true);
            iconoAdelantePrendas.setVisible(true);
        }else{
            indicePanelesPrendas.setText("");
            iconoAtrasPrendas.setVisible(false);
            iconoAdelantePrendas.setVisible(false);
        }*/
                        
        for (int i = 0; i < cantidadPanelesHabitaciones; i++) {
            IUPanel panelPrenda = new IUPanel(new Limitacion(panelHabitaciones.getLimitacion().getAncho(), panelHabitaciones.getLimitacion().getAlto()));
            panelHabitaciones.add(panelPrenda);
            
            int anchura = (panelPrenda.getLimitacion().getAncho() - (numeroColumnas*2 + 2))/numeroColumnas;
            int altura = (panelPrenda.getLimitacion().getAlto() - numeroFilas*2)/numeroFilas;
            
            for(int filas = 0; filas < numeroFilas; filas++){
                for (int columnas = 0; columnas < numeroColumnas; columnas++) {
                    if(indice < numeroElementos){
                        Habitacion habitacion = lista.get(indice);
                        if(habitacion != null){

                            IUHabitacion iuHabitacion = new IUHabitacion(new Limitacion(columnas*2 + 2 + columnas*anchura, filas*2 + 2 + filas*altura, anchura, altura), habitacion);
                            listaHabitaciones.add(iuHabitacion);
                            panelPrenda.add(iuHabitacion);  
                            iuHabitacion.addEventoRaton(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    iuHabitacion.setColorPanel(new Color(255, 255, 125), Color.WHITE, new Color(221, 221, 0));
                                    despintarBotonHabitacion(iuHabitacion);                                    
                                    switch(habitacion.getEstado()){
                                        case "VACANTE":
                                            ventanaVacante(habitacion);                                                
                                        break;
                                    }
                                }
                            });
                            indice++;
                        }                    
                    }                    
                }                
            }
        }        
    }
    private void ventanaVacante(Habitacion hab){
        setOpacity(0.5f);
        String[] nombres = {"FUERA DE SERVICIO", "HISTORIAL DE HABITACIONES", "VER DISPONIBILIDAD"};
        IUVentanaVacante iuVacante = new IUVentanaVacante(this, "hab: "+hab.getNumero().toUpperCase()+" "+hab.getSimbolo().toUpperCase()+"      "+hab.getEstado(), new Limitacion(Asistente.ANCHO/2 - Asistente.ANCHO/10, Asistente.ALTO - Asistente.ALTO/3), nombres);
        iuVacante.mostrarVentana();
        if(iuVacante.getEstado()){            
            switch(iuVacante.getNombreBoton()){
                case "FUERA DE SERVICIO":
                    
                break;
                case "HISTORIAL DE HABITACIONES":
                    
                break;
                case "VER DISPONIBILIDAD":
                    
                break;
                
            }
        }
        setOpacity(1f);
    }
    private void despintarBotonHabitacion(IUHabitacion elemento){
        for (int i = 0; i < listaHabitaciones.size(); i++) {
            IUHabitacion iuhab = listaHabitaciones.get(i);
            if(!elemento.equals(iuhab))
                iuhab.setColorPanel(new Color(242, 238, 236), new Color(250, 250, 250), new Color(170, 170, 170));
        }
    }
    private void controlBotones(){
        botonHabitaciones.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarVentanaHabitaciones();
            }
        });
        botonDisponibilidad.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarVentanaDisponibilidad();
            }
        });
    }
    private void mostrarVentanaHabitaciones(){
        CHabitaciones controlHabitaciones = new CHabitaciones();
        IUVentanaHabitaciones iuHabitaciones = new IUVentanaHabitaciones(controlHabitaciones, this, "administracion de habitaciones");
        controlHabitaciones.controlarIUHabitaciones(iuHabitaciones);
        iuHabitaciones.mostrarVentana();
        construirPanelHabitaciones();
    }
    private void mostrarVentanaDisponibilidad(){
        CDisponibilidad controlDisponibilidad = new CDisponibilidad();
        IUVentanaDisponibilidad iuDisponibilidad = new IUVentanaDisponibilidad(this, controlDisponibilidad, "disponibilidad de habitaciones", new Limitacion(Asistente.ANCHO, Asistente.ALTO));
        controlDisponibilidad.controlarVentanaDisponibilidad(iuDisponibilidad);
        iuDisponibilidad.mostrarVentana();
    }
}