/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.temporadas;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelCTU;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.paneles.IUPanelTCB;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CTemporadas;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class IUNuevaTemporada extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanelBD primerPanel;
    private IUPanelCT tipoTemporada;
    private IUPanelCTU precioTemporada;
    private IUPanelTCB tipoMoneda;
    private ArrayList<Moneda> listaMonedas;
    private IUPanelTA motivoTeporada;
    
    private IUPanelBD segundoPanel;
    private IUBoton botonSalir;
    private IUBoton botonGuardar;
    
    private boolean estado;
    
    public IUNuevaTemporada(IUVentanaHotel ventanaPrincipal, CTemporadas controlTemporadas, String titulo, Limitacion limitacion, int porcentajeAlturaTitulo) {
        super(ventanaPrincipal, titulo, limitacion, porcentajeAlturaTitulo);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.estado = false;
        this.listaMonedas = controlTemporadas.getListaMonedas();
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(80)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(82), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(17)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        tipoTemporada = new IUPanelCT("tipo de temporada", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(15)), 40, 60);
        tipoTemporada.iuTexto.setRestriccionLetrasMayusculas();
        primerPanel.add(tipoTemporada);
        
        precioTemporada = new IUPanelCTU("precio temporada", "", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(25), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(15)), 40, 60, 40);        
        precioTemporada.iuTexto.setRestriccionNumeroDecimal();
        primerPanel.add(precioTemporada);
        
        String[] tipoMonedas = getTiposMonedas();
        tipoMoneda = new IUPanelTCB("moneda", tipoMonedas, new Limitacion(limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(25), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(15)), 40, 60);        
        primerPanel.add(tipoMoneda);
        
        motivoTeporada = new IUPanelTA("motivo de temporada", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(45), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(50)), 12, 87);
        motivoTeporada.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 17));
        primerPanel.add(motivoTeporada);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonSalir = new IUBoton("salir de la ventana", new Limitacion(limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(60)));        
        segundoPanel.add(botonSalir);
        
        botonGuardar = new IUBoton("guardar temporada", new Limitacion(limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(60)));
        segundoPanel.add(botonGuardar);
    }
    private boolean estaValidadoCampos(){
        boolean verificador = false;
        if(!tipoTemporada.iuTexto.getText().isEmpty()){
            if(!precioTemporada.iuTexto.getText().isEmpty()){
                if(!tipoMoneda.iuTexto.getTexto().isEmpty()){
                    if(!motivoTeporada.iuAreaTexto.getText().isEmpty()){                        
                        verificador = true;
                    }else{
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo MOTIVO DE TEMPORADA.", "advertencia");
                    }                        
                }else{
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo TIPO DE MONEDA.", "advertencia");
                }
            }else{
                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo PRECIO DE TEMPORADA.", "advertencia");
            }
        }else{
            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo TIPO DE TEMPORADA.", "advertencia");            
        }
        return verificador;
    }    
    private void escucharEventos(){
        tipoMoneda.iuTexto.addItemListener((ItemEvent e) -> {            
            for (int i = 0; i < listaMonedas.size(); i++) {
                Moneda moneda = listaMonedas.get(i);
                if(tipoMoneda.getTexto().equalsIgnoreCase(moneda.getTipo()+" ("+moneda.getUnidad()+")")){
                    precioTemporada.iuTexto.iuUnidad.setText(moneda.getUnidad());
                    precioTemporada.iuTexto.iuUnidad.setName(String.valueOf(moneda.getId()));
                    break;
                }
            }
        });
        botonSalir.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        botonGuardar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(estaValidadoCampos()){
                    estado = true;
                    dispose();                    
                }
            }
        });
    }
    private String[] getTiposMonedas(){
        String[] monedas = new String[listaMonedas.size()+1];
        monedas[0] = "";
        int indice = 1;
        for (int i = 0; i < listaMonedas.size(); i++) {
            Moneda moneda = listaMonedas.get(i);
            monedas[indice] = moneda.getTipo()+" ("+moneda.getUnidad()+")";
            indice++;
        }
        return monedas;
    }
    private Moneda getMoneda(){
        Moneda monedaEncontrada = new Moneda(0);
        boolean encontrado = false;
        int contador = 0;
        int id = Integer.parseInt(precioTemporada.iuTexto.iuUnidad.getName());
        
        while ( contador < listaMonedas.size() && !encontrado) {
            Moneda moneda = listaMonedas.get(contador);            
            if(moneda.getId() == id){
                monedaEncontrada = moneda;
                encontrado = true;
            }
            contador++;
        }
        return monedaEncontrada;
    }
    public boolean getEstado(){
        return estado;
    }
    public Temporada getTemporada(){
        Temporada temporada = new Temporada(0);
        temporada.setTipoTemporada(tipoTemporada.iuTexto.getText());
        temporada.setUnidadMoneda(getMoneda());
        temporada.setPrecio(Asistente.acotarNumero(Double.parseDouble(precioTemporada.iuTexto.getText()), 2));
        temporada.setMotivo(motivoTeporada.iuAreaTexto.getText());
        return temporada;
    }
}
