/*
 * Goal - Traverse a directory recursively and delete duplicate files that are next to each other
 *
 * Currently - Traverses file tree, printing file and folder names. Starts at path specified as command line argument.
 * 
 * Usage: java checker <path>
 *
 */
package duplicatedeleter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.*;
import java.nio.file.FileVisitResult;
import java.util.ArrayList;

import java.io.IOException;

public class checker {
    public static void main (String[] args) {
        Path p1 = Paths.get(args[0]);

        System.out.println("getName(0): " + p1.getName(0));

        traverse(p1);
    }

    private static void traverse(Path p) {
        ArrayList<Path> paths = new ArrayList<>();

        try {
            Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(file.getFileName().toString().charAt(0) != '.') {
                        paths.add(file);                    
                    }

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    
                    FileCheck fc;

                    for(int i = 0; i < paths.size() - 1; i++) {
                        for(int j = i + 1; j < paths.size(); j++) {
                            fc = new FileCheck(paths.get(i));

                            if(fc.fileChecker(paths.get(j))) {
                                System.out.println(paths.get(i).getFileName() + " " + paths.get(j).getFileName());
                                System.out.println(fc.fileChecker(paths.get(j)));
                            }

                        }
                    }

                    paths.clear();

                    return FileVisitResult.CONTINUE;
                
                }

//                @Override
//                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
//                    paths.clear();
//                    if(dir.getFileName().toString().charAt(0) == '.') {
//                        System.out.println("Skipping " + dir + "...");
//                        return FileVisitResult.SKIP_SUBTREE;
//                    }
//                    return FileVisitResult.CONTINUE;
//                }
//
            });
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    
}
