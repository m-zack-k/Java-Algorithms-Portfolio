package cz.cuni.mff.miyazakk.toolkit;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class ArraysTest {

    @Test
    void testStandardSort() {
        int[] input = {3, 4, 2, 5, 1};
        int[] expected = {1, 2, 3, 4, 5};
        
        Arrays.paraMergeSort(input);
        
        assertArrayEquals(expected, input, "The array should be sorted correctly.");
    }

    @Test
    void testEmptySingleElement() {
        int[] empty = {};
        Arrays.paraMergeSort(empty);
        assertArrayEquals(new int[]{}, empty, "Empty array should remain empty.");

        int[] single = {42};
        Arrays.paraMergeSort(single);
        assertArrayEquals(new int[]{42}, single, "Single element array should remain unchanged.");
    }

    @Test
    void testLargeRandomArray() {
        int size = 10000; 
        int[] arr = new int[size];
        int[] javaSorted = new int[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            int val = rand.nextInt();
            arr[i] = val;
            javaSorted[i] = val;
        }

        Arrays.paraMergeSort(arr);
        java.util.Arrays.sort(javaSorted);

        assertArrayEquals(javaSorted, arr, "Large random array should be sorted correctly.");
    }
}