/*
 * Traverses file tree, deleting duplicate files while leaving one behind
 * Usage: java checker <path>
 *
 */
package dupdelete;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.*;
import java.nio.file.FileVisitResult;
import java.util.ArrayList;

import java.io.IOException;

public class Deleter {
    public static void main (String[] args) {
        Path p1 = Paths.get(args[0]);

        traverse(p1);
    }

    /* 
     * traverse(Path p) creates SimpleFileVisitor and walks file tree
     */ 

    private static void traverse(Path p) {
        ArrayList<Path> paths = new ArrayList<>();

        try {
            Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
                // Upon visiting each file, adds that file to ArrayList
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    paths.add(file);                    

                    return FileVisitResult.CONTINUE;
                }

                /* After traversing entire directory, iterates through ArrayList paths,
                 * checks for duplicates, and deletes if found
                 * */
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    
                    FileCheck fc;

                    for (int i = 0; i < paths.size() - 1; i++) {
                        for (int j = i + 1; j < paths.size(); j++) {
                            fc = new FileCheck(paths.get(i));

                            if (fc.fileChecker(paths.get(j))) {
                                System.out.println(paths.get(i).getFileName() + " " + paths.get(j).getFileName());
                                System.out.println(fc.fileChecker(paths.get(j)));

                                System.out.println("Deleting duplicate file...");
                                makeWritable(paths.get(i), paths.get(j));
                                Files.delete(paths.get(j));
                                paths.remove(j--);
                            }

                        }
                    }

                    paths.clear();

                    return FileVisitResult.CONTINUE;
                
                }
                
                /*
                 * Checks each directory prior to traversing, and if that directory name
                 * begins with '.', skips it and its subtrees.
                 * */
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (dir.getFileName().toString().charAt(0) == '.') {
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                    return FileVisitResult.CONTINUE;
                }

            });
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }


    /*
     * Takes two paths, and if the files they point to are read only,
     * makes them writable
     *
     * */
    private static void makeWritable(Path p1, Path p2) {

        if (!Files.isWritable(p1)) {
            File f1 = new File(p1.toString());
            f1.setWritable(true, true);
        }
        if (!Files.isWritable(p2)) {
            File f2 = new File(p2.toString());
            f2.setWritable(true, true);
        }
    }
}
