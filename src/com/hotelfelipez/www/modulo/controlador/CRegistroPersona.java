/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.controlador;

import com.hotelfelipez.www.modulo.registroHospedaje.IUVentanaRegistroNuevaPersona;

/**
 *
 * @author rudolf
 */
public class CRegistroPersona {
    private IUVentanaRegistroNuevaPersona iuRegistroPersona;

    public CRegistroPersona() {
    }
    
    public void controlarRegistroPersona(IUVentanaRegistroNuevaPersona iuRegistroPersona){
        this.iuRegistroPersona = iuRegistroPersona;
    }
}
