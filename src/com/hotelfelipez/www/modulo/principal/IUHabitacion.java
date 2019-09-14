/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.principal;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;

/**
 *
 * @author hotel-felipez
 */
public class IUHabitacion extends IUPanelBD{
    
    private IUPanelBD primerPanel;
    private IUEtiqueta tituloHabitacion;
    private IUPanel segundoPanel;
    private IUEtiqueta estadoHabitacion;
    private IUPanelCT fechaSalida;
    private IUPanelCT nroNoches;
    
    private Habitacion habitacion;    
    
    public IUHabitacion(Limitacion limitacion, Habitacion habitacion) {
        super(limitacion);
        this.habitacion = habitacion;
        construirPaneles();
        actualizarEstado();
    }
    private void construirPaneles(){
        primerPanel = new IUPanelBD(new Limitacion(limitacion.getAncho(), limitacion.getPorcentajeAlto(20)));
        add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(0, limitacion.getPorcentajeAlto(20), limitacion.getAncho(), limitacion.getPorcentajeAlto(80)));
        add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        tituloHabitacion = new IUEtiqueta(habitacion.getNumero()+" "+habitacion.getSimbolo(), new Limitacion(limite.getAncho(), limite.getAlto()));
        tituloHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
        tituloHabitacion.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(60)));
        primerPanel.add(tituloHabitacion);
    }
    private void construirSegundoPanel(Limitacion limite){
        
        estadoHabitacion = new IUEtiqueta(habitacion.getEstado(), new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(40)));
        estadoHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
        estadoHabitacion.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(12)));        
        segundoPanel.add(estadoHabitacion);
        
        fechaSalida = new IUPanelCT("salida", new Fecha().getFecha3().toUpperCase(), new Limitacion(0, limite.getPorcentajeAlto(70), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(30)), 40, 60);
        fechaSalida.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        fechaSalida.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        fechaSalida.iuTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(10)));
        fechaSalida.iuTexto.setEditable(false);
        fechaSalida.iuTexto.setFocusable(false);
        segundoPanel.add(fechaSalida);
        fechaSalida.setVisible(false);
        
        nroNoches = new IUPanelCT("noches", "2", new Limitacion(limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(70), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(30)), 40, 60);
        nroNoches.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        nroNoches.iuTexto.setEditable(false);
        nroNoches.iuTexto.setFocusable(false);
        segundoPanel.add(nroNoches);
        nroNoches.setVisible(false);
        
    }
    private void actualizarEstado(){
        switch(habitacion.getEstado()){
            case "OCUPADO":
                estadoHabitacion.setForeground(new Color(120, 0, 0));
                fechaSalida.setVisible(true);
                nroNoches.setVisible(true);
            break;
            case "FUERA SERVICIO":
                estadoHabitacion.setForeground(new Color(120, 120, 120));
            break;
            case "VACANTE":
                estadoHabitacion.setForeground(new Color(0, 128, 64));
            break;
        }
    }
    public void addEventoRaton(MouseAdapter mouse){
        addMouseListener(mouse);
        tituloHabitacion.addMouseListener(mouse);
        estadoHabitacion.addMouseListener(mouse);
        fechaSalida.setEventoRaton(mouse);
        nroNoches.setEventoRaton(mouse);
        
    }
}
