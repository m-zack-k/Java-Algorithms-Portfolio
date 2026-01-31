package cz.cuni.mff.java.hw.wordcount;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {
        // continue here
        // Argument Validation
        if (args.length == 0){
            System.err.println("Provide a directory path");
            return;
        }

        File dir = new File(args[0]);
        if (!dir.exists() || !dir.isDirectory()){
            System.err.println("Directory path is invalid");
            return;
        }
        //filter only files ending with .txt
        File[] files = dir.listFiles((directory, name) -> name.toLowerCase().endsWith(".txt"));

        //// If no files are found or an IO error occurred, print 0.
        if (files == null || files.length == 0){
            System.out.println("0");
            return;
        }

        AtomicLong totalWords = new AtomicLong(0);
        List<Thread> threads = new ArrayList<>();

        // Create thread
        for (File file : files){
            WordCounter counter = new WordCounter(file, totalWords);

            Thread thread = new Thread(counter);
            threads.add(thread);
            thread.start();
        }
        //Wait for all threads
        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Main thread interrupted");
            }
        }
        //output the result after all threads joined
        System.out.println(totalWords.get());
            
    }

}

