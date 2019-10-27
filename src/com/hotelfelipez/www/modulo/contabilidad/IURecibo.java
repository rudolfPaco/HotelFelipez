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
import com.hotelfelipez.www.modulo.modelo.Comanda;
import com.hotelfelipez.www.modulo.modelo.Recibo;
import com.hotelfelipez.www.modulo.modelo.RegistroHospedaje;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IURecibo extends IUVentanaT{
    
    private RegistroHospedaje registro;
    private IUVentanaHotel ventanaPrincipal;
    
    private IUPanelBD primerPanel;
    private IUPanelTT iuNroRecibo;
    private IUEtiqueta iuFecha;
    private IUEtiqueta iuNombrePersona;
    private IUComboBox iuNombre;
    private IUEtiqueta iuMontoDinero;
    private IUCampoTexto iuCampoDinero;
    private IUComboBox iuUnidadDinero;
    private IUEtiqueta iuMontoLiteral;
    private IUAreaTexto iuCampoLiteral;
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
    
    private ArrayList<Comanda> lista;
    private boolean estado;
    
    public IURecibo(IUVentanaHotel ventanaPrincipal, ArrayList<Comanda> lista, String titulo, Limitacion limitacion, RegistroHospedaje registro) {
        super(ventanaPrincipal, titulo, limitacion, 5);
        this.ventanaPrincipal = ventanaPrincipal;
        this.lista = lista;
        this.registro = registro;
        this.estado = false;
        construirPaneles(panelFondo.getLimitacion());
        escucharEvento();
        setDatosRecibos();
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
        iuNroRecibo = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(5)), "nro Recibo", "", 65, 35);
        iuNroRecibo.setFuente(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3), new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));        
        primerPanel.add(iuNroRecibo);
        
        iuFecha = new IUEtiqueta("Cochabamba, "+new Fecha().getFecha2(), new Limitacion(limite.getPorcentajeAncho(31), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(68), limite.getPorcentajeAlto(5)));
        iuFecha.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        iuFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        primerPanel.add(iuFecha);
        
        iuNombrePersona = new IUEtiqueta("nombre: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuNombrePersona.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        //iuNombrePersona.setBorder(new LineBorder(Color.yellow));
        iuNombrePersona.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuNombrePersona);
        
        String[] opciones = Asistente.getColumnas("nombreCompleto", "select concat(persona.nombres,' ', persona.apellidos) as nombreCompleto from persona inner join persona_registrohospedaje on persona.idpersona = persona_registrohospedaje.idpersona where persona_registrohospedaje.idregistroHospedaje = "+registro.getId());
        iuNombre = new IUComboBox(opciones, new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(10), limite.getPorcentajeAncho(73), limite.getPorcentajeAlto(5)));
        iuNombre.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        primerPanel.add(iuNombre);
        
        iuMontoDinero = new IUEtiqueta("monto de dinero: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuMontoDinero.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        //iuMontoDinero.setBorder(new LineBorder(Color.yellow));
        iuMontoDinero.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuMontoDinero);
        
        String[] unidades = Asistente.getColumnas("unidad", "select unidad from moneda");
        iuUnidadDinero = new IUComboBox(unidades, new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(5)));
        iuUnidadDinero.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        primerPanel.add(iuUnidadDinero);
        
        iuCampoDinero = new IUCampoTexto("", new Limitacion(limite.getPorcentajeAncho(47), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(5)));
        iuCampoDinero.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuCampoDinero.setRestriccionNumeroDecimal();
        primerPanel.add(iuCampoDinero);
        
        iuMontoLiteral = new IUEtiqueta("monto literal: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(24), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuMontoLiteral.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        //iuMontoLiteral.setBorder(new LineBorder(Color.yellow));
        iuMontoLiteral.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuMontoLiteral);
        
        iuCampoLiteral = new IUAreaTexto("", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(24), limite.getPorcentajeAncho(73), limite.getPorcentajeAlto(10)));
        iuCampoLiteral.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuCampoLiteral.setEditable(false);
        iuCampoLiteral.setFocusable(false);
        primerPanel.add(iuCampoLiteral);
        
        iuDescripcion = new IUEtiqueta("observacion: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(36), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuDescripcion.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        //iuDescripcion.setBorder(new LineBorder(Color.yellow));
        iuDescripcion.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuDescripcion);
        
        iuDescripcionObservacion = new IUAreaTexto("", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(36), limite.getPorcentajeAncho(73), limite.getPorcentajeAlto(15)));
        iuDescripcionObservacion.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        primerPanel.add(iuDescripcionObservacion);
        
        iuTotal = new IUPanelCT("total", "", new Limitacion(limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(54), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 35, 65);        
        iuTotal.iuTexto.setRestriccionNumeroDecimal();
        iuTotal.iuTexto.setEditable(false);
        iuTotal.iuTexto.setFocusable(false);
        iuTotal.iuTexto.setHorizontalAlignment(SwingConstants.RIGHT);
        primerPanel.add(iuTotal);
        
        iuIgual = new IUEtiqueta("=", new Limitacion(limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(59), limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(6)));
        iuIgual.setHorizontalAlignment(SwingConstants.CENTER);
        iuIgual.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuIgual.setForeground(new Color(120, 0, 0));        
        primerPanel.add(iuIgual);
        
        iuAcuenta = new IUPanelCT("acuenta", "", new Limitacion(limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(54), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 35, 65);        
        iuAcuenta.iuTexto.setRestriccionNumeroDecimal();
        iuAcuenta.iuTexto.setHorizontalAlignment(SwingConstants.RIGHT);        
        iuAcuenta.iuTexto.setEditable(false);
        iuAcuenta.iuTexto.setFocusable(false);
        primerPanel.add(iuAcuenta);
        
        iuMas = new IUEtiqueta("+", new Limitacion(limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(59), limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(6)));
        iuMas.setHorizontalAlignment(SwingConstants.CENTER);
        iuMas.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuMas.setForeground(new Color(120, 0, 0));
        primerPanel.add(iuMas);
        
        iuSaldo = new IUPanelCT("saldo", "", new Limitacion(limite.getPorcentajeAncho(65), limite.getPorcentajeAlto(54), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 35, 65);        
        iuSaldo.iuTexto.setRestriccionNumeroDecimal();
        iuSaldo.iuTexto.setHorizontalAlignment(SwingConstants.RIGHT);
        iuSaldo.iuTexto.setEditable(false);
        iuSaldo.iuTexto.setFocusable(false);
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
                
                if(!iuCampoDinero.getText().isEmpty() && !iuUnidadDinero.getTexto().isEmpty()){                    
                    double total = Double.valueOf(iuTotal.iuTexto.getText());
                    double acuenta = Double.valueOf(iuCampoDinero.getText());
                    if(acuenta <= total){
                        NumeroLiteral numeroLiteral = new NumeroLiteral();
                        iuCampoLiteral.setText(numeroLiteral.Convertir(iuCampoDinero.getText(), false, iuUnidadDinero.getTexto()));
                        double saldo = total - acuenta;                    
                        iuAcuenta.iuTexto.setText(String.valueOf(acuenta));
                        iuSaldo.iuTexto.setText(String.valueOf(saldo));
                        iuDescripcionObservacion.transferFocus();
                    }else
                        iuCampoDinero.requestFocus();
                }
            }
        });
        botonGuardar.addEventoRaton(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(esValidado()){
                    estado = true;
                    dispose();
                }
            }
        });
    }
    public void setDatosRecibos(){
        double total = 0;
        for (int i = 0; i < lista.size(); i++) {
            Comanda comanda = lista.get(i);
            total = total + comanda.getTotal();
        }
        iuTotal.iuTexto.setText(String.valueOf(total));
        iuNroRecibo.iuTexto.setText(String.valueOf(Asistente.getPostId("idrecibo", "select idrecibo from recibo order by idrecibo desc limit 1")));
    }
    private boolean esValidado(){
        boolean verificador = false;
        if(!iuNombre.getTexto().isEmpty())
            if(!iuUnidadDinero.getTexto().isEmpty())
                if(!iuCampoDinero.getText().isEmpty())
                    verificador = true;
                else
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... NO PUEDE ESTAR VACIO el campo de monto de dinero", "verificador");
            else
                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... NO PUEDE ESTAR VACIO el campo de unidad dinero", "verificador");
        else
            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento... NO PUEDE ESTAR VACIO el campo de nombres", "verificador");
        return verificador;
    }
    public boolean getEstado(){
        return estado;
    }
    public Recibo getRecibo(){
        Recibo recibo = new Recibo(0);
        recibo.setNroRecibo(iuNroRecibo.iuTexto.getText());
        recibo.setFecha(new Fecha().getFecha());
        recibo.setNombrePersona(iuNombre.getTexto());
        recibo.setMontoRecibido(Double.parseDouble(iuCampoDinero.getText()));
        recibo.setMontoLiteral(iuCampoLiteral.getText());
        recibo.setDescripcion(iuDescripcionObservacion.getText());
        recibo.setTotal(Double.valueOf(iuTotal.iuTexto.getText()));
        recibo.setAcuenta(Double.valueOf(iuAcuenta.iuTexto.getText()));
        recibo.setSaldo(Double.valueOf(iuSaldo.iuTexto.getText()));
        return recibo;
    }
}