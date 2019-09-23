/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.frigobar;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.modelo.Producto;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUNuevaComanda extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;    
    private ArrayList<Producto> lista;
    private IUPanel primerPanel;
    private IUEtiqueta iuTitulo;
    private IUTablaFrigobar iuTablaFrigobar;
    private IUPanelComanda panelComanda;    
    private IUPanel tercerPanel;
    private IUBoton botonReponer;
    private IUBoton botonConsumir;
    private IUBoton botonSalir;
    private IUBoton botonGuardar;
    
    public IUNuevaComanda(IUVentanaHotel ventanaPrincipal, ArrayList<Producto> lista, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 5);
        this.ventanaPrincipal = ventanaPrincipal;
        this.lista = lista;
        construirPaneles(panelFondo.getLimitacion());
        actualizarTablaProductos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(59), limite.getAlto()));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        panelComanda = new IUPanelComanda(new Limitacion(limite.getPorcentajeAncho(59), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(70)));
        panelFondo.add(panelComanda);
        
        tercerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(59), limite.getPorcentajeAlto(71), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(29)));
        panelFondo.add(tercerPanel);
        construirTercerPanel(tercerPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        iuTitulo = new IUEtiqueta("Productos del Frigobar Actualmente", new Limitacion(0, limite.getPorcentajeAlto(1), limite.getAncho(), limite.getPorcentajeAlto(5)));
        iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        //iuTitulo.setForeground(new Color(120, 0, 0));
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuTitulo.setSubrayarTexto(true);
        primerPanel.add(iuTitulo);
        
        iuTablaFrigobar = new IUTablaFrigobar(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(94)));
        primerPanel.add(iuTablaFrigobar.tabla.deslizador);        
    }
    private void construirTercerPanel(Limitacion limite){
        botonReponer = new IUBoton("◄ reponer producto", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(20)));
        botonReponer.iuTexto.setFont(new Font("Arial", Font.PLAIN, limite.getPorcentajeAlto(7)));
        tercerPanel.add(botonReponer);
        
        botonConsumir = new IUBoton("consumir producto ►", new Limitacion(limite.getPorcentajeAncho(54), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(20)));
        botonConsumir.iuTexto.setFont(new Font("Arial", Font.PLAIN, limite.getPorcentajeAlto(7)));
        tercerPanel.add(botonConsumir);
        
        botonSalir = new IUBoton("Salir de COMANDA", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(40), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(50)));
        tercerPanel.add(botonSalir);
        
        botonGuardar = new IUBoton("Guardar COMANDA", new Limitacion(limite.getPorcentajeAncho(54), limite.getPorcentajeAlto(40), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(50)));
        tercerPanel.add(botonGuardar);
        
    }
    private void actualizarTablaProductos(){
        iuTablaFrigobar.limpiarTabla();
        for (int i = 0; i < lista.size(); i++) {
            Producto producto = lista.get(i);
            iuTablaFrigobar.setFila(producto);
        }
    }
}
