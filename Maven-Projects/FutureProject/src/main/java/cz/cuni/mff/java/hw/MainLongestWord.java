package cz.cuni.mff.java.hw;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import cz.cuni.mff.miyazakk.toolkit.FileUtils;

public class MainLongestWord {

    public static void main(String[] args) {
        if (args.length == 0){
            System.err.println("No path");
            return;
        }

        Path path = Paths.get(args[0]);

        Future<Optional<String>> future = FileUtils.longestWordInFile(path);


        try {
            Optional<String> res = future.get();

            if (res.isPresent()){
                System.out.println(res.get());
            } else{
                System.out.println("Nothing found");
            }
            
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Nothing found");
        }
    }
}
