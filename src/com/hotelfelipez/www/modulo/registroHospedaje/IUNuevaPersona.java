/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.botones.IUBotonRadio;
import com.aplicacionjava.www.calendario.IUCalendario;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.paneles.IUPanelTCB;
import com.aplicacionjava.www.recursos.Archivo;
import com.aplicacionjava.www.recursos.Digitalizacion;
import com.hotelfelipez.www.modulo.modelo.Documento;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaL;
import com.aplicacionjava.www.ventanas.IUVentanaRI;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.modelo.Asistente;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author rudolf
 */
public class IUNuevaPersona extends IUVentanaT{
    
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
    private IUBoton botonFechaNacimiento;
    private IUEtiquetaI etiquetaImagen;
    private IUBoton botonEliminar;
    private IUBoton botonFoto;
    private IUPanelTCB panelTipoDocumento;
    private IUPanelCT panelNumeroIdentidad;
    private IUPanelTCB panelOrigen;
    private IUPanelCT panelFechaCaducidad;
    private IUBoton botonFechaCaducidad;
    private IUPanelCT panelNumeroDias;
    private IUPanelCT panelEstadoCaducidad;
    private IUPanelTCB panelCiudad;
    private IUPanelTCB panelPais;
    private IUPanelTCB panelEstadoCivil;
    private IUPanelTCB panelProfesion;
    private IUPanelCT panelDireccion;
    private IUPanelTCB panelprocedencia;
    private IUPanelTCB panelDestino;
    private IUPanelCT panelTelefonos;
    private IUPanelCT panelEmail;
    private IUPanelTCB panelTipoPersona;
    private IUPanelTA panelObservacion;
    
    private IUBoton botonGuardarRegistro;
    
    private Digitalizacion digitalizacion; 
    private boolean estado;
    
