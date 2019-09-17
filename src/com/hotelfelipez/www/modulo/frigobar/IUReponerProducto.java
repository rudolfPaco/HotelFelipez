/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.frigobar;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.calendario.IUCalendario;
import com.aplicacionjava.www.paneles.IUPanel;
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
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

/**
 *
 * @author neo
 */
public class IUReponerProducto extends IUVentanaT{

    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanelBD primerPanel;
    private IUPanelCT categoriaNombre;
    private IUPanelCTU costo;
    private IUPanelCTU precio;
    private IUPanelCT promocion;
    
    private IUPanelCT fechaVencimiento;
    private IUBoton botonFecha;
    private IUPanelCTU cantidad;
    
    private IUPanelBD segundoPanel;
    private IUBoton botonSalir;
    private IUBoton botonGuardar;
    
    private boolean estado;
    private Producto producto;
    
    public IUReponerProducto(IUVentanaHotel ventanaPrincipal, String titulo, Limitacion limitacion) {
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
        
        costo = new IUPanelCTU("costo", "", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60, 50);
        costo.setBorder(new LineBorder(Color.LIGHT_GRAY));
        costo.iuTexto.setBorder(null);
        costo.iuTexto.setFocusable(false);
        costo.iuTexto.setEditable(false);
        primerPanel.add(costo);
        
        precio = new IUPanelCTU("precio", "", "", new Limitacion(limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60, 50);
        precio.setBorder(new LineBorder(Color.LIGHT_GRAY));
        precio.iuTexto.setBorder(null);
        precio.iuTexto.setFocusable(false);
        precio.iuTexto.setEditable(false);
        primerPanel.add(precio);
        
        promocion = new IUPanelCT("promocion", "", new Limitacion(limite.getPorcentajeAncho(68), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60);
        promocion.setBorder(new LineBorder(Color.LIGHT_GRAY));
        promocion.iuTexto.setBorder(null);
        promocion.iuTexto.setFocusable(false);
        promocion.iuTexto.setEditable(false);
        primerPanel.add(promocion);
        
        fechaVencimiento = new IUPanelCT("fecha vencimiento", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(10)), 40, 60);
        fechaVencimiento.iuTexto.setEditable(false);
        fechaVencimiento.iuTexto.setFocusable(false);
        primerPanel.add(fechaVencimiento);
        
        botonFecha = new IUBoton("fecha", new Limitacion(limite.getPorcentajeAncho(32), limite.getPorcentajeAlto(47), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(6)));
        primerPanel.add(botonFecha);
        
        cantidad = new IUPanelCTU("cantidad", "", "", new Limitacion(limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 40, 60, 50);
        cantidad.iuTexto.setRestriccionNumeroEnteros();
        primerPanel.add(cantidad);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(13), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(60)));
        segundoPanel.add(botonSalir);
        
        botonGuardar = new IUBoton("guardar", new Limitacion(limite.getPorcentajeAncho(58), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(60)));
        segundoPanel.add(botonGuardar);
    }
    private boolean estaValidadCamposDatos(){
        boolean verificador = false;
        if(!fechaVencimiento.iuTexto.getText().isEmpty()){
            if(!cantidad.iuTexto.getText().isEmpty() && Integer.parseInt(cantidad.iuTexto.getText()) > 0){
                verificador = true;
            }else
                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento, pero el campo CANTIDAD no puede estar vacio. y DEBE ser MAYOR a 0.", "advertencia");
        }else
            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento, pero el campo FECHA DE VENCIMIENTO no puede estar vacio.", "advertencia");
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
        botonFecha.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.3f);
                
                IUCalendario iuCalendario = new IUCalendario(ventanaPrincipal, new Fecha().getFecha2(), new Fecha(), new Limitacion(Asistente.ANCHO/2, Asistente.ALTO/2));
                iuCalendario.mostrarVentana();
                if(iuCalendario.getEstado()){
                    fechaVencimiento.iuTexto.setText(iuCalendario.getFecha().getFecha());
                }
                
                setOpacity(1f);
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
        promocion.iuTexto.setText(p.getPromocion());
        fechaVencimiento.iuTexto.setText(p.getFechaVencimiento());
        cantidad.iuTexto.setText(String.valueOf(p.getCantidad()));
        cantidad.iuTexto.iuUnidad.setText("uds.");
    }
    public Producto getProducto(){
        producto.setFechaVencimiento(fechaVencimiento.iuTexto.getText());
        producto.setCantidad(Integer.parseInt(cantidad.iuTexto.getText()));
        return producto;
    }
}
