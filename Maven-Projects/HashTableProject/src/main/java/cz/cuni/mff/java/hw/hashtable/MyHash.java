package cz.cuni.mff.java.hw.hashtable;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Hash Table using separate chaining.
 * The keys are Strings and values are Objects.
 */
public class MyHash implements Iterable<String> {

    /**
     * Internal node class representing a key-value pair in the bucket chain.
     */
    private static class Node {
        String key;
        Object value;
        Node next;

        Node(String key, Object value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node[] mybucketNodes;
    private int capacity;
    private int size;

    /**
     * Constructs a new hash table with a default initial capacity of 8.
     */
    public MyHash(){
        this.capacity = 8;
        this.mybucketNodes = new Node[capacity];
        this.size = 0;
    }

    /**
     * Gets the bucket index for a given key.
     * @param key the key to hash
     * @return the index in the buckets array
     */
    private int getIdx(String key){
        int a = key.hashCode();
        int idx = a % capacity;
        return idx < 0 ? idx + capacity : idx; 
    }

    /**
     * get value with the specific key.
     * @param key the key to look for 
     * @return value associated with the key, or null if NOT found
     */
    public Object get(String key){
        if (key == null) {
            return null;
        }
        int idx = getIdx(key);
        Node current = mybucketNodes[idx];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }


    /**
     * Associates the specified value with the specified key in this map.
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void set(String key, Object value) {
        if (key == null) {
            return;
        } 
        
        int idx = getIdx(key);
        Node current = mybucketNodes[idx];

        // Search for existing key to update
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        // Key not found, insert at head of the bucket
        mybucketNodes[idx] = new Node(key, value, mybucketNodes[idx]);
        size++;
    }

    /**
     * Returns an iterator over the keys in this hash table.
     * @return an Iterator of Strings
     */
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int currentBucket = 0;
            private Node currentNode = null;

            {
                // Initialize by finding the first node
                proceed();
            }
            /**
             * Moves the pointer to the next valid node
             * Goes over the linked list at the current bucket, or moves 
             * 
             * to the next bucket if the current list handles end.
             */
            private void proceed() {
                if (currentNode != null && currentNode.next != null) {
                    currentNode = currentNode.next;
                } else {
                    currentNode = null; // End of current chain
                    while (currentBucket < capacity) {
                        if (mybucketNodes[currentBucket] != null) {
                            currentNode = mybucketNodes[currentBucket];
                            currentBucket++;
                            break;
                        }
                        currentBucket++;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public String next() {
                if (currentNode == null) {
                    throw new NoSuchElementException();
                }
                String key = currentNode.key;
                proceed();
                return key;
            }
        };
    }

    public void forEachValue(ValueOperation operation) {
        if (operation == null) {
            return;
        }

        // Iterate through all buckets
        for (int i = 0; i < capacity; i++) {
            Node current = mybucketNodes[i];
            // Iterate through the linked list in this bucket
            while (current != null) {
                operation.apply(current.value);
                current = current.next;
            }
        }
    }

}
