package cz.cuni.mff.miyazakk.programs;


import cz.cuni.mff.miyazakk.toolkit.BinaryTree;

public class MainProgram {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        for (String str : args){
            try {
                int num = Integer.parseInt(str);
                tree.add(num);
            } catch(NumberFormatException e){
                System.out.println("INPUT ERROR");
                return;
            }
        }

        for(Integer val : tree){
            System.out.println(val);
        }

    }
    
}
