/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.habitaciones;

import com.hotelfelipez.www.modulo.temporadas.IUElegirTemporada;
import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCT;
import com.aplicacionjava.www.paneles.IUPanelCTU;
import com.aplicacionjava.www.paneles.IUPanelTA;
import com.aplicacionjava.www.paneles.IUPanelTCB;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import com.hotelfelipez.www.modulo.controlador.CHabitaciones;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Frigobar;
import com.hotelfelipez.www.modulo.modelo.Habitacion;
import com.hotelfelipez.www.modulo.modelo.Temporada;
import com.hotelfelipez.www.modulo.principal.IUVentanaHotel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class IUNuevaHabitacion extends IUVentanaT{
    
    private IUVentanaHotel ventanaPrincipal;
    private CHabitaciones controlHabitaciones;
    
    private IUPanelBD primerPanel;
    private IUPanelCT panelNumero;
    private IUPanelCT panelAbreviatura;
    private IUPanelTCB panelTipo;
    private IUPanelTCB panelNombre;
    private IUPanelCT panelEstado;
    private IUPanelTCB panelNumeroPiso;
    private IUPanelCT panelNumeroTelefono;
    private IUPanelTCB panelCapacidad;
    private IUPanelCT panelLugar;
    private IUPanelTA panelDescripcion;
    private IUPanelCT tipoTemporada;
    private IUBoton botonTemporada;
    private IUPanelCTU panelPrecio;
    private IUPanelCT panelMoneda;
    private IUPanelTCB panelSofaCama;
    private IUPanelCTU panelCostoSofaCama;
    private IUPanelTCB panelTipoVenta;
    private IUPanelCT panelPromocionFrigobar;
    private IUPanelCT panelDisponibilidad;
        
    private IUPanelBD segundoPanel;
    private IUBoton botonSalir;
    private IUBoton botonGuardar;
    
    private ArrayList<Temporada> listaTemporadas;
    private Temporada temporada;
    private boolean estado;    
    
    public IUNuevaHabitacion(IUVentanaHotel ventanaPrincipal, CHabitaciones controlHabitaciones, String titulo, Limitacion limitacion) {
        super(ventanaPrincipal, titulo, limitacion, 4);
        
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlHabitaciones = controlHabitaciones;
        this.listaTemporadas = controlHabitaciones.getListaTemporadas();
        this.temporada = null;
        
        construirPaneles(panelFondo.getLimitacion());
        escucharEventos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(85)));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanelBD(new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(91), limite.getPorcentajeAncho(90), limite.getPorcentajeAlto(8)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){
        panelNumero = new IUPanelCT("numero", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(7)), 40, 60);
        panelNumero.iuTexto.setRestriccionNumeroEnteros();
        primerPanel.add(panelNumero);
        
        panelAbreviatura = new IUPanelCT("tipo", "", new Limitacion(limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(7), limite.getPorcentajeAlto(7)), 40, 60);
        panelAbreviatura.iuTexto.setFocusable(false);
        panelAbreviatura.iuTexto.setEditable(false);
        primerPanel.add(panelAbreviatura);
        
        panelTipo = new IUPanelTCB("habitacion", Asistente.getColumnas("tipo", "select distinct tipo from habitacion"), new Limitacion(limite.getPorcentajeAncho(23), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(7)), 40, 60);
        primerPanel.add(panelTipo);        
                
        panelNombre = new IUPanelTCB("nombre habitacion", Asistente.getColumnas("estilo", "select distinct estilo from habitacion"), new Limitacion(limite.getPorcentajeAncho(65), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(33), limite.getPorcentajeAlto(7)), 40, 60);        
        panelNombre.iuTexto.setRestriccionLetrasMayusculas();
        primerPanel.add(panelNombre);
        
        panelEstado = new IUPanelCT("estado", "VACANTE", new Limitacion(limite.getPorcentajeAncho(65), limite.getPorcentajeAlto(9), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(7)), 40, 60);
        panelEstado.iuTexto.setFocusable(false);
        panelEstado.iuTexto.setEditable(false);
        primerPanel.add(panelEstado);
        
        String[] pisos = {"", "primer piso", "segundo piso", "tercer piso", "cuarto piso"};
        panelNumeroPiso = new IUPanelTCB("numero piso", pisos, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(9), limite.getPorcentajeAncho(23), limite.getPorcentajeAlto(7)), 40, 60);
        primerPanel.add(panelNumeroPiso);
        
        panelNumeroTelefono = new IUPanelCT("telefono", "", new Limitacion(limite.getPorcentajeAncho(27), limite.getPorcentajeAlto(9), limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(7)), 40, 60);
        primerPanel.add(panelNumeroTelefono);
        
        String[] capacidades = {"", "1 persona", "2 personas", "3 personas", "4 personas", "5 personas"};
        panelCapacidad = new IUPanelTCB("capacidad", capacidades, new Limitacion(limite.getPorcentajeAncho(39), limite.getPorcentajeAlto(9), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(7)), 40, 60);
        primerPanel.add(panelCapacidad);
        
        panelLugar = new IUPanelCT("ubicacion en el hotel", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(17), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(7)), 40, 60);
        primerPanel.add(panelLugar);
        
        panelDescripcion = new IUPanelTA("descripcion de la habitacion", "", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(25), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(21)), 13, 87);
        panelDescripcion.iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, 18));
        primerPanel.add(panelDescripcion);
        
        tipoTemporada = new IUPanelCT("tipo temporada", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(48), limite.getPorcentajeAncho(45), limite.getPorcentajeAlto(7)), 40, 60);
        tipoTemporada.iuTexto.setEditable(false);
        tipoTemporada.iuTexto.setFocusable(false);
        primerPanel.add(tipoTemporada);
        
        botonTemporada = new IUBoton("elegir temporada", new Limitacion(limite.getPorcentajeAncho(47), limite.getPorcentajeAlto(51), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(4)));
        primerPanel.add(botonTemporada);
        
        panelPrecio = new IUPanelCTU("precio habitacion", "", "", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(57), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(7)), 40, 60, 40);
        panelPrecio.iuTexto.setEditable(false);
        panelPrecio.iuTexto.setFocusable(false);
        primerPanel.add(panelPrecio);
        
        panelMoneda = new IUPanelCT("moneda", "", new Limitacion(limite.getPorcentajeAncho(29), limite.getPorcentajeAlto(57), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(7)), 40, 60);
        panelMoneda.iuTexto.setEditable(false);
        panelMoneda.iuTexto.setFocusable(false);
        primerPanel.add(panelMoneda);
        
        String[] tipos = {"NORMAL", "INTERNET"};
        panelTipoVenta = new IUPanelTCB("tipo venta", tipos, new Limitacion(limite.getPorcentajeAncho(56), limite.getPorcentajeAlto(57), limite.getPorcentajeAncho(35), limite.getPorcentajeAlto(7)), 40, 60);
        panelTipoVenta.iuTexto.setFocusable(false);
        primerPanel.add(panelTipoVenta);
        
        String[] sofaSiNo = {"NO", "SI"};
        panelSofaCama = new IUPanelTCB("sofa cama", sofaSiNo, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(66), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(7)), 40, 60);        
        primerPanel.add(panelSofaCama);
        
        panelCostoSofaCama = new IUPanelCTU("costo sofa", "", "", new Limitacion(limite.getPorcentajeAncho(29), limite.getPorcentajeAlto(66), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(7)), 40, 60, 50);        
        panelCostoSofaCama.iuTexto.setRestriccionNumeroDecimal();
        primerPanel.add(panelCostoSofaCama);
        
        panelPromocionFrigobar = new IUPanelCT("promocion frigobar", "NO", new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(75), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(7)), 40, 60);
        panelPromocionFrigobar.iuTexto.setEditable(false);
        panelPromocionFrigobar.iuTexto.setFocusable(false);
        primerPanel.add(panelPromocionFrigobar);
        
        panelDisponibilidad = new IUPanelCT("habitacion disponible", "SI", new Limitacion(limite.getPorcentajeAncho(29), limite.getPorcentajeAlto(75), limite.getPorcentajeAncho(25), limite.getPorcentajeAlto(7)), 40, 60);
        panelDisponibilidad.iuTexto.setEditable(false);
        panelDisponibilidad.iuTexto.setFocusable(false);
        primerPanel.add(panelDisponibilidad);
    }
    private void construirSegundoPanel(Limitacion limite){
        botonSalir = new IUBoton("salir", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(15), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(70)));
        segundoPanel.add(botonSalir);
        
        botonGuardar = new IUBoton("guardar", new Limitacion(limite.getPorcentajeAncho(52), limite.getPorcentajeAlto(15), limite.getPorcentajeAncho(42), limite.getPorcentajeAlto(70)));
        segundoPanel.add(botonGuardar);
    }
    private void llenarDatosTemporada(Temporada temporada){
        this.temporada = temporada;
        tipoTemporada.iuTexto.setText(temporada.getTipoTemporada());
        panelPrecio.iuTexto.setText(String.valueOf(Asistente.acotarNumero(temporada.getPrecio(), 2)));
        panelPrecio.iuTexto.iuUnidad.setText(temporada.getUnidadMoneda().getUnidad());
        panelMoneda.iuTexto.setText(temporada.getUnidadMoneda().getTipo());
        panelCostoSofaCama.iuTexto.iuUnidad.setText(temporada.getUnidadMoneda().getUnidad());
    }
    private boolean estaValidadoCamposDatos(){
        boolean verificador = false;
        if(!panelNumero.iuTexto.getText().isEmpty()){
            if(!panelAbreviatura.iuTexto.getText().isEmpty()){
                if(!panelTipo.iuTexto.getTexto().isEmpty()){
                    if(!panelNombre.iuTexto.getTexto().isEmpty()){
                        if(!panelEstado.iuTexto.getText().isEmpty()){
                            if(!panelNumeroPiso.iuTexto.getTexto().isEmpty()){
                                if(!panelNumeroTelefono.iuTexto.getText().isEmpty()){
                                    if(!panelCapacidad.iuTexto.getTexto().isEmpty()){
                                        if(!panelLugar.iuTexto.getText().isEmpty()){
                                            if(!panelDescripcion.iuAreaTexto.getText().isEmpty()){
                                                if(!tipoTemporada.iuTexto.getText().isEmpty()){
                                                    if(!panelSofaCama.iuTexto.getTexto().isEmpty()){
                                                        if(!panelCostoSofaCama.iuTexto.getText().isEmpty()){
                                                            verificador = true;
                                                        }else{
                                                            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato COSTO SOFA CAMA", "advertencia");
                                                        }                                                        
                                                    }else{
                                                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato SOFA CAMA", "advertencia");
                                                    }                                                    
                                                }else{
                                                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato TIPO DE TEMPORADA", "advertencia");
                                                }
                                            }else{
                                                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato DESCRIPCION DE HABITACION", "advertencia");
                                            }
                                        }else{
                                            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato LUGAR DE HABITACION", "advertencia");
                                        }
                                    }else{
                                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato CAPACIDAD PERSONAS", "advertencia");
                                    }
                                }else{
                                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato NUMERO TELEFONO", "advertencia");
                                }
                            }else{
                                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato NUMERO PISO", "advertencia");
                            }
                        }else{
                            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato ESTADO HABITACION", "advertencia");
                        }
                    }else{
                        Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato NOMBRE HABITACION", "advertencia");
                    }                    
                }else{
                    Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato TIPO HABITACION", "advertencia");
                }
            }else{
                Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato ABREVIATURA DE HABITACION", "advertencia");
            }
        }else{
            Asistente.mensajeVerificacion(ventanaPrincipal, "aviso", "lo siento pero no puede estar vacio el campo de dato NUMERO HABITACION", "advertencia");
        }
        return verificador;
    }
    private void escucharEventos(){
        panelTipo.iuTexto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch(panelTipo.iuTexto.getTexto()){
                    case "":
                        panelAbreviatura.iuTexto.setText("");
                    break;
                    case "SIMPLE":
                        panelAbreviatura.iuTexto.setText("S");
                    break;
                    case "DOBLE SIMPLE":
                        panelAbreviatura.iuTexto.setText("DS");
                    break;
                    case "MATRIMONIAL":
                        panelAbreviatura.iuTexto.setText("M");
                    break;
                    case "TRIPLE MATRIMONIAL":
                        panelAbreviatura.iuTexto.setText("TM");
                    break;
                    case "FAMILIAR":
                        panelAbreviatura.iuTexto.setText("F");
                    break;
                }                
            }
        });
        botonTemporada.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setOpacity(0.0f);
                IUElegirTemporada iuElegir = new IUElegirTemporada(ventanaPrincipal, listaTemporadas, "precios de temporadas", new Limitacion(Asistente.ANCHO/2 - Asistente.ANCHO/7, Asistente.ALTO));        
                iuElegir.mostrarVentana();
                if(iuElegir.getEstado()){
                    llenarDatosTemporada(iuElegir.getTemporada());
                }
                setOpacity(1f);
            }
        });
        botonSalir.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        botonGuardar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(estaValidadoCamposDatos()){
                    dispose();
                    estado = true;                    
                }
            }
        });
    }
    public boolean getEstado(){
        return estado;
    }
    public Habitacion getHabitacion(){
        Habitacion habitacion = new Habitacion(0);
        habitacion.setNumero(panelNumero.iuTexto.getText());
        habitacion.setSimbolo(panelAbreviatura.iuTexto.getText());
        habitacion.setTipo(panelTipo.iuTexto.getTexto());
        habitacion.setEstilo(panelNombre.iuTexto.getTexto());
        habitacion.setEstado(panelEstado.iuTexto.getText());
        habitacion.setNumeroPiso(panelNumeroPiso.iuTexto.getTexto());
        habitacion.setCapacidad(panelCapacidad.iuTexto.getTexto());
        habitacion.setNumeroTelefono(panelNumeroTelefono.iuTexto.getText());
        habitacion.setDetalle(panelLugar.iuTexto.getText());
        habitacion.setDescripcion(panelDescripcion.iuAreaTexto.getText());
        habitacion.setDisponible(panelDisponibilidad.iuTexto.getText());
        habitacion.setPromocionFrigobar(panelPromocionFrigobar.iuTexto.getText());
        habitacion.setSofaCama(panelSofaCama.iuTexto.getTexto());
        habitacion.setCostoSofaCama(Asistente.acotarNumero(Double.parseDouble(panelCostoSofaCama.iuTexto.getText()), 2));
        habitacion.setTipoVenta(panelTipoVenta.iuTexto.getTexto());
        habitacion.setTipoTemporada(tipoTemporada.iuTexto.getText());
        habitacion.setIdtemporada(temporada.getId());
        habitacion.setIdmoneda(temporada.getUnidadMoneda().getId());
        habitacion.setIdprestacion(1);
        
        Frigobar frigobar = new Frigobar(0);
        frigobar.setNombreFrigobar("FRI-"+habitacion.getNumero());
        frigobar.setCantidadProductos(0);
        frigobar.setIdhabitacion(0);
        
        habitacion.setFrigobar(frigobar);
        
        return habitacion;
    }
}
