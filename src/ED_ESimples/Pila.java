/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ED_ESimples;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 * @author sebastian
 */
public class Pila {
    private Nodo cabeza = null;
    
    public boolean vacia (){
        return this.cabeza == null;
    }
    
    public void agregar(Object o){
        Nodo nuevoNodo = new Nodo(o);
        nuevoNodo.setSiguiente(this.cabeza);
        this.cabeza = nuevoNodo;
    }
    
    public void eliminar(){
        Nodo temp = this.cabeza;
        this.cabeza = this.cabeza.getSiguiente();
        temp = null;
        System.gc();
    }
    
    public Nodo mirar(){
        return this.cabeza;
    }
    
    public Nodo sacar(){
        Nodo temp = this.cabeza;       
        this.cabeza = this.cabeza.getSiguiente();       
        System.gc();
        return temp;       
    }
    
    public void imprimir(){
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Nodo temp = this.cabeza;
        try{
            while(temp != null){
                bw.write(temp.toString());
                temp = temp.getSiguiente();
            }
            bw.flush();
        }
        catch(IOException ex){
        }
    }
    
}
