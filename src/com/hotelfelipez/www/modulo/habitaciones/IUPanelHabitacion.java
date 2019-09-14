/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.habitaciones;

import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelCTU;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.mantenimiento.IUPanelMantenimiento;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.prestacion.IUPanelPrestacion;
import com.hotelfelipez.www.modulo.temporadas.IUPanelTemporada;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

/**
 *
 * @author hotel-felipez
 */
public class IUPanelHabitacion extends IUPanel{
    
    private IUPanelCT panelHabitacion;
    private IUPanelCT panelTipo;
    private IUPanelCT panelNombre;
    private IUPanelCT panelEstado;
    private IUPanelCT panelTelefono;
    private IUPanelCT panelNumeroPiso;    
    private IUPanelCT panelCapacidad;
    private IUPanelCT panelLugar;
    private IUPanelTA panelDescripcion;
    private IUPanelCT panelTipoTemporada;
    private IUPanelCTU panelPrecio;
    private IUPanelCT panelMoneda;
    private IUPanelCTU panelCostoSofa;
    private IUPanelCT panelSofaCama;
    private IUPanelCT panelPromocionFrigobar;
    private IUPanelCT panelTipoVenta;
    private IUPanelCT panelDisponibilidad;
    
    private IUPanelMantenimiento panelMantenimiento;
    private IUPanelPrestacion panelPrestacion;
    
