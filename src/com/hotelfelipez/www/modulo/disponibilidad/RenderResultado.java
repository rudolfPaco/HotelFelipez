/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.disponibilidad;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author rudolf
 */
public class RenderResultado extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccion, boolean foco, int fila, int columna){
        switch(columna){
            case 0:
                setHorizontalAlignment(SwingConstants.LEFT);
            break;
            case 2:
                setHorizontalAlignment(SwingConstants.LEFT);
            break;
            case 4:
                setHorizontalAlignment(SwingConstants.RIGHT);
            break;
            default:
                setHorizontalAlignment(SwingConstants.CENTER);
            break;
        }
        
        if(((boolean)tabla.getModel().getValueAt(fila, 6))){
            setBackground(Color.ORANGE);
            tabla.setFont(new Font("Verdana", Font.BOLD, 15));            
            tabla.setForeground(new Color(120, 0, 0));
        }else{
            setBackground(null);
            tabla.setForeground(new Color(2, 67, 109));
            tabla.setFont(new Font("Verdana", Font.PLAIN, 14));
        }      
        
        super.getTableCellRendererComponent(tabla, valor, seleccion, foco, fila, columna);
        return this;
    }
}
