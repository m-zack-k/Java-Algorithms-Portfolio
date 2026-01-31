package cz.cuni.mff.miyazakk.toolkit;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileUtilsTest {
    @Test
    void testLongestWord() throws Exception{
        Path temp = Files.createTempFile("test", ".txt");
        Files.writeString(temp, "rohlik postel nemocnice les");

        Future<Optional<String>> future = FileUtils.longestWordInFile(temp);

        Optional<String> res = future.get();

        Assertions.assertTrue(res.isPresent());
        Assertions.assertEquals("nemocnice", res.get());

        Files.deleteIfExists(temp);
    }

    @Test
    void testEmptyFile() throws Exception{
        Path temp = Files.createTempFile("empty", ".txt");
        
        Future<Optional<String>> future = FileUtils.longestWordInFile(temp);

        Optional<String> res = future.get();

        Assertions.assertFalse(res.isPresent());
        
        Files.deleteIfExists(temp);
    }   
}
