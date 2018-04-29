/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utilities.ListaPalabras;
import Utilities.Palabra;
import static Utilities.Status.ERROR;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Karen Velasco
 */
public class SopaDeLetras {
    char[][] grid;
    //File INPUT;
    Integer n;
    Integer m;
    Integer p;
    
    ListaPalabras listaPalabras;
    ListaPalabras renglones;
 
    
    public SopaDeLetras(String texto) throws FileNotFoundException, IOException{
        //identificar los elementos
        listaPalabras = new ListaPalabras();
        renglones = new ListaPalabras();
        char[] cadena = texto.toCharArray(); //ya son caracteres
        
        int valor = 0;
        int i = 0;
        
        String numero="";
        //encontrar n
        //EN CADA SALTO DE LINEA, HAY UN 13 Y UN 10 QUE SIGNIFICAN retorno de carro y salto de linea respectivaemnte
        
        //1. CALCULAR n
        char c;
        while(valor!=32){//si el valor no es espacio
            valor = (int) cadena[i];
      //      System.out.println("Valor = "+valor);
            
            if(valor!=32){
                c = (char) valor;
                numero = numero.concat(String.valueOf(c));
                n = Integer.valueOf(numero);
            }
            
            i++;
        }
        //2. Calcular m
        numero = "";
        do{//si el valor no es espacio
            valor = (int) cadena[i];
          //  System.out.println("Valor = "+valor);
            if(valor!=32){
                c = (char) valor;
                numero = numero.concat(String.valueOf(c));
                m = Integer.valueOf(numero);
            }
            i++;
        }while(valor!=32);
        
        //3. Calcular p
        numero = "";
        while(valor!=13){//si el valor no es retorno
            valor = (int) cadena[i];
      //      System.out.println("Valor = "+valor);
            if(valor!=13){
                c = (char) valor;
                numero = numero.concat(String.valueOf(c));
                p = Integer.valueOf(numero);
                numero = "";
            }
            i++;
        }
        valor = (int) cadena[i];
        
//3. Leer las palabras, son palabras
        
        for(int contW=0; contW<p; contW++){//contW
            
            numero = "";
            
            while(valor != 13){
                
                valor = (int) cadena[i];
                
                if(valor != 13 && valor!=10){
                    c = (char) valor;
                    numero = numero.concat(String.valueOf(c)); //cadena de texto
                }
                i++;//estaba en 13, mas este, ahora esta en 10
            }
            
            i++;
            valor = (int) cadena[i];
            listaPalabras.append(new Palabra(numero.toCharArray()));
        }
        
//agregar los renglones
        
        String[] renglon = new String[m];
        int ka = 0;
        
        valor = (int) cadena[i];
        
        for(int contW=0; contW<m; contW++){//contW
            
            numero = "";
            
            while(valor != 13 && i<cadena.length){
                    
                valor = (int) cadena[i];
                
//                System.out.println("    i = "+i);
//                System.out.println("    valor = "+valor);//entero, codigo ascii
                
                if(valor != 13 && valor!=10){
                    c = (char) valor;
                    numero = numero.concat(String.valueOf(c)); //cadena de texto
                }
                i++;//estaba en 13, mas este, ahora esta en 10
            }
            renglones.append(new Palabra(numero.toCharArray()));
            i++;
            if(i<cadena.length) valor = (int) cadena[i];
        }
//Inicializar el tablero        
        buildGrid();
    }

    public void buildGrid(){
        grid = new char[n][m];
        
        Palabra current = renglones.getHead();
        for(int i=0; i<m ;i++){//m es el numero de filas
            //dentro de este renglon
            for(int j=0; j<n ; j++){
                grid[j][i] = current.getChar(j);
            }
            
            current = current.getNext();
        }
    }
    
    //generar un archivo, con la cantidad de errores
    public void Evaluar() throws IOException, IOException{
        //el path es hasta 
         
        String currentDir = System.getProperty("user.dir");
         
        File file = new File(currentDir+"/OUTPUT.txt");
        
        try ( //crear un archivo
                FileWriter writer = new FileWriter(file) //pide un file, input output error
        ) {
            int counter = 0;
        
            Palabra current = listaPalabras.getHead();

            while(current!=null){

                current.IdentificarOrigenes(n, m, grid);
                current.Resolver(n, m, grid);

                if(current.getStatus() == ERROR)
                    counter++;

                current = current.getNext();
            }

            writer.append(Integer.toString(counter));
            writer.append("\r\n");

            current = listaPalabras.getHead();

            while(current!=null){

                writer.append(current.toString());
                writer.append("\r\n");
                current = current.getNext();
            }
        }
        
    }
    
}
