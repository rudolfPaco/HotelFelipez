/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.botones.IUBotonRadio;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.paneles.IUPanelTT;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CRegistroPersona;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rudolf
 */
public class IUBuscarPesonas extends IUVentanaT{

    private IUVentanaHotel ventanaPrincipal;
    private CRegistroPersona control;
    private IUTablaPersonas tablaPersonasRegistradas;
    
    private IUPanelBD primerPanel;
    private ButtonGroup grupo;
    private IUBotonRadio botonCI;
    private IUBotonRadio botonPassporte;
    private IUBotonRadio botonCertificado;

    private IUPanel panelAdministrador;
    private CardLayout administrador;
    private IUPanel panelCI;
    public IUEtiquetaI ciCara;
    public IUEtiquetaI ciEspalda;    
    private IUPanel panelPassporte;
    public IUEtiquetaI passporte;    
    private IUPanelBD panelCertificado;
    public IUEtiquetaI certificado;
    
    private IUPanel segundoPanel;
    private IUPanel panelArriba;
    public IUEtiquetaI iuFoto;
    public IUPanelTT iuTipoPersona;
    private IUBoton botonVerRegistro;
    public IUPanelTA iuObservacion;
    private IUPanelCT iuCampoBusqueda;
    private ButtonGroup grupoBotones;
    private IUBotonRadio botonCarnetIdentidad;
    private IUBotonRadio botonNombrePersona;    
    private IUPanel panelCentro;
    private IUTablaBusquedaPersonas tablaPersonas;    
    private IUPanel panelAbajo;
    private IUBoton botonOk;
    private IUBoton botonSalir;
    private boolean estado;
    
