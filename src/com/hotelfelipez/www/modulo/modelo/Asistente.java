/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotelfelipez.www.modulo.modelo;

import com.aplicacionjava.www.recursos.Directorio;
import com.aplicacionjava.www.recursos.ImageTransform;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaM;
import com.hotelfelipez.www.modulo.dao.Conexion;
import java.awt.Toolkit;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sourceforge.jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Code128;

/**
 *
 * @author neo
 */
public class Asistente {
    
    public static int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public static boolean mensajeVerificacion(JFrame ventanaPrincipal, JDialog ventanaSecundaria, String tipo, String mensaje, String titulo){
        boolean verificador = false;
        ventanaPrincipal.setOpacity(0.5f);
        ventanaSecundaria.setOpacity(0.5f);
        IUVentanaM iuMensaje = new IUVentanaM(ventanaPrincipal, new Limitacion(ANCHO/2 - ANCHO/7, ALTO/2), tipo, mensaje, titulo, 10);
        iuMensaje.mostrarVentana();
        if(iuMensaje.getEstado())
            verificador = true;
        ventanaSecundaria.setOpacity(1f);
        ventanaPrincipal.setOpacity(1f);
        return verificador;
    }
    public static boolean mensajeVerificacion(JFrame ventanaPrincipal, String tipo, String mensaje, String titulo){
        boolean verificador = false;
        ventanaPrincipal.setOpacity(0.5f);
        IUVentanaM iuMensaje = new IUVentanaM(ventanaPrincipal, new Limitacion(ANCHO/2 - ANCHO/7, ALTO/2), tipo, mensaje, titulo, 10);
        iuMensaje.mostrarVentana();
        if(iuMensaje.getEstado())
            verificador = true;
        ventanaPrincipal.setOpacity(1f);
        return verificador;
    }
    public static String examinarArchivo(JFrame ventanaPrincipal, String direccionBuscar){
        
        String direccionArchivo = "";
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen","jpg","png");
        File archivo = new File(direccionBuscar);
        
        JFileChooser fileChooser = new JFileChooser(archivo);        
        fileChooser.setFileFilter(filter);       
        fileChooser.setVisible(true);
        
        int result = fileChooser.showOpenDialog(ventanaPrincipal);
        
        if ( result == JFileChooser.APPROVE_OPTION ){
            try {                 
                URL url = fileChooser.getSelectedFile().toURL();
                BufferedImage buffered = ImageIO.read(url);
                direccionArchivo = url.getPath();
            } 
            catch (IOException ex) {                
                System.err.println("Recurso.examinarArchivo(): "+ ex.getMessage() );
            } 
        }
        return direccionArchivo;
    }
    public static void getCodigoBarra(String codigo, String direccionCodigo, Limitacion limite){
        try {
            BufferedImage buffered = null;
            JBarcodeBean barcode = new JBarcodeBean();
            
            // nuestro tipo de codigo de barra
            //barcode.setCodeType(new Interleaved25());
            //barcode.setCodeType(new Code39());
            barcode.setCodeType(new Code128());
            
            // nuestro valor a codificar y algunas configuraciones mas
            barcode.setCode(codigo);            
            barcode.setCheckDigit(true);
            
            BufferedImage bufferedImage = barcode.draw(new BufferedImage(limite.getAncho(), limite.getAlto(), BufferedImage.TYPE_INT_RGB));
            
            // guardar en disco como png
            File file = new File(direccionCodigo);
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException ex) {
            System.out.println("Error Asistente.getCodigoBarra(): "+ex.getMessage());
        }
    }
    public static double acotarNumero(double numero, int cota){
        BigDecimal big = new BigDecimal(numero);
        return big.setScale(cota, RoundingMode.HALF_UP).doubleValue();
    }
    public static int getCantidadPaneles(int numeroElementos, int limiteElementosPanel){
        int cantidadPaneles = 0;
        if(numeroElementos%limiteElementosPanel == 0)
            cantidadPaneles = numeroElementos/limiteElementosPanel;
        else
            cantidadPaneles = numeroElementos/limiteElementosPanel + 1;
        return cantidadPaneles;
    }
    public static boolean existeAlgunDato(String columna, String sql){
        boolean verificador = false;
        Conexion conexion = new Conexion();
        if(conexion.getDato(columna, sql) > 0)
            verificador = true;
        conexion.cerrarConexion();
        return verificador;
    }
    public static String[] getColumnas(String columna, String sql){        
        Conexion conexion = new Conexion();
        ArrayList<String> columnas = conexion.getColumnaTabla(columna, sql);
        String[] datos = new String[columnas.size()+1];        
        int indice = 0;
        datos[indice] = "";
        for (int i = 0; i < columnas.size(); i++) {
            indice++;
            String dato = columnas.get(i);
            datos[indice] = dato;
            
        }
        conexion.cerrarConexion();
        return datos;
    }    
    public static int getPostId(String columna, String sql){
        int id = 0;
        Conexion conexion = new Conexion();
        id = conexion.getDato(columna, sql);
        id++;
        conexion.cerrarConexion();
        return id;
    }
    public static int getId(String columna, String sql){
        int id = 0;
        Conexion conexion = new Conexion();
        id = conexion.getDato(columna, sql);
        conexion.cerrarConexion();
        return id;
    }
    public static Directorio getDirectorio(){
        Directorio directorio = new Directorio("/src/imagenes/", "c:/imagenes/", "c:/imagenes/fotos/");
        directorio.crearDirectorioOrigen();
        
        
        return directorio;
    }
    
    /**
     *
     * @param cm convierte el valor de centimetros a pixeles
     * @return un valor en pixeles
     */
    public static double fromCMToPPI(double cm) {
        return toPPI(cm * 0.393700787);
    }
    private static double toPPI(double inch) {
        return inch * 72d;
    }
    public static BufferedImage getJPGImage(String ruta) throws IOException {
        BufferedImage imagen = ImageIO.read(new File(ruta));

        BufferedImage source = new BufferedImage(imagen.getWidth(),
                imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
        source.getGraphics().drawImage(imagen, 0, 0, null);
        return source;
    }
    public static BufferedImage rotacionImagen(BufferedImage origen, double grados) {
        BufferedImage destinationImage;
        ImageTransform imTransform = new ImageTransform(origen.getHeight(), origen.getWidth());
        imTransform.rotate(grados);
        imTransform.findTranslation();
        AffineTransformOp ato = new AffineTransformOp(imTransform.getTransform(), AffineTransformOp.TYPE_BILINEAR);
        destinationImage = ato.createCompatibleDestImage(origen, origen.getColorModel());
        return ato.filter(origen, destinationImage);
    }
}
