/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.botones.IUBotonCheckBox;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.hotelfelipez.www.modulo.modelo.ImprimirPanel;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Documento;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 *
 * @author neo
 */
public class IUImprimirDatosPersona extends IUVentanaT{
    
    private final IUVentanaHotel ventanaPrincipal;
    private final Persona persona;
    private IUEtiqueta iuTitulo;
    private IUPanelBD panelHojaImpresion;
    private IUEtiquetaI iuCarnetIdentidadC;
    private IUEtiquetaI iuCarnetIdentidadE;
    private IUEtiquetaI iuPassporte;
    private IUEtiquetaI iuCertificado;
    private IUPanelDatos iuDatos;
    
    private IUPanelBD panelOpciones;    
    private IUEtiqueta etiquetaOpciones;
    private IUBotonCheckBox botonCarnetIdentidad;
    private IUBotonCheckBox botonPassporte;
    private IUBotonCheckBox botonCertificado;
    private IUBotonCheckBox botonDatosPersona;

    private IUBoton botonImprimir;    
    public IUImprimirDatosPersona(IUVentanaHotel ventanaPrincipal, Persona persona, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        this.ventanaPrincipal = ventanaPrincipal;
        this.persona = persona;
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEvento();
    }
    private void construirPaneles(Limitacion limite){
        
        iuTitulo = new IUEtiqueta("Panel Tamaño Carta", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(62), limite.getPorcentajeAlto(4)));
        iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuTitulo.setSubrayarTexto(true);
        panelFondo.add(iuTitulo);
        
        panelHojaImpresion = new IUPanelBD(new Limitacion((int)Asistente.fromCMToPPI(1), (int)Asistente.fromCMToPPI(2), (int)Asistente.fromCMToPPI(22), (int)Asistente.fromCMToPPI(28)));          
        panelHojaImpresion.setArco(10);
        panelFondo.add(panelHojaImpresion);        
        
