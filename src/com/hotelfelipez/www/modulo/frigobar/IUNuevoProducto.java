/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.frigobar;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelCTU;
import com.aplicacionjava.www.paneles.IUPanelTCB;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CFrigobar;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Producto;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Jesus Junior Felipez
 */
public class IUNuevoProducto extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanelBD primerPanel;
    private IUPanelTCB panelCategoria;
    private IUPanelCT panelMarca;
    private IUPanelCT panelNombre;
    private IUPanelTCB panelMoneda;
    private IUPanelCTU panelCosto;
    private IUPanelCTU panelPrecio;
    private IUPanelCTU panelCantidad;
    private IUPanelCT panelFechaVencimiento;
    private IUBoton botonFecha;
    private IUPanelCT panelPromocion;
    private IUPanelBD segundoPanel;
    private IUBoton botonSalir;
    private IUBoton botonGuardar;
    
    private CFrigobar controlFrigobar;
    private ArrayList<Moneda> listaMonedas;
    private int idfrigobar;
    private int idhabitacion;
    
    private boolean estado;
    private Producto producto;
    
    public IUNuevoProducto(IUVentanaHotel ventanaPrincipal, CFrigobar controlFrigobar, int idfrigobar, int idhabitacion, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 5);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.listaMonedas = controlFrigobar.getListaMonedas();
        this.controlFrigobar = controlFrigobar;
        this.producto = null;
        this.idfrigobar = idfrigobar;
        this.idhabitacion = idhabitacion;
        this.estado = false;
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEvento();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(85)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(87), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(12)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        
        panelCategoria = new IUPanelTCB("categoria", controlFrigobar.getCategoriasFrigobar(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(8)), 40, 60);
        panelCategoria.iuTexto.setResctriccionLetras();
        primerPanel.add(panelCategoria);
        
        panelMarca = new IUPanelCT("marca", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(12), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(8)), 40, 60);
        panelMarca.iuTexto.setRestriccionLetras();
        primerPanel.add(panelMarca);
        
        panelNombre = new IUPanelCT("nombre", "", new Limitacion(limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(12), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(8)), 40, 60);
        primerPanel.add(panelNombre);
        
        panelMoneda = new IUPanelTCB("monedas", getTiposMonedas(), new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(22), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(8)), 40, 60);        
        primerPanel.add(panelMoneda);
                
        panelCosto = new IUPanelCTU("costo", "", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(32), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(8)), 40, 60, 40);
        panelCosto.iuTexto.setRestriccionNumeroDecimal();
        primerPanel.add(panelCosto);
        
        panelPrecio = new IUPanelCTU("precio", "", "", new Limitacion(limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(32), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(8)), 40, 60, 40);
        panelPrecio.iuTexto.setRestriccionNumeroDecimal();
        primerPanel.add(panelPrecio);
        
        panelCantidad = new IUPanelCTU("cantidad", "0", "uddes.", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(42), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(8)), 40, 60, 50);
        panelCantidad.iuTexto.setEditable(false);
        panelCantidad.iuTexto.setFocusable(false);
        primerPanel.add(panelCantidad);
        
        panelFechaVencimiento = new IUPanelCT("fecha Vencimiento", "__/___/____", new Limitacion(limite.getPorcentajeAncho(27), limite.getPorcentajeAlto(42), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(8)), 40, 60);
        panelFechaVencimiento.iuTexto.setEditable(false);
        panelFechaVencimiento.iuTexto.setFocusable(false);
        primerPanel.add(panelFechaVencimiento);
                       
        panelPromocion = new IUPanelCT("promocion", "NO", new Limitacion(limite.getPorcentajeAncho(62), limite.getPorcentajeAlto(42), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(8)), 40, 60);
        panelPromocion.iuTexto.setEditable(false);
        panelPromocion.iuTexto.setFocusable(false);
        primerPanel.add(panelPromocion);
        
    }
    private void construirSegundoPanel(Limitacion limite){
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(30), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(40)));
        segundoPanel.add(botonSalir);
        
        botonGuardar = new IUBoton("guardar", new Limitacion(limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(30), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(40)));
        segundoPanel.add(botonGuardar);
    }
    private String[] getTiposMonedas(){
        String[] monedas = new String[listaMonedas.size()+1];
        monedas[0] = "";
        int indice = 1;
        for (int i = 0; i < listaMonedas.size(); i++) {
            Moneda moneda = listaMonedas.get(i);
            monedas[indice] = moneda.getTipo()+" ("+moneda.getUnidad()+")";
            indice++;
        }
        return monedas;
    }
    private boolean estaValidadoCamposDatos(){
        boolean verificador = false;        
        if(!panelCategoria.iuTexto.getTexto().isEmpty()){
            if(!panelMarca.iuTexto.getText().isEmpty()){
                if(!panelNombre.iuTexto.getText().isEmpty()){
                    if(!panelMoneda.iuTexto.getTexto().isEmpty()){
                        if(!panelCosto.iuTexto.getText().isEmpty()){
                            if(!panelPrecio.iuTexto.getText().isEmpty()){
                                verificador = true;
                            }else{
                                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo de datos PRECIO", "advertencia");            
                            }
                        }else{
                            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo de datos COSTO", "advertencia");            
                        }
                    }else{
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo de datos TIPO MONEDA", "advertencia");            
                    }
                }else{
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo de datos NOMBRE", "advertencia");            
                }
            }else{
                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo de datos MARCA", "advertencia");            
            }
        }else{
            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no puede estar vacio el campo de datos CATEGORIA", "advertencia");            
        }
        return verificador;
    }
    private void escucharEvento(){
        panelMoneda.iuTexto.addItemListener((ItemEvent e) -> {            
            for (int i = 0; i < listaMonedas.size(); i++) {
                Moneda moneda = listaMonedas.get(i);
                if(panelMoneda.getTexto().equalsIgnoreCase(moneda.getTipo()+" ("+moneda.getUnidad()+")")){
                    panelPrecio.iuTexto.iuUnidad.setText(moneda.getUnidad());
                    panelCosto.iuTexto.iuUnidad.setText(moneda.getUnidad());
                    panelPrecio.iuTexto.iuUnidad.setName(String.valueOf(moneda.getId()));
                    panelCosto.iuTexto.iuUnidad.setName(String.valueOf(moneda.getId()));
                    break;
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
                if(estaValidadoCamposDatos()){
                    dispose();
                    estado = true;
                }                
            }
        });
    }
    public boolean getEstado(){
        return estado;
    }
    public Producto getProducto(){
        Producto p = new Producto(0);
        p.setCategoria(panelCategoria.iuTexto.getTexto());
        p.setMarca(panelMarca.iuTexto.getText());
        p.setNombre(panelNombre.iuTexto.getText());
        p.setCosto(Asistente.acotarNumero(Double.parseDouble(panelCosto.iuTexto.getText()), 2));
        p.setPrecio(Asistente.acotarNumero(Double.parseDouble(panelPrecio.iuTexto.getText()), 2));
        p.setCantidad(0);
        p.setFechaVencimiento("");
        p.setPromocion("NO");
        p.setPrecioAnterior(Asistente.acotarNumero(Double.parseDouble(panelPrecio.iuTexto.getText()), 2));
        p.setIdmoneda(Integer.parseInt(panelPrecio.iuTexto.iuUnidad.getName()));
        p.setIdfrigobar(idfrigobar);
        p.setIdhabitacion(idhabitacion);
        
        return p;
    }
}