/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.disponibilidad;

import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.tablas.IUTabla;
import com.aplicacionjava.www.tablas.ModeloTabla;
import com.hotelfelipez.www.modulo.habitaciones.IUVentanaHabitaciones;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import java.awt.Color;
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
public class IUTablaDisponibilidad extends ModeloTabla<Habitacion>{
    public IUTabla tabla;   
    private final String[] nombreCabecera = {"habitacion","s/cama","descripcion","precio","cap.","estado","check"};
    private final ArrayList<Habitacion> lista = new ArrayList<>();
    private final ArrayList<Habitacion> habitacionesSeleccionadas = new ArrayList<>();
    private final Class[] columnas = {String.class, String.class, String.class, Double.class, String.class, String.class, Boolean.class};
    private final int[] porcentajes = {20, 4, 45, 4, 7, 7, 3};
    private final Limitacion limitacion;
    private Habitacion habitacion;
    
    public IUTablaDisponibilidad(Limitacion limitacion) {
        this.limitacion = limitacion;        
        this.habitacion = null;
        
        construirTabla();
        setEventos();
    }
    private void construirTabla(){
        setModelo(nombreCabecera, columnas, lista);
        
        tabla = new IUTabla(this, limitacion);
        tabla.agregarAnchoColumnas(porcentajes);        
                        
        for (int i = 0; i < columnas.length-1; i++) {            
            tabla.setDefaultRenderer(columnas[i], new RenderResultado());
        }
        
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
                }
            }
        });
    }
    public boolean estaSeleccionado(){
        return tabla.getSelectedRow() > -1;
    }
    public Habitacion getHabitacion(){
        return habitacion;
    }
    public ArrayList<Habitacion> getHabitacionesSeleccionadas(){
        return habitacionesSeleccionadas;
    }
    public void limpiar(){
        habitacionesSeleccionadas.clear();        
        limpiarTabla();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lista.get(rowIndex).getNumero()+" "+lista.get(rowIndex).getSimbolo()+" ("+lista.get(rowIndex).getTipo()+") "+lista.get(rowIndex).getEstilo();
            case 1:
                return lista.get(rowIndex).getSofaCama();
            case 2:
                return lista.get(rowIndex).getDescripcion();
            case 3:
                return lista.get(rowIndex).getTemporada().getPrecio();
            case 4:                
                return lista.get(rowIndex).getCapacidad();
            case 5:
                return lista.get(rowIndex).getEstado();
            case 6:
                return lista.get(rowIndex).isCheck();
            default:
                return null;
        }
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {     
            case 6:
                return true;
            default:
                return false;
        }
    }
    public boolean getHabitacionMarcado(){
        boolean verificador = false;
        int contador = 0;
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).isCheck())
                contador++;
        }
        if(contador == 1)
            verificador = true;
        return verificador;
    }
    public Habitacion getHabitacionMarcada(){
        Habitacion hab = null;
        boolean estado = false;
        int contador = 0;
        while(contador < lista.size() && !estado){
            if(lista.get(contador).isCheck()){
                hab = lista.get(contador);
                estado = true;
            }
            contador++;
        }            
        return hab;
    }
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 6:
                lista.get(rowIndex).setCheck((boolean) value);                
                fireTableCellUpdated(rowIndex, columnIndex);
                if((boolean)value){
                    habitacionesSeleccionadas.add(lista.get(rowIndex));
                }else{
                    habitacionesSeleccionadas.remove(lista.get(rowIndex));
                }
            default:                
        }
    }
}
