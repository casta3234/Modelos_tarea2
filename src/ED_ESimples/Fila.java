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
public class Fila {
    private Nodo cabeza = null;
    
    public boolean vacia (){
        return this.cabeza == null;
    }
    public void añadir (Object o){
        Nodo nuevoNodo = new Nodo(o);
        if (vacia()){
            this.cabeza = nuevoNodo;
        }
        else {
            Nodo temp = this.cabeza;
            while(temp.getSiguiente() != null){
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
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
    
    public void eliminar(){
        Nodo temp = this.cabeza;
        this.cabeza = this.cabeza.getSiguiente();
        temp = null;
        System.gc();
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