    public IUPanelHabitacion(Limitacion limitacion) {
        super(limitacion);
        
        construirPaneles();
    }
    private void construirPaneles(){
        panelHabitacion = new IUPanelCT("habitacion", "", new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(3), limitacion.getPorcentajeAncho(10), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelHabitacion.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelHabitacion.iuTexto.setBorder(null);
        panelHabitacion.iuTexto.setFocusable(false);
        panelHabitacion.iuTexto.setEditable(false);
        add(panelHabitacion);
        
        panelTipo = new IUPanelCT("tipo", "", new Limitacion(limitacion.getPorcentajeAncho(14), limitacion.getPorcentajeAlto(3), limitacion.getPorcentajeAncho(24), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelTipo.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelTipo.iuTexto.setBorder(null);
        panelTipo.iuTexto.setFocusable(false);
        panelTipo.iuTexto.setEditable(false);
        add(panelTipo);
        
        panelNombre = new IUPanelCT("nombre habitacion", "", new Limitacion(limitacion.getPorcentajeAncho(40), limitacion.getPorcentajeAlto(3), limitacion.getPorcentajeAncho(19), limitacion.getPorcentajeAlto(7)), 40, 60);        
        panelNombre.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelNombre.iuTexto.setBorder(null);
        panelNombre.iuTexto.setEditable(false);
        panelNombre.iuTexto.setFocusable(false);
        add(panelNombre);
        
        panelTelefono = new IUPanelCT("telefono", "", new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(13), limitacion.getPorcentajeAncho(7), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelTelefono.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelTelefono.iuTexto.setBorder(null);
        panelTelefono.iuTexto.setEditable(false);
        panelTelefono.iuTexto.setFocusable(false);
        add(panelTelefono);
        
        panelNumeroPiso = new IUPanelCT("numero piso", "", new Limitacion(limitacion.getPorcentajeAncho(11), limitacion.getPorcentajeAlto(13), limitacion.getPorcentajeAncho(12), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelNumeroPiso.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelNumeroPiso.iuTexto.setBorder(null);
        panelNumeroPiso.iuTexto.setEditable(false);
        panelNumeroPiso.iuTexto.setFocusable(false);
        add(panelNumeroPiso);
        
        panelCapacidad = new IUPanelCT("capacidad", "", new Limitacion(limitacion.getPorcentajeAncho(25), limitacion.getPorcentajeAlto(13), limitacion.getPorcentajeAncho(13), limitacion.getPorcentajeAlto(7)), 40, 60);        
        panelCapacidad.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelCapacidad.iuTexto.setBorder(null);
        panelCapacidad.iuTexto.setEditable(false);
        panelCapacidad.iuTexto.setFocusable(false);
        add(panelCapacidad);
        
        panelEstado = new IUPanelCT("estado", "", new Limitacion(limitacion.getPorcentajeAncho(40), limitacion.getPorcentajeAlto(13), limitacion.getPorcentajeAncho(19), limitacion.getPorcentajeAlto(7)), 40, 60);        
        panelEstado.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelEstado.iuTexto.setBorder(null);
        panelEstado.iuTexto.setEditable(false);
        panelEstado.iuTexto.setFocusable(false);
        add(panelEstado);
        
        panelLugar = new IUPanelCT("lugar de habitacion", "", new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(23), limitacion.getPorcentajeAncho(57), limitacion.getPorcentajeAlto(7)), 40, 60);        
        panelLugar.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelLugar.iuTexto.setBorder(null);
        panelLugar.iuTexto.setEditable(false);
        panelLugar.iuTexto.setFocusable(false);
        add(panelLugar); 
        
        panelDescripcion = new IUPanelTA("descripcion de la habitacion", "", new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(33), limitacion.getPorcentajeAncho(57), limitacion.getPorcentajeAlto(17)), 16, 89);        
        panelDescripcion.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelDescripcion.iuAreaTexto.setBorder(null);
        panelDescripcion.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 16));
        panelDescripcion.iuAreaTexto.setEditable(false);
        panelDescripcion.iuAreaTexto.setFocusable(false);
        add(panelDescripcion); 
        
        panelTipoTemporada = new IUPanelCT("temporada de precio", "", new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(54), limitacion.getPorcentajeAncho(40), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelTipoTemporada.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelTipoTemporada.iuTexto.setBorder(null);
        panelTipoTemporada.iuTexto.setEditable(false);
        panelTipoTemporada.iuTexto.setFocusable(false);
        add(panelTipoTemporada);
        
        panelPrecio = new IUPanelCTU("precio", "", "", new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(64), limitacion.getPorcentajeAncho(15), limitacion.getPorcentajeAlto(7)), 40, 60, 40);
        panelPrecio.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelPrecio.iuTexto.setBorder(null);
        panelPrecio.iuTexto.setEditable(false);
        panelPrecio.iuTexto.setFocusable(false);
        add(panelPrecio);
        
        panelMoneda = new IUPanelCT("moneda", "", new Limitacion(limitacion.getPorcentajeAncho(20), limitacion.getPorcentajeAlto(64), limitacion.getPorcentajeAncho(20), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelMoneda.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelMoneda.iuTexto.setBorder(null);
        panelMoneda.iuTexto.setEditable(false);
        panelMoneda.iuTexto.setFocusable(false);
        add(panelMoneda);
        
        panelCostoSofa = new IUPanelCTU("costo sofa cama", "", "", new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(74), limitacion.getPorcentajeAncho(15), limitacion.getPorcentajeAlto(7)), 40, 60, 40);
        panelCostoSofa.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelCostoSofa.iuTexto.setBorder(null);
        panelCostoSofa.iuTexto.setEditable(false);
        panelCostoSofa.iuTexto.setFocusable(false);
        add(panelCostoSofa);
        
        panelSofaCama = new IUPanelCT("sofa cama", "", new Limitacion(limitacion.getPorcentajeAncho(20), limitacion.getPorcentajeAlto(74), limitacion.getPorcentajeAncho(20), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelSofaCama.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelSofaCama.iuTexto.setBorder(null);
        panelSofaCama.iuTexto.setEditable(false);
        panelSofaCama.iuTexto.setFocusable(false);
        add(panelSofaCama);
        
        panelPromocionFrigobar = new IUPanelCT("promocion frigobar", "", new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(84), limitacion.getPorcentajeAncho(15), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelPromocionFrigobar.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelPromocionFrigobar.iuTexto.setBorder(null);
        panelPromocionFrigobar.iuTexto.setEditable(false);
        panelPromocionFrigobar.iuTexto.setFocusable(false);
        add(panelPromocionFrigobar);
        
        panelTipoVenta = new IUPanelCT("tipo de venta", "", new Limitacion(limitacion.getPorcentajeAncho(19), limitacion.getPorcentajeAlto(84), limitacion.getPorcentajeAncho(15), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelTipoVenta.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelTipoVenta.iuTexto.setBorder(null);
        panelTipoVenta.iuTexto.setEditable(false);
        panelTipoVenta.iuTexto.setFocusable(false);
        add(panelTipoVenta);
        
        panelDisponibilidad = new IUPanelCT("disponibilidad", "", new Limitacion(limitacion.getPorcentajeAncho(36), limitacion.getPorcentajeAlto(84), limitacion.getPorcentajeAncho(15), limitacion.getPorcentajeAlto(7)), 40, 60);
        panelDisponibilidad.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelDisponibilidad.iuTexto.setBorder(null);
        panelDisponibilidad.iuTexto.setEditable(false);
        panelDisponibilidad.iuTexto.setFocusable(false);
        add(panelDisponibilidad);
        
        panelMantenimiento = new IUPanelMantenimiento(new Limitacion(limitacion.getPorcentajeAncho(62), limitacion.getPorcentajeAlto(2), limitacion.getPorcentajeAncho(36), limitacion.getPorcentajeAlto(35)));
        panelMantenimiento.setVisible(false);
        add(panelMantenimiento);
        
        panelPrestacion = new IUPanelPrestacion(new Limitacion(limitacion.getPorcentajeAncho(62), limitacion.getPorcentajeAlto(39), limitacion.getPorcentajeAncho(36), limitacion.getPorcentajeAlto(57)));
        add(panelPrestacion);
    }
    public void setHabitacion(Habitacion hab){        
        panelHabitacion.iuTexto.setText(hab.getNumero()+" "+hab.getSimbolo());
        panelTipo.iuTexto.setText(hab.getTipo());
        panelNombre.iuTexto.setText(hab.getEstilo());
        panelEstado.iuTexto.setText(hab.getEstado().toUpperCase());
        panelTelefono.iuTexto.setText(hab.getNumeroTelefono());
        panelNumeroPiso.iuTexto.setText(hab.getNumeroPiso());
        panelCapacidad.iuTexto.setText(hab.getCapacidad());
        panelLugar.iuTexto.setText(hab.getDetalle());
        panelDescripcion.iuAreaTexto.setText(hab.getDescripcion());
        panelTipoTemporada.iuTexto.setText(hab.getTemporada().getTipoTemporada());
        panelPrecio.iuTexto.setText(String.valueOf(Asistente.acotarNumero(hab.getTemporada().getPrecio(), 2)));
        panelPrecio.iuTexto.iuUnidad.setText(hab.getTemporada().getUnidadMoneda().getUnidad());
        panelMoneda.iuTexto.setText(hab.getTemporada().getUnidadMoneda().getTipo());
        panelSofaCama.iuTexto.setText(hab.getSofaCama());
        panelCostoSofa.iuTexto.setText(String.valueOf(Asistente.acotarNumero(hab.getCostoSofaCama(), 2)));
        panelCostoSofa.iuTexto.iuUnidad.setText(hab.getTemporada().getUnidadMoneda().getUnidad());
        panelPromocionFrigobar.iuTexto.setText(hab.getPromocionFrigobar());
        panelTipoVenta.iuTexto.setText(hab.getTipoVenta());
        panelDisponibilidad.iuTexto.setText(hab.getDisponible());
        
        panelPrestacion.setPrestacion(hab.getPrestacion());
        if(hab.getEstado().equalsIgnoreCase("FUERA SERVICIO")){
            panelMantenimiento.setVisible(true);
            panelEstado.iuTexto.setForeground(new Color(120, 0, 0));
        }            
        else{
            panelMantenimiento.setVisible(false);
            panelEstado.iuTexto.setForeground(new Color(2, 67, 109));            
        }            
    }
}
