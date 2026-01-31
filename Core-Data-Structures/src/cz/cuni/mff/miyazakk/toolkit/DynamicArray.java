package cz.cuni.mff.miyazakk.toolkit;

import java.util.Arrays;
import java.util.Random;

public class DynamicArray<T> implements SimpleCollection<T> {
    private Object[] array;
    private int size;

    private final Random random = new Random();

    public DynamicArray(){
        array = new Object[5];
        size = 0;

    }

    @SafeVarargs
    public static <T> DynamicArray<T> of(T... params){
        DynamicArray<T> newArray = new DynamicArray<>();
        for(T p : params){
            newArray.add(p);
        }
        return newArray;
    }

    public void sort() {
        if (size > 1) {
            quickSort(0, size - 1);
        }
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int partitionIdx = partition(low, high);
            quickSort(low, partitionIdx - 1);
            quickSort(partitionIdx + 1, high);
        }
    }
    @SuppressWarnings("unchecked")
    private int partition(int low, int high){
        int range = high - low;
        //Choose a random pivot to decrease the probability of worst-case behavior O(N**2)
        if (range > 0){
            int pivotIdx = random.nextInt(range) + low +1;
            swap(pivotIdx, high);
        }

        Object pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            Comparable<Object> compObj = (Comparable<Object>) array[j];
            if (compObj.compareTo(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        // Swap the pivot with the element at (i+1)
        swap(i + 1, high);
        return i + 1;
    }      

    @Override
    public void add(T o){
        if (array.length == size){
            // Double the size
            array = Arrays.copyOf(array, size*2);

        }
        array[size++] = o;

    };

    @Override
    @SuppressWarnings("unchecked")
    public T get(int i){
        return (T)array[i];
    };

    @Override
    public void remove(T o){
        for (int i = 0; i < size; ++i){
            if (array[i].equals(o)){
                remove(i);
                return;
            }
        }
    };

    @Override
    public void remove(int i){
        //Shift elements to the left
        for (int j = i; j < size-1; ++j){
            array[j] = array[j+1];
        }
        size--;
    };

    private void swap(int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public int size(){
        return size;
    }
}
