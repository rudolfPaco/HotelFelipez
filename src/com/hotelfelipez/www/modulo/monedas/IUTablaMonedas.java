/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.monedas;

import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.tablas.IUTabla;
import com.aplicacionjava.www.tablas.ModeloTabla;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import com.hotelfelipez.www.modulo.temporadas.IUVentanaTemporadas;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 *
 * @author neo
 */
public class IUTablaMonedas extends ModeloTabla<Moneda>{
    public IUTabla tabla;   
    private final String[] nombreCabecera = {"tipo de moneda","unidad"};
    private final ArrayList<Moneda> lista = new ArrayList<>();
    private final Class[] columnas = {String.class, String.class};
    private final int[] porcentajes = {70, 30};
    private final Limitacion limitacion;
    private Moneda moneda;
    
    public IUTablaMonedas(Limitacion limitacion) {
        this.limitacion = limitacion;        
        this.moneda = null;
        
        construirTabla();
        setEventos();
    }
    private void construirTabla(){
        setModelo(nombreCabecera, columnas, lista);
        
        tabla = new IUTabla(this, limitacion);
        tabla.agregarAnchoColumnas(porcentajes);
        tabla.setPosicionTextoHorizontal(1, SwingConstants.RIGHT);        
        
        //tabla.setRowHeight(limitacion.getPorcentajeAlto(15));
        /*for (int i = 0; i < tipoColumnas.length - 1; i++) {
            tabla.setDefaultRenderer(tipoColumnas[i], new IURenderProductoMinibar(this));            
        }
        tabla.agregarCellRender(1, new IURenderProductoMinibar(new Limitacion(limitacion.getPorcentajeAncho(30) - 1, limitacion.getPorcentajeAlto(15) - 1)));        */
    }
    private void setEventos(){
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                                
                if (row > -1) {
                    moneda = getFila(tabla.getSelectedRow());                    
                }
            }
        });
    }
    public boolean estaSeleccionado(){
        return tabla.getSelectedRow() > -1;
    }
    public Moneda getMoneda(){
        return moneda;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lista.get(rowIndex).getTipo();
            case 1:
                return "("+lista.get(rowIndex).getUnidad()+")";
            default:
                return null;
        }
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {     
            default:
                return false;
        }
    }
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        /*if(rowIndex < lista.size()){            
        }
        if((Producto)lista.get(rowIndex) != null){                
            switch (columnIndex) {
                case 3:
                    lista.get(rowIndex).setEstado((String) value);
                    fireTableCellUpdated(rowIndex, columnIndex);
                default:                
            }
        }*/
    }
}
