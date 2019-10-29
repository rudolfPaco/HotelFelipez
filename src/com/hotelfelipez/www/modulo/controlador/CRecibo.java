/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.dao.Conexion;
import com.hotelfelipez.www.modulo.dao.ReciboDao;
import com.hotelfelipez.www.modulo.modelo.Recibo;

/**
 *
 * @author rudolf
 */
public class CRecibo {

    public CRecibo(){
        
    }
    public boolean guardarNuevoRecibo(Recibo recibo){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        ReciboDao reciboDao = new ReciboDao(conexion);
        if(reciboDao.seGuardoRecibo(recibo))
            verificador = true;
        conexion.cerrarConexion();
        return verificador;
    }
    public boolean guardoRecibo_RegistroHospedaje(int idRecibo, int idRegistroHospedaje){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        ReciboDao reciboDao = new ReciboDao(conexion);
        if(reciboDao.seGuardoRecibo_RegistroHospedaje(idRecibo, idRegistroHospedaje))
            verificador = true;
        conexion.cerrarConexion();
        return verificador;
    }
}
