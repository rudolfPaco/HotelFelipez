/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class IUPanelRegistroPersonal extends IUPanel{
    
    private IUVentanaHotel ventanaPrincipal;
    private IURegistroHospedaje iuRegistroHospedaje;
    private IUTablaPersonas tablaPersonas;
    
    private IUBoton botonBuscarPersonas;
    private IUBoton botonNuevaPersona;
    private IUBoton botonModificarDatos;
    private IUBoton botonQuitarPersona;
    
    public IUPanelRegistroPersonal(IUVentanaHotel ventanaPrincipal, IURegistroHospedaje iuResgistroHospedaje, Limitacion limitacion) {
        super(limitacion);
        this.ventanaPrincipal = ventanaPrincipal;
        this.iuRegistroHospedaje = iuResgistroHospedaje;
        construirPaneles();
        escucharEventos();
    }
    private void construirPaneles(){
        tablaPersonas = new IUTablaPersonas(new Limitacion(limitacion.getPorcentajeAncho(1), limitacion.getPorcentajeAlto(1), limitacion.getPorcentajeAncho(98), limitacion.getPorcentajeAlto(28)));
        add(tablaPersonas.tabla.deslizador);
        
        botonBuscarPersonas = new IUBoton("buscar personas", new Limitacion(limitacion.getPorcentajeAncho(76), limitacion.getPorcentajeAlto(31), limitacion.getPorcentajeAncho(23), limitacion.getPorcentajeAlto(7)));
        add(botonBuscarPersonas);
        
        botonNuevaPersona = new IUBoton("registrar nueva persona", new Limitacion(limitacion.getPorcentajeAncho(50), limitacion.getPorcentajeAlto(31), limitacion.getPorcentajeAncho(25), limitacion.getPorcentajeAlto(7)));
        add(botonNuevaPersona);
        
        botonModificarDatos = new IUBoton("modificar datos persona", new Limitacion(limitacion.getPorcentajeAncho(22), limitacion.getPorcentajeAlto(31), limitacion.getPorcentajeAncho(27), limitacion.getPorcentajeAlto(7)));
        add(botonModificarDatos);
        
        botonQuitarPersona = new IUBoton("quitar persona", new Limitacion(limitacion.getPorcentajeAncho(1), limitacion.getPorcentajeAlto(31), limitacion.getPorcentajeAncho(20), limitacion.getPorcentajeAlto(7)));
        add(botonQuitarPersona);
    }
    private void escucharEventos(){
        botonNuevaPersona.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                iuRegistroHospedaje.setOpacity(0.3f);                
                
                //tarea ---> validar su numero de C.I. para no duplicar la persona en la base de datos.
                IUNuevaPersona iuRegistroPersona = new IUNuevaPersona(ventanaPrincipal, "registro de nueva persona al hotel", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/3, Asistente.ALTO));
                iuRegistroPersona.mostrarVentana();
                if(iuRegistroPersona.getEstado())
                    tablaPersonas.setFila(iuRegistroPersona.getPersona());
                
                iuRegistroHospedaje.setOpacity(1f);
            }
        });
        botonModificarDatos.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                iuRegistroHospedaje.setOpacity(0.3f);
                
                if(tablaPersonas.estaSeleccionado()){
                    Persona persona = tablaPersonas.getPersona();
                    IUModificarPersona iuModificarDatos = new IUModificarPersona(ventanaPrincipal, persona, "registro de nueva persona al hotel", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/3, Asistente.ALTO));
                    iuModificarDatos.mostrarVentana();
                    if(iuModificarDatos.getEstado())
                        tablaPersonas.reemplazarPersona(iuModificarDatos.getPersona());
                    
                }               
                
                iuRegistroHospedaje.setOpacity(1f);
            }
        });
    }
    public boolean existePersonaRegistro(){
        boolean verificador = false;
        if(!tablaPersonas.isVacia())
            verificador = true;
        return verificador;
    }
    public ArrayList<Persona> getListaPersonas(){
        ArrayList<Persona> personas = new ArrayList<>();
        for (int i = 0; i < tablaPersonas.getRowCount(); i++) {
            personas.add(tablaPersonas.getFila(i));
        }
        return personas;
    }
}
