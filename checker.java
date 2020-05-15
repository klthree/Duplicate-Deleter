/*
 * Traverses file tree, printing file and folder names. Starts at path specified as command line argument.
 * 
 * Usage: java checker <path>
 *
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.*;
import java.nio.file.FileVisitResult;

import java.io.IOException;

public class checker {
    public static void main (String[] args) {
        System.out.println("Hello");

        Path p1 = Paths.get(args[0]);

        System.out.println(p1);

        System.out.println("getName(0): " + p1.getName(0));

        try {
            Files.walkFileTree(p1, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    
                    System.out.println(file.toString());
                    return FileVisitResult.CONTINUE;
                    
                }
            });
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
