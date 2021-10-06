/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualtravels;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import static virtualtravels.DocReporteController.fechaActual;

/**
 *
 * @author Joseph Cruz
 */
public class Conexion {

    public Connection getConnection() {
        Connection conn;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtual_travels?useTimezone=true&serverTimezone=UTC", "root", "berlin64");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    public Connection configConection(String BD, String claveConetion) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + BD
                    + "?useTimezone=true&serverTimezone=UTC", "root", claveConetion);
            JOptionPane.showMessageDialog(null, "Conexion Establecida");
            //Crear documento de lectura 
            File carpeta = new File("C:\\Users\\omar\\AppData\\Local\\HelixAspersa");
            if (carpeta.exists()) {
                System.out.println("* si existe directorio *");
            } else {
                System.out.println("- - creando carperta - -");
                carpeta.mkdir();
                //crear block de notas
            }
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al establecer conexion");
            System.out.println("error al establecer conexion -> " + e.getMessage());
            return null;
        }
    }
}
