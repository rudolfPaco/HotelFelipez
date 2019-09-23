/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.frigobar;

import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.tablas.IUTabla;
import com.aplicacionjava.www.tablas.ModeloTabla;
import com.hotelfelipez.www.modulo.modelo.Detalle;
import com.hotelfelipez.www.modulo.modelo.Producto;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUTablaFrigobar extends ModeloTabla<Producto>{
    public IUTabla tabla;   
    private final String[] nombreCabecera = {"categoria","producto","promo", "Anterior","Actual", "cant"};
    private final ArrayList<Producto> lista = new ArrayList<>();
    private final Class[] columnas = {String.class, String.class, String.class, Double.class, Double.class, String.class};
    private final int[] porcentajes = {20, 40, 10, 11, 11, 8};
    private final Limitacion limitacion;
    private Producto producto;
    
    public IUTablaFrigobar(Limitacion limitacion) {
        this.limitacion = limitacion;        
        this.producto = null;
        
        construirTabla();
        setEventos();
    }
    private void construirTabla(){
        setModelo(nombreCabecera, columnas, lista);
        
        tabla = new IUTabla(this, limitacion);
        tabla.agregarAnchoColumnas(porcentajes);
        tabla.setPosicionTextoHorizontal(0, SwingConstants.LEFT);
        tabla.setPosicionTextoHorizontal(1, SwingConstants.LEFT);
        tabla.setPosicionTextoHorizontal(2, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(3, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(4, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(5, SwingConstants.CENTER);
        
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
                    
                }
            }
        });
    }
    public boolean estaSeleccionado(){
        return tabla.getSelectedRow() > -1;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:                
                return lista.get(rowIndex).getCategoria();    
            case 1:
                return lista.get(rowIndex).getMarca()+" "+lista.get(rowIndex).getNombre();
            case 2:
                return lista.get(rowIndex).getPromocion();
            case 3:
                switch(lista.get(rowIndex).getPromocion()){
                    case "NO":
                        return "";
                    case "SI":
                        return lista.get(rowIndex).getPrecioAnterior();
                }                
            case 4:
                return lista.get(rowIndex).getPrecio();
            case 5:
                return lista.get(rowIndex).getCantidad();
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
