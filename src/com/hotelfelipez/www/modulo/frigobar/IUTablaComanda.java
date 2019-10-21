/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.frigobar;

import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.tablas.IUTabla;
import com.aplicacionjava.www.tablas.ModeloTabla;
import com.hotelfelipez.www.modulo.disponibilidad.RenderResultado;
import com.hotelfelipez.www.modulo.modelo.Comanda;
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
public class IUTablaComanda extends ModeloTabla<Comanda>{
    public IUTabla tabla;   
    private final String[] nombreCabecera = {"N°" ,"fecha hora","tipo comanda","total","estado", "check"};
    private final ArrayList<Comanda> lista = new ArrayList<>();
    private final Class[] columnas = {String.class, String.class, String.class, Double.class, String.class, Boolean.class};
    private final int[] porcentajes = {8, 33, 26, 12, 15, 6};
    private final Limitacion limitacion;
    private IUPanelComanda panelComanda;
    private Comanda comanda;
    
    public IUTablaComanda(IUPanelComanda panelComanda, Limitacion limitacion) {
        this.limitacion = limitacion;        
        this.panelComanda = panelComanda;
        this.comanda = null;
        
        construirTabla();
        setEventos();
    }
    private void construirTabla(){
        setModelo(nombreCabecera, columnas, lista);
        
        tabla = new IUTabla(this, limitacion);
        tabla.agregarAnchoColumnas(porcentajes);
        tabla.setPosicionTextoHorizontal(0, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(1, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(2, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(3, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(4, SwingConstants.CENTER);        
    }
    private void setEventos(){
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                                
                if (row > -1) {
                    comanda = getFila(row);
                    panelComanda.llenarComanda(comanda);
                }
            }
        });
    }
    public boolean estaSeleccionado(){
        return tabla.getSelectedRow() > -1;
    }
    public Comanda getComanda(){
        return comanda;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:                
                return lista.get(rowIndex).getNroComanda();
            case 1:
                return new Fecha(lista.get(rowIndex).getFecha()).getFecha6()+"    "+lista.get(rowIndex).getHora();
            case 2:
                return lista.get(rowIndex).getNombre();
            case 3:
                return lista.get(rowIndex).getTotal();
            case 4:
                return lista.get(rowIndex).getEstado();
            case 5:
                return lista.get(rowIndex).isCheck();
            default:
                return null;
        }
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {     
            case 5:         
                if(lista.get(rowIndex).getEstado().equalsIgnoreCase("PAGADO"))
                    return false;
                else
                    return true;
            default:
                return false;
        }
    }
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 5:
                lista.get(rowIndex).setCheck((boolean) value);                
                fireTableCellUpdated(rowIndex, columnIndex);
                /*if((boolean)value){
                    //habitacionesSeleccionadas.add(lista.get(rowIndex));
                }else{
                    //habitacionesSeleccionadas.remove(lista.get(rowIndex));
                }*/
            default:                
        }
    }
    public void seleccionarTodo(){
        for (int i = 0; i < lista.size(); i++) {
            Comanda comanda = getFila(i);
            if(!comanda.getEstado().equalsIgnoreCase("PAGADO"))
                comanda.setCheck(true);
        }       
        tabla.updateUI();
    }
    public void deSeleccionarTodo(){
        for (int i = 0; i < lista.size(); i++) {
            Comanda comanda = getFila(i);
            if(!comanda.getEstado().equalsIgnoreCase("PAGADO"))
                comanda.setCheck(false);
        }       
        tabla.updateUI();
    }
}
