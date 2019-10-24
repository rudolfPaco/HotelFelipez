/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.ComandaDao;
import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.ProductoDao;
import com.hotelfelipez.www.modulo.modelo.Asistente;
import com.hotelfelipez.www.modulo.modelo.Comanda;
import com.hotelfelipez.www.modulo.modelo.Detalle;
import com.hotelfelipez.www.modulo.modelo.Producto;
import java.util.ArrayList;

/**
 *
 * @author rudolf
 */
public class CComanda {

    public CComanda(){
        
    }
    public boolean guardarNuevaComanda(Comanda comanda){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        ComandaDao comandaDao = new ComandaDao(conexion);
        if(comandaDao.seGuardoComanda(comanda)){
            int idComanda = conexion.getDato("idcomanda", "select idcomanda from comanda order by idcomanda desc limit 1");
            for (int i = 0; i < comanda.getLista().size(); i++) {
                Detalle detalle = comanda.getLista().get(i);
                detalle.setIdComanda(idComanda);
                if(comandaDao.seGuardoDetalle(detalle))
                    verificador = true;
            }
        }
        conexion.cerrarConexion();
        return verificador;
    }
    public boolean modificarComanda(Comanda comanda, ArrayList<Detalle> detallesEliminados){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        ComandaDao comandaDao = new ComandaDao(conexion);
        if(comandaDao.seModificoComanda(comanda)){
            for (int i = 0; i < comanda.getLista().size(); i++) {
                Detalle detalle = comanda.getLista().get(i);
                detalle.setIdComanda(comanda.getId());
                if(Asistente.existeAlgunDato("iddetalle", "select * from detalle where iddetalle = "+detalle.getId())){
                   comandaDao.seModificoDetalle(detalle); 
                }else{
                    comandaDao.seGuardoDetalle(detalle);
                }
            }
            for (int i = 0; i < detallesEliminados.size(); i++) {
                Detalle detalle = detallesEliminados.get(i);                
                if(Asistente.existeAlgunDato("iddetalle", "select * from detalle where iddetalle = "+detalle.getId())){
                   comandaDao.seEliminoDetalle(detalle); 
                }
            }
            verificador = true;
        }
        conexion.cerrarConexion();
        return verificador;
    }
    public boolean eliminarComanda(Comanda comanda){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        ComandaDao comandaDao = new ComandaDao(conexion);
        ProductoDao productoDao = new ProductoDao(conexion);
        int cantComanda = 0;
        int cantProducto = 0;
        if(comandaDao.seEliminoComanda(comanda)){
            for (int i = 0; i < comanda.getLista().size(); i++) {
                Detalle detalle = comanda.getLista().get(i);
                cantComanda = (int) detalle.getCantidad();
                Producto producto = productoDao.getProducto(detalle.getIdProducto());
                cantProducto = producto.getCantidad() + cantComanda;
                producto.setCantidad(cantProducto);
                if(productoDao.seModificoProducto(producto))
                    comandaDao.seEliminoDetalle(comanda.getLista().get(i));
            }
            verificador = true;
        }            
        conexion.cerrarConexion();
        return verificador;
    }
    public ArrayList<Comanda> getListaComandas(int idRegistroHospedaje){
        ArrayList<Comanda> lista = new ArrayList<>();
        Conexion conexion = new Conexion();
        ComandaDao comandaDao = new ComandaDao(conexion);
        lista = comandaDao.getListaComandas(idRegistroHospedaje);
        conexion.cerrarConexion();
        return lista;
    }    
}