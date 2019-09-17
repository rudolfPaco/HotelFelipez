/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * conexion de la base de datos con el sistema.
 * @author rudolf
 */
public class Conexion {
    private Connection conexion;
    private final String base_datos = "bdhotelfelipez";
    private final String usuario = "root";
    private final String contrasena = "";
    private final String servidor = "jdbc:mysql://localhost/"+base_datos;
    
    private PreparedStatement consulta;
    private ResultSet resultado;
    
    public Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(servidor,usuario,contrasena);
            //System.out.println("la conexion fue un EXITO");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Imposible realizar conexion con la Base de Datos");
        }
    }   
    public void cerrarConexion(){        
        try {
            if(resultado != null)
                resultado.close();
            if(consulta != null)
                consulta.close();
            if(conexion != null)
                conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error...!"+ex.getMessage());
        }
    }
    public Connection getConexion(){
        return conexion;
    }
    public ArrayList<Integer> getlistaInt(String columna, String sql){
        ArrayList<Integer> lista = new ArrayList<>();
        try {            
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(rs.getInt(columna));
            }
        } catch (SQLException e) {
            System.out.println("error...! CompraDao.getLista(): "+e.getMessage());
        }
        return lista;
    }
    public ArrayList<String> getColumnaTabla(String columna, String sql){
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {                
                list.add(rs.getString(columna));
            }
        } catch (SQLException e) {            
            System.out.println("Error Conexion.getColumnaTabla(): " + e.getMessage());            
        }
        return list;
    }
    public ArrayList<String> getColumnaTablaFiltrada(String columna, String sql, String datoExcluyente){
        ArrayList<String> lista = getColumnaTabla(columna, sql);
        boolean encontrado = false;
        int contador = 0;
        while(contador < lista.size() && !encontrado){
            if(lista.get(contador).equalsIgnoreCase(datoExcluyente)){
                lista.remove(contador);
                encontrado = true;
            }
            contador++;
        }
        return lista;
    }
    public int getDato(String columna, String sql){
        int respuesta = 0;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();            
            
            while (rs.next()) {                
                if(rs.getInt(columna) > 0)
                    respuesta = rs.getInt(columna);
            }            

        } catch (SQLException e){
            System.out.println("Error Conexion.getDato(): " + e.getMessage());
        }
        return respuesta;
    }
    public double getDatoDouble(String columna, String sql){
        double respuesta = 0;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();            
            
            while (rs.next()) {                
                if(rs.getDouble(columna) > 0)
                    respuesta = rs.getDouble(columna);
            }            

        } catch (SQLException e){
            System.out.println("Error Conexion.getDato(): " + e.getMessage());
        }
        return respuesta;
    }
    public String getCadena(String columna, String sql){
        String respuesta = "";
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();            
            
            while (rs.next()) {                
                if(!rs.getString(columna).isEmpty())
                    respuesta = rs.getString(columna);
            }            

        } catch (SQLException e){
            System.out.println("Error Conexion.getCadena(): " + e.getMessage());
        }
        return respuesta;
    }
}
