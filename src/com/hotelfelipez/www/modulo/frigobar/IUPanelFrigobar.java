/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.frigobar;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.botones.IUBotonRadio;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelTT;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.controlador.CFrigobar;
import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.habitaciones.IUVentanaHabitaciones;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Frigobar;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.Producto;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;

/**
 *
 * @author Rudolf Felipez Mancilla
 */
public class IUPanelFrigobar extends IUPanel{

    private IUPanel primerPanel;
    private IUPanelTT panelNombreFrigobar;
    private IUPanelTT panelCantidadFrigobar;
    private ButtonGroup grupoBotones;
    private IUBotonRadio botonOrdCategoria;
    private IUBotonRadio botonOrdCant;
    private IUBotonRadio botonOrdDias;    
    private IUTablaProductos tablaFrigobar;
    private IUPanel segundoPanel;
    private IUBoton botonNuevoProducto;
    private IUBoton botonModificarProducto;
    private IUBoton botonEliminarProducto;
    
    private IUBoton botonReponerProductos;
    private IUBoton botonPromocionar;
    
    private IUBoton botonImprimirFrigobar;
    
    private IUBoton botonDesPromocionar;
    
    private CFrigobar controlFrigobar;
    private IUVentanaHotel ventanaPrincipal;
    private IUVentanaHabitaciones ventanaHabitaciones;
    private Habitacion habitacion;
    private Frigobar frigobar;
    
