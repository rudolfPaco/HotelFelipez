/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.habitaciones;

import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.tablas.IUTabla;
import com.aplicacionjava.www.tablas.ModeloTabla;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 *
 * @author hotel-felipez
 */
public class IUTablaHabitaciones extends ModeloTabla<Habitacion>{
    public IUTabla tabla;   
    private final String[] nombreCabecera = {"hab","tipo"};
    private final ArrayList<Habitacion> lista = new ArrayList<>();
    private final Class[] columnas = {String.class, String.class};
    private final int[] porcentajes = {30, 70};
    private final Limitacion limitacion;
    private Habitacion habitacion;
    private IUVentanaHabitaciones iuHabitaciones;
    
    public IUTablaHabitaciones(IUVentanaHabitaciones iuHabitaciones, Limitacion limitacion) {
        this.iuHabitaciones = iuHabitaciones;
        this.limitacion = limitacion;        
        this.habitacion = null;
        
        construirTabla();
        setEventos();
    }
    private void construirTabla(){
        setModelo(nombreCabecera, columnas, lista);
        
        tabla = new IUTabla(this, limitacion);
        tabla.agregarAnchoColumnas(porcentajes);
        tabla.setPosicionTextoHorizontal(0, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(1, SwingConstants.LEFT);
        
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
                    habitacion = getFila(tabla.getSelectedRow());                    
                    iuHabitaciones.mostrarHabitacion(habitacion);
                }
            }
        });
    }
    public boolean estaSeleccionado(){
        return tabla.getSelectedRow() > -1;
    }
    public Habitacion getMoneda(){
        return habitacion;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lista.get(rowIndex).getNumero()+" "+lista.get(rowIndex).getSimbolo();
            case 1:
                return "("+lista.get(rowIndex).getTipo()+")";
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
