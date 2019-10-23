/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.controlador.CComanda;
import com.hotelfelipez.www.modulo.controlador.CFrigobar;
import com.hotelfelipez.www.modulo.controlador.CHabitaciones;
import com.hotelfelipez.www.modulo.controlador.CRegistroPersona;
import com.hotelfelipez.www.modulo.disponibilidad.IUVentanaDisponibilidad;
import com.hotelfelipez.www.modulo.frigobar.IUModificarComanda;
import com.hotelfelipez.www.modulo.frigobar.IUNuevaComanda;
import com.hotelfelipez.www.modulo.frigobar.IUPanelComanda;
import com.hotelfelipez.www.modulo.frigobar.IUTablaComanda;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Comanda;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.Producto;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import com.hotelfelipez.www.modulo.principal.IUVentanaOpciones;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUPanelServicio extends IUPanel{

    private IUVentanaHotel ventanaPrincipal;
    private Habitacion habitacion;
    private IUVentanaRegistroHospedajeOcupado iuRegistro;
    private IUPanelBD primerPanel;
    private IUEtiqueta iuTitulo;
    private IUPanelComanda iuComanda;
    private IUEtiqueta iuTituloTabla;
    private IUTablaComanda iuTablaComanda;
    private IUPanelBD segundoPanel;
    private IUBoton botonNuevaComanda;
    private IUBoton botonModificarComanda;
    private IUBoton botonEliminarComanda;
    private IUBoton botonImprimirComanda;
    private IUBoton botonImprimirTodasComandas;
    private IUBoton botonSeleccionarTodo;
    private IUBoton botonDeseleccionarTodo;
    private IUBoton botonSalir;
    private IUBoton botonPagarComanda;
    
    public IUPanelServicio(IUVentanaHotel ventanaPrincipal, IUVentanaRegistroHospedajeOcupado iuRegistro, Habitacion habitacion, Limitacion limitacion) {
        super(limitacion);
        this.ventanaPrincipal = ventanaPrincipal;        
        this.iuRegistro = iuRegistro;
        this.habitacion = habitacion;
        construirPaneles(getLimitacion());
        escucharEvento();
        actualizarTablaComandas();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(85), limite.getAlto()));
        primerPanel.setArco(25);
        add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(86), 0, limite.getPorcentajeAncho(14), limite.getAlto()));
        add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        iuTitulo = new IUEtiqueta("Comanda", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(4)));
        iuTitulo.setForeground(new Color(120, 0, 0));        
        iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        primerPanel.add(iuTitulo);
        
        iuComanda = new IUPanelComanda(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(7), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(91)), iuRegistro.getRegistroHospedaje().getId());
        primerPanel.add(iuComanda);
        
        iuTituloTabla = new IUEtiqueta("Lista de Comandas", new Limitacion(limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(57), limite.getPorcentajeAlto(4)));
        iuTituloTabla.setForeground(new Color(120, 0, 0));        
        iuTituloTabla.setHorizontalAlignment(SwingConstants.CENTER);
        iuTituloTabla.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        primerPanel.add(iuTituloTabla);
        
        iuTablaComanda = new IUTablaComanda(iuComanda, new Limitacion(limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(7), limite.getPorcentajeAncho(57), limite.getPorcentajeAlto(82)));
        primerPanel.add(iuTablaComanda.tabla.deslizador);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonNuevaComanda = new IUBoton("nueva comanda", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonNuevaComanda);
        
        botonModificarComanda = new IUBoton("modificar comanda", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(12), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonModificarComanda);

        botonEliminarComanda = new IUBoton("eliminar comanda", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(21), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonEliminarComanda);
        
        botonImprimirComanda = new IUBoton("imprimir comanda", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(30), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonImprimirComanda);
        
        botonImprimirTodasComandas = new IUBoton("imprimir todas comandas", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(39), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonImprimirTodasComandas);
        
        botonSeleccionarTodo = new IUBoton("seleccionar todo", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(48), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonSeleccionarTodo);
        
        botonDeseleccionarTodo = new IUBoton("deseleccionar todo", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(57), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonDeseleccionarTodo);
        
        botonSalir = new IUBoton("salir del registro", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(66), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(13)));
        segundoPanel.add(botonSalir);
    }
    private void actualizarTablaComandas(){
        CComanda control = new CComanda();
        ArrayList<Comanda> lista = control.getListaComandas(iuRegistro.getRegistroHospedaje().getId());
        
        iuTablaComanda.limpiarTabla();
        for (int i = 0; i < lista.size(); i++) {
            Comanda comanda = lista.get(i);
            iuTablaComanda.setFila(comanda);
        }
    }
    private void escucharEvento(){
        botonNuevaComanda.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                iuRegistro.setOpacity(0.2f);
                
                String[] opciones = {"comanda FRIGOBAR", "comanda OTROS"};
                IUVentanaOpciones iuOpciones = new IUVentanaOpciones(ventanaPrincipal, "elija una opcion", new Limitacion(Asistente.ANCHO/3, Asistente.ALTO - Asistente.ALTO/7), opciones);
                iuOpciones.mostrarVentana();
                if(iuOpciones.getEstado()){
                    switch(iuOpciones.getOpcion()){
                        case"comanda FRIGOBAR":
                            IUNuevaComanda iuNuevaComanda = new IUNuevaComanda(ventanaPrincipal, "Frigobar", iuRegistro.getRegistroHospedaje().getId(), new CFrigobar(), habitacion, "Registrar Nueva Comanda de Frigobar", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/7, Asistente.ALTO - Asistente.ALTO/12));
                            iuNuevaComanda.mostrarVentana();
                            if(iuNuevaComanda.getEstado()){
                                if(new CComanda().guardarNuevaComanda(iuNuevaComanda.getComanda())){
                                    ArrayList<Producto> lista = iuNuevaComanda.getListaProductos();
                                    CFrigobar control = new CFrigobar();
                                    for (int i = 0; i < lista.size(); i++) 
                                        control.modificarProducto(lista.get(i));
                                } 
                                actualizarTablaComandas();
                            }
                        break;
                        case"comanda OTROS":
                            
                        break;
                    }
                }
                
                iuRegistro.setOpacity(1f);
            }
        });
        botonModificarComanda.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                
                if(iuTablaComanda.estaSeleccionado()){
                    iuRegistro.setOpacity(0.2f);
                    
                    Comanda comanda = iuTablaComanda.getComanda();
                    switch(comanda.getNombre()){
                        case "Frigobar":
                            if(comanda.getEstado().equalsIgnoreCase("PAGADO")){
                                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento...!  No puede modificar la comanda, por que ya esta PAGADO...!", "advertencia");
                            }else{
                                IUModificarComanda iuModificarComanda = new IUModificarComanda(ventanaPrincipal, comanda, "Frigobar", iuRegistro.getRegistroHospedaje().getId(), new CFrigobar(), habitacion, "Modificar la Comanda de Frigobar", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/7, Asistente.ALTO - Asistente.ALTO/12));
                                iuModificarComanda.mostrarVentana();
                                if(iuModificarComanda.getEstado()){
                                    if(new CComanda().modificarComanda(iuModificarComanda.getComanda(), iuModificarComanda.getDetalleEliminados())){
                                        ArrayList<Producto> lista = iuModificarComanda.getListaProductos();
                                        CFrigobar control = new CFrigobar();
                                        for (int i = 0; i < lista.size(); i++){
                                            control.modificarProducto(lista.get(i));
                                        }
                                    }
                                    actualizarTablaComandas();
                                }
                            }                            
                        break;
                    }
                    iuRegistro.setOpacity(1f);
                }                
            }
        });
        botonEliminarComanda.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(iuTablaComanda.estaSeleccionado()){
                    iuRegistro.setOpacity(0.2f);
                    
                    Comanda comanda = iuTablaComanda.getComanda();
                    if(Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "esta seguro que quiere eliminar la comanda: "+comanda.getNroComanda()+"  "+comanda.getNombre(), "advertencia"))
                        if(new CComanda().eliminarComanda(comanda))
                            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "se elimino la comanda CORRECTAMENTE...!", "advertencia");                    
                    actualizarTablaComandas();
                    //mequede aqui...debo modificar el producto modificado
                    iuRegistro.setOpacity(1f);
                }
            }
        });
        botonSeleccionarTodo.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                iuTablaComanda.seleccionarTodo();
            }
        });
        botonDeseleccionarTodo.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                iuTablaComanda.deSeleccionarTodo();
            }
        });
    }
}
