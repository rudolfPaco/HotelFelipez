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
import com.hotelfelipez.www.modulo.controlador.CHabitaciones;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author hotel-felipez
 */
public class IUHabitacion extends IUPanelBD{
    
    private IUPanelBD primerPanel;
    private IUEtiqueta tituloHabitacion;
    private IUPanel segundoPanel;
    private IUEtiqueta estadoHabitacion;
    private IUEtiqueta mensajeHuesped;
    private IUEtiqueta mensajeEstado;
    private IUPanelCT fechaSalida;
    private IUPanelCT nroNoches;
    
    private CHabitaciones controlHabitaciones;
    private Habitacion habitacion;    
    
    public IUHabitacion(Limitacion limitacion, CHabitaciones controlHabitaciones, Habitacion habitacion) {
        super(limitacion);
        this.controlHabitaciones = controlHabitaciones;
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
        
        estadoHabitacion = new IUEtiqueta(habitacion.getEstado(), new Limitacion(0, limite.getPorcentajeAlto(10),limite.getAncho(), limite.getPorcentajeAlto(20)));
        estadoHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
        //estadoHabitacion.setBorder(new LineBorder(Color.yellow));
        estadoHabitacion.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(12)));        
        segundoPanel.add(estadoHabitacion);
        
        mensajeHuesped = new IUEtiqueta("mensaje URGENTE...!", new Limitacion(0, limite.getPorcentajeAlto(35),limite.getAncho(), limite.getPorcentajeAlto(13)));
        mensajeHuesped.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeHuesped.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(10)));
        mensajeHuesped.setForeground(new Color(204, 0, 102));
        mensajeHuesped.setVisible(false);
        segundoPanel.add(mensajeHuesped);
        
        mensajeEstado = new IUEtiqueta("", new Limitacion(0, limite.getPorcentajeAlto(55),limite.getAncho(), limite.getPorcentajeAlto(10)));
        mensajeEstado.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeEstado.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(8)));
        mensajeEstado.setForeground(new Color(255, 0, 0));
        mensajeEstado.setVisible(false);
        segundoPanel.add(mensajeEstado);
        
        fechaSalida = new IUPanelCT("salida", "", new Limitacion(0, limite.getPorcentajeAlto(70), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(30)), 40, 60);
        fechaSalida.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        fechaSalida.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        fechaSalida.iuTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(10)));
        fechaSalida.iuTexto.setEditable(false);
        fechaSalida.iuTexto.setFocusable(false);
        segundoPanel.add(fechaSalida);
        fechaSalida.setVisible(false);
        
        nroNoches = new IUPanelCT("noches", "", new Limitacion(limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(70), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(30)), 40, 60);
        nroNoches.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        nroNoches.iuTexto.setEditable(false);
        nroNoches.iuTexto.setFocusable(false);
        segundoPanel.add(nroNoches);
        nroNoches.setVisible(false);        
    }
    private void setDatosRegistroHospedaje(RegistroHospedaje registro){
        fechaSalida.iuTexto.setText(new Fecha(registro.getFechaSalida()).getFecha3().toUpperCase());        
        nroNoches.iuTexto.setText(String.valueOf(registro.getNroNoches()));
        if(registro.getNroNoches() < 1){
            mensajeEstado.setVisible(true);
            mensajeEstado.setText("actualizar FECHA SALIDA...!");
            nroNoches.iuTexto.setForeground(new Color(120, 0, 0));
            fechaSalida.iuTexto.setForeground(new Color(120, 0, 0));
        }else{            
            mensajeEstado.setVisible(false);
            nroNoches.iuTexto.setForeground(new Color(2, 67, 109));
            fechaSalida.iuTexto.setForeground(new Color(2, 67, 109));
        }
            
    }
    private void actualizarEstado(){
        switch(habitacion.getEstado()){
            case "OCUPADO":
                RegistroHospedaje registro = controlHabitaciones.getRegistroHospedaje(habitacion.getId());
                if(registro != null){
                    setDatosRegistroHospedaje(registro);
                }
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
        mensajeEstado.addMouseListener(mouse);
    }
}
