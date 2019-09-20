/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.botones.IUBotonRadio;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.paneles.IUPanelTCB;
import com.aplicacionjava.www.recursos.Digitalizacion;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Documento;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUVentanaVerPersona extends IUVentanaT{

    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanelBD primerPanel;
    private ButtonGroup grupo;
    private IUBotonRadio botonCI;
    private IUBotonRadio botonPassporte;
    private IUBotonRadio botonCertificado;

    private IUPanel panelAdministrador;
    private CardLayout administrador;
    private IUPanel panelCI;
    private IUEtiquetaI ciCara;
    private IUEtiquetaI ciEspalda;    
    private IUPanel panelPassporte;
    private IUEtiquetaI passporte;    
    private IUPanelBD panelCertificado;
    private IUEtiquetaI certificado;
    
    private IUPanel segundoPanel;
    private IUPanelCT panelNombres;
    private IUPanelCT panelApellidos;
    private IUPanelCT panelFechaNacimiento;
    private IUEtiquetaI etiquetaImagen;
    private IUPanelCT panelTipoDocumento;
    private IUPanelCT panelNumeroIdentidad;
    private IUPanelCT panelOrigen;
    private IUPanelCT panelFechaCaducidad;
    private IUPanelCT panelEstadoCaducidad;
    private IUPanelCT panelCiudad;
    private IUPanelCT panelPais;
    private IUPanelCT panelEstadoCivil;
    private IUPanelCT panelProfesion;
    private IUPanelCT panelDireccion;
    private IUPanelCT panelprocedencia;
    private IUPanelCT panelDestino;
    private IUPanelCT panelTelefonos;
    private IUPanelCT panelEmail;
    private IUPanelCT panelTipoPersona;
    private IUPanelTA panelObservacion;
    
    private IUBoton botonSalirRegistro;
    
    public IUVentanaVerPersona(IUVentanaHotel ventanaPrincipal, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        this.ventanaPrincipal = ventanaPrincipal;
        construirPaneles(panelFondo.getLimitacion());
        escucharEvento();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(44), limite.getPorcentajeAlto(10)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        administrador = new CardLayout();
        panelAdministrador = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(12), limite.getPorcentajeAncho(44), limite.getPorcentajeAlto(87)));
        panelAdministrador.setLayout(administrador);
        panelFondo.add(panelAdministrador);
        construirPanelAdministrador(panelAdministrador.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(46), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(53), limite.getPorcentajeAlto(93)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
        
        botonSalirRegistro = new IUBoton("salir de la ventana ver persona", new Limitacion(limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(95), limite.getPorcentajeAncho(28), limite.getPorcentajeAlto(4)));
        panelFondo.add(botonSalirRegistro);
    }    
    private void construirPanelAdministrador(Limitacion limite){
        panelCI = new IUPanel(new Limitacion(limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(60)));
        panelAdministrador.add(panelCI);
        construirPanelCI(panelCI.getLimitacion());
        
        panelPassporte = new IUPanel(limite);
        panelAdministrador.add(panelPassporte);
        construirPanelPassaporte(panelPassporte.getLimitacion());
        
        panelCertificado = new IUPanelBD(limite);
        panelAdministrador.add(panelCertificado);
        construirPanelCertificado(panelCertificado.getLimitacion());
    }
    private void construirPanelCI(Limitacion limite){
        ciCara = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(15), limite.getAncho(), limite.getPorcentajeAlto(49)));
        ciCara.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ciCara.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelCI.add(ciCara);
     
        ciEspalda = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(66), limite.getAncho(), limite.getPorcentajeAlto(49)));
        ciEspalda.setBorder(new LineBorder(Color.LIGHT_GRAY));
        ciEspalda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCI.add(ciEspalda);
    }
    private void construirPanelPassaporte(Limitacion limite){
        passporte = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(80)));
        passporte.setBorder(new LineBorder(Color.LIGHT_GRAY));
        passporte.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelPassporte.add(passporte);
    }
    private void construirPanelCertificado(Limitacion limite){
        certificado = new IUEtiquetaI("", new Limitacion(limite.getAncho(), limite.getAlto()));
        certificado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelCertificado.add(certificado);
    }
    public void setPersona(Persona p){
        panelNombres.iuTexto.setText(p.getNombres());
        panelApellidos.iuTexto.setText(p.getApellidos());
        panelFechaNacimiento.iuTexto.setText(p.getFechaNacimiento());
        panelTipoDocumento.iuTexto.setText(p.getTipoDocumento());
        panelNumeroIdentidad.iuTexto.setText(p.getCarnetIdentidad());
        panelOrigen.iuTexto.setText(p.getOrigen());
        panelFechaCaducidad.iuTexto.setText(p.getFechaCaducidad());
        panelEstadoCaducidad.iuTexto.setText(p.getCaducidad());
        panelCiudad.iuTexto.setText(p.getCiudad());
        panelPais.iuTexto.setText(p.getPais());
        panelEstadoCivil.iuTexto.setText(p.getEstadoCivil());
        panelProfesion.iuTexto.setText(p.getProfesion());
        panelDireccion.iuTexto.setText(p.getDireccion());
        panelprocedencia.iuTexto.setText(p.getProcedencia());
        panelDestino.iuTexto.setText(p.getDestino());
        panelTelefonos.iuTexto.setText(p.getTelefono());
        panelEmail.iuTexto.setText(p.getEmail());
        panelTipoPersona.iuTexto.setText(p.getTipoPersona());
        panelObservacion.iuAreaTexto.setText(p.getObservacion());
        
        for (int i = 0; i < p.getDocumentos().size(); i++) {
            Documento doc = p.getDocumentos().get(i);
            switch(doc.getTipo()){
                case "carnetIdentidadC":
                    ciCara.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(ciCara.getWidth(), ciCara.getHeight(), Image.SCALE_DEFAULT)));
                    ciCara.setBuffered(doc.getBuffer());
                break;
                case "carnetIdentidadE":
                    ciEspalda.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(ciEspalda.getWidth(), ciEspalda.getHeight(), Image.SCALE_DEFAULT)));
                    ciEspalda.setBuffered(doc.getBuffer());
                break;
                case "passporte":
                    passporte.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(passporte.getWidth(), passporte.getHeight(), Image.SCALE_DEFAULT)));
                    passporte.setBuffered(doc.getBuffer());
                break;
                case "certificado":
                    certificado.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(certificado.getWidth(), certificado.getHeight(), Image.SCALE_DEFAULT)));
                    certificado.setBuffered(doc.getBuffer());
                break;
                case "foto":
                    etiquetaImagen.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(etiquetaImagen.getWidth(), etiquetaImagen.getHeight(), Image.SCALE_DEFAULT)));
                    etiquetaImagen.setBuffered(doc.getBuffer());
                break;
            }
        }
    }
    private void construirSegundoPanel(Limitacion limite){
        panelNombres = new IUPanelCT("nombres (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(6)), 40, 60);
        panelNombres.iuTexto.setEditable(false);
        panelNombres.iuTexto.setFocusable(false);
        segundoPanel.add(panelNombres);
        
        panelApellidos = new IUPanelCT("apellidos (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(8), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(6)), 40, 60);
        panelApellidos.iuTexto.setEditable(false);
        panelApellidos.iuTexto.setFocusable(false);
        segundoPanel.add(panelApellidos);
        
        etiquetaImagen = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(65), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(26)));
        etiquetaImagen.setBorder(new LineBorder(Color.LIGHT_GRAY));
        segundoPanel.add(etiquetaImagen);
                        
        panelFechaNacimiento = new IUPanelCT("fecha nacimiento (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(15), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(6)), 40, 60);
        panelFechaNacimiento.iuTexto.setEditable(false);
        panelFechaNacimiento.iuTexto.setFocusable(false);
        segundoPanel.add(panelFechaNacimiento);
                        
        panelTipoDocumento = new IUPanelCT("tipo documento (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(22), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(6)), 40, 60);
        panelTipoDocumento.iuTexto.setEditable(false);
        panelTipoDocumento.iuTexto.setFocusable(false);
        segundoPanel.add(panelTipoDocumento);
        
        panelNumeroIdentidad = new IUPanelCT("numero identidad (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(6)), 40, 60);
        panelNumeroIdentidad.iuTexto.setEditable(false);
        panelNumeroIdentidad.iuTexto.setFocusable(false);
        segundoPanel.add(panelNumeroIdentidad);
        
        panelOrigen = new IUPanelCT("origen (*)", "", new Limitacion(limite.getPorcentajeAncho(32), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(6)), 40, 60);
        panelOrigen.iuTexto.setEditable(false);
        panelOrigen.iuTexto.setFocusable(false);
        segundoPanel.add(panelOrigen);
        
        panelFechaCaducidad = new IUPanelCT("fecha caducidad (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(36), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(6)), 40, 60);
        panelFechaCaducidad.iuTexto.setEditable(false);
        panelFechaCaducidad.iuTexto.setFocusable(false);
        segundoPanel.add(panelFechaCaducidad);
        
        panelEstadoCaducidad = new IUPanelCT("estado", "", new Limitacion(limite.getPorcentajeAncho(43), limite.getPorcentajeAlto(36), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(6)), 40, 60);
        panelEstadoCaducidad.iuTexto.setEditable(false);
        panelEstadoCaducidad.iuTexto.setFocusable(false);
        segundoPanel.add(panelEstadoCaducidad);
        
        panelCiudad = new IUPanelCT("ciudad", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        panelCiudad.iuTexto.setEditable(false);
        panelCiudad.iuTexto.setFocusable(false);
        segundoPanel.add(panelCiudad);
        
        panelPais = new IUPanelCT("pais", "", new Limitacion(limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        panelPais.iuTexto.setEditable(false);
        panelPais.iuTexto.setFocusable(false);
        segundoPanel.add(panelPais);
        
        panelEstadoCivil = new IUPanelCT("estado civil", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        panelEstadoCivil.iuTexto.setEditable(false);
        panelEstadoCivil.iuTexto.setFocusable(false);
        segundoPanel.add(panelEstadoCivil);
        
        panelProfesion = new IUPanelCT("profesion", "", new Limitacion(limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(57), limite.getPorcentajeAlto(6)), 40, 60);
        panelProfesion.iuTexto.setEditable(false);
        panelProfesion.iuTexto.setFocusable(false);
        segundoPanel.add(panelProfesion);
        
        panelDireccion = new IUPanelCT("direccion", "", new Limitacion(limite.getPorcentajeAncho(0), limite.getPorcentajeAlto(57), limite.getPorcentajeAncho(99), limite.getPorcentajeAlto(6)), 40, 60);
        panelDireccion.iuTexto.setEditable(false);
        panelDireccion.iuTexto.setFocusable(false);
        segundoPanel.add(panelDireccion);
        
        panelprocedencia = new IUPanelCT("procedencia", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(64), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        panelprocedencia.iuTexto.setEditable(false);
        panelprocedencia.iuTexto.setFocusable(false);
        segundoPanel.add(panelprocedencia);
        
        panelDestino = new IUPanelCT("destino", "", new Limitacion(limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(64), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        panelDestino.iuTexto.setEditable(false);
        panelDestino.iuTexto.setFocusable(false);
        segundoPanel.add(panelDestino);
        
        panelTelefonos = new IUPanelCT("telefonos", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(71), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(6)), 40, 60);
        panelTelefonos.iuTexto.setEditable(false);
        panelTelefonos.iuTexto.setFocusable(false);
        segundoPanel.add(panelTelefonos);
        
        panelEmail = new IUPanelCT("correo electronico", "", new Limitacion(limite.getPorcentajeAncho(37), limite.getPorcentajeAlto(71), limite.getPorcentajeAncho(62), limite.getPorcentajeAlto(6)), 40, 60);
        panelEmail.iuTexto.setEditable(false);
        panelEmail.iuTexto.setFocusable(false);
        segundoPanel.add(panelEmail);        
        
        panelTipoPersona = new IUPanelCT("tipo de persona", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(78), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        panelTipoPersona.iuTexto.setEditable(false);
        panelTipoPersona.iuTexto.setFocusable(false);
        segundoPanel.add(panelTipoPersona);
        
        panelObservacion = new IUPanelTA("observacion", "", new Limitacion(limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(78), limite.getPorcentajeAncho(56), limite.getPorcentajeAlto(20)), 12, 88);
        panelObservacion.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 16));
        panelObservacion.iuAreaTexto.setEditable(false);
        panelObservacion.iuAreaTexto.setFocusable(false);
        segundoPanel.add(panelObservacion);        
    }
    private void construirPrimerPanel(Limitacion limite){
        grupo = new ButtonGroup();
        
        botonCI = new IUBotonRadio("carnet identidad", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(40)), true);
        botonCI.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(23)));
        grupo.add(botonCI);
        primerPanel.add(botonCI);        
        
        botonPassporte = new IUBotonRadio("passaporte", new Limitacion(limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(40)), false);
        botonPassporte.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(23)));
        grupo.add(botonPassporte);
        primerPanel.add(botonPassporte);        
        
        botonCertificado = new IUBotonRadio("certificado u otros", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(40)), false);
        botonCertificado.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(23)));
        grupo.add(botonCertificado);
        primerPanel.add(botonCertificado);        
    }
    private void escucharEvento(){
        
        botonCI.addItemListener((ItemEvent e) -> {
            if(botonCI.isSelected()){
                administrador.first(panelAdministrador);
            }
        });
        botonPassporte.addItemListener((ItemEvent e) -> {
            if(botonPassporte.isSelected()){
                administrador.first(panelAdministrador);
                administrador.next(panelAdministrador);
            }
        });
        botonCertificado.addItemListener((ItemEvent e) -> {
            if(botonCertificado.isSelected()){
                administrador.last(panelAdministrador);                    
            }
        });
        botonSalirRegistro.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
    }
}
