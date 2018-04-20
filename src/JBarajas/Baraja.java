/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JBarajas;

import ED_ESimples.Lista;
import ED_ESimples.Nodo;

/**
 *
 * @author USUARIO
 */
public class Baraja {
    private Lista cartas = new Lista();

    public Baraja(String pinta1,String pinta2,String pinta3,String pinta4 ) {
        for(int i = 1;i <= 13;i++){
            this.cartas.añadirInicio(new Carta(pinta1, i));
        }
        for(int i = 1;i <= 13;i++){
            this.cartas.añadirInicio(new Carta(pinta2, i));
        }
        for(int i = 1;i <= 13;i++){
            this.cartas.añadirInicio(new Carta(pinta3, i));
        }
        for(int i = 1;i <= 13;i++){
            this.cartas.añadirInicio(new Carta(pinta4, i));
        }
    }
    public Baraja(){
        this.cartas.añadirInicio(5);
        this.cartas.añadirInicio(4);
        this.cartas.añadirInicio(3);
        this.cartas.añadirInicio(2);
        this.cartas.añadirInicio(1);
        this.cartas.añadirInicio(0);
    }
    public void revolver2(){
        Lista templ = new Lista();
        Nodo temp = this.cartas.getNodo(this.cartas.getSize()/2 + 1);        
        while (temp != null) {            
            templ.añadirInicio(temp.clon());
            temp = temp.getSiguiente();
        }
        this.cartas.getNodo(this.cartas.getSize()/2).setSiguiente(null);
        templ.getNodo(this.cartas.getSize()/2).setSiguiente(this.cartas.getNodo(0));
        this.cartas.setCabeza(templ.getNodo(0));
    }
/*la funcion revolver 4 no esta acabada la idea partir la baraja en 4 
    y intercalar esas 4 partes (despues hacemos otras funciones de revolver4 
    que sean las diferentes permutaciones de revolver esas 4 partes)
*/     
    public void revolver4(){
        Lista segundo_cuarto = new Lista();
        Lista tercer_cuarto = new Lista();
        Lista cuarto_cuarto = new Lista();
        Nodo dos = this.cartas.getNodo(12);
        Nodo tres = this.cartas.getNodo(25);
        Nodo cuatro = this.cartas.getNodo(38);
    }
    
    public void intercalar(){
        Lista pares = new Lista();
        Lista impares = new Lista();        
        for (int i = 0; i <= this.cartas.getSize(); i++) {
            if (i%2 == 0){
                pares.añadirInicio(this.cartas.getNodo(i).clon());
            }
            else{
                impares.añadirInicio(this.cartas.getNodo(i).clon());
            }
        }
        this.cartas.setCabeza(null);
        for (int i = 0; i < 26; i++) {
            this.cartas.añadirInicio(pares.getNodo(i).clon());
            this.cartas.añadirInicio(impares.getNodo(25-i).clon());
        }        
    }
    public void intercalar2(){
        Lista pares = new Lista();
        Lista impares = new Lista();        
        for (int i = 0; i <= this.cartas.getSize(); i++) {
            if (i%2 == 0){
                pares.añadirInicio(this.cartas.getNodo(i).clon());
            }
            else{
                impares.añadirInicio(this.cartas.getNodo(i).clon());
            }
        }
        this.cartas.setCabeza(null);
        for (int i = 0; i < 26; i++) {
            this.cartas.añadirFinal(pares.getNodo(25-i).clon());
            this.cartas.añadirInicio(impares.getNodo(i).clon());
        }        
    }
    
    public void moverNDerecha(int n){
        Lista templ = new Lista();
        Nodo tempn = this.cartas.getNodo(0);
        for (int i = 0; i < this.cartas.getSize() - n+1; i++) {
            tempn = tempn.getSiguiente();
        }
        while (tempn != null) {            
            templ.añadirInicio(tempn.clon());
            tempn = tempn.getSiguiente();
        }
        this.cartas.getNodo(this.cartas.getSize() - (n+1)).setSiguiente(null);
        templ.getNodo(templ.getSize()).setSiguiente(this.cartas.getNodo(0));
        this.cartas.setCabeza(templ.getNodo(0));
    }
    
    public void moverNzquierda(int n){
        Lista templ = new Lista();
        Nodo tempn = this.cartas.getNodo(0);
        for (int i = 0; i < n ; i++) {
            tempn = tempn.getSiguiente();            
        }
        while (tempn != null) {            
            templ.añadirInicio(tempn.clon());
            tempn = tempn.getSiguiente();
        }
        this.cartas.getNodo(this.cartas.getSize() - (n)).setSiguiente(null);
        templ.getNodo(templ.getSize()).setSiguiente(this.cartas.getNodo(0));
        this.cartas.setCabeza(templ.getNodo(0));
    }
    
    public static void main(String[] args) {
        Baraja b = new Baraja("corazones", "diamantes", "picas", "treboles");
        b.cartas.imprimir();
        System.out.println("******************************************");        
        b.intercalar();
        b.intercalar2();
        b.revolver2();
        b.intercalar2();
        b.revolver2();
        b.intercalar();
        b.intercalar2();
//        b.moverNDerecha(0);
//        b.moverNDerecha(18);       
        b.intercalar2();
//        b.moverNzquierda(1);
        b.cartas.imprimir();
        System.out.println(b.cartas.getSize());
        
    }
}