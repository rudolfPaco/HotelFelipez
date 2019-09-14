/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.temporadas;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanel;
import com.hotelfelipez.www.modulo.temporadas.IUPanelTemporada;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author neo
 */
public class IUElegirTemporada extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    
    private CardLayout adminstrador;
    private IUPanel contenedorTemporadas;
    private IUPanelBD panelBotones;
    private IUEtiquetaI iconoAtras;
    private IUEtiqueta indicePanelesTemporadas;
    private IUEtiquetaI iconoAdelante;
    
    private ArrayList<Temporada> listaTemporadas;
    private int cantidadPanelesTemporadas;
    private int numeroPanelTemporada;
    private ArrayList<IUPanelTemporada> listaPanelesTemporadas;
    private boolean estado;
    private Temporada temporada;
    
    public IUElegirTemporada(IUVentanaHotel ventanaPrincipal, ArrayList<Temporada> listaTemporadas, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.listaTemporadas = listaTemporadas;
        this.listaPanelesTemporadas = new ArrayList<>();
        this.numeroPanelTemporada = 1;
        this.estado = false;
        this.temporada = null;
        
        construirPaneles(panelFondo.getLimitacion());        
        escucharEventos();
        actualizarPanelesTemporadas();
    }
    private void construirPaneles(Limitacion limite){
        adminstrador = new CardLayout();
        contenedorTemporadas = new IUPanel(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(90)));
        contenedorTemporadas.setLayout(adminstrador);
        panelFondo.add(contenedorTemporadas);        
        
        panelBotones = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(92), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(7)));
        panelFondo.add(panelBotones);
        construirPanelBotones(panelBotones.getLimitacion());
    }
    private void construirPanelBotones(Limitacion limite){
        iconoAtras = new IUEtiquetaI("src/imagenes/izquierda.png", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(8), limite.getPorcentajeAlto(90)));
        iconoAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBotones.add(iconoAtras);
        
        indicePanelesTemporadas = new IUEtiqueta("", new Limitacion(limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(60)));
        indicePanelesTemporadas.setHorizontalAlignment(SwingConstants.CENTER);
        indicePanelesTemporadas.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(40)));        
        panelBotones.add(indicePanelesTemporadas);
        
        iconoAdelante = new IUEtiquetaI("src/imagenes/derecha.png", new Limitacion(limite.getPorcentajeAncho(91), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(8), limite.getPorcentajeAlto(90)));
        iconoAdelante.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBotones.add(iconoAdelante);
    }
    private void actualizarPanelesTemporadas(){        
        int numeroElementos = listaTemporadas.size();
        int limite = 5;
        cantidadPanelesTemporadas = Asistente.getCantidadPaneles(numeroElementos, limite);
        int indice = 0;
        
        indicePanelesTemporadas.setText(numeroPanelTemporada+"/"+cantidadPanelesTemporadas);
        if(cantidadPanelesTemporadas < 2){
            iconoAtras.setVisible(false);
            iconoAdelante.setVisible(false);
        }else{
            iconoAtras.setVisible(true);
            iconoAdelante.setVisible(true);
        }
        
        listaPanelesTemporadas.clear();
        contenedorTemporadas.removeAll();
        contenedorTemporadas.updateUI();
                        
        for (int i = 0; i < cantidadPanelesTemporadas; i++) {
            IUPanel panel = new IUPanel(new Limitacion(contenedorTemporadas.getLimitacion().getAncho(), contenedorTemporadas.getLimitacion().getAlto()));
            contenedorTemporadas.add(panel);
            
            int ancho = panel.getLimitacion().getAncho() - limite;
            int alto = (panel.getLimitacion().getAlto() - 10)/limite;
            
            for(int j = 0; j < limite; j++){
                if(indice < numeroElementos){
                    Temporada elemento = listaTemporadas.get(indice);
                    IUPanelTemporada panelTemporada = new IUPanelTemporada(new Limitacion(2, j*2 + j + j*alto, ancho, alto));
                    panelTemporada.setDatosTemporada(elemento);
                    listaPanelesTemporadas.add(panelTemporada);
                    panel.add(panelTemporada);  
                    panelTemporada.addEventoRaton(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            temporada = elemento;
                            estado = true;
                            dispose();
                        }
                    });
                }
                indice++;
            }
        }
    }
    private void escucharEventos(){
        iconoAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numeroPanelTemporada > 1){
                    adminstrador.previous(contenedorTemporadas);
                    numeroPanelTemporada--;
                    indicePanelesTemporadas.setText(numeroPanelTemporada+"/"+cantidadPanelesTemporadas);
                }
            }
        });
        iconoAdelante.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(numeroPanelTemporada > 0 && numeroPanelTemporada < cantidadPanelesTemporadas){
                    adminstrador.next(contenedorTemporadas);
                    numeroPanelTemporada++;
                    indicePanelesTemporadas.setText(numeroPanelTemporada+"/"+cantidadPanelesTemporadas);
                }
            }
        });
    }
    public boolean getEstado(){
        return estado;
    }
    public Temporada getTemporada(){
        return temporada;
    }
}
