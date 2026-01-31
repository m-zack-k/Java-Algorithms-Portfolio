package cz.cuni.mff.miyazakk.args;

public class Args{
    public static void main(String[] args){
        int n = args.length;
        for (int i = 0; i < n; ++i){
            System.out.println(args[i]);
        }
        // System.out.println("url: ");
        // http://google.com/
        // System.out.println(":url");
    }
}