    public IUBuscarPesonas(IUVentanaHotel ventanaPrincipal, CRegistroPersona control, IUTablaPersonas iuTablaPersonas, String titulo, Limitacion limitacion) {        
        super(ventanaPrincipal, titulo, limitacion, 4);
        this.ventanaPrincipal = ventanaPrincipal;
        this.control = control;
        this.tablaPersonasRegistradas = iuTablaPersonas;
        this.estado = false;
        construirPaneles(panelFondo.getLimitacion());
        escucharEvento();
        actualizarListaPersonas();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(34), limite.getPorcentajeAlto(10)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        administrador = new CardLayout();
        panelAdministrador = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(12), limite.getPorcentajeAncho(34), limite.getPorcentajeAlto(87)));
        panelAdministrador.setLayout(administrador);
        panelFondo.add(panelAdministrador);
        construirPanelAdministrador(panelAdministrador.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(36), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(63), limite.getPorcentajeAlto(98)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());        
        
    }
    private void construirPanelAdministrador(Limitacion limite){
        panelCI = new IUPanel(new Limitacion(limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(60)));
        panelAdministrador.add(panelCI);
        construirPanelCI(panelCI.getLimitacion());
        
        panelPassporte = new IUPanel(limite);
        panelAdministrador.add(panelPassporte);
        construirPanelPassaporte(panelPassporte.getLimitacion());
        
        panelCertificado = new IUPanelBD(limite);
        panelAdministrador.add(panelCertificado);
        construirPanelCertificado(panelCertificado.getLimitacion());
    }
    private void construirPanelCI(Limitacion limite){
        ciCara = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(15), limite.getAncho(), limite.getPorcentajeAlto(49)));
        ciCara.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ciCara.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelCI.add(ciCara);
     
        ciEspalda = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(66), limite.getAncho(), limite.getPorcentajeAlto(49)));
        ciEspalda.setBorder(new LineBorder(Color.LIGHT_GRAY));
        ciEspalda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCI.add(ciEspalda);
    }
    private void construirPanelPassaporte(Limitacion limite){
        passporte = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(80)));
        passporte.setBorder(new LineBorder(Color.LIGHT_GRAY));
        passporte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelPassporte.add(passporte);
    }
    private void construirPanelCertificado(Limitacion limite){
        certificado = new IUEtiquetaI("", new Limitacion(limite.getAncho(), limite.getAlto()));
        certificado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCertificado.add(certificado);
    }
    private void construirPrimerPanel(Limitacion limite){
        grupo = new ButtonGroup();
        
        botonCI = new IUBotonRadio("carnet identidad", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(40)), true);
        botonCI.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(23)));
        grupo.add(botonCI);
        primerPanel.add(botonCI);        
        
        botonPassporte = new IUBotonRadio("passaporte", new Limitacion(limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(40)), false);
        botonPassporte.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(23)));
        grupo.add(botonPassporte);
        primerPanel.add(botonPassporte);        
        
        botonCertificado = new IUBotonRadio("certificado u otros", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(40)), false);
        botonCertificado.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(23)));
        grupo.add(botonCertificado);
        primerPanel.add(botonCertificado);        
    }
    private void construirSegundoPanel(Limitacion limite){
        panelArriba = new IUPanel(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(40)));
        segundoPanel.add(panelArriba);
        construirPanelArriba(panelArriba.getLimitacion());
        
        panelCentro = new IUPanel(new Limitacion(0, limite.getPorcentajeAlto(40), limite.getAncho(), limite.getPorcentajeAlto(50)));
        segundoPanel.add(panelCentro);
        construirPanelCentro(panelCentro.getLimitacion());
        
        panelAbajo = new IUPanel(new Limitacion(0, limite.getPorcentajeAlto(90), limite.getAncho(), limite.getPorcentajeAlto(10)));
        segundoPanel.add(panelAbajo);
        construirPanelAbajo(panelAbajo.getLimitacion());
    }
    private void construirPanelArriba(Limitacion limite){
        iuFoto = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(70)));
        iuFoto.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelArriba.add(iuFoto);        
        
        iuTipoPersona = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(23), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(57), limite.getPorcentajeAlto(10)), "tipo de persona", "", 25, 75);
        iuTipoPersona.iuTexto.setForeground(new Color(120, 0, 0));        
        panelArriba.add(iuTipoPersona);
        
        botonVerRegistro = new IUBoton("ver antecedentes", new Limitacion(limite.getPorcentajeAncho(81), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(10)));
        botonVerRegistro.setVisible(false);
        panelArriba.add(botonVerRegistro);
        
        iuObservacion = new IUPanelTA("observacion / antecedentes", "", new Limitacion(limite.getPorcentajeAncho(21), limite.getPorcentajeAlto(16), limite.getPorcentajeAncho(79), limite.getPorcentajeAlto(60)), 9, 91);
        iuObservacion.iuAreaTexto.setEditable(false);
        iuObservacion.iuAreaTexto.setFocusable(false);
        panelArriba.add(iuObservacion);
        
        iuCampoBusqueda = new IUPanelCT("busqueda de persona:", "", new Limitacion(limite.getPorcentajeAncho(72), limite.getPorcentajeAlto(80), limite.getPorcentajeAncho(27), limite.getPorcentajeAlto(15)), 40, 50);
        panelArriba.add(iuCampoBusqueda);
        
        grupoBotones = new ButtonGroup();
        
        botonNombrePersona = new IUBotonRadio("nombre de persona", new Limitacion(limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(83), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(15)), false);
        panelArriba.add(botonNombrePersona);
        grupoBotones.add(botonNombrePersona);
        
        botonCarnetIdentidad = new IUBotonRadio("carnet de identidad", new Limitacion(limite.getPorcentajeAncho(46), limite.getPorcentajeAlto(83), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(15)), true);
        panelArriba.add(botonCarnetIdentidad);
        grupoBotones.add(botonCarnetIdentidad);
    }
    private void construirPanelCentro(Limitacion limite){
        tablaPersonas = new IUTablaBusquedaPersonas(this, control, new Limitacion(limite.getAncho(), limite.getAlto()));
        panelCentro.add(tablaPersonas.tabla.deslizador);
    }
    private void construirPanelAbajo(Limitacion limite){
        botonSalir = new IUBoton("SALIR", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(90)));
        panelAbajo.add(botonSalir);
        
        botonOk = new IUBoton("OK, ACEPTAR", new Limitacion(limite.getPorcentajeAncho(79), limite.getPorcentajeAlto(10 ), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(90)));
        panelAbajo.add(botonOk);
    }
    private void actualizarListaPersonas(){
        tablaPersonas.limpiarTabla();
        ArrayList<Persona> lista = control.getListaPersonas(tablaPersonasRegistradas);
        for (int i = 0; i < lista.size(); i++) {
            Persona persona = lista.get(i);
            tablaPersonas.setFila(persona);
        }
    }
    private void escucharEvento(){
        
        botonCI.addItemListener((ItemEvent e) -> {
            if(botonCI.isSelected()){
                administrador.first(panelAdministrador);
            }
        });
        botonPassporte.addItemListener((ItemEvent e) -> {
            if(botonPassporte.isSelected()){
                administrador.first(panelAdministrador);
                administrador.next(panelAdministrador);
            }
        });
        botonCertificado.addItemListener((ItemEvent e) -> {
            if(botonCertificado.isSelected()){
                administrador.last(panelAdministrador);                    
            }
        });    
        iuCampoBusqueda.iuTexto.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                int columna = 1;
                if(botonCarnetIdentidad.isSelected())
                    columna = 3;                
                tablaPersonas.filtro.setRowFilter(RowFilter.regexFilter(iuCampoBusqueda.iuTexto.getText(), columna));
            }
        });
        botonOk.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaPersonas.estaSeleccionado()){
                    dispose();
                    estado = true;
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
    public boolean getEstado(){
        return estado;
    }
    public Persona getPersona(){
        return tablaPersonas.getPersona();
    }
    public void mostrarTipoDocumento(String tipo){
        switch(tipo){
            case "carnetIdentidadC":
                administrador.first(panelAdministrador);
                botonCI.setSelected(true);
            break;
            case "carnetIdentidadE":
                administrador.first(panelAdministrador);
                botonCI.setSelected(true);
            break;
            case "passporte":
                administrador.first(panelAdministrador);
                administrador.next(panelAdministrador);
                botonPassporte.setSelected(true);
            break;
            case "certificado":
                administrador.last(panelAdministrador);
                botonCertificado.setSelected(true);
            break;
        }
    }
}
