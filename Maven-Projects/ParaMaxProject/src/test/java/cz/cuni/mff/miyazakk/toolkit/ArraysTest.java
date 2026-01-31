package cz.cuni.mff.miyazakk.toolkit;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
class ArraysTest {

    @Test
    void testStandardMax() {
        int[] input = {3, 4, 2, 5, 1};
        int expected = 5;
        assertEquals(expected, Arrays.max(input));
    }

    @Test
    void testNegativeNumbers() {
        int[] input = {-10, -500, -3, -99};
        int expected = -3;
        assertEquals(expected, Arrays.max(input));
    }

    @Test
    void testSingleElement() {
        int[] input = {42};
        assertEquals(42, Arrays.max(input));
    }

    @Test
    void testLargeRandomArray() {
        
        int size = 10000;
        int[] arr = new int[size];
        int actualMax = Integer.MIN_VALUE;
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt();
            if (arr[i] > actualMax) {
                actualMax = arr[i];
            }
        }
        int result = Arrays.max(arr);

        assertEquals(actualMax, result);
    }
}