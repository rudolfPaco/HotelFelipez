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
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Prestacion;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author neo
 */
public class IUModificarPrestacion extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanelBD primerPanel;
    private IUPanelCT primeraPrestacion;
    private IUPanelCT segundaPrestacion;
    private IUPanelCT terceraPrestacion;
    private IUPanelCT cuartaPrestacion;
    private IUPanelCT quintaPrestacion;
    private IUPanelCT sextaPrestacion;
    private IUPanel segundoPanel;
    private IUBoton botonSalir;
    private IUBoton botonModificar;
    
    private int id;
    private boolean estado;
    
    public IUModificarPrestacion(IUVentanaHotel ventanaPrincipal, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 6);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.id = 0;
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(87)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(89), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(8)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        primeraPrestacion = new IUPanelCT("primer servicio de habitacion", "", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(10)), 40, 60);
        primeraPrestacion.iuTexto.setRestriccionLetras();
        primerPanel.add(primeraPrestacion);
        
        segundaPrestacion = new IUPanelCT("segundo servicio de habitacion", "", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(10)), 40, 60);
        segundaPrestacion.iuTexto.setRestriccionLetras();
        primerPanel.add(segundaPrestacion);
        
        terceraPrestacion = new IUPanelCT("tercer servicio de habitacion", "", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(10)), 40, 60);
        terceraPrestacion.iuTexto.setRestriccionLetras();
        primerPanel.add(terceraPrestacion);
        
        cuartaPrestacion = new IUPanelCT("cuarto servicio de habitacion", "", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(42), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(10)), 40, 60);
        cuartaPrestacion.iuTexto.setRestriccionLetras();
        primerPanel.add(cuartaPrestacion);
        
        quintaPrestacion = new IUPanelCT("quinto servicio de habitacion", "", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(54), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(10)), 40, 60);
        quintaPrestacion.iuTexto.setRestriccionLetras();
        primerPanel.add(quintaPrestacion);
        
        sextaPrestacion = new IUPanelCT("sexto servicio de habitacion", "", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(67), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(10)), 40, 60);
        sextaPrestacion.iuTexto.setRestriccionLetras();
        primerPanel.add(sextaPrestacion);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(30), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(70)));
        segundoPanel.add(botonSalir);
        
        botonModificar = new IUBoton("modificar prestacion", new Limitacion(limite.getPorcentajeAncho(52), limite.getPorcentajeAlto(30), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(70)));
        segundoPanel.add(botonModificar);
    }
    private boolean estaValidadaCampoDatos(){
        boolean verificador = false;
        if(!primeraPrestacion.iuTexto.getText().isEmpty()){
            verificador = true;
        }else{
            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero al menos el PRIMER SERVICIO DE HABITACION no debe estar vacio", "advertencia");
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
        botonModificar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(estaValidadaCampoDatos()){
                    estado = true;
                    dispose();
                }
            }
        });
    }
    public boolean getEstado(){
        return estado;
    }
    public void setPrestacion(Prestacion p){
        id = p.getId();
        primeraPrestacion.iuTexto.setText(p.getPrimerServicio());
        segundaPrestacion.iuTexto.setText(p.getSegundoServicio());
        terceraPrestacion.iuTexto.setText(p.getTercerServicio());
        cuartaPrestacion.iuTexto.setText(p.getCuartoServicio());
        quintaPrestacion.iuTexto.setText(p.getQuintoServicio());
        sextaPrestacion.iuTexto.setText(p.getSextoServicio());        
    }
    public Prestacion getPrestacion(){
        Prestacion prestacion = new Prestacion(id);
        prestacion.setPrimerServicio(primeraPrestacion.iuTexto.getText());
        prestacion.setSegundoServicio(segundaPrestacion.iuTexto.getText());
        prestacion.setTercerServicio(terceraPrestacion.iuTexto.getText());
        prestacion.setCuartoServicio(cuartaPrestacion.iuTexto.getText());
        prestacion.setQuintoServicio(quintaPrestacion.iuTexto.getText());
        prestacion.setSextoServicio(sextaPrestacion.iuTexto.getText());
        return prestacion;
    }
}
