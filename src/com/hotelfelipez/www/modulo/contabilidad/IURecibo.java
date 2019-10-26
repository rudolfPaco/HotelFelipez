/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.contabilidad;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.campos.IUAreaTexto;
import com.aplicacionjava.www.campos.IUCampoTexto;
import com.aplicacionjava.www.campos.IUComboBox;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelTT;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.recursos.NumeroLiteral;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IURecibo extends IUVentanaT{
    
    private RegistroHospedaje registro;
    
    private IUPanelBD primerPanel;
    private IUPanelTT iuNroRecibo;
    private IUEtiqueta iuFecha;
    private IUEtiqueta iuNombrePersona;
    private IUComboBox iuNombre;
    private IUEtiqueta iuMontoDinero;
    private IUCampoTexto iuCampoDinero;
    private IUComboBox iuUnidadDinero;
    private IUEtiqueta iuMontoLiteral;
    private IUAreaTexto iuCampoMonto;
    private IUEtiqueta iuDescripcion;
    private IUAreaTexto iuDescripcionObservacion;
    private IUPanelCT iuTotal;
    private IUEtiqueta iuIgual;
    private IUPanelCT iuAcuenta;
    private IUEtiqueta iuMas;
    private IUPanelCT iuSaldo;
    
    private IUPanelBD segundoPanel;
    private IUBoton botonSalir;
    private IUBoton botonGuardar;
    

    public IURecibo(JFrame ventanaPrincipal, String titulo, Limitacion limitacion, RegistroHospedaje registro) {
        super(ventanaPrincipal, titulo, limitacion, 5);
        
        this.registro = registro;
        construirPaneles(panelFondo.getLimitacion());
        escucharEvento();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(89)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(91), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(8)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        iuNroRecibo = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(5)), "nro Recibo", "11542", 65, 35);
        iuNroRecibo.setFuente(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3), new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));        
        primerPanel.add(iuNroRecibo);
        
        iuFecha = new IUEtiqueta("Cochabamba, "+new Fecha().getFecha2(), new Limitacion(limite.getPorcentajeAncho(31), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(68), limite.getPorcentajeAlto(5)));
        iuFecha.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        iuFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        primerPanel.add(iuFecha);
        
        iuNombrePersona = new IUEtiqueta("nombre de persona: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuNombrePersona.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2)));
        //iuNombrePersona.setBorder(new LineBorder(Color.yellow));
        iuNombrePersona.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuNombrePersona);
        
        String[] opciones = Asistente.getColumnas("nombreCompleto", "select concat(persona.nombres,' ', persona.apellidos) as nombreCompleto from persona inner join persona_registrohospedaje on persona.idpersona = persona_registrohospedaje.idpersona where persona_registrohospedaje.idregistroHospedaje = "+registro.getId());
        iuNombre = new IUComboBox(opciones, new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(73), limite.getPorcentajeAlto(5)));
        iuNombre.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        primerPanel.add(iuNombre);
        
        iuMontoDinero = new IUEtiqueta("monto de dinero: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuMontoDinero.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2)));
        //iuMontoDinero.setBorder(new LineBorder(Color.yellow));
        iuMontoDinero.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuMontoDinero);
        
        iuCampoDinero = new IUCampoTexto("9520.24", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(5)));
        iuCampoDinero.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuCampoDinero.setRestriccionNumeroDecimal();
        primerPanel.add(iuCampoDinero);
        
        String[] unidades = Asistente.getColumnas("unidad", "select unidad from moneda");
        iuUnidadDinero = new IUComboBox(unidades, new Limitacion(limite.getPorcentajeAncho(47), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(5)));
        iuUnidadDinero.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        primerPanel.add(iuUnidadDinero);
        
        iuMontoLiteral = new IUEtiqueta("monto literal: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(24), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuMontoLiteral.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2)));
        //iuMontoLiteral.setBorder(new LineBorder(Color.yellow));
        iuMontoLiteral.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuMontoLiteral);
        
        iuCampoMonto = new IUAreaTexto("", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(24), limite.getPorcentajeAncho(73), limite.getPorcentajeAlto(10)));
        iuCampoMonto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuCampoMonto.setEditable(false);
        iuCampoMonto.setFocusable(false);
        primerPanel.add(iuCampoMonto);
        
        iuDescripcion = new IUEtiqueta("descripcion/observacion: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(36), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuDescripcion.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2)));
        //iuDescripcion.setBorder(new LineBorder(Color.yellow));
        iuDescripcion.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuDescripcion);
        
        iuDescripcionObservacion = new IUAreaTexto("", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(36), limite.getPorcentajeAncho(73), limite.getPorcentajeAlto(15)));
        iuDescripcionObservacion.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));        
        primerPanel.add(iuDescripcionObservacion);
        
        iuTotal = new IUPanelCT("total", "", new Limitacion(limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(54), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(7)), 40, 60);        
        iuTotal.iuTexto.setRestriccionNumeroDecimal();
        primerPanel.add(iuTotal);
        
        iuIgual = new IUEtiqueta("=", new Limitacion(limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(57), limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(4)));
        iuIgual.setHorizontalAlignment(SwingConstants.CENTER);
        iuIgual.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuIgual.setForeground(new Color(120, 0, 0));
        primerPanel.add(iuIgual);
        
        iuAcuenta = new IUPanelCT("acuenta", "", new Limitacion(limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(54), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(7)), 40, 60);        
        iuAcuenta.iuTexto.setRestriccionNumeroDecimal();
        primerPanel.add(iuAcuenta);
        
        iuMas = new IUEtiqueta("+", new Limitacion(limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(57), limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(4)));
        iuMas.setHorizontalAlignment(SwingConstants.CENTER);
        iuMas.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuMas.setForeground(new Color(120, 0, 0));
        primerPanel.add(iuMas);
        
        iuSaldo = new IUPanelCT("saldo", "", new Limitacion(limite.getPorcentajeAncho(65), limite.getPorcentajeAlto(54), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(7)), 40, 60);        
        iuSaldo.iuTexto.setRestriccionNumeroDecimal();
        primerPanel.add(iuSaldo);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonSalir = new IUBoton("salir del panel Recibo", new Limitacion(limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(60)));
        segundoPanel.add(botonSalir);
        
        botonGuardar = new IUBoton("guardar pago de comandas", new Limitacion(limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(60)));
        segundoPanel.add(botonGuardar);
    }
    private void escucharEvento(){
        botonSalir.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        iuCampoDinero.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                NumeroLiteral numeroLiteral = new NumeroLiteral();
                if(!iuCampoDinero.getText().isEmpty()){
                    iuCampoMonto.setText(numeroLiteral.Convertir(iuCampoDinero.getText(), true));
                }
            }
        });
    }
}