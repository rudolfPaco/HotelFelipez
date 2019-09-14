/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.temporadas;

import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelCTU;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.paneles.IUPanelTT;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;

/**
 *
 * @author neo
 */
public class IUPanelTemporada extends IUPanelBD{
    
    private IUPanelTT panelTipoTemporada;
    private IUPanelCTU panelPrecio;
    private IUPanelCT panelMoneda;
    private IUPanelTA panelMotivo;
    private Temporada temporada;
    
    public IUPanelTemporada(Limitacion limitacion) {
        super(limitacion);
        
        this.temporada = null;
        construirPaneles(limitacion);
    }
    private void construirPaneles(Limitacion limite){
        panelTipoTemporada = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(12)), "temporada: ", "", 40, 55);
        panelTipoTemporada.iuTexto.setForeground(new Color(180, 0, 0));
        panelTipoTemporada.setPosicionHorizontal(SwingConstants.RIGHT, SwingConstants.LEFT);
        add(panelTipoTemporada);
        
        panelPrecio = new IUPanelCTU("precio de hab.", "", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(18), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(30)), 40, 55, 40);
        panelPrecio.iuTexto.setEditable(false);
        panelPrecio.iuTexto.setFocusable(false);
        add(panelPrecio);
        
        panelMoneda = new IUPanelCT("moneda", "", new Limitacion(limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(18), limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(30)), 40, 55);
        panelMoneda.iuTexto.setEditable(false);
        panelMoneda.iuTexto.setFocusable(false);
        add(panelMoneda);
        
        panelMotivo = new IUPanelTA("motivo", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(42)), 28, 72);
        panelMotivo.iuAreaTexto.setEditable(false);
        panelMotivo.iuAreaTexto.setFocusable(false);
        panelMotivo.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 15));
        add(panelMotivo);
    }
    public void setDatosTemporada(Temporada temporada){
        this.temporada = temporada;
        panelTipoTemporada.iuTexto.setText(temporada.getTipoTemporada());
        panelPrecio.iuTexto.setText(String.valueOf(Asistente.acotarNumero(temporada.getPrecio(), 2)));
        panelPrecio.iuTexto.iuUnidad.setText(temporada.getUnidadMoneda().getUnidad());
        panelMoneda.iuTexto.setText(temporada.getUnidadMoneda().getTipo());
        panelMotivo.iuAreaTexto.setText(temporada.getMotivo());
    }
    public void addEventoRaton(MouseAdapter mouse){
        addMouseListener(mouse);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelTipoTemporada.addEventoRaton(mouse);
        panelPrecio.setEventoRaton(mouse);
        panelMoneda.setEventoRaton(mouse);
        panelMotivo.setEventoRaton(mouse);
    }    
    public void setLimpiarDatosPanelTemporada(){
        panelTipoTemporada.iuTexto.setText("");
        panelPrecio.iuTexto.setText("");
        panelPrecio.iuTexto.iuUnidad.setText("");
        panelMoneda.iuTexto.setText("");
        panelMotivo.iuAreaTexto.setText("");
    }
    public Temporada getTemporada(){
        return temporada;
    }
}