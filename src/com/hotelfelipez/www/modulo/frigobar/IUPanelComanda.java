/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.frigobar;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelTT;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Hora;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUPanelComanda extends IUPanelBD{

    private IUPanel primerPanel;
    private IUEtiqueta iuNombre;
    private IUPanelTT iuNumero;
    private IUPanelTT iuFecha;
    private IUPanelTT iuHora;    
    private IUPanelBD segundoPanel;
    private IUTablaDetalleComanda iuTablaComanda;
    private IUEtiqueta iuEstado;
    private IUPanel tercerPanel;
    private IUPanelTT iuTotal;
    
    public IUPanelComanda(Limitacion limitacion) {
        super(limitacion);
        setArco(20);
        construirPaneles(getLimitacion());
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(10)));
        add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(0, limite.getPorcentajeAlto(10), limite.getAncho(), limite.getPorcentajeAlto(80)));
        segundoPanel.setArco(5);
        add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
        
        tercerPanel = new IUPanel(new Limitacion(0, limite.getPorcentajeAlto(90), limite.getAncho(), limite.getPorcentajeAlto(10)));
        add(tercerPanel);
        construirTercerPanel(tercerPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        iuNombre = new IUEtiqueta("Lavado y Planchado", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(40)));        
        iuNombre.setHorizontalAlignment(SwingConstants.CENTER);
        iuNombre.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(30)));
        iuNombre.setSubrayarTexto(true);
        primerPanel.add(iuNombre);
        
        iuNumero = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(70), 0, limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(50)), "nro: ", "12544", 15, 85);
        iuNumero.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuNumero.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(35)));
        primerPanel.add(iuNumero);        
        
        iuFecha = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(50)), "fecha: ", new Fecha().getFecha6(), 30, 70);
        primerPanel.add(iuFecha);
        
        iuHora = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(50)), "hrs: ", new Hora().getHora()+" "+new Hora().getFormato(), 25, 75);
        primerPanel.add(iuHora);
    }
    private void construirSegundoPanel(Limitacion limite){
        iuEstado = new IUEtiqueta("PAGADO", new Limitacion(limite.getAncho(), limite.getAlto()));
        iuEstado.setHorizontalAlignment(SwingConstants.CENTER);
        iuEstado.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(20)));
        iuEstado.setForeground(new Color(120, 0, 0));
        iuEstado.setVisible(false);
        segundoPanel.add(iuEstado);
        
        iuTablaComanda = new IUTablaDetalleComanda(new Limitacion(limite.getAncho(), limite.getAlto()));
        segundoPanel.add(iuTablaComanda.tabla.deslizador);
        
    }
    private void construirTercerPanel(Limitacion limite){
        iuTotal = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(90)), "total a pagar (en BOB.-)", "", 70, 30);
        iuTotal.iuTexto.setHorizontalAlignment(SwingConstants.RIGHT);
        iuTotal.iuTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(40)));
        iuTotal.iuTexto.setBorder(new LineBorder(Color.LIGHT_GRAY));
        tercerPanel.add(iuTotal);
    }
}
