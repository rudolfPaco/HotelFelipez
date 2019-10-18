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
import com.hotelfelipez.www.modulo.controlador.CFrigobar;
import com.hotelfelipez.www.modulo.modelo.Comanda;
import com.hotelfelipez.www.modulo.modelo.Detalle;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.Producto;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private CFrigobar control;
    private int idRegistro;
    private boolean estado;
    private ArrayList<Producto> lista;
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private IUPanel primerPanel;
    private IUEtiqueta iuTitulo;
    private IUTablaFrigobar iuTablaFrigobar;
    private IUPanelComanda panelComanda;    
    private IUPanel tercerPanel;
    private IUBoton botonReponer;
    private IUBoton botonConsumir;
    private IUBoton botonSalir;
    private IUBoton botonGuardar;
    //.getListaProductos(habitacion.getFrigobar().getId()
    public IUNuevaComanda(IUVentanaHotel ventanaPrincipal, int idRegistro, CFrigobar control, Habitacion habitacion, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 5);
        this.ventanaPrincipal = ventanaPrincipal;
        this.control = control;
        this.idRegistro = idRegistro;
        
        this.lista = control.getListaProductos(habitacion.getFrigobar().getId(), habitacion.getId(), "categoria");
        this.estado = false;
        construirPaneles(panelFondo.getLimitacion());
        actualizarTablaProductos();
        escucharEvento();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(59), limite.getAlto()));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        panelComanda = new IUPanelComanda(new Limitacion(limite.getPorcentajeAncho(59), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(70)), idRegistro);
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
        
        iuTablaFrigobar = new IUTablaFrigobar(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(6), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(93)));
        primerPanel.add(iuTablaFrigobar.tabla.deslizador);        
    }
    private void construirTercerPanel(Limitacion limite){
        botonReponer = new IUBoton("◄ reponer producto", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(50)));
        botonReponer.iuTexto.setFont(new Font("Arial", Font.PLAIN, limite.getPorcentajeAlto(7)));
        tercerPanel.add(botonReponer);
        
        botonConsumir = new IUBoton("consumir producto ►", new Limitacion(limite.getPorcentajeAncho(54), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(50)));
        botonConsumir.iuTexto.setFont(new Font("Arial", Font.PLAIN, limite.getPorcentajeAlto(7)));
        tercerPanel.add(botonConsumir);
        
        botonSalir = new IUBoton("Salir de COMANDA", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(70), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(20)));
        tercerPanel.add(botonSalir);
        
        botonGuardar = new IUBoton("Guardar COMANDA", new Limitacion(limite.getPorcentajeAncho(54), limite.getPorcentajeAlto(70), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(20)));
        tercerPanel.add(botonGuardar);
    }
    private void escucharEvento(){
        botonConsumir.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(iuTablaFrigobar.estaSeleccionado()){
                    Producto productoFrigobar = iuTablaFrigobar.getProducto();
                    if(panelComanda.existeProducto(productoFrigobar)){
                        Detalle detalle = panelComanda.getDetalle(productoFrigobar.getId());
                        
                        int cant = productoFrigobar.getCantidad();
                        int cantComanda = (int) detalle.getCantidad();
                        if(cant > 0){
                            cant--;
                            cantComanda++;
                            detalle.setCantidad(cantComanda);
                            productoFrigobar.setCantidad(cant);
                        }                        
                    }else{
                        int cant = productoFrigobar.getCantidad();
                        if(cant > 0){
                            cant--;
                        }
                        Detalle detalle = new Detalle(0);
                        detalle.setDescripcion(productoFrigobar.getMarca()+" "+productoFrigobar.getNombre());
                        detalle.setPrecio(productoFrigobar.getPrecio());
                        detalle.setCantidad(1);
                        productoFrigobar.setCantidad(cant);
                        detalle.setUnidad("uds.");
                        detalle.setTotal(productoFrigobar.getPrecio());
                        detalle.setIdComanda(0);
                        detalle.setIdRegistroHospedaje(0);
                        detalle.setProducto(productoFrigobar);
                        panelComanda.iuTablaComanda.setFila(detalle);
                    }
                }
                actualizarTablas();
            }
        });
        botonReponer.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(panelComanda.iuTablaComanda.estaSeleccionado()){
                    Detalle detalle = panelComanda.iuTablaComanda.getDetalle();
                    Producto productoFrigobar = iuTablaFrigobar.getProducto(detalle.getProducto().getId());
                    
                    if(detalle.getCantidad() > 0){
                        int cantFrigobar = productoFrigobar.getCantidad();
                        int cantComanda = (int) detalle.getCantidad();
                        cantFrigobar++;
                        cantComanda--;
                        detalle.setCantidad(cantComanda);
                        productoFrigobar.setCantidad(cantFrigobar);
                    }
                    if(detalle.getCantidad() == 0)    
                        panelComanda.iuTablaComanda.removeFila(panelComanda.iuTablaComanda.tabla.getSelectedRow());
                    actualizarTablas();
                }
                
            }
        });
        botonSalir.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        botonGuardar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(panelComanda.getEstado()){
                    setProductoModificar();
                    estado = true;
                    dispose();
                }
            }
        });
    }
    private void setProductoModificar(){        
        for (int i = 0; i < iuTablaFrigobar.getRowCount(); i++) {
            Producto producto = iuTablaFrigobar.getFila(i);
            for (int j = 0; j < panelComanda.iuTablaComanda.getRowCount(); j++) {
                Detalle detalle = panelComanda.iuTablaComanda.getFila(j);
                if(producto.getId() == detalle.getProducto().getId()){
                    listaProductos.add(producto);
                }
            }
        }        
    }
    public boolean getEstado(){
        return estado;
    }
    public Comanda getComanda(){        
        return panelComanda.getComanda();
    }
    public ArrayList<Producto> getListaProductos(){
        return listaProductos;
    }
    private void actualizarTablas(){
        iuTablaFrigobar.tabla.updateUI();
        panelComanda.actualizarTablaComanda();
    }
    private void actualizarTablaProductos(){
        iuTablaFrigobar.limpiarTabla();
        for (int i = 0; i < lista.size(); i++) {
            Producto producto = lista.get(i);
            iuTablaFrigobar.setFila(producto);
        }        
    }
}