        panelOpciones= new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(45), limite.getPorcentajeAncho(27), limite.getPorcentajeAlto(35)));
        panelFondo.add(panelOpciones);        
        construirPanelOpciones(panelOpciones.getLimitacion());
        construirPanelAdministrador(panelHojaImpresion.getLimitacion());
        
        botonImprimir = new IUBoton("IMPRIMIR REGISTRO", new Limitacion(limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(86), limite.getPorcentajeAncho(27), limite.getPorcentajeAlto(12)));
        panelFondo.add(botonImprimir);
    }
    private void construirPanelAdministrador(Limitacion limite){
        for (int i = 0; i < persona.getDocumentos().size(); i++) {
            Documento doc = persona.getDocumentos().get(i);
            switch(doc.getTipo()){
                case "carnetIdentidadC":
                    iuCarnetIdentidadC = new IUEtiquetaI(doc.getUrl(), new Limitacion((int)Asistente.fromCMToPPI(1), (int)Asistente.fromCMToPPI(1), (int)Asistente.fromCMToPPI(8.5), (int)Asistente.fromCMToPPI(5.5)));
                    iuCarnetIdentidadC.setMovimiento();                    
                    iuCarnetIdentidadC.setVisible(false);
                    panelHojaImpresion.add(iuCarnetIdentidadC);                    
                break;
                case "carnetIdentidadE":
                    iuCarnetIdentidadE = new IUEtiquetaI(doc.getUrl(), new Limitacion((int)Asistente.fromCMToPPI(12), (int)Asistente.fromCMToPPI(1), (int)Asistente.fromCMToPPI(8.5), (int)Asistente.fromCMToPPI(5.5)));
                    iuCarnetIdentidadE.setMovimiento();                    
                    iuCarnetIdentidadE.setVisible(false);
                    panelHojaImpresion.add(iuCarnetIdentidadE);
                    botonCarnetIdentidad.setEnabled(true);                    
                break;
                case "passporte":
                    iuPassporte = new IUEtiquetaI(doc.getUrl(), new Limitacion((int)Asistente.fromCMToPPI(1), (int)Asistente.fromCMToPPI(7), (int)Asistente.fromCMToPPI(18), (int)Asistente.fromCMToPPI(13)));
                    iuPassporte.setMovimiento();
                    iuPassporte.setVisible(false);
            
                    try {
                        iuPassporte.setIcon(new ImageIcon(new ImageIcon(Asistente.rotacionImagen(Asistente.getJPGImage(doc.getUrl()), -90)).getImage().getScaledInstance((int)Asistente.fromCMToPPI(18), (int)Asistente.fromCMToPPI(13), Image.SCALE_DEFAULT)));
                    } catch (IOException ex) {
                        System.out.println("Error... IUImprimirDatosPersona.construirPrimeraHoja(): "+ex.getMessage());
                    }
            
                    panelHojaImpresion.add(iuPassporte);
                    botonPassporte.setEnabled(true);                    
                break;
                case "certificado":
                    iuCertificado = new IUEtiquetaI(doc.getUrl(), new Limitacion((int)Asistente.fromCMToPPI(1), (int)Asistente.fromCMToPPI(1), (int)Asistente.fromCMToPPI(20), (int)Asistente.fromCMToPPI(26)));
                    iuCertificado.setMovimiento();        
                    iuCertificado.setVisible(false);
                    panelHojaImpresion.add(iuCertificado);                    
                    botonCertificado.setEnabled(true);
                break;
            }
        }
        iuDatos = new IUPanelDatos(persona, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(51), limite.getPorcentajeAncho(92), limite.getPorcentajeAlto(47)));
        iuDatos.setMovimiento();
        panelHojaImpresion.add(iuDatos);        
        
        if(iuCarnetIdentidadC != null && iuCarnetIdentidadE != null){
            botonCarnetIdentidad.setSelected(true);
            iuCarnetIdentidadC.setVisible(true);
            iuCarnetIdentidadE.setVisible(true);
        }else{
            if(iuPassporte != null){
                iuPassporte.setVisible(true);
                botonPassporte.setSelected(true);
            }else{
                if(iuCertificado != null){
                    iuCertificado.setVisible(true);
                    botonCertificado.setSelected(true);
                    iuDatos.setVisible(false);
                    botonDatosPersona.setSelected(false);
                }
            }
        }
        
    }
    private void construirPanelOpciones(Limitacion limite){
        etiquetaOpciones = new IUEtiqueta("Opciones de Impresion", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(10)));
        etiquetaOpciones.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaOpciones.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(6)));
        etiquetaOpciones.setSubrayarTexto(true);
        panelOpciones.add(etiquetaOpciones);
        
        botonCarnetIdentidad = new IUBotonCheckBox(" + CARNET IDENTIDAD", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(10)), false);
        botonCarnetIdentidad.setEnabled(false);
        panelOpciones.add(botonCarnetIdentidad);
        
        botonPassporte = new IUBotonCheckBox(" + PASSPORTE", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(35), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(10)), false);
        botonPassporte.setEnabled(false);
        panelOpciones.add(botonPassporte);
        
        botonCertificado = new IUBotonCheckBox(" + CERTIFICADO", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(10)), false);
        botonCertificado.setEnabled(false);
        panelOpciones.add(botonCertificado);
        
        botonDatosPersona = new IUBotonCheckBox(" + DATOS PERSONALES", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(65), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(10)), true);
        panelOpciones.add(botonDatosPersona);
    }
    private void escucharEvento(){
        botonCarnetIdentidad.addItemListener((ItemEvent e) -> {
            if(botonCarnetIdentidad.isSelected()){
                iuCarnetIdentidadC.setVisible(true);
                iuCarnetIdentidadE.setVisible(true);
            }else{
                iuCarnetIdentidadC.setVisible(false);
                iuCarnetIdentidadE.setVisible(false);                    
            }
        });
        botonPassporte.addItemListener((ItemEvent e) -> {
            if(botonPassporte.isSelected())
                iuPassporte.setVisible(true);
            else
                iuPassporte.setVisible(false);
        });
        botonCertificado.addItemListener((ItemEvent e) -> {
            if(botonCertificado.isSelected())
                iuCertificado.setVisible(true);
            else
                iuCertificado.setVisible(false);
        });
        botonDatosPersona.addItemListener((ItemEvent e) -> {
            if(botonDatosPersona.isSelected())
                iuDatos.setVisible(true);
            else
                iuDatos.setVisible(false);
        });
        botonImprimir.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                ImprimirPanel imprimirDocumentos = new ImprimirPanel(panelHojaImpresion);
                
            }
        });
    }
}
