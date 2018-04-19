/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ED_ESimples;

/**
 *
 * @author SC
 */
public class Nodo {
    
    private Object o;
    private Nodo siguiente = null;

    public Nodo() {
        
    }

    public Nodo(Object o) {
        this.o = o;
    }
    
    public Nodo clon (){
        Nodo temp = new Nodo(this);
        return temp;
    }
    
    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return o.toString();
    }
    
}
