/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.modelo.Persona;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author neo
 */
public class IUPanelDatos extends IUPanelBD{
    
    private IUEtiqueta etiquetaTitulo;
    private IUEtiquetaI iuFoto;
    private IUPanelCT panelNombre;
    private IUPanelCT panelfechaNacimiento;
    private IUPanelCT panelEdad;
    private IUPanelCT panelTipoDocumento;
    private IUPanelCT panelCarnetIdentdad;
    private IUPanelCT panelFechaCaducidad;
    private IUPanelCT panelEstadoCaducidad;
    private IUPanelCT panelCiudad;
    private IUPanelCT panelPais;
    private IUPanelCT panelEstadoCivil;
    private IUPanelCT panelProfesion;
    private IUPanelCT panelDireccion;
    private IUPanelCT panelProcedencia;
    private IUPanelCT panelDestino;
    private IUPanelCT panelTelefonos;
    private IUPanelCT panelEmail;
    private IUPanelCT panelTipoPersona;
    private IUPanelTA panelObservacion;
    
    private int inicioX;
    private int inicioY;
    private int x;
    private int y;
    private int moviendoX;
    private int moviendoY;
    
    private Persona persona;
    
    public IUPanelDatos(Persona persona, Limitacion limitacion) {
        super(limitacion);
        this.persona = persona;
        this.x = limitacion.getX();
        this.y = limitacion.getY();
        
        construirComponenetes(this.getLimitacion());
    }    
    private void construirComponenetes(Limitacion limite){
        
        etiquetaTitulo = new IUEtiqueta("Registro de Datos Personal", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(6)));
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(4)));
        etiquetaTitulo.setSubrayarTexto(true);
        etiquetaTitulo.setForeground(new Color(120, 0, 0));
        add(etiquetaTitulo);
        
        iuFoto = new IUEtiquetaI(persona.getUrlFoto(), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(7), limite.getPorcentajeAncho(17), limite.getPorcentajeAlto(35)));
        iuFoto.setBorder(new LineBorder(Color.LIGHT_GRAY));
        add(iuFoto);
        
        panelNombre = new IUPanelCT("nombre completo", persona.getNombres()+" "+persona.getApellidos(), new Limitacion(limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(12), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(10)), 40, 60);
        panelNombre.iuTexto.setBorder(null);
        panelNombre.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelNombre.iuTexto.setEditable(false);
        panelNombre.iuTexto.setFocusable(false);
        add(panelNombre);
        
        panelfechaNacimiento = new IUPanelCT("fecha nacimiento", new Fecha(persona.getFechaNacimiento()).getFecha3(), new Limitacion(limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(22), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 40, 60);
        panelfechaNacimiento.iuTexto.setBorder(null);
        panelfechaNacimiento.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelfechaNacimiento.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        panelfechaNacimiento.iuTexto.setEditable(false);
        panelfechaNacimiento.iuTexto.setFocusable(false);
        add(panelfechaNacimiento);
        
        panelEdad = new IUPanelCT("edad", String.valueOf(persona.getEdad()), new Limitacion(limite.getPorcentajeAncho(38), limite.getPorcentajeAlto(22), limite.getPorcentajeAncho(7), limite.getPorcentajeAlto(10)), 40, 60);
        panelEdad.iuTexto.setBorder(null);
        panelEdad.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelEdad.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        panelEdad.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelEdad.iuTexto.setEditable(false);
        panelEdad.iuTexto.setFocusable(false);
        add(panelEdad);
        
        panelTipoDocumento = new IUPanelCT("documento", persona.getTipoDocumento(), new Limitacion(limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(22), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(10)), 40, 60);
        panelTipoDocumento.iuTexto.setBorder(null);
        panelTipoDocumento.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelTipoDocumento.iuTexto.setEditable(false);
        panelTipoDocumento.iuTexto.setFocusable(false);
        add(panelTipoDocumento);
        
        panelCarnetIdentdad = new IUPanelCT("nro documento", persona.getCarnetIdentidad()+" "+persona.getOrigen(), new Limitacion(limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(22), limite.getPorcentajeAncho(19), limite.getPorcentajeAlto(10)), 40, 60);
        panelCarnetIdentdad.iuTexto.setBorder(null);
        panelCarnetIdentdad.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelCarnetIdentdad.iuTexto.setEditable(false);
        panelCarnetIdentdad.iuTexto.setFocusable(false);
        add(panelCarnetIdentdad);
        
        panelFechaCaducidad = new IUPanelCT("fecha caducidad", new Fecha(persona.getFechaCaducidad()).getFecha3(), new Limitacion(limite.getPorcentajeAncho(18), limite.getPorcentajeAlto(32), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 40, 60);
        panelFechaCaducidad.iuTexto.setBorder(null);
        panelFechaCaducidad.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelFechaCaducidad.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelFechaCaducidad.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        panelFechaCaducidad.iuTexto.setEditable(false);
        panelFechaCaducidad.iuTexto.setFocusable(false);
        add(panelFechaCaducidad);
        
        panelEstadoCaducidad = new IUPanelCT("estado", persona.getCaducidad(), new Limitacion(limite.getPorcentajeAncho(38), limite.getPorcentajeAlto(32), limite.getPorcentajeAncho(13), limite.getPorcentajeAlto(10)), 40, 60);
        panelEstadoCaducidad.iuTexto.setBorder(null);
        panelEstadoCaducidad.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelEstadoCaducidad.iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        panelEstadoCaducidad.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelEstadoCaducidad.iuTexto.setEditable(false);
        panelEstadoCaducidad.iuTexto.setFocusable(false);
        add(panelEstadoCaducidad);
        
        panelCiudad = new IUPanelCT("ciudad", persona.getCiudad(), new Limitacion(limite.getPorcentajeAncho(51), limite.getPorcentajeAlto(32), limite.getPorcentajeAncho(24), limite.getPorcentajeAlto(10)), 40, 60);
        panelCiudad.iuTexto.setBorder(null);
        panelCiudad.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelCiudad.iuTexto.setEditable(false);
        panelCiudad.iuTexto.setFocusable(false);
        add(panelCiudad);
        
        panelPais = new IUPanelCT("pais", persona.getPais(), new Limitacion(limite.getPorcentajeAncho(75), limite.getPorcentajeAlto(32), limite.getPorcentajeAncho(24), limite.getPorcentajeAlto(10)), 40, 60);
        panelPais.iuTexto.setBorder(null);
        panelPais.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelPais.iuTexto.setEditable(false);
        panelPais.iuTexto.setFocusable(false);
        add(panelPais);
        
        panelEstadoCivil = new IUPanelCT("estado civil", persona.getEstadoCivil(), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(10)), 40, 60);
        panelEstadoCivil.iuTexto.setBorder(null);
        panelEstadoCivil.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelEstadoCivil.iuTexto.setEditable(false);
        panelEstadoCivil.iuTexto.setFocusable(false);
        add(panelEstadoCivil);
        
        panelProfesion = new IUPanelCT("profesion", persona.getProfesion(), new Limitacion(limite.getPorcentajeAncho(16), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 40, 60);
        panelProfesion.iuTexto.setBorder(null);
        panelProfesion.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelProfesion.iuTexto.setEditable(false);
        panelProfesion.iuTexto.setFocusable(false);
        add(panelProfesion);
        
        panelDireccion = new IUPanelCT("direccion", persona.getDireccion(), new Limitacion(limite.getPorcentajeAncho(36), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(63), limite.getPorcentajeAlto(10)), 40, 60);
        panelDireccion.iuTexto.setBorder(null);
        panelDireccion.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelDireccion.iuTexto.setEditable(false);
        panelDireccion.iuTexto.setFocusable(false);        
        add(panelDireccion);
        
        panelProcedencia = new IUPanelCT("procedencia", persona.getProcedencia(), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(53), limite.getPorcentajeAncho(24), limite.getPorcentajeAlto(10)), 40, 60);
        panelProcedencia.iuTexto.setBorder(null);
        panelProcedencia.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelProcedencia.iuTexto.setEditable(false);
        panelProcedencia.iuTexto.setFocusable(false);
        add(panelProcedencia);
        
        panelDestino = new IUPanelCT("destino", persona.getDestino(), new Limitacion(limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(53), limite.getPorcentajeAncho(24), limite.getPorcentajeAlto(10)), 40, 60);
        panelDestino.iuTexto.setBorder(null);
        panelDestino.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelDestino.iuTexto.setEditable(false);
        panelDestino.iuTexto.setFocusable(false);
        add(panelDestino);
        
        panelTelefonos = new IUPanelCT("telefonos", persona.getTelefono(), new Limitacion(limite.getPorcentajeAncho(49), limite.getPorcentajeAlto(53), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(10)), 40, 60);
        panelTelefonos.iuTexto.setBorder(null);
        panelTelefonos.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelTelefonos.iuTexto.setEditable(false);
        panelTelefonos.iuTexto.setFocusable(false);
        add(panelTelefonos);
        
        panelEmail = new IUPanelCT("email", persona.getEmail(), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(63), limite.getPorcentajeAncho(48), limite.getPorcentajeAlto(10)), 40, 60);
        panelEmail.iuTexto.setBorder(null);
        panelEmail.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelEmail.iuTexto.setEditable(false);
        panelEmail.iuTexto.setFocusable(false);
        add(panelEmail);
        
        panelTipoPersona = new IUPanelCT("tipo persona/antecedentes", persona.getTipoPersona(), new Limitacion(limite.getPorcentajeAncho(49), limite.getPorcentajeAlto(63), limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(10)), 40, 60);
        panelTipoPersona.iuTexto.setBorder(null);
        panelTipoPersona.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelTipoPersona.iuTexto.setEditable(false);
        panelTipoPersona.iuTexto.setFocusable(false);
        add(panelTipoPersona);
        
        panelObservacion = new IUPanelTA("observacion", persona.getObservacion(), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(74), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(22)), 18, 82);
        panelObservacion.iuAreaTexto.setBorder(null);
        panelObservacion.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3) + limite.getPorcentajeAlto(1)/3));
        panelObservacion.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelObservacion.iuAreaTexto.setEditable(false);
        panelObservacion.iuAreaTexto.setFocusable(false);
        add(panelObservacion);
    }
    public void setMovimiento(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                inicioX = e.getX();
                inicioY = e.getY();                
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moviendoX = e.getX();
                moviendoY = e.getY();

                int nuevaPosicionX = x + moviendoX - inicioX;
                int nuevaPosicionY = y + moviendoY - inicioY;

                // Movemos la etiqueta a la nueva posición
                setLocation( nuevaPosicionX,nuevaPosicionY );
                // Guarda la posición actual de la etiqueta
                x = nuevaPosicionX;
                y = nuevaPosicionY;
            }
        });        
    }
}
