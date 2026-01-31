package cz.cuni.mff.miyazakk.programs;

import cz.cuni.mff.miyazakk.toolkit.MString;

public class Main {

    public static void main(String[] args) {
        // A single try-catch block to handle all possible errors
        try {
            MString ms = new MString();
            // Initialize index for iterating through arguments.
            int i = 0;
            int n = args.length;

            // Loop through the arguments as long as 'i' is within bounds
            while (i < n){
                String parameter = args[i];

                if (parameter.equals( "a")){
                    ms.append(args[i+1]);
                    // Increment index by 2 to move to the next command
                    i += 2;
                } else if (parameter.equals("i")){
                    int pos = parse(args[i + 1]);
                    // Insert the following argument (args[i+2]) at that position.
                    ms.insert(pos, args[i + 2]);
                    // Increment index by 3 to move to the next command
                    i += 3;
                } else if (parameter.equals("d")) {
                    int pos = parse(args[i + 1]);
                    int length = parse(args[i + 2]);
                    // Delete the specified number of characters.
                    ms.delete(pos, length);
                    // Increment index by 3 to move to the next command
                    i += 3;
                } else {
                    throw new IllegalArgumentException("Unknown parameter");
                }
            }
            // Print the final string
            System.out.println(ms.toString());
            
        } catch (Exception e) {
            // If any exception occurs, print "INPUT ERROR"
            System.out.println("INPUT ERROR");
        }
    
    }
    
    //Helper method to parse a string into an integer.
    private static int parse(String s){
        return Integer.parseInt(s);
    }

}
