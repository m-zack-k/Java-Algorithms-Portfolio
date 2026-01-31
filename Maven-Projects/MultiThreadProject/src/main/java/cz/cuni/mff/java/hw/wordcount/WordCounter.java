package cz.cuni.mff.java.hw.wordcount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

//Implements Runnable to be executed by a Thread.
public class WordCounter implements Runnable {
    private final File file;
    private final AtomicLong counter;

    /**
     * @param file    The text file to process.
     * @param counter The shared thread-safe counter to update.
     */
    public WordCounter(File file, AtomicLong counter){
        this.file = file;
        this.counter = counter;
    }

    @Override
    public void run(){
        // Use a local variable to count words for this specific file
        long localCounter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null){
                // Split by one or more whitespace characters
                String[] words = line.trim().split("\\s");

                for (String word: words){
                    if (!word.isEmpty()){
                        localCounter++;
                    }
                }
            }
        } catch (IOException e){
            System.err.println("File Error");
        }
        //add our local count to the global count.
        counter.addAndGet(localCounter);
    }
}
