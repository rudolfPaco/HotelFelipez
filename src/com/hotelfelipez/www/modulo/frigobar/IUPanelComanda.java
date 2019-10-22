/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.frigobar;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelTT;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Hora;
import com.aplicacionjava.www.recursos.Limitacion;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Comanda;
import com.hotelfelipez.www.modulo.modelo.Detalle;
import com.hotelfelipez.www.modulo.modelo.Producto;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUPanelComanda extends IUPanelBD{

    private IUPanel primerPanel;
    private IUEtiqueta iuNombre;
    private IUPanelTT iuNumero;
    private IUPanelTT iuFecha;
    private IUPanelTT iuHora;    
    private IUPanelBD segundoPanel;
    public IUTablaDetalleComanda iuTablaComanda;
    private IUEtiqueta iuEstado;
    private IUPanel tercerPanel;
    private IUPanelTT iuTotal;
    private int idRegistroHospedaje; 
    
    public IUPanelComanda(Limitacion limitacion, int idRegistroHospedaje) {
        super(limitacion);
        this.idRegistroHospedaje = idRegistroHospedaje;
        setArco(20);
        construirPaneles(getLimitacion());
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(10)));
        add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(0, limite.getPorcentajeAlto(10), limite.getAncho(), limite.getPorcentajeAlto(80)));
        segundoPanel.setArco(5);
        add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
        
        tercerPanel = new IUPanel(new Limitacion(0, limite.getPorcentajeAlto(90), limite.getAncho(), limite.getPorcentajeAlto(10)));
        add(tercerPanel);
        construirTercerPanel(tercerPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        iuNombre = new IUEtiqueta("", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(40)));        
        iuNombre.setHorizontalAlignment(SwingConstants.CENTER);
        iuNombre.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(30)));
        iuNombre.setSubrayarTexto(true);
        primerPanel.add(iuNombre);
        
        iuNumero = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(70), 0, limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(50)), "nro: ", "", 15, 85);
        iuNumero.iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuNumero.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(35)));
        primerPanel.add(iuNumero);        
        
        iuFecha = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(50)), "fecha: ", "", 30, 70);
        primerPanel.add(iuFecha);
        
        iuHora = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(50), limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(50)), "hrs: ", "", 25, 75);
        primerPanel.add(iuHora);
    }
    private void construirSegundoPanel(Limitacion limite){
        iuEstado = new IUEtiqueta("", new Limitacion(limite.getAncho(), limite.getAlto()));
        iuEstado.setHorizontalAlignment(SwingConstants.CENTER);
        iuEstado.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(20)));
        iuEstado.setForeground(new Color(120, 0, 0));
        iuEstado.setVisible(false);
        
        iuTablaComanda = new IUTablaDetalleComanda(new Limitacion(limite.getAncho(), limite.getAlto()));
        segundoPanel.add(iuTablaComanda.tabla.deslizador);
        iuTablaComanda.tabla.add(iuEstado);
    }
    private void construirTercerPanel(Limitacion limite){
        iuTotal = new IUPanelTT(new Limitacion(limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(90)), "total a pagar (en BOB.-)", "", 70, 30);
        iuTotal.iuTexto.setHorizontalAlignment(SwingConstants.RIGHT);
        iuTotal.iuTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(40)));
        iuTotal.iuTexto.setBorder(new LineBorder(Color.LIGHT_GRAY));
        tercerPanel.add(iuTotal);
    }
    public void llenarNuevaComanda(String tipoComanda){
        iuNombre.setText(tipoComanda);
        iuNumero.iuTexto.setText(String.valueOf(Asistente.getPostId("idcomanda", "select idcomanda from comanda order by idcomanda desc limit 1")));
        iuFecha.iuTexto.setText(new Fecha().getFecha6());
        iuHora.iuTexto.setText(new Hora().getHora()+" "+new Hora().getFormato());
        iuEstado.setText("DEBE");
    }
    public void llenarComanda(Comanda c){
        iuNombre.setText(c.getNombre());
        iuNumero.iuTexto.setText(c.getNroComanda());
        iuEstado.setText(c.getEstado());
        iuFecha.iuTexto.setText(new Fecha(c.getFecha()).getFecha6());
        iuHora.iuTexto.setText(c.getHora());
        iuTotal.iuTexto.setText(String.valueOf(c.getTotal()));
        iuTablaComanda.limpiarTabla();
        for (int i = 0; i < c.getLista().size(); i++) {
            iuTablaComanda.setFila(c.getLista().get(i));
        }
        if(iuEstado.getText().equalsIgnoreCase("PAGADO"))
            iuEstado.setVisible(true);
        else
            iuEstado.setVisible(false);
    }
    public boolean existeProducto(Producto producto){
        boolean verificador = false;
        int contador = 0;
        while(contador < iuTablaComanda.getRowCount() && !verificador){
            Detalle detalle = iuTablaComanda.getFila(contador);
            if(detalle.getIdProducto() == producto.getId())
                verificador = true;
            contador++;
        }        
        return verificador;
    }
    public Detalle getDetalle(int idProducto){
        boolean verificador = false;
        Detalle detalle = null;
        int contador = 0;
        while(contador < iuTablaComanda.getRowCount() && !verificador){
            if(iuTablaComanda.getFila(contador).getProducto().getId() == idProducto){
                detalle = iuTablaComanda.getFila(contador);
                verificador = true;
            }                
            contador++;
        }        
        return detalle;
    }
    public void actualizarTablaComanda(){
        double total = 0;
        iuTablaComanda.tabla.updateUI();
        for (int i = 0; i < iuTablaComanda.getRowCount(); i++) {
            total = total + iuTablaComanda.getFila(i).getTotal();
        }
        iuTotal.iuTexto.setText(String.valueOf(total));
    }
    public boolean getEstado(){
        boolean verificador = false;
        if(esCorrectoComanda())
            verificador = true;
        return verificador;
    }
    private boolean esCorrectoComanda(){
        boolean verificador = false;
        if(!iuTablaComanda.isVacia())
            verificador = true;
        return verificador;
    }
    public Comanda getComanda(){
        Comanda comanda = new Comanda(0);
        comanda.setNroComanda(iuNumero.iuTexto.getText());
        comanda.setNombre(iuNombre.getText());
        comanda.setFecha(new Fecha().fecha());
        comanda.setHora(new Hora().getHora()+" "+new Hora().getFormato());
        comanda.setTotal(Double.valueOf(iuTotal.iuTexto.getText()));
        comanda.setEstado(iuEstado.getText());        
        comanda.setIdRegistroHospedaje(idRegistroHospedaje);
        for (int i = 0; i < iuTablaComanda.getRowCount(); i++) {
            Detalle detalle = iuTablaComanda.getFila(i);
            detalle.setIdRegistroHospedaje(idRegistroHospedaje);
            comanda.setDetalle(detalle);
        }
        return comanda;
    }
}
