/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ED_ESimples;


/**
 *
 * @author Sebastian
 */
public class Node {

    private Object o;
    private Node next = null;

    public Node() {
        
    }

    public Node(Object o) {
        this.o = o;
    }
    
    @Override
    public Node clone (){
        Node temp = new Node(this);
        return temp;
    }
    
    public Node get_next() {
        return next;
    }

    public Object get_object() {
        return o;
    }
    
    public void set_next(Node siguiente) {
        this.next = siguiente;
    }

    @Override
    public String toString() {
        return o.toString() + "\n";
    }
    
}
