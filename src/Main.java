
import Controller.SopaDeLetras;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karen Velasco
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Archivo archivo = new Archivo();
        String texto = archivo.leerArchivo("INPUT.TXT");
        
//        System.out.println("Texto de INPUT");
//        System.out.println(texto);
        
//        System.out.println("Fin del texto de INPUT \n A continuacion la funcion sopa de Letras \n");        
        SopaDeLetras sopaDeLetras = new SopaDeLetras(texto);
        sopaDeLetras.Evaluar();
            
    }
}
