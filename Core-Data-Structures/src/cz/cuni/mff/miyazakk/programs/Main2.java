package cz.cuni.mff.miyazakk.programs;

import cz.cuni.mff.miyazakk.toolkit.DynamicArray;

public class Main2{
    public static void main(String[] args) {
        DynamicArray<String> array = new DynamicArray<>();

        for(String str : args){
            array.add(str);
        }
        array.sort();

        for (int i = 0; i < array.size() ; ++i){
            System.out.println(array.get(i));
        }
    }
}