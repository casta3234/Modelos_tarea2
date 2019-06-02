/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_2_4;

/**
 *
 * @author USUARIO
 */
public class Material {
    int tipo;
    int peso;

    public Material(int tipo, int peso) {
        this.tipo = tipo;
        this.peso = peso;
    }

    public Material() {
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    
}
