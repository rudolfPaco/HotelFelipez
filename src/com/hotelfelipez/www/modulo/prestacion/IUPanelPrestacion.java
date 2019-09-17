/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.prestacion;

import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.modelo.Prestacion;

/**
 *
 * @author neo
 */
public class IUPanelPrestacion extends IUPanelBD{
    
    private IUPanelCT primerServicio;
    private IUPanelCT segundoServicio;
    private IUPanelCT tercerServicio;
    private IUPanelCT cuartoServicio;
    private IUPanelCT quintoServicio;
    private IUPanelCT sextoServicio;
    
    public IUPanelPrestacion(Limitacion limitacion) {
        super(limitacion);
        construirPaneles();
    }
    
    private void construirPaneles(){
        primerServicio = new IUPanelCT("primer servicio", "", new Limitacion(limitacion.getPorcentajeAncho(5), limitacion.getPorcentajeAlto(2), limitacion.getPorcentajeAncho(90), limitacion.getPorcentajeAlto(12)), 40, 60);
        primerServicio.iuTexto.setFocusable(false);
        primerServicio.iuTexto.setEditable(false);
        primerServicio.iuTexto.setBorder(null);
        add(primerServicio);
        
        segundoServicio = new IUPanelCT("segundo servicio", "", new Limitacion(limitacion.getPorcentajeAncho(5), limitacion.getPorcentajeAlto(16), limitacion.getPorcentajeAncho(90), limitacion.getPorcentajeAlto(12)), 40, 60);
        segundoServicio.iuTexto.setFocusable(false);
        segundoServicio.iuTexto.setEditable(false);
        segundoServicio.iuTexto.setBorder(null);
        add(segundoServicio);
        
        tercerServicio = new IUPanelCT("tercer servicio", "", new Limitacion(limitacion.getPorcentajeAncho(5), limitacion.getPorcentajeAlto(30), limitacion.getPorcentajeAncho(90), limitacion.getPorcentajeAlto(12)), 40, 60);
        tercerServicio.iuTexto.setFocusable(false);
        tercerServicio.iuTexto.setEditable(false);
        tercerServicio.iuTexto.setBorder(null);
        add(tercerServicio);
        
        cuartoServicio = new IUPanelCT("cuarto servicio", "", new Limitacion(limitacion.getPorcentajeAncho(5), limitacion.getPorcentajeAlto(44), limitacion.getPorcentajeAncho(90), limitacion.getPorcentajeAlto(12)), 40, 60);
        cuartoServicio.iuTexto.setFocusable(false);
        cuartoServicio.iuTexto.setEditable(false);
        cuartoServicio.iuTexto.setBorder(null);
        add(cuartoServicio);
        
        quintoServicio = new IUPanelCT("quinto servicio", "", new Limitacion(limitacion.getPorcentajeAncho(5), limitacion.getPorcentajeAlto(58), limitacion.getPorcentajeAncho(90), limitacion.getPorcentajeAlto(12)), 40, 60);
        quintoServicio.iuTexto.setFocusable(false);
        quintoServicio.iuTexto.setEditable(false);
        quintoServicio.iuTexto.setBorder(null);
        add(quintoServicio);
        
        sextoServicio = new IUPanelCT("sexto servicio", "", new Limitacion(limitacion.getPorcentajeAncho(5), limitacion.getPorcentajeAlto(72), limitacion.getPorcentajeAncho(90), limitacion.getPorcentajeAlto(12)), 40, 60);
        sextoServicio.iuTexto.setFocusable(false);
        sextoServicio.iuTexto.setEditable(false);
        sextoServicio.iuTexto.setBorder(null);
        add(sextoServicio);
    }
    public void setPrestacion(Prestacion prestacion){
        primerServicio.iuTexto.setText(prestacion.getPrimerServicio());
        segundoServicio.iuTexto.setText(prestacion.getSegundoServicio());
        tercerServicio.iuTexto.setText(prestacion.getTercerServicio());
        cuartoServicio.iuTexto.setText(prestacion.getCuartoServicio());
        quintoServicio.iuTexto.setText(prestacion.getQuintoServicio());
        sextoServicio.iuTexto.setText(prestacion.getSextoServicio());
    }
}
