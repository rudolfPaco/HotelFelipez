/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.principal;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author hotel-felipez
 */
public class IUVentanaOpciones extends IUVentanaT{
    
    private IUPanelBD primerPanel;
    private String[] nombresBotones;
    private ArrayList<IUBoton> listaBotones;
    private IUPanel segundoPanel;
    private boolean estado;
    private IUBoton botonSalir;
    private String nombreBoton;
    
    public IUVentanaOpciones(JFrame ventanaPrincipal, String titulo, Limitacion limitacion, String[] nombresBotones) {
        super(ventanaPrincipal, titulo, limitacion, 7);
        
        this.listaBotones = new ArrayList<>();
        this.nombresBotones = nombresBotones;
        this.estado = false;
        this.nombreBoton = "";
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(2), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(85)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(89), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(9)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        int altura = 15;
        int multiplicador = 1;
        for (int i = 0; i < nombresBotones.length; i++) {
            IUBoton iuBoton = new IUBoton(nombresBotones[i], new Limitacion(limite.getPorcentajeAncho(15), limite.getPorcentajeAlto(5*multiplicador + altura*i), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(altura)));            
            iuBoton.iuTexto.setFont(new Font("Verdana", Font.BOLD, limite.getPorcentajeAlto(altura/4)));
            listaBotones.add(iuBoton);
            primerPanel.add(iuBoton);            
            multiplicador++;
        }
    }
    private void construirSegundoPanel(Limitacion limite){
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(98)));
        botonSalir.iuTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(35)));
        botonSalir.iuTexto.setForeground(new Color(120, 0, 0));
        segundoPanel.add(botonSalir);
    }
    private void escucharEventos(){
        for (int i = 0; i < listaBotones.size(); i++) {
            IUBoton iuBoton = listaBotones.get(i);
            iuBoton.addEventoRaton(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    dispose();
                    estado = true;
                    nombreBoton = iuBoton.iuTexto.getText();
                }
            });
        }
        botonSalir.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
    }
    public boolean getEstado(){
        return estado;
    }
    public String getOpcion(){
        return nombreBoton;
    }
}
