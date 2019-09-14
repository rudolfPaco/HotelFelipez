/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.principal;

import java.awt.Toolkit;

/**
 *
 * @author neo
 */
public class Principal {
    public static void main(String[] arg){
        int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = Toolkit.getDefaultToolkit().getScreenSize().height;                
        
        IUVentanaHotel iuHote = new IUVentanaHotel("HOTEL FELIPEZ");
        iuHote.mostrarVentana();
    }
}
