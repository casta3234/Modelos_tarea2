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
public class Queue {
    private Node head = null;
    
    public boolean empty (){
        return this.head == null;
    }
    public void enqueue (Object o){
        Node newNodo = new Node(o);
        if (empty()){
            this.head = newNodo;
        }
        else {
            Node temp = this.head;
            while(temp.get_next() != null){
                temp = temp.get_next();
            }
            temp.set_next(newNodo);
        }
    }
    
    public Node peek(){
        return this.head;
    }
    
    public Node dequeue(){
        Node temp = this.head;       
        this.head = this.head.get_next();       
        System.gc();    
        return temp;       
    }
    
    public void delete(){
        Node temp = this.head;
        this.head = this.head.get_next();
        temp = null;
        System.gc();
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