    public IUNuevaPersona(IUVentanaHotel ventanaPrincipal, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        this.ventanaPrincipal = ventanaPrincipal;
        this.estado = false;
        this.digitalizacion = null;
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
        
        botonGuardarRegistro = new IUBoton("guardar nuevo registro de persona", new Limitacion(limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(95), limite.getPorcentajeAncho(28), limite.getPorcentajeAlto(4)));
        panelFondo.add(botonGuardarRegistro);
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
    private void construirSegundoPanel(Limitacion limite){
        panelNombres = new IUPanelCT("nombres (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelNombres);
        
        panelApellidos = new IUPanelCT("apellidos (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(8), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelApellidos);
        
        etiquetaImagen = new IUEtiquetaI("", new Limitacion(limite.getPorcentajeAncho(65), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(26)));
        etiquetaImagen.setBorder(new LineBorder(Color.LIGHT_GRAY));
        //segundoPanel.add(etiquetaImagen);
        
        botonEliminar = new IUBoton("eliminar", new Limitacion(limite.getPorcentajeAncho(66), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(12), limite.getPorcentajeAlto(3)));
        //segundoPanel.add(botonEliminar);
        
        botonFoto = new IUBoton("foto", new Limitacion(limite.getPorcentajeAncho(82), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(12), limite.getPorcentajeAlto(3)));
        //segundoPanel.add(botonFoto);
        
        panelFechaNacimiento = new IUPanelCT("fecha nacimiento (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(15), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(6)), 40, 60);
        panelFechaNacimiento.iuTexto.setEditable(false);
        panelFechaNacimiento.iuTexto.setFocusable(false);
        segundoPanel.add(panelFechaNacimiento);
        
        botonFechaNacimiento = new IUBoton("fecha", new Limitacion(limite.getPorcentajeAncho(31), limite.getPorcentajeAlto(15), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonFechaNacimiento);
        
        panelTipoDocumento = new IUPanelTCB("tipo documento (*)", Asistente.getColumnas("tipoDocumento", "select distinct tipoDocumento from persona"), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(22), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelTipoDocumento);
        
        panelNumeroIdentidad = new IUPanelCT("numero identidad (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelNumeroIdentidad);
        
        panelOrigen = new IUPanelTCB("origen (*)", Asistente.getColumnas("origen", "select distinct origen from persona"), new Limitacion(limite.getPorcentajeAncho(32), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelOrigen);
        
        panelFechaCaducidad = new IUPanelCT("fecha caducidad (*)", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(36), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(6)), 40, 60);
        panelFechaCaducidad.iuTexto.setEditable(false);
        panelFechaCaducidad.iuTexto.setFocusable(false);
        segundoPanel.add(panelFechaCaducidad);
        
        botonFechaCaducidad = new IUBoton("fecha", new Limitacion(limite.getPorcentajeAncho(31), limite.getPorcentajeAlto(36), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(6)));
        segundoPanel.add(botonFechaCaducidad);
        
        panelEstadoCaducidad = new IUPanelCT("estado", "", new Limitacion(limite.getPorcentajeAncho(43), limite.getPorcentajeAlto(36), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(6)), 40, 60);
        panelEstadoCaducidad.iuTexto.setEditable(false);
        panelEstadoCaducidad.iuTexto.setFocusable(false);
        segundoPanel.add(panelEstadoCaducidad);
        
        panelCiudad = new IUPanelTCB("ciudad", Asistente.getColumnas("ciudad", "select distinct ciudad from persona"), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelCiudad);
        
        panelPais = new IUPanelTCB("pais", Asistente.getColumnas("pais", "select distinct pais from persona"), new Limitacion(limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(43), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelPais);
        
        panelEstadoCivil = new IUPanelTCB("estado civil", Asistente.getColumnas("estadoCivil", "select distinct estadoCivil from persona"), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelEstadoCivil);
        
        panelProfesion = new IUPanelTCB("profesion", Asistente.getColumnas("profesion", "select distinct profesion from persona"), new Limitacion(limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(57), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelProfesion);
        
        panelDireccion = new IUPanelCT("direccion", "", new Limitacion(limite.getPorcentajeAncho(0), limite.getPorcentajeAlto(57), limite.getPorcentajeAncho(99), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelDireccion);
        
        panelprocedencia = new IUPanelTCB("procedencia", Asistente.getColumnas("procedencia", "select distinct procedencia from persona"), new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(64), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelprocedencia);
        
        panelDestino = new IUPanelTCB("destino", Asistente.getColumnas("destino", "select distinct destino from persona"), new Limitacion(limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(64), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelDestino);
        
        panelTelefonos = new IUPanelCT("telefonos", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(71), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelTelefonos);
        
        panelEmail = new IUPanelCT("correo electronico", "", new Limitacion(limite.getPorcentajeAncho(37), limite.getPorcentajeAlto(71), limite.getPorcentajeAncho(62), limite.getPorcentajeAlto(6)), 40, 60);
        segundoPanel.add(panelEmail);        
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
        botonEliminar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                etiquetaImagen.setBuffered(null);
                etiquetaImagen.setIcon(null);
                etiquetaImagen.updateUI();
            }
        });
        ciCara.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.3f);
                String[] nombresBotones = {"scanear imagen", "examinar imagen", "eliminar imagen"};
                IUVentanaL listaOpciones = new IUVentanaL(ventanaPrincipal, "elija una opcion", new Limitacion(Asistente.ANCHO/4, Asistente.ALTO - Asistente.ALTO/3), nombresBotones);
                listaOpciones.mostrarVentana();
                if(listaOpciones.getEstado()){
                    String opcion = listaOpciones.getNombreBoton();
                    switch(opcion){
                        case "scanear imagen":
                            escanearImagen(ciCara);
                        break;
                        case "examinar imagen":
                            examinarImagen(ciCara);
                        break;
                        case "eliminar imagen":
                            ciCara.setIcon(null);
                            ciCara.setBuffered(null);
                        break;
                    }
                }
                setOpacity(1f);
            }
        });
        ciEspalda.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.3f);
                String[] nombresBotones = {"scanear imagen", "examinar imagen", "eliminar imagen"};
                IUVentanaL listaOpciones = new IUVentanaL(ventanaPrincipal, "elija una opcion", new Limitacion(Asistente.ANCHO/4, Asistente.ALTO - Asistente.ALTO/3), nombresBotones);
                listaOpciones.mostrarVentana();
                if(listaOpciones.getEstado()){
                    String opcion = listaOpciones.getNombreBoton();
                    switch(opcion){
                        case "scanear imagen":                             
                            escanearImagen(ciEspalda);
                        break;
                        case "examinar imagen":
                            examinarImagen(ciEspalda);
                        break;
                        case "eliminar imagen":
                            ciEspalda.setIcon(null);
                            ciEspalda.setBuffered(null);
                        break;
                    }
                }
                setOpacity(1f);
            }
        });
        passporte.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.3f);
                String[] nombresBotones = {"scanear imagen", "examinar imagen", "eliminar imagen"};
                IUVentanaL listaOpciones = new IUVentanaL(ventanaPrincipal, "elija una opcion", new Limitacion(Asistente.ANCHO/4, Asistente.ALTO - Asistente.ALTO/3), nombresBotones);
                listaOpciones.mostrarVentana();
                if(listaOpciones.getEstado()){
                    String opcion = listaOpciones.getNombreBoton();
                    switch(opcion){
                        case "scanear imagen":
                            escanearImagen(passporte);
                        break;
                        case "examinar imagen":
                            examinarImagen(passporte);
                        break;
                        case "eliminar imagen":
                            passporte.setIcon(null);
                            passporte.setBuffered(null);
                        break;
                    }
                }
                setOpacity(1f);
            }
        });
        certificado.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.3f);
                String[] nombresBotones = {"scanear imagen", "examinar imagen", "eliminar imagen"};
                IUVentanaL listaOpciones = new IUVentanaL(ventanaPrincipal, "elija una opcion", new Limitacion(Asistente.ANCHO/4, Asistente.ALTO - Asistente.ALTO/3), nombresBotones);
                listaOpciones.mostrarVentana();
                if(listaOpciones.getEstado()){
                    String opcion = listaOpciones.getNombreBoton();
                    switch(opcion){
                        case "scanear imagen":
                            escanearImagen(certificado);
                        break;
                        case "examinar imagen":
                            examinarImagen(certificado);
                        break;
                        case "eliminar imagen":
                            certificado.setIcon(null);
                            certificado.setBuffered(null);
                        break;
                    }
                }
                setOpacity(1f);
            }
        });
        /*botonFoto.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.3f);
                String[] nombresBotones = {"scanear imagen", "examinar imagen", "buscar en imagenes propias"};
                IUVentanaL listaOpciones = new IUVentanaL(ventanaPrincipal, "elija una opcion", new Limitacion(Asistente.ANCHO/4, Asistente.ALTO - Asistente.ALTO/3), nombresBotones);
                listaOpciones.mostrarVentana();
                if(listaOpciones.getEstado()){
                    String opcion = listaOpciones.getNombreBoton();
                    switch(opcion){
                        case "scanear imagen":
                            escanearImagen(etiquetaImagen);
                        break;
                        case "examinar imagen":
                            examinarImagen(etiquetaImagen);
                        break;
                        case "buscar en imagenes propias":
                            
                        break;
                    }
                }
                setOpacity(1f);
            }
        });*/
        botonFechaNacimiento.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                IUCalendario calendario = new IUCalendario(ventanaPrincipal, "Fecha de Nacimiento", new Fecha("1980-01-01"), new Limitacion(Asistente.ANCHO/3, Asistente.ALTO/2));
                calendario.mostrarVentana();
                if(calendario.getEstado()){
                    Fecha fecha = calendario.getFecha();
                    panelFechaNacimiento.iuTexto.setText(fecha.getFecha());
                }
            }
        });
        botonFechaCaducidad.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                IUCalendario calendario = new IUCalendario(ventanaPrincipal, "Fecha de Caducidad", new Fecha(), new Limitacion(Asistente.ANCHO/3, Asistente.ALTO/2));
                calendario.mostrarVentana();
                if(calendario.getEstado()){
                    Fecha fecha = calendario.getFecha();
                    panelFechaCaducidad.iuTexto.setText(fecha.getFecha());
                    if(fecha.esMayor(new Fecha()))
                        panelEstadoCaducidad.iuTexto.setText("INVALIDO");
                    else
                        panelEstadoCaducidad.iuTexto.setText("VALIDO");
                }
            }
        });
        botonGuardarRegistro.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(estaValidado()){
                    estado = true;
                    dispose();
                }else
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... debe insertar imagenes del documento... o al menos debe ingresar su nombre y apellido.!", "advertencia");
            }
        });
    }
    private boolean estaValidado(){
        boolean verificador = false;
        if(ciCara.getBuffered() != null && ciEspalda.getBuffered() != null)
            verificador = true;
        else
            if(passporte.getBuffered() != null)
                verificador = true;
            else
                if(certificado.getBuffered() != null)
                    verificador = true;
                else
                    if(!panelNombres.iuTexto.getText().isEmpty() && !panelApellidos.iuTexto.getText().isEmpty())
                        verificador = true;
        return verificador;
    }
    private void escanearImagen(IUEtiquetaI iuImagen){
        digitalizacion = new Digitalizacion(iuImagen);        
    }
    private void examinarImagen(IUEtiquetaI iuImagen){
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen","jpg","png");
        File archivo = new File("src/imagenes/");
        JFileChooser fileChooser = new JFileChooser(archivo);
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        
        if ( result == JFileChooser.APPROVE_OPTION ){
            try {
                BufferedImage buffered = ImageIO.read(fileChooser.getSelectedFile());
                
                String[] nombres = {"desea recortar la imagen", "desea aceptar la imagen"};
                IUVentanaL opciones = new IUVentanaL(ventanaPrincipal, "elija una opcion", new Limitacion(Asistente.ANCHO/3, Asistente.ALTO/2), nombres);
                opciones.mostrarVentana();
                if(opciones.getEstado()){
                    switch(opciones.getNombreBoton()){
                        case "desea recortar la imagen":
                            IUVentanaRI ventanaEspalda = new IUVentanaRI(ventanaPrincipal, "Recortar Imagen", iuImagen, buffered, new Limitacion(Asistente.ANCHO - Asistente.ANCHO/3 - Asistente.ANCHO/10, Asistente.ALTO), 4);
                            ventanaEspalda.mostrarVentana();
                        break;
                        case "desea aceptar la imagen":
                            if(buffered != null){
                                iuImagen.setIcon(new ImageIcon(new ImageIcon(buffered.getScaledInstance(iuImagen.getWidth(), iuImagen.getHeight(), Image.SCALE_AREA_AVERAGING)).getImage()));
                                iuImagen.setBuffered(buffered);
                            }                            
                        break;
                    }
                }
            }
            catch (IOException ex) {                
                System.err.println( ex.getMessage() );
            } 
         }
    }
    public Persona getPersona(){
        Persona persona = new Persona(0);
        
        if(ciCara.getBuffered() != null && ciEspalda.getBuffered() != null){
            Documento docCara = new Documento(0);
            docCara.setBuffer(ciCara.getBuffered());
            docCara.setTipo("carnetIdentidadC");
            
            Documento docEspalda = new Documento(0);
            docEspalda.setBuffer(ciEspalda.getBuffered());
            docEspalda.setTipo("carnetIdentidadE");
            
            persona.setDocumento(docCara);
            persona.setDocumento(docEspalda);            
        }            
        if(passporte.getBuffered() != null){
            Documento docPassporte = new Documento(0);
            docPassporte.setBuffer(passporte.getBuffered());
            docPassporte.setTipo("passporte");

            persona.setDocumento(docPassporte);
        }
        if(certificado.getBuffered() != null){
            Documento docCertificado = new Documento(0);
            docCertificado.setBuffer(certificado.getBuffered());
            docCertificado.setTipo("certificado");

            persona.setDocumento(docCertificado);
        }        
        persona.setNombres(panelNombres.iuTexto.getText());
        persona.setApellidos(panelApellidos.iuTexto.getText());
        persona.setFechaNacimiento(panelFechaNacimiento.iuTexto.getText());
        persona.setTipoDocumento(panelTipoDocumento.iuTexto.getTexto());
        persona.setCarnetIdentidad(panelNumeroIdentidad.iuTexto.getText());
        persona.setOrigen(panelOrigen.iuTexto.getTexto());
        persona.setFechaCaducidad(panelFechaCaducidad.iuTexto.getText());        
        persona.setCiudad(panelCiudad.iuTexto.getTexto());
        persona.setPais(panelPais.iuTexto.getTexto());
        persona.setEstadoCivil(panelEstadoCivil.iuTexto.getTexto());
        persona.setProfesion(panelProfesion.iuTexto.getTexto());
        persona.setDireccion(panelDireccion.iuTexto.getText());
        persona.setProcedencia(panelprocedencia.iuTexto.getTexto());
        persona.setDestino(panelDestino.iuTexto.getTexto());
        persona.setTelefono(panelTelefonos.iuTexto.getText());
        persona.setEmail(panelEmail.iuTexto.getText());
        
        persona.setTipoPersona("");
        persona.setObservacion("");
        return persona;
    }
    public boolean getEstado(){
        return estado;
    }
}
