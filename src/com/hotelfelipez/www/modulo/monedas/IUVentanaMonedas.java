/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.monedas;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CMonedas;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author neo
 */
public class IUVentanaMonedas extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    private CMonedas controlMonedas;    
    
    private IUPanel primerPanel;
    private IUTablaMonedas tablaMonedas;
    private IUPanel segundoPanel;
    private IUBoton botonNuevaMoneda;
    private IUBoton botonModificarMoneda;
    private IUBoton botonEliminarMoneda;
    private IUBoton botonSalir;
        
    public IUVentanaMonedas(IUVentanaHotel ventanaPrincipal, CMonedas controlMonedas, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 8);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlMonedas = controlMonedas;
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
        actualizarTablaMonedas();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(90)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(64), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(34), limite.getPorcentajeAlto(90)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        tablaMonedas = new IUTablaMonedas(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(98)));
        primerPanel.add(tablaMonedas.tabla.deslizador);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonNuevaMoneda = new IUBoton("nueva moneda", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(8)));
        segundoPanel.add(botonNuevaMoneda);
        
        botonModificarMoneda = new IUBoton("modificar moneda", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(15), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(8)));
        segundoPanel.add(botonModificarMoneda);
        
        botonEliminarMoneda = new IUBoton("eliminar moneda", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(28), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(8)));
        segundoPanel.add(botonEliminarMoneda);
        
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(90), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(8)));
        segundoPanel.add(botonSalir);
    }
    private void escucharEventos(){
        botonNuevaMoneda.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.3f);
                IUNuevaMoneda iuNuevaMoneda = new IUNuevaMoneda(ventanaPrincipal, "nueva moneda", new Limitacion(ventanaPrincipal.ANCHO/4, ventanaPrincipal.ALTO/3));                
                iuNuevaMoneda.mostrarVentana();
                if(iuNuevaMoneda.getEstado()){
                    controlMonedas.agregarNuevaMoneda(iuNuevaMoneda.getMoneda());
                    actualizarTablaMonedas();
                }
                setOpacity(1f);
            }
        });
        botonModificarMoneda.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaMonedas.estaSeleccionado()){
                    setOpacity(0.3f);
                    IUModificarMoneda iuModificarMoneda = new IUModificarMoneda(ventanaPrincipal, "modificar moneda", new Limitacion(ventanaPrincipal.ANCHO/4, ventanaPrincipal.ALTO/3));                
                    iuModificarMoneda.setMoneda(tablaMonedas.getMoneda());
                    iuModificarMoneda.mostrarVentana();
                    if(iuModificarMoneda.getEstado()){
                        controlMonedas.modificarMoneda(iuModificarMoneda.getMoneda());
                        actualizarTablaMonedas();
                    }
                    setOpacity(1f);
                }else{
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero debe seleccionar una fila de la tabla.", "advertencia");
                }                
            }
        });
        botonEliminarMoneda.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaMonedas.estaSeleccionado()){
                    if(controlMonedas.eliminarMoneda(tablaMonedas.getMoneda())){
                        actualizarTablaMonedas();
                    }else{
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no se puede eliminar esta moneda... por que esta enlazado a una tabla de B.D.", "advertencia");
                    }
                }else{
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero debe seleccionar una fila de la tabla.", "advertencia");
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
    
    private void actualizarTablaMonedas(){
        tablaMonedas.limpiarTabla();
        ArrayList<Moneda> listaMonedas = controlMonedas.getListaMonedas();
        for (int i = 0; i < listaMonedas.size(); i++) {
            Moneda moneda = listaMonedas.get(i);
            tablaMonedas.setFila(moneda);
        }
    }
}
