package cz.cuni.mff.miyazakk.toolkit;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 References: 
 Iterable: https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
 */

public class MString implements Iterable<Character>{
    
    private char[] data;
    private int size;

    public MString(){
        this.data = new char[16];
        this.size = 0;
    }

    public MString(String str){
        if (str == null){
            this.data = new char[16];
            this.size = 0;
        } else{
            this.size = str.length();
            this.data = new char[Math.max(16, size)];
            for (int i = 0; i < size; ++i){
                this.data[i] = str.charAt(i);
            }
        }
    }

    private void secureCapacity(int minCapacity){
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2, minCapacity);
            char[] newData = new char[newCapacity];
            
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            this.data = newData;
        }
    }
    //Appends a string
    public void append(String str){
        if (str == null) return;
        int len = str.length();
        secureCapacity(size + len);
        
        // Copy string chars to the end of our array
        for (int i = 0; i < len; i++) {
            data[size + i] = str.charAt(i);
        }
        size += len;
    }

    //Appends a character 
    public void append(char ch){
        secureCapacity(size + 1);
        data[size] = ch;
        size++;
    }

    //Inserts a string into this MString at a specified position.
    public void insert(int pos, String str){
        if (str == null) return;
        // Basic bounds check
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Position " + pos + " is invalid.");
        }
        
        int len = str.length();
        if (len == 0) return;

        secureCapacity(size + len);

        for (int i = size - 1; i >= pos; i--) {
            data[i + len] = data[i];
        }

        for (int i = 0; i < len; i++) {
            data[pos + i] = str.charAt(i);
        }
        size += len;
    }
    
    //Inserts a character into this MString at a specified position.
    public void insert(int pos, char ch){
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        
        secureCapacity(size + 1);

        for (int i = size; i > pos; i--) {
            data[i] = data[i - 1];
        }

        data[pos] = ch;
        size++;
    }

    //Deletes a sequence of characters from this MString
    public void delete(int pos, int length){
        if (pos < 0 || pos >= size) {
             throw new IndexOutOfBoundsException();
        }
        
        if (pos + length > size) {
            length = size - pos;
        }
        if (length <= 0) return;

        int elementsToMove = size - (pos + length);
        for (int i = 0; i < elementsToMove; i++) {
            data[pos + i] = data[pos + length + i];
        }

        size -= length;
    }

    //Returns the String representation of this MString.
    @Override
    public String toString(){
        return new String(data, 0, size);
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Character next() {
                if (!hasNext()) throw new NoSuchElementException();
                return data[index++];
            }
        };
    }

}


