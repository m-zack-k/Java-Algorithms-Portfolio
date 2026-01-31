package cz.cuni.mff.miyazakk.toolkit;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/*
 References: 
 Iterable: https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html

 */

public class BinaryTree implements Iterable<Integer>{

    private static class Node{
        int val;
        Node left, right;
        Node(int val){
            this.val = val;
        }
    }
    private Node top = null;

    public void add(int val){
        top = addNode(top, val);
    }

    private Node addNode(Node node, int val){
        if (node == null) {
            return new Node(val);
        }

        if (val < node.val){
            node.left = addNode(node.left, val);
        } else{
            node.right = addNode(node.right, val);
        }
        return node;


    }

    @Override
    public Iterator<Integer> iterator(){
        return new InOrderIterator(top);
    }

    private static class InOrderIterator implements Iterator<Integer>{
        private final Stack<Node> stack = new Stack<>();

        InOrderIterator(Node top) {
            pushAllLeft(top);
        }

        // This pushes all its subsequent left chidren to stack
        private void pushAllLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            if (stack.isEmpty()) throw new NoSuchElementException();
            Node node = stack.pop();
            
            //if there is a subtree on the right, the next is the smallest in that tree.
            if (node.right != null) {
                pushAllLeft(node.right);
            }
            return node.val;
        }
    }

    
}
