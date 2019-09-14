/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.mantenimiento;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelCTU;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import sun.swing.SwingAccessor;

/**
 *
 * @author neo
 */
public class IUPanelMantenimiento extends IUPanelBD{
    
    private IUEtiqueta titulo;
    private IUPanelCT fechaInicio;
    private IUPanelCTU numeroDias;
    private IUPanelCT responsable;
    private IUPanelTA problema;
    
    public IUPanelMantenimiento(Limitacion limitacion) {
        super(limitacion);
        
        construirPaneles();
    }
    private void construirPaneles(){
        titulo = new IUEtiqueta("MANTENIMIENTO", new Limitacion(limitacion.getPorcentajeAncho(5), limitacion.getPorcentajeAlto(1), limitacion.getPorcentajeAncho(90), limitacion.getPorcentajeAlto(15)));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(new Color(120, 0, 0));
        add(titulo);
        
        fechaInicio = new IUPanelCT("fecha inicial", "", new Limitacion(limitacion.getPorcentajeAncho(5), limitacion.getPorcentajeAlto(16), limitacion.getPorcentajeAncho(30), limitacion.getPorcentajeAlto(20)), 40, 60);
        fechaInicio.iuTexto.setEditable(false);
        fechaInicio.iuTexto.setFocusable(false);
        add(fechaInicio);
        
        numeroDias = new IUPanelCTU("dias pasados", "", "", new Limitacion(limitacion.getPorcentajeAncho(40), limitacion.getPorcentajeAlto(16), limitacion.getPorcentajeAncho(30), limitacion.getPorcentajeAlto(20)), 40, 60, 40);
        numeroDias.iuTexto.setEditable(false);
        numeroDias.iuTexto.setFocusable(false);
        add(numeroDias);
        
        responsable = new IUPanelCT("responsable", "", new Limitacion(limitacion.getPorcentajeAncho(3), limitacion.getPorcentajeAlto(37), limitacion.getPorcentajeAncho(94), limitacion.getPorcentajeAlto(20)), 40, 60);
        responsable.iuTexto.setEditable(false);
        responsable.iuTexto.setFocusable(false);
        add(responsable);
        
        problema = new IUPanelTA("descripcion del problema", "", new Limitacion(limitacion.getPorcentajeAncho(3), limitacion.getPorcentajeAlto(59), limitacion.getPorcentajeAncho(94), limitacion.getPorcentajeAlto(36)), 20, 80);
        problema.iuAreaTexto.setEditable(false);
        problema.iuAreaTexto.setFocusable(false);
        problema.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 16));
        add(problema);
    }
}
