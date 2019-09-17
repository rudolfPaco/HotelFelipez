/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.temporadas;

import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.tablas.IUTabla;
import com.aplicacionjava.www.tablas.ModeloTabla;
import com.hotelfelipez.www.modulo.modelo.Moneda;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import java.awt.Font;
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
class IUTablaTemporadas extends ModeloTabla<Temporada>{
    public IUTabla tabla;   
    private final String[] nombreCabecera = {"Temporada","precio","moneda"};
    private final ArrayList<Temporada> lista = new ArrayList<>();
    private final Class[] columnas = {String.class, String.class, String.class};
    private final int[] porcentajes = {60, 20, 20};
    private final Limitacion limitacion;
    private IUVentanaTemporadas iuTemporadas;
    private Temporada temporada;
    
    public IUTablaTemporadas(IUVentanaTemporadas iuTemporadas, Limitacion limitacion) {
        this.iuTemporadas = iuTemporadas;
        this.limitacion = limitacion;        
        this.temporada = null;
        
        construirTabla();
        setEventos();
    }
    private void construirTabla(){
        setModelo(nombreCabecera, columnas, lista);
        
        tabla = new IUTabla(this, limitacion);
        tabla.agregarAnchoColumnas(porcentajes);
        tabla.setPosicionTextoHorizontal(1, SwingConstants.RIGHT);
        tabla.setPosicionTextoHorizontal(2, SwingConstants.CENTER);        
        
        //tabla.setRowHeight(limitacion.getPorcentajeAlto(15));
        /*for (int i = 0; i < tipoColumnas.length - 1; i++) {
            tabla.setDefaultRenderer(tipoColumnas[i], new IURenderProductoMinibar(this));            
        }
        tabla.agregarCellRender(1, new IURenderProductoMinibar(new Limitacion(limitacion.getPorcentajeAncho(30) - 1, limitacion.getPorcentajeAlto(15) - 1)));        */
    }
    public boolean estaSeleccionado(){
        return tabla.getSelectedRow() > -1;
    }
    public Temporada getTemporada(){
        return temporada;
    }
    private void setEventos(){
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                                
                if (row > -1) {
                    temporada = getFila(tabla.getSelectedRow());
                    iuTemporadas.mostrarDatosTemporada(temporada);                    
                }
            }
        });
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lista.get(rowIndex).getTipoTemporada();
            case 1:
                return lista.get(rowIndex).getPrecio()+" "+lista.get(rowIndex).getUnidadMoneda().getUnidad();
            case 2:
                return lista.get(rowIndex).getUnidadMoneda().getTipo();
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
