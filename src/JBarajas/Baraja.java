/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JBarajas;

import ED_ESimples.Lista;

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
    
    public void revolver(){
        
    }
    
    public static void main(String[] args) {
        Baraja b = new Baraja("corazones", "diamantes", "picas", "treboles");
        b.cartas.imprimir();
    }
}