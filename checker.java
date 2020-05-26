/*
 * Goal - Traverse a directory recursively and delete duplicate files that are next to each other
 *
 * Currently - Traverses file tree, printing file and folder names. Starts at path specified as command line argument.
 * 
 * Usage: java checker <path>
 *
 */
package duplicatedelete;

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

        Path[] filesInDirectory = [];

        try {
            Files.walkFileTree(p1, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (!Files.isDirectory(file)) {
                        System.out.println("Filename: " + file.getFileName());
                        System.out.println("File size: " + Files.size(file) / 1000.0);
                    }                    
                    
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    
}
if file is a directory
print file array
clear file array
start 
