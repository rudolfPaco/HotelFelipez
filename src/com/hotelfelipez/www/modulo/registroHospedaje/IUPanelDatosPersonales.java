/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.controlador.CRegistroPersona;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUPanelDatosPersonales extends IUPanel{

    private IUVentanaHotel ventanaPrincipal;
    
    private IUVentanaRegistroHospedajeOcupado iuRegistroOcupado;
    private CRegistroPersona controlRegistroPersonas;
    
    private IUPanelBD primerPanel;
    public IUEtiquetaI iuFoto;
    public IUPanelCT iuTipoPersona;
    private IUTablaPersonas iuTablaPersonas;
    public IUPanelTA iuObservacion;
    private IUPanelTA iuMensaje;
    
    private IUPanelBD segundoPanel;
    private IUBoton botonVerDatos;
    private IUBoton botonBuscarPersona;
    private IUBoton botonNuevoPersona;
    private IUBoton botonModificarPersona;
    private IUBoton botonQuitarPersona;
    private IUBoton botonImprimirPersona;
    private IUBoton botonImprimirRegistros;
    private IUBoton botonSalir;
    private IUBoton botonModificarMensaje;
    private IUBoton botonEliminarMensaje;
    
    public IUPanelDatosPersonales(IUVentanaHotel ventanaPrincipal, CRegistroPersona control, IUVentanaRegistroHospedajeOcupado iuRegistroOcupado, Limitacion limitacion) {
        super(limitacion);
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlRegistroPersonas = control;        
        this.iuRegistroOcupado = iuRegistroOcupado;
        construirPaneles(getLimitacion());
        actualizarTablaPersonas();
        escucharEvento();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(85), limite.getAlto()));        
        primerPanel.setArco(20);
        add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(86), 0, limite.getPorcentajeAncho(14), limite.getAlto()));        
        add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        iuFoto = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(13), limite.getPorcentajeAlto(37)));
        iuFoto.setBorder(new LineBorder(Color.LIGHT_GRAY));
        primerPanel.add(iuFoto);
        
        iuTipoPersona = new IUPanelCT("tipo persona", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(42), limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(8)), 44, 56);
        iuTipoPersona.iuTexto.setEditable(false);
        iuTipoPersona.iuTexto.setFocusable(false);        
        iuTipoPersona.iuTexto.setForeground(new Color(120, 0, 0));
        primerPanel.add(iuTipoPersona);
        
        iuTablaPersonas = new IUTablaPersonas(this, new Limitacion(limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(81), limite.getPorcentajeAlto(47)));
        primerPanel.add(iuTablaPersonas.tabla.deslizador);
        
        iuObservacion = new IUPanelTA("observacion", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(45)), 7, 93);
        iuObservacion.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 15));
        iuObservacion.iuAreaTexto.setEditable(false);
        iuObservacion.iuAreaTexto.setFocusable(false);
        primerPanel.add(iuObservacion);
        
        iuMensaje = new IUPanelTA("mensaje del huesped", "", new Limitacion(limite.getPorcentajeAncho(31), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(68), limite.getPorcentajeAlto(45)), 7, 93);
        iuMensaje.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 15));
        iuMensaje.iuAreaTexto.setEditable(false);
        iuMensaje.iuAreaTexto.setFocusable(false);
        iuMensaje.iuAreaTexto.setForeground(new Color(230, 0, 115));
        primerPanel.add(iuMensaje);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonVerDatos = new IUBoton("ver datos personales", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonVerDatos);
                
        botonBuscarPersona = new IUBoton("buscar persona", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(12), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonBuscarPersona);
        
        botonNuevoPersona = new IUBoton("registrar nueva persona", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(21), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonNuevoPersona);
        
        botonModificarPersona = new IUBoton("modificar datos persona", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(30), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonModificarPersona);
        
        botonQuitarPersona = new IUBoton("quitar persona de tabla", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(39), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonQuitarPersona);
        
        botonImprimirPersona = new IUBoton("imprimir datos persona", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(48), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonImprimirPersona);
        
        botonImprimirRegistros = new IUBoton("imprimir todos registros", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(57), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonImprimirRegistros);
        
        botonSalir = new IUBoton("salir del registro", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(66), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(13)));
        segundoPanel.add(botonSalir);
                
        botonModificarMensaje = new IUBoton("modificar mensaje", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(82), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonModificarMensaje);
        
        botonEliminarMensaje = new IUBoton("eliminar mensaje", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(91), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonEliminarMensaje);
    }
    private void escucharEvento(){
        botonVerDatos.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(iuTablaPersonas.estaSeleccionado()){
                    
                    iuRegistroOcupado.setOpacity(0.3f);
                    
                    Persona persona = iuTablaPersonas.getPersona();
                    IUMostrarPersona iuVerPersona = new IUMostrarPersona(ventanaPrincipal, "Datos de la Persona", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/3, Asistente.ALTO));
                    iuVerPersona.setPersona(persona);
                    iuVerPersona.mostrarVentana();

                    iuRegistroOcupado.setOpacity(1f);
                }
                
            }
        });
        botonBuscarPersona.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                iuRegistroOcupado.setOpacity(0.3f);
                
                IUBuscarPesonas iuBuscar = new IUBuscarPesonas(ventanaPrincipal, controlRegistroPersonas, iuTablaPersonas, "buscar personas", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/7, Asistente.ALTO));
                iuBuscar.mostrarVentana();
                if(iuBuscar.getEstado()){
                    if(controlRegistroPersonas.guardarPersonaRegistroHospedaje(iuBuscar.getPersona().getId())){
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "En buena hora...! se AGREGO otra persona al registro de datos correctamente...!", "advertencia");
                        actualizarTablaPersonas();
                    }
                }
                    //iuTablaPersonas.setFila(iuBuscar.getPersona());
                
                iuRegistroOcupado.setOpacity(1f);
            }
        });
        botonNuevoPersona.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                iuRegistroOcupado.setOpacity(0.3f);

                IUNuevaPersona iuNuevaPersona = new IUNuevaPersona(ventanaPrincipal, "registro de nueva persona al hotel", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/3, Asistente.ALTO));
                iuNuevaPersona.mostrarVentana();
                if(iuNuevaPersona.getEstado()){                        
                    Persona persona = iuNuevaPersona.getPersona();
                    if(controlRegistroPersonas.guardarNuevaPersona(persona))
                        if(controlRegistroPersonas.guardarPersonaRegistroHospedaje(Asistente.getId("idpersona", "select idpersona from persona ORDER by idpersona DESC LIMIT 1"))){
                            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "En buena hora...! se guardo los datos de la NUEVA persona correctamente...!", "advertencia");
                            actualizarTablaPersonas();
                        }                            
                }

                iuRegistroOcupado.setOpacity(1f);
            }
        });
        botonModificarPersona.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(iuTablaPersonas.estaSeleccionado()){
                    Persona persona = iuTablaPersonas.getPersona();
                    iuRegistroOcupado.setOpacity(0.3f);
                    
                    IUModificarPersona iuModificarPersona = new IUModificarPersona(ventanaPrincipal, persona, "modificar los datos de la Persona", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/3, Asistente.ALTO));
                    iuModificarPersona.mostrarVentana();
                    if(iuModificarPersona.getEstado()){                        
                        if(controlRegistroPersonas.modificarDatosPersona(iuModificarPersona.getPersona())){
                            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "En buena hora...! se modifico los datos de la persona correctamente...!", "advertencia");
                            actualizarTablaPersonas();                                    
                        }
                    }
                    
                    iuRegistroOcupado.setOpacity(1f);
                }
            }
        });
        botonQuitarPersona.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(iuTablaPersonas.estaSeleccionado()){
                    if(iuTablaPersonas.getRowCount() > 1){
                        Persona persona = iuTablaPersonas.getPersona();
                        if(Asistente.mensajeVerificacion(ventanaPrincipal, "peligro", "...Esta seguro que quiere eliminar de la tabla la persona "+persona.getNombres().toUpperCase()+" "+persona.getApellidos().toUpperCase()+"...?", "advertencia")){
                            if(controlRegistroPersonas.eliminarPersonaRegistro(persona.getId()))
                                if(Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "...se elimino de la tabla de persona CORRECTAMENTE...!", "aviso"))
                                    actualizarTablaPersonas();
                        }                        
                    }else
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... pero la persona NO PUEDE SER ELIMINADA por que solo existe una persona en el registro...! ", "advertencia");
                }
            }
        });
        botonImprimirPersona.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(iuTablaPersonas.estaSeleccionado()){
                    Persona persona = iuTablaPersonas.getPersona();
                    
                    iuRegistroOcupado.setOpacity(0.3f);
                    
                    IUImprimirDatosPersona iuImprimirDatosPersona = new IUImprimirDatosPersona(ventanaPrincipal, persona, "Imprimir Hoja de Registro Personal", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/2 + Asistente.ANCHO/10, Asistente.ALTO));
                    iuImprimirDatosPersona.mostrarVentana();
                    
                    iuRegistroOcupado.setOpacity(1f);
                }
            }
        });
        botonSalir.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                iuRegistroOcupado.dispose();
            }
        });
    }
    private void actualizarTablaPersonas(){
        ArrayList<Persona> lista = controlRegistroPersonas.getPersonasRegistradas();
        iuTablaPersonas.limpiarTabla();
        for (int i = 0; i < lista.size(); i++) {
            Persona persona = lista.get(i);
            iuTablaPersonas.setFila(persona);
            
        }
    }    
}