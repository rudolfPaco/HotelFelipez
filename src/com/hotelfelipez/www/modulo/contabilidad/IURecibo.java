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
import javax.swing.border.LineBorder;

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
    private IUEtiqueta iuTipoMoneda;
    private IUComboBox iuUnidadDinero;    
    private IUEtiqueta iuMontoRecibido;    
    private IUCampoTexto iuCampoMontoRecibido;    
    private IUEtiqueta iuTotalPagar;
    private IUEtiqueta iuCampoTotalPagar;
    private IUEtiqueta iuCambioDevolver;
    private IUEtiqueta iuCampoCambioDevolver;
    private IUEtiqueta iuMontoLiteral;
    private IUAreaTexto iuCampoLiteral;
    private IUEtiqueta iuDescripcion;
    private IUAreaTexto iuDescripcionObservacion;
    private IUPanelCT iuTotal;
    private IUEtiqueta iuIgual;
    private IUPanelCT iuCobramos;
    private IUEtiqueta iuMas;
    private IUPanelCT iuCambio;
    
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
        
        iuTipoMoneda = new IUEtiqueta("tipo moneda: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuTipoMoneda.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        //iuMontoDinero.setBorder(new LineBorder(Color.yellow));
        iuTipoMoneda.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuTipoMoneda);
        
        String[] unidades = Asistente.getColumnas("unidad", "select unidad from moneda");
        iuUnidadDinero = new IUComboBox(unidades, new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(5)));
        iuUnidadDinero.setSelectedIndex(1);
        iuUnidadDinero.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuUnidadDinero.setFocusable(false);
        primerPanel.add(iuUnidadDinero);
        
        iuMontoRecibido = new IUEtiqueta("monto recibido: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(24), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuMontoRecibido.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        //iuMontoDinero.setBorder(new LineBorder(Color.yellow));
        iuMontoRecibido.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuMontoRecibido);
        
        iuCampoMontoRecibido = new IUCampoTexto("", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(24), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(5)));
        iuCampoMontoRecibido.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuCampoMontoRecibido.setRestriccionNumeroDecimal();
        primerPanel.add(iuCampoMontoRecibido);
        
        iuTotalPagar = new IUEtiqueta("total a cobrar: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(31), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuTotalPagar.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));        
        iuTotalPagar.setForeground(new Color(120, 120, 120));        
        primerPanel.add(iuTotalPagar);
        
        iuCampoTotalPagar = new IUEtiqueta("", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(31), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuCampoTotalPagar.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/2));
        iuCampoTotalPagar.setBorder(new LineBorder(Color.yellow));
        iuCampoTotalPagar.setForeground(new Color(120, 0, 0));
        primerPanel.add(iuCampoTotalPagar);
        
        iuCambioDevolver = new IUEtiqueta("cambio:", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(38), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuCambioDevolver.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        //iuMontoDinero.setBorder(new LineBorder(Color.yellow));
        iuCambioDevolver.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuCambioDevolver);
        
        iuCampoCambioDevolver = new IUEtiqueta("0.0", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(38), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuCampoCambioDevolver.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/2));
        iuCampoCambioDevolver.setBorder(new LineBorder(Color.yellow));
        //iuCampoCambioDevolver.setForeground(new Color(120, 0, 0));
        primerPanel.add(iuCampoCambioDevolver);
        
        iuMontoLiteral = new IUEtiqueta("monto literal: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuMontoLiteral.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        //iuMontoLiteral.setBorder(new LineBorder(Color.yellow));
        iuMontoLiteral.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuMontoLiteral);
        
        iuCampoLiteral = new IUAreaTexto("", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(52), limite.getPorcentajeAncho(73), limite.getPorcentajeAlto(10)));
        iuCampoLiteral.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuCampoLiteral.setEditable(false);
        iuCampoLiteral.setFocusable(false);
        primerPanel.add(iuCampoLiteral);
        
        iuDescripcion = new IUEtiqueta("observacion: ", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(64), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(5)));
        iuDescripcion.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(2) + limite.getPorcentajeAlto(2)/3));
        //iuDescripcion.setBorder(new LineBorder(Color.yellow));
        iuDescripcion.setForeground(new Color(120, 120, 120));
        primerPanel.add(iuDescripcion);
        
        iuDescripcionObservacion = new IUAreaTexto("", new Limitacion(limite.getPorcentajeAncho(26), limite.getPorcentajeAlto(64), limite.getPorcentajeAncho(73), limite.getPorcentajeAlto(15)));
        iuDescripcionObservacion.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        primerPanel.add(iuDescripcionObservacion);
        
        iuTotal = new IUPanelCT("total", "", new Limitacion(limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(82), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 35, 65);        
        iuTotal.iuTexto.setRestriccionNumeroDecimal();
        iuTotal.iuTexto.setEditable(false);
        iuTotal.iuTexto.setFocusable(false);
        iuTotal.iuTexto.setHorizontalAlignment(SwingConstants.RIGHT);
        primerPanel.add(iuTotal);
        
        iuIgual = new IUEtiqueta("=", new Limitacion(limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(85), limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(6)));
        iuIgual.setHorizontalAlignment(SwingConstants.CENTER);
        iuIgual.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuIgual.setForeground(new Color(120, 0, 0));        
        primerPanel.add(iuIgual);
        
        iuCobramos = new IUPanelCT("nos paga..?", "", new Limitacion(limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(82), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 35, 65);        
        iuCobramos.iuTexto.setRestriccionNumeroDecimal();
        iuCobramos.iuTexto.setHorizontalAlignment(SwingConstants.RIGHT);        
        iuCobramos.iuTexto.setEditable(false);
        iuCobramos.iuTexto.setFocusable(false);
        primerPanel.add(iuCobramos);
        
        iuMas = new IUEtiqueta("-", new Limitacion(limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(85), limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(6)));
        iuMas.setHorizontalAlignment(SwingConstants.CENTER);
        iuMas.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(3)));
        iuMas.setForeground(new Color(120, 0, 0));
        primerPanel.add(iuMas);
        
        iuCambio = new IUPanelCT("cambio", "", new Limitacion(limite.getPorcentajeAncho(65), limite.getPorcentajeAlto(82), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(10)), 35, 65);        
        iuCambio.iuTexto.setRestriccionNumeroDecimal();
        iuCambio.iuTexto.setHorizontalAlignment(SwingConstants.RIGHT);
        iuCambio.iuTexto.setEditable(false);
        iuCambio.iuTexto.setFocusable(false);
        primerPanel.add(iuCambio);
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
        iuCampoMontoRecibido.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                
                if(!iuCampoMontoRecibido.getText().isEmpty() && !iuUnidadDinero.getTexto().isEmpty()){                    
                    double total = Double.valueOf(iuTotal.iuTexto.getText());
                    double monto = Double.valueOf(iuCampoMontoRecibido.getText());
                    if(monto >= total){
                        NumeroLiteral numeroLiteral = new NumeroLiteral();
                        iuCampoLiteral.setText(numeroLiteral.Convertir(iuTotal.iuTexto.getText(), false, iuUnidadDinero.getTexto()));
                        double cambio = monto - total;
                        iuCampoCambioDevolver.setText(String.valueOf(cambio));
                        iuCobramos.iuTexto.setText(String.valueOf(monto));
                        iuCambio.iuTexto.setText(String.valueOf(cambio));
                        iuDescripcionObservacion.transferFocus();
                    }else
                        iuCampoMontoRecibido.requestFocus();
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
        iuCampoTotalPagar.setText(iuTotal.iuTexto.getText());
        iuNroRecibo.iuTexto.setText(String.valueOf(Asistente.getPostId("idrecibo", "select idrecibo from recibo order by idrecibo desc limit 1")));
    }
    private boolean esValidado(){
        boolean verificador = false;
        if(!iuNombre.getTexto().isEmpty())
            if(!iuUnidadDinero.getTexto().isEmpty())
                if(!iuCampoMontoRecibido.getText().isEmpty())
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
        recibo.setMontoRecibido(Double.parseDouble(iuCampoMontoRecibido.getText()));
        recibo.setMontoLiteral(iuCampoLiteral.getText());
        recibo.setDescripcion(iuDescripcionObservacion.getText());
        recibo.setTotal(Double.valueOf(iuTotal.iuTexto.getText()));
        recibo.setPago(Double.valueOf(iuCobramos.iuTexto.getText()));
        recibo.setCambio(Double.valueOf(iuCambio.iuTexto.getText()));
        recibo.setEstado("PAGADO");
        return recibo;
    }
}