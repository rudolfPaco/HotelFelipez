/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUPanelRegistroPersonalOcupado extends IUPanel{

    private IUPanelBD primerPanel;
    private IUEtiquetaI iuFoto;
    private IUPanelCT iuTipoPersona;
    private IUTablaPersonas iuTablaPersonas;
    private IUPanelTA iuObservacion;
    private IUPanelTA iuMensaje;
    
    private IUPanelBD segundoPanel;
    
    public IUPanelRegistroPersonalOcupado(Limitacion limitacion) {
        super(limitacion);
        construirPaneles(getLimitacion());
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(85), limite.getAlto()));        
        primerPanel.setArco(20);
        add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(86), 0, limite.getPorcentajeAncho(14), limite.getAlto()));        
        add(segundoPanel);
    }
    private void construirPrimerPanel(Limitacion limite){
        iuFoto = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(13), limite.getPorcentajeAlto(37)));
        iuFoto.setBorder(new LineBorder(Color.LIGHT_GRAY));
        primerPanel.add(iuFoto);
        
        iuTipoPersona = new IUPanelCT("tipo persona", "huesped", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(42), limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(8)), 44, 56);
        primerPanel.add(iuTipoPersona);
        
        iuTablaPersonas = new IUTablaPersonas(new Limitacion(limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(81), limite.getPorcentajeAlto(47)));
        primerPanel.add(iuTablaPersonas.tabla.deslizador);
        
        iuObservacion = new IUPanelTA("observacion", "hola eeste es el mensaje...!", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(45)), 7, 93);
        iuObservacion.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 15));
        primerPanel.add(iuObservacion);
        
        iuMensaje = new IUPanelTA("mensaje del huesped", "hola eeste es el mensaje...!", new Limitacion(limite.getPorcentajeAncho(31), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(68), limite.getPorcentajeAlto(45)), 7, 93);
        iuMensaje.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 15));
        iuMensaje.iuAreaTexto.setForeground(new Color(230, 0, 115));
        primerPanel.add(iuMensaje);
    }
}
