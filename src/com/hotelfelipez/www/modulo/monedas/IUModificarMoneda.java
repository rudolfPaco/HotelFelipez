/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.monedas;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author hotel-felipez
 */
public class IUModificarMoneda extends IUVentanaT{
    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanelBD primerPanel;
    private IUPanelCT panelTipoMoneda;
    private IUPanelCT panelUnidad;    
    private IUPanel segundoPanel;
    private IUBoton botonGuardar;
    private IUBoton botonSalir;
    private int id;
    
    private boolean estado;
    
    public IUModificarMoneda(IUVentanaHotel ventanaPrincipal, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 11);
        this.ventanaPrincipal = ventanaPrincipal;
        this.id = 0;
        this.estado = false;
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(80)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(84), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(16)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        panelTipoMoneda = new IUPanelCT("moneda", "", new Limitacion(limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(23)), 40, 60);
        panelTipoMoneda.iuTexto.setRestriccionLetras();
        primerPanel.add(panelTipoMoneda);
        
        panelUnidad = new IUPanelCT("unidad", "", new Limitacion(limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(40), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(23)), 40, 60);
        panelUnidad.iuTexto.setRestriccionLetrasMayusculas();
        primerPanel.add(panelUnidad);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(88)));
        segundoPanel.add(botonSalir);
        
        botonGuardar = new IUBoton("modificar moneda", new Limitacion(limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(88)));
        segundoPanel.add(botonGuardar);
    }
    public void setMoneda(Moneda moneda){        
        id = moneda.getId();
        panelTipoMoneda.iuTexto.setText(moneda.getTipo());
        panelUnidad.iuTexto.setText(moneda.getUnidad());
    }
    private boolean estaValidadoDatos(){
        boolean verificador = false;
        if(!panelTipoMoneda.iuTexto.getText().isEmpty()){
            if(!panelUnidad.iuTexto.getText().isEmpty()){
                verificador = true;
            }else{
                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de texto UNIDAD", "advertencia");            
            }
        }else{
            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de texto MONEDA", "advertencia");            
        }
        return verificador;
    }
    private void escucharEventos(){
        botonSalir.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        botonGuardar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(estaValidadoDatos()){
                    estado = true;
                    dispose();
                }
            }
        });
    }
    public boolean getEstado(){
        return estado;
    }
    public Moneda getMoneda(){
        Moneda moneda = new Moneda(id);
        moneda.setTipo(panelTipoMoneda.iuTexto.getText());
        moneda.setUnidad(panelUnidad.iuTexto.getText());
        return moneda;
    }
}
