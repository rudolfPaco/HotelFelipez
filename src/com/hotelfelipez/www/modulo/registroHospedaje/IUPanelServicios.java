/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;

/**
 *
 * @author hotel-felipez
 */
public class IUPanelServicios extends IUPanel{
    
    private IUPanelBD primerPanel;
    
    private IUPanelBD segundoPanel;
    
    public IUPanelServicios(Limitacion limitacion) {
        super(limitacion);
        
        construirPaneles(getLimitacion());
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(85), limite.getAlto()));
        primerPanel.setArco(10);
        add(primerPanel);
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(86), 0, limite.getPorcentajeAncho(14), limite.getAlto()));
        add(segundoPanel);
    }
    
}
