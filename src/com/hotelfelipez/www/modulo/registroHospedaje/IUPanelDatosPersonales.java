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
import com.hotelfelipez.www.modulo.controlador.CRegistroHospedaje;
import com.hotelfelipez.www.modulo.controlador.CRegistroPersona;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
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
    private IUEtiquetaI iuFoto;
    private IUPanelCT iuTipoPersona;
    private IUTablaPersonas iuTablaPersonas;
    private IUPanelTA iuObservacion;
    private IUPanelTA iuMensaje;
    
    private IUPanelBD segundoPanel;
    private IUBoton botonVerDatos;
    private IUBoton botonNuevoPersona;
    private IUBoton botonModificarPersona;
    private IUBoton botonQuitarPersona;
    private IUBoton botonImprimirPersona;
    private IUBoton botonImprimirRegistros;
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
        
        iuTipoPersona = new IUPanelCT("tipo persona", "huesped", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(42), limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(8)), 44, 56);
        primerPanel.add(iuTipoPersona);
        
        iuTablaPersonas = new IUTablaPersonas(new Limitacion(limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(81), limite.getPorcentajeAlto(47)));
        primerPanel.add(iuTablaPersonas.tabla.deslizador);
        
        iuObservacion = new IUPanelTA("observacion", "hola eeste es el mensaje...!", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(45)), 7, 93);
        iuObservacion.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 15));
        primerPanel.add(iuObservacion);
        
        iuMensaje = new IUPanelTA("mensaje del huesped", "hola eeste es el mensaje...!", new Limitacion(limite.getPorcentajeAncho(31), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(68), limite.getPorcentajeAlto(45)), 7, 93);
        iuMensaje.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 15));
        iuMensaje.iuAreaTexto.setForeground(new Color(230, 0, 115));
        primerPanel.add(iuMensaje);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonVerDatos = new IUBoton("ver datos personales", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(3), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonVerDatos);
        
        botonNuevoPersona = new IUBoton("registrar nueva persona", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(12), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonNuevoPersona);
        
        botonModificarPersona = new IUBoton("modificar datos persona", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(21), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonModificarPersona);
        
        botonQuitarPersona = new IUBoton("quitar persona de tabla", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(30), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonQuitarPersona);
        
        botonImprimirPersona = new IUBoton("imprimir datos persona", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(39), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonImprimirPersona);
        
        botonImprimirRegistros = new IUBoton("imprimir todos registros", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(48), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonImprimirRegistros);
                
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
                    IUVentanaVerPersona iuVerPersona = new IUVentanaVerPersona(ventanaPrincipal, "Datos de la Persona", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/3, Asistente.ALTO));
                    iuVerPersona.setPersona(persona);
                    iuVerPersona.mostrarVentana();

                    iuRegistroOcupado.setOpacity(1f);                
                }
                
            }
        });
        botonNuevoPersona.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                iuRegistroOcupado.setOpacity(0.3f);

                IUNuevaPersona iuRegistroPersona = new IUNuevaPersona(ventanaPrincipal, "registro de nueva persona al hotel", new Limitacion(Asistente.ANCHO - Asistente.ANCHO/3, Asistente.ALTO));
                iuRegistroPersona.mostrarVentana();
                if(iuRegistroPersona.getEstado()){                        
                    Persona persona = iuRegistroPersona.getPersona();
                    if(controlRegistroPersonas.guardarNuevaPersona(persona))
                        if(controlRegistroPersonas.guardarPersonaRegistroHospedaje(Asistente.getId("idpersona", "select idpersona from persona ORDER by idpersona DESC LIMIT 1")))
                            iuTablaPersonas.setFila(persona);
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
