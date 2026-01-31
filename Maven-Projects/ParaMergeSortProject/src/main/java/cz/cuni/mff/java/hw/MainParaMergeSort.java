package cz.cuni.mff.java.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.cuni.mff.miyazakk.toolkit.Arrays;

public class MainParaMergeSort {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            List<Integer> numbers = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null){
                try {
                    int num = Integer.parseInt(line.trim());
                    numbers.add(num);
                } catch (NumberFormatException e) {
                }
            }

            int[] arr = numbers.stream().mapToInt(a -> a).toArray();
            Arrays.paraMergeSort(arr);

            for (int num : arr){
                System.out.println(num);
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
