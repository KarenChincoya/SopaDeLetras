/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author Karen Velasco
 */
public class ListaOrigenes {
    Origen head;
    Origen tail;
    
    public void append(Origen origen){
        if(head==null){
            this.head = origen;
            this.tail = origen;
        }else{
            tail.setNext(origen);
            tail = origen;
        }
    }

    public Origen getHead() {
        return head;
    }
    
    public void printList(){
        Origen current = head;
        while(current!=null){
            System.out.println(current.toString());
            current = current.getNext();
        }
    }
    
}
