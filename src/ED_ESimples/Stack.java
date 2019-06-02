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
 * @author Sebastian
 */
public class Stack {
    private Node head = null;
    
    public boolean empty (){
        return this.head == null;
    }
    
    public void push(Object o){
        Node newNodo = new Node(o);
        newNodo.set_next(this.head);
        this.head = newNodo;
    }
    
    public void delete(){
        Node temp = this.head;
        this.head = this.head.get_next();
        temp = null;
        System.gc();
    }
    
    public Node peek(){
        return this.head;
    }
    
    public Object pop(){
        Node temp = this.head;       
        this.head = this.head.get_next();       
        System.gc();
        return temp.get_object();       
    }
    
    public void print(){
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Node temp = this.head;
        try{
            while(temp != null){
                bw.write(temp.toString());
                temp = temp.get_next();
            }
            bw.flush();
        }
        catch(IOException ex){
        }
    }    
}
