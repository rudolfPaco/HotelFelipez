/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.tablas.IUTabla;
import com.aplicacionjava.www.tablas.ModeloTabla;
import com.hotelfelipez.www.modulo.modelo.Documento;
import com.hotelfelipez.www.modulo.modelo.Persona;
import com.hotelfelipez.www.modulo.modelo.Producto;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 *
 * @author neo
 */
public class IUTablaPersonas extends ModeloTabla<Persona>{
    public IUTabla tabla;   
    private IUPanelDatosPersonales iuDatosPersonales;
    private final String[] nombreCabecera = {"N°","nombre completo","edad","carnet identidad","caducidad", "doc.","datos"};
    private final ArrayList<Persona> lista = new ArrayList<>();
    private final Class[] columnas = {Integer.class, String.class, Integer.class, String.class, String.class, String.class, String.class};
    private final int[] porcentajes = {7, 42, 9, 20, 10, 7, 15};
    private final Limitacion limitacion;
    private Persona persona;
    
    public IUTablaPersonas(IUPanelDatosPersonales iuDatosPersonales, Limitacion limitacion) {
        this.iuDatosPersonales = iuDatosPersonales;
        this.limitacion = limitacion;        
        this.persona = null;
        
        construirTabla();
        setEventos();
    }
    private void construirTabla(){
        setModelo(nombreCabecera, columnas, lista);
        
        tabla = new IUTabla(this, limitacion);
        tabla.agregarAnchoColumnas(porcentajes);
        tabla.setPosicionTextoHorizontal(0, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(1, SwingConstants.LEFT);
        tabla.setPosicionTextoHorizontal(2, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(3, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(4, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(5, SwingConstants.CENTER);
        tabla.setPosicionTextoHorizontal(6, SwingConstants.CENTER);
        
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
                    persona = getFila(tabla.getSelectedRow());                                        
                    if(iuDatosPersonales != null){
                        for (int i = 0; i < persona.getDocumentos().size(); i++) {
                            Documento doc = persona.getDocumentos().get(i);
                            if(doc.getTipo().equalsIgnoreCase("foto")){
                                if(doc.getBuffer() != null){
                                    iuDatosPersonales.iuFoto.setBuffered(doc.getBuffer());
                                    iuDatosPersonales.iuFoto.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(iuDatosPersonales.iuFoto.getWidth(), iuDatosPersonales.iuFoto.getHeight(), Image.SCALE_DEFAULT)));
                                }                        
                                else{
                                    iuDatosPersonales.iuFoto.setUrlImagen(doc.getUrl());
                                    iuDatosPersonales.iuFoto.setIcon(new ImageIcon(new ImageIcon(doc.getUrl()).getImage().getScaledInstance(iuDatosPersonales.iuFoto.getWidth(), iuDatosPersonales.iuFoto.getHeight(), Image.SCALE_DEFAULT)));                    
                                }
                            }else{
                                if(doc.getUrl().isEmpty()){
                                    iuDatosPersonales.iuFoto.setUrlImagen("");
                                    iuDatosPersonales.iuFoto.setIcon(null);
                                }                                
                            }                                
                        }
                        if(persona.getDocumentos().isEmpty()){
                            iuDatosPersonales.iuFoto.setUrlImagen("");
                            iuDatosPersonales.iuFoto.setIcon(null);
                        }
                        iuDatosPersonales.iuTipoPersona.iuTexto.setText(persona.getTipoPersona().toUpperCase());
                        iuDatosPersonales.iuObservacion.iuAreaTexto.setText(persona.getObservacion());
                    }
                }
            }
        });
    }
    public boolean estaSeleccionado(){
        return tabla.getSelectedRow() > -1;
    }
    public Persona getPersona(){
        return persona;
    }
    public void reemplazarPersona(Persona personaReemplazar){
        int contador = 0;
        boolean estado = false;
        while(contador < lista.size() && !estado){
            Persona personaLista = lista.get(contador);
            if(personaLista.equals(personaReemplazar)){
                lista.set(contador, personaReemplazar);
                estado = true;
            }
            contador++;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lista.get(rowIndex).getId();
            case 1:
                return lista.get(rowIndex).getNombres()+" "+lista.get(rowIndex).getApellidos();                
            case 2:
                return lista.get(rowIndex).getEdad();                
            case 3:
                return lista.get(rowIndex).getCarnetIdentidad()+" "+lista.get(rowIndex).getOrigen();                
            case 4:
                return lista.get(rowIndex).getCaducidad();                
            case 5:
                return lista.get(rowIndex).getEstadoDocumentos();
            case 6:
                return lista.get(rowIndex).getEstado();
                
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
