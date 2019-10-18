/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.modelo.Comanda;
import com.hotelfelipez.www.modulo.modelo.Detalle;
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
    public ArrayList<Comanda> getListaComandas(int idRegistroHospedaje){
        ArrayList<Comanda> lista = new ArrayList<>();
        Conexion conexion = new Conexion();
        ComandaDao comandaDao = new ComandaDao(conexion);
        lista = comandaDao.getListaComandas(idRegistroHospedaje);
        conexion.cerrarConexion();
        return lista;
    }
}
