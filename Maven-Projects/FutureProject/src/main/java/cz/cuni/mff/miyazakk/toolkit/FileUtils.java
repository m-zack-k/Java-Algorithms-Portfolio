package cz.cuni.mff.miyazakk.toolkit;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class FileUtils {
    public static Future<Optional<String>> longestWordInFile(Path path){
        return CompletableFuture.supplyAsync(() -> {
            String longestWord = null;
            try (Scanner scanner = new Scanner(path)){
                while (scanner.hasNext()){
                    String currWord = scanner.next();
                    if (longestWord == null || currWord.length() > longestWord.length()){
                        longestWord = currWord;
                    }
                }
            } catch (IOException e){
                throw new RuntimeException(e);
            }
            return Optional.ofNullable(longestWord);
        });
    }
}
