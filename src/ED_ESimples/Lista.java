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
 * @author SC
 */
public class Lista {
    
    private Nodo cabeza = null;
    
    public boolean vacia(){
        return this.cabeza == null; 
        
    }
    
    public void añadirInicio(Object o){
        Nodo nuevoNodo = new Nodo(o);
        nuevoNodo.setSiguiente(this.cabeza);
        this.cabeza = nuevoNodo;
    }
    
    public void añadirFinal(Object o){
        Nodo nuevoNodo = new Nodo(o);
        if (vacia()){
            this.cabeza = nuevoNodo;                     
        }
        else{
            Nodo temp = this.cabeza;
            while(temp.getSiguiente() != null){
                temp = temp.getSiguiente();         
            }
            temp.setSiguiente(nuevoNodo);
        }
    }
    
    public void añadirPosicion(Object o, int posicion){
        Nodo nuevoNodo = new Nodo(o);
        Nodo temp = this.cabeza;
        for(int i = 0; i < posicion-1; i++ ){
            temp = temp.getSiguiente();
        }
        nuevoNodo.setSiguiente(temp.getSiguiente()); 
        temp.setSiguiente(nuevoNodo);           
    }
       
    public void eliminarInicio(){
        Nodo temp = this.cabeza;
        this.cabeza = this.cabeza.getSiguiente();
        temp = null;
        System.gc();
    }
    
    public void eliminarFinal(){
        Nodo pre = this.cabeza;
        Nodo temp = this.cabeza.getSiguiente().getSiguiente();
        while (temp != null){            
            pre = pre.getSiguiente();
            temp = temp.getSiguiente();
        }        
        pre.setSiguiente(null);
        System.gc();
    }
    
    public void eliminarPosicion(int posicion){
        Nodo temp = this.cabeza;
        Nodo eliminado;
        for(int i = 0; i < posicion-1; i++){
            temp = temp.getSiguiente();
        }
        eliminado = temp.getSiguiente();
        temp.setSiguiente(temp.getSiguiente().getSiguiente()); 
        eliminado = null;
        System.gc();
    }
    
    public void inversa(){
        Pila ptemp = new Pila();
        Nodo temp = this.cabeza;
        
        while(temp != null){
            ptemp.agregar(temp.clon());
            temp = temp.getSiguiente();
        }
        
        this.cabeza = ptemp.mirar();
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
