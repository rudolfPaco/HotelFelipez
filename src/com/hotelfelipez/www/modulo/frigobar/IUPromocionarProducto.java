/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.frigobar;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.calendario.IUCalendario;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelCTU;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Producto;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUPromocionarProducto extends IUVentanaT{
    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanelBD primerPanel;
    private IUPanelCT categoriaNombre;
    private IUPanelCTU costo;
    private IUPanelCTU precio;
    private IUPanelCT promocion;
    private IUPanelCT fecha;
    private IUPanelCTU dias;

    private IUPanelCTU precioPromocion;
    
    private IUPanelBD segundoPanel;
    private IUBoton botonSalir;
    private IUBoton botonGuardar;
    
    private boolean estado;
    private Producto producto;
    
    public IUPromocionarProducto(IUVentanaHotel ventanaPrincipal, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 7);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.estado = false;
        this.producto = null;
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEvento();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(85)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(87), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(11)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        categoriaNombre = new IUPanelCT("nombre del producto", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(10)), 40, 60);
        categoriaNombre.setBorder(new LineBorder(Color.LIGHT_GRAY));
        categoriaNombre.iuTexto.setBorder(null);
        categoriaNombre.iuTexto.setFocusable(false);
        categoriaNombre.iuTexto.setEditable(false);
        primerPanel.add(categoriaNombre);
        
        fecha = new IUPanelCT("fecha vencimiento", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60);
        fecha.setBorder(new LineBorder(Color.LIGHT_GRAY));
        fecha.iuTexto.setBorder(null);
        fecha.iuTexto.setFocusable(false);
        fecha.iuTexto.setEditable(false);
        primerPanel.add(fecha);
        
        dias = new IUPanelCTU("dias faltantes", "", "", new Limitacion(limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60, 50);
        dias.setBorder(new LineBorder(Color.LIGHT_GRAY));
        dias.iuTexto.setBorder(null);
        dias.iuTexto.setFocusable(false);
        dias.iuTexto.setEditable(false);
        primerPanel.add(dias);
        
        costo = new IUPanelCTU("costo", "", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60, 50);
        costo.setBorder(new LineBorder(Color.LIGHT_GRAY));
        costo.iuTexto.setBorder(null);
        costo.iuTexto.setFocusable(false);
        costo.iuTexto.setEditable(false);
        primerPanel.add(costo);
        
        precio = new IUPanelCTU("precio actual", "", "", new Limitacion(limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60, 50);
        precio.setBorder(new LineBorder(Color.LIGHT_GRAY));
        precio.iuTexto.setForeground(new Color(120, 0, 0));
        precio.iuTexto.setBorder(null);
        precio.iuTexto.setFocusable(false);
        precio.iuTexto.setEditable(false);
        primerPanel.add(precio);
        
        promocion = new IUPanelCT("promocion", "", new Limitacion(limite.getPorcentajeAncho(68), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60);
        promocion.setBorder(new LineBorder(Color.LIGHT_GRAY));
        promocion.iuTexto.setForeground(new Color(120, 0, 0));
        promocion.iuTexto.setBorder(null);
        promocion.iuTexto.setFocusable(false);
        promocion.iuTexto.setEditable(false);
        primerPanel.add(promocion);
        
        precioPromocion = new IUPanelCTU("precio promocion", "", "", new Limitacion(limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(53), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60, 50);
        precioPromocion.iuTexto.setRestriccionNumeroDecimal();
        precioPromocion.setBorder(new LineBorder(Color.LIGHT_GRAY));
        precioPromocion.iuTexto.setBorder(null);
        primerPanel.add(precioPromocion);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(13), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(60)));
        segundoPanel.add(botonSalir);
        
        botonGuardar = new IUBoton("guardar", new Limitacion(limite.getPorcentajeAncho(58), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(60)));
        segundoPanel.add(botonGuardar);
    }
    private boolean estaValidadCamposDatos(){
        boolean verificador = false;
        if(!precioPromocion.iuTexto.getText().isEmpty() && Double.parseDouble(precioPromocion.iuTexto.getText()) > 0){
            verificador = true;
        }else
            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento, pero el campo CANTIDAD no puede estar vacio. y DEBE ser MAYOR a 0.", "advertencia");
        return verificador;
    }
    private void escucharEvento(){
        botonSalir.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        botonGuardar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(estaValidadCamposDatos()){
                    estado = true;
                    dispose();
                }
            }
        });        
    }
    public boolean getEstado(){
        return estado;
    }
    public void setProducto(Producto p){
        this.producto = p;
        
        categoriaNombre.iuTexto.setText(p.getCategoria()+" "+p.getNombre());
        costo.iuTexto.setText(String.valueOf(Asistente.acotarNumero(p.getCosto(), 2)));
        costo.iuTexto.iuUnidad.setText(p.getMoneda().getUnidad());
        precio.iuTexto.setText(String.valueOf(Asistente.acotarNumero(p.getPrecio(), 2)));
        precio.iuTexto.iuUnidad.setText(p.getMoneda().getUnidad());
        promocion.iuTexto.setText("SI");
        fecha.iuTexto.setText(p.getFechaVencimiento());
        dias.iuTexto.setText(String.valueOf(new Fecha(p.getFechaVencimiento()).restarDias(new Fecha())));
        dias.iuTexto.iuUnidad.setText("dias");
        precioPromocion.iuTexto.setText(String.valueOf(Asistente.acotarNumero(p.getCosto(), 2)));
        precioPromocion.iuTexto.iuUnidad.setText(p.getMoneda().getUnidad());
        
    }
    public Producto getProducto(){
        producto.setPrecio(Asistente.acotarNumero(Double.parseDouble(precioPromocion.iuTexto.getText()), 2));
        producto.setPromocion("SI");
        return producto;
    }
}
