package View;


import Utilities.Archivo;
import Controller.SopaDeLetras;
import Utilities.FileChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karen Velasco
 */
public class MainFrame extends JFrame{
    JLabel instrucciones;
    JButton seleccionar;
    
    public MainFrame() throws IOException, FileNotFoundException, ClassNotFoundException{
        
        super("Sopa de letras");
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(200, 200);
        
        instrucciones = new JLabel("Evaluador de sopa de letras");
        seleccionar = new JButton("Elegir archivo");
        
        seleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Archivo archivo = new Archivo();
        
                    String path = FileChooser.getFilePath();

                    String texto = archivo.leerArchivo(path);


                    SopaDeLetras sopaDeLetras = new SopaDeLetras(texto);
                    sopaDeLetras.Evaluar();
                    System.out.println("Tarea finalizada");
                            
                } catch (Exception e) {
                    System.out.println("Error de ejecucion.");
                }
                
                MainFrame.this.setVisible(false);
            }
        });
        
       
        super.add(instrucciones, BorderLayout.NORTH);
        super.add(seleccionar, BorderLayout.CENTER);
        super.setVisible(true);
    }
}
