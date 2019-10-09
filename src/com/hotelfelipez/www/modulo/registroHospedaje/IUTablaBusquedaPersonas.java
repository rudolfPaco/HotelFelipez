/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.registroHospedaje;

import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.tablas.IUTabla;
import com.aplicacionjava.www.tablas.ModeloTabla;
import com.hotelfelipez.www.modulo.controlador.CRegistroPersona;
import com.hotelfelipez.www.modulo.modelo.Documento;
import com.hotelfelipez.www.modulo.modelo.Persona;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rudolf
 */
public class IUTablaBusquedaPersonas extends ModeloTabla<Persona>{
    public IUTabla tabla;   
    private IUBuscarPesonas iuBuscar;
    private CRegistroPersona control;
    private final String[] nombreCabecera = {"N°","nombre completo","edad","carnet identidad","caducidad","datos"};
    private final ArrayList<Persona> lista = new ArrayList<>();
    private final Class[] columnas = {Integer.class, String.class, Integer.class, String.class, String.class, String.class, String.class};
    private final int[] porcentajes = {7, 47, 9, 20, 12, 15};
    private final Limitacion limitacion;
    public TableRowSorter filtro;
    private Persona persona;
    
    public IUTablaBusquedaPersonas(IUBuscarPesonas iuBuscar, CRegistroPersona control, Limitacion limitacion) {
        this.iuBuscar = iuBuscar;
        this.control = control;
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
        
        filtro = new TableRowSorter(this);
        tabla.setRowSorter(filtro);
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
                    persona = getFila(tabla.convertRowIndexToModel(tabla.getSelectedRow()));
                    if(persona.getDocumentos().isEmpty())
                        persona.setDocumentos(control.getDocumentosPersona(persona.getId()));
                        
                    iuBuscar.iuFoto.setUrlImagen("");
                    iuBuscar.iuFoto.setIcon(null);
                    iuBuscar.ciCara.setUrlImagen("");
                    iuBuscar.ciCara.setIcon(null);
                    iuBuscar.ciEspalda.setUrlImagen("");
                    iuBuscar.ciEspalda.setIcon(null);
                    iuBuscar.passporte.setUrlImagen("");
                    iuBuscar.passporte.setIcon(null);
                    iuBuscar.certificado.setUrlImagen("");
                    iuBuscar.certificado.setIcon(null);


                    for (int i = 0; i < persona.getDocumentos().size(); i++) {
                        Documento doc = persona.getDocumentos().get(i);
                        iuBuscar.mostrarTipoDocumento(doc.getTipo());
                        switch(doc.getTipo()){
                            case "foto":
                                if(doc.getBuffer() != null){
                                    iuBuscar.iuFoto.setBuffered(doc.getBuffer());
                                    iuBuscar.iuFoto.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(iuBuscar.iuFoto.getWidth(), iuBuscar.iuFoto.getHeight(), Image.SCALE_DEFAULT)));                                    
                                }                        
                                else{
                                    iuBuscar.iuFoto.setUrlImagen(doc.getUrl());
                                    iuBuscar.iuFoto.setIcon(new ImageIcon(new ImageIcon(doc.getUrl()).getImage().getScaledInstance(iuBuscar.iuFoto.getWidth(), iuBuscar.iuFoto.getHeight(), Image.SCALE_DEFAULT)));                    
                                }
                            break;
                            case "carnetIdentidadC":
                                if(doc.getBuffer() != null){
                                    iuBuscar.ciCara.setBuffered(doc.getBuffer());
                                    iuBuscar.ciCara.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(iuBuscar.ciCara.getWidth(), iuBuscar.ciCara.getHeight(), Image.SCALE_DEFAULT)));
                                }                        
                                else{
                                    iuBuscar.ciCara.setUrlImagen(doc.getUrl());
                                    iuBuscar.ciCara.setIcon(new ImageIcon(new ImageIcon(doc.getUrl()).getImage().getScaledInstance(iuBuscar.ciCara.getWidth(), iuBuscar.ciCara.getHeight(), Image.SCALE_DEFAULT)));
                                }
                            break;
                            case "carnetIdentidadE":
                                if(doc.getBuffer() != null){
                                    iuBuscar.ciEspalda.setBuffered(doc.getBuffer());
                                    iuBuscar.ciEspalda.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(iuBuscar.ciEspalda.getWidth(), iuBuscar.ciEspalda.getHeight(), Image.SCALE_DEFAULT)));
                                }                        
                                else{
                                    iuBuscar.ciEspalda.setUrlImagen(doc.getUrl());
                                    iuBuscar.ciEspalda.setIcon(new ImageIcon(new ImageIcon(doc.getUrl()).getImage().getScaledInstance(iuBuscar.ciEspalda.getWidth(), iuBuscar.ciEspalda.getHeight(), Image.SCALE_DEFAULT)));
                                }
                            break;
                            case "passporte":
                                if(doc.getBuffer() != null){
                                    iuBuscar.passporte.setBuffered(doc.getBuffer());
                                    iuBuscar.passporte.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(iuBuscar.passporte.getWidth(), iuBuscar.passporte.getHeight(), Image.SCALE_DEFAULT)));
                                }                        
                                else{
                                    iuBuscar.passporte.setUrlImagen(doc.getUrl());
                                    iuBuscar.passporte.setIcon(new ImageIcon(new ImageIcon(doc.getUrl()).getImage().getScaledInstance(iuBuscar.passporte.getWidth(), iuBuscar.passporte.getHeight(), Image.SCALE_DEFAULT)));
                                }
                            break;    
                            case "certificado":
                                if(doc.getBuffer() != null){
                                    iuBuscar.certificado.setBuffered(doc.getBuffer());
                                    iuBuscar.certificado.setIcon(new ImageIcon(new ImageIcon(doc.getBuffer()).getImage().getScaledInstance(iuBuscar.certificado.getWidth(), iuBuscar.certificado.getHeight(), Image.SCALE_DEFAULT)));
                                }                        
                                else{
                                    iuBuscar.certificado.setUrlImagen(doc.getUrl());
                                    iuBuscar.certificado.setIcon(new ImageIcon(new ImageIcon(doc.getUrl()).getImage().getScaledInstance(iuBuscar.certificado.getWidth(), iuBuscar.certificado.getHeight(), Image.SCALE_DEFAULT)));
                                }
                            break;
                        }                                                        
                    }
                    
                    iuBuscar.iuTipoPersona.iuTexto.setText(persona.getTipoPersona().toUpperCase());
                    iuBuscar.iuObservacion.iuAreaTexto.setText(persona.getObservacion());
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
                return lista.get(rowIndex).getNombres().toLowerCase()+" "+lista.get(rowIndex).getApellidos().toLowerCase();
            case 2:
                return lista.get(rowIndex).getEdad();                
            case 3:
                return lista.get(rowIndex).getCarnetIdentidad()+" "+lista.get(rowIndex).getOrigen();                
            case 4:
                return lista.get(rowIndex).getCaducidad();
            case 5:
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