    public IUPanelFrigobar(CFrigobar controlFrigobar, IUVentanaHotel ventanaPrincipal, IUVentanaHabitaciones ventanaHabitaciones, Limitacion limitacion) {
        super(limitacion);
        this.controlFrigobar = controlFrigobar;
        this.ventanaPrincipal = ventanaPrincipal;
        this.ventanaHabitaciones = ventanaHabitaciones;
        construirPaneles();
        escucharEventos();        
    }
    private void construirPaneles(){
        primerPanel = new IUPanel(new Limitacion(limitacion.getPorcentajeAncho(80), limitacion.getAlto()));
        add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limitacion.getPorcentajeAncho(80), 0, limitacion.getPorcentajeAncho(20), limitacion.getAlto()));
        add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        panelNombreFrigobar = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(3), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(7)), "nombre frigobar: ", "", 70, 30);
        primerPanel.add(panelNombreFrigobar);
        
        panelCantidadFrigobar = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(27), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(7)), "cantidad total: ", "", 80, 20);
        primerPanel.add(panelCantidadFrigobar);
        
        grupoBotones = new ButtonGroup();
        
        botonOrdCategoria = new IUBotonRadio("categoria", new Limitacion(limite.getPorcentajeAncho(47), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(7)), true);
        primerPanel.add(botonOrdCategoria);
        grupoBotones.add(botonOrdCategoria);
        
        botonOrdDias = new IUBotonRadio("dias", new Limitacion(limite.getPorcentajeAncho(62), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(7)), false);
        primerPanel.add(botonOrdDias);
        grupoBotones.add(botonOrdDias);
        
        botonOrdCant = new IUBotonRadio("cant", new Limitacion(limite.getPorcentajeAncho(77), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(7)), false);
        primerPanel.add(botonOrdCant);
        grupoBotones.add(botonOrdCant);
        
        tablaFrigobar = new IUTablaProductos(new Limitacion(limite.getPorcentajeAncho(3), limite.getPorcentajeAlto(8), limite.getPorcentajeAncho(94), limite.getPorcentajeAlto(90)));
        primerPanel.add(tablaFrigobar.tabla.deslizador);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonNuevoProducto = new IUBoton("nuevo producto", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(5)));
        segundoPanel.add(botonNuevoProducto);
        
        botonModificarProducto = new IUBoton("modificar producto", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(13), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(5)));
        segundoPanel.add(botonModificarProducto);
        
        botonEliminarProducto = new IUBoton("eliminar producto", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(21), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(5)));
        segundoPanel.add(botonEliminarProducto);
        
        botonReponerProductos = new IUBoton("reponer productos", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(35), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(5)));
        segundoPanel.add(botonReponerProductos);
        
        botonPromocionar = new IUBoton("promocionar producto", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(5)));
        segundoPanel.add(botonPromocionar);
        
        botonDesPromocionar = new IUBoton("quitar promocion", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(51), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(5)));
        segundoPanel.add(botonDesPromocionar);
        
        botonImprimirFrigobar = new IUBoton("imprimir frigobar", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(70), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(5)));
        segundoPanel.add(botonImprimirFrigobar);
        
        
    }
    public void setHabitacion(Habitacion habitacion){
        this.habitacion = habitacion;
        this.frigobar = habitacion.getFrigobar();
        panelNombreFrigobar.iuTexto.setText(frigobar.getNombreFrigobar());
        panelCantidadFrigobar.iuTexto.setText(String.valueOf(frigobar.getCantidadProductos()));
        actualizarTablaProductos("categoria");
        actualizarBotonesRadios("categoria");
    }
    private void escucharEventos(){
        botonOrdCategoria.addItemListener((ItemEvent e) -> {
            actualizarTablaProductos("categoria");
        });
        botonOrdCant.addItemListener((ItemEvent e) -> {
            actualizarTablaProductos("cantidad");
        });
        botonOrdDias.addItemListener((ItemEvent e) -> {
            actualizarTablaProductos("fechaVencimiento");
        });
        botonNuevoProducto.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ventanaPrincipal.setOpacity(0.3f);
                ventanaHabitaciones.setOpacity(0.3f);
                
                IUNuevoProducto nuevoProducto = new IUNuevoProducto(ventanaPrincipal, controlFrigobar, frigobar.getId(), habitacion.getId(), "nuevo producto", new Limitacion(Asistente.ANCHO/2 - Asistente.ANCHO/10, Asistente.ALTO - Asistente.ALTO/7));
                nuevoProducto.mostrarVentana(); 
                if(nuevoProducto.getEstado()){
                    controlFrigobar.agregarNuevoProducto(nuevoProducto.getProducto());
                    actualizarFrigobar();
                    actualizarTablaProductos("categoria");
                }
                
                ventanaHabitaciones.setOpacity(1f);
                ventanaPrincipal.setOpacity(1f);
            }
        });
        botonModificarProducto.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaFrigobar.estaSeleccionado()){
                    ventanaPrincipal.setOpacity(0.3f);
                    ventanaHabitaciones.setOpacity(0.3f);

                    IUModificarProducto modificarProducto = new IUModificarProducto(ventanaPrincipal, controlFrigobar, "modificar producto", new Limitacion(Asistente.ANCHO/2 - Asistente.ANCHO/10, Asistente.ALTO - Asistente.ALTO/7));        
                    modificarProducto.setProducto(tablaFrigobar.getProducto());
                    modificarProducto.mostrarVentana();
                    if(modificarProducto.getEstado()){
                        controlFrigobar.modificarProducto(modificarProducto.getProducto());
                        actualizarTablaProductos("categoria");
                    }

                    ventanaHabitaciones.setOpacity(1f);
                    ventanaPrincipal.setOpacity(1f);
                }else
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... debe seleccionar una fila de la tabla de productos.", "advertencia");
            }
        });
        botonEliminarProducto.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaFrigobar.estaSeleccionado()){
                    ventanaPrincipal.setOpacity(0.3f);
                    ventanaHabitaciones.setOpacity(0.3f);
                    
                    if(Asistente.mensajeVerificacion(ventanaPrincipal, "peligro", "esta seguro que desea eliminar este producto...?", "advertencia")){
                        if(controlFrigobar.eliminarProducto(tablaFrigobar.getProducto())){
                            Asistente.mensajeVerificacion(ventanaPrincipal, "correcto", "en hora buena.... se elimino correctamente el producto...!", "confirmacion");
                            actualizarFrigobar();
                            actualizarTablaProductos("categoria");
                        }else
                            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero no se puede eliminar el producto por que tiene al menos 1 cantidad en frigobar", "advertencia");
                    }
                    
                    ventanaHabitaciones.setOpacity(1f);
                    ventanaPrincipal.setOpacity(1f);
                }else
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... debe seleccionar una fila de la tabla de productos.", "advertencia");
            }
        });
        botonReponerProductos.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaFrigobar.estaSeleccionado()){
                    ventanaPrincipal.setOpacity(0.3f);
                    ventanaHabitaciones.setOpacity(0.3f);
                    
                    Producto producto = tablaFrigobar.getProducto();
                    if(producto.getCantidad() > 0){
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "tome encuenta por favor... que esta intentando modificar la fecha y cantidad del producto seleccionado", "advertencia");
                    }
                    
                    IUReponerProducto reponerProducto = new IUReponerProducto(ventanaPrincipal, "reponer producto", new Limitacion(Asistente.ANCHO/3, Asistente.ALTO - Asistente.ALTO/3));
                    reponerProducto.setProducto(producto);
                    reponerProducto.mostrarVentana();
                    if(reponerProducto.getEstado()){
                        controlFrigobar.modificarProducto(reponerProducto.getProducto());
                        actualizarTablaProductos("cantidad");
                        actualizarBotonesRadios("cantidad");
                    }
                    
                    ventanaHabitaciones.setOpacity(1f);
                    ventanaPrincipal.setOpacity(1f);
                }else
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... debe seleccionar una fila de la tabla de productos.", "advertencia");
            }
        });
        botonPromocionar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaFrigobar.estaSeleccionado()){
                    boolean verificador = false;
                    Producto producto = tablaFrigobar.getProducto();
                    if(producto.getCantidad() > 0)
                        if(producto.getPromocion().equalsIgnoreCase("NO"))
                            verificador = true;
                        else
                            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... No puede promocionar el producto. por que este producto YA ESTA en PROMOCION", "advertencia");
                    else
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... No puede promocionar el producto. por que la cantidad DEBE ser MAYOR a 0", "advertencia");
                    
                    if(verificador){
                        ventanaPrincipal.setOpacity(0.3f);
                        ventanaHabitaciones.setOpacity(0.3f);                    

                        IUPromocionarProducto promocionarProducto = new IUPromocionarProducto(ventanaPrincipal, "promocionar producto", new Limitacion(Asistente.ANCHO/3, Asistente.ALTO - Asistente.ALTO/3));
                        promocionarProducto.setProducto(producto);
                        promocionarProducto.mostrarVentana();
                        if(promocionarProducto.getEstado()){
                            controlFrigobar.modificarProducto(promocionarProducto.getProducto());
                            actualizarTablaProductos("fechaVencimiento");
                            actualizarBotonesRadios("fechaVencimiento");
                        }
                        
                        ventanaHabitaciones.setOpacity(1f);
                        ventanaPrincipal.setOpacity(1f);
                    }                    
                }else
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... debe seleccionar una fila de la tabla de productos.", "advertencia");
            }
        });
        botonDesPromocionar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaFrigobar.estaSeleccionado()){
                    Producto producto = tablaFrigobar.getProducto();
                    if(producto.getPromocion().equalsIgnoreCase("SI")){
                        if(Asistente.mensajeVerificacion(ventanaPrincipal, "peligro", "esta seguro de QUERER QUITAR LA PROMOCION a este producto..?.", "advertencia")){
                            producto.setPrecio(Asistente.acotarNumero(producto.getPrecioAnterior(), 2));
                            producto.setPromocion("NO");
                            controlFrigobar.modificarProducto(producto);
                            actualizarTablaProductos("fechaVencimiento");
                            actualizarBotonesRadios("fechaVencimiento");
                        }
                    }
                }
            }
        });
    }
    private void actualizarFrigobar(){
        Conexion conexion = new Conexion();
        frigobar.setCantidadProductos(conexion.getDato("cantidad", "select count(*) as cantidad from producto where idfrigobar = "+frigobar.getId()+" and idhabitacion = "+habitacion.getId()));
        panelCantidadFrigobar.iuTexto.setText(String.valueOf(frigobar.getCantidadProductos()));        
        controlFrigobar.modificarFrigobar(frigobar);
        conexion.cerrarConexion();
    }
    private void actualizarBotonesRadios(String tipoOrden){
        switch(tipoOrden){
            case "categoria":
                botonOrdCategoria.setSelected(true);
                botonOrdCant.setSelected(false);
                botonOrdDias.setSelected(false);
            break;
            case "cantidad":
                botonOrdCategoria.setSelected(false);
                botonOrdCant.setSelected(true);
                botonOrdDias.setSelected(false);
            break;
            case "fechaVencimiento":
                botonOrdCategoria.setSelected(false);
                botonOrdCant.setSelected(false);
                botonOrdDias.setSelected(true);
            break;
        }
    }
    private void actualizarTablaProductos(String tipoOrden){
        ArrayList<Producto> lista = controlFrigobar.getListaProductos(frigobar.getId(), habitacion.getId(), tipoOrden);
        tablaFrigobar.limpiarTabla();
        for (int i = 0; i < lista.size(); i++) {
            Producto p = lista.get(i);
            tablaFrigobar.setFila(p);
        }        
    }
}